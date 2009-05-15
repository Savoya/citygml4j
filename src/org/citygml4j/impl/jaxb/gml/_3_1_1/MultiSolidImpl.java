package org.citygml4j.impl.jaxb.gml._3_1_1;

import java.util.ArrayList;
import java.util.List;

import org.citygml4j.geometry.Point;
import org.citygml4j.jaxb.gml._3_1_1.MultiSolidType;
import org.citygml4j.jaxb.gml._3_1_1.SolidPropertyType;
import org.citygml4j.model.gml.AbstractSolid;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.MultiSolid;
import org.citygml4j.model.gml.SolidArrayProperty;
import org.citygml4j.model.gml.SolidProperty;

public class MultiSolidImpl extends AbstractGeometricAggregateImpl implements MultiSolid {
	private MultiSolidType multiSolidType;

	public MultiSolidImpl() {
		this(new MultiSolidType());
	}

	public MultiSolidImpl(MultiSolidType multiSolidType) {
		super(multiSolidType);
		this.multiSolidType = multiSolidType;
	}

	@Override
	public MultiSolidType getJAXBObject() {
		return multiSolidType;
	}

	@Override
	public GMLClass getGMLClass() {
		return GMLClass.MULTISOLID;
	}

	public List<SolidProperty> getSolidMember() {
		List<SolidProperty> solidPropertyList = new ArrayList<SolidProperty>();

		for (SolidPropertyType solidPropertyType : multiSolidType.getSolidMember())
			solidPropertyList.add(new SolidPropertyImpl(solidPropertyType));

		return solidPropertyList;
	}

	public SolidArrayProperty getSolidMembers() {
		if (multiSolidType.isSetSolidMembers())
			return new SolidArrayPropertyImpl(multiSolidType.getSolidMembers());

		return null;
	}

	public void calcBoundingBox(Point min, Point max) {
		if (isSetSolidMember()) {
			for (SolidProperty solidProperty : getSolidMember()) {
				AbstractSolid abstractSolid = solidProperty.getSolid();
				if (abstractSolid != null)
					abstractSolid.calcBoundingBox(min, max);
			}
		}

		if (isSetSolidMembers()) {
			SolidArrayProperty solidArrayProperty = getSolidMembers();

			if (solidArrayProperty.isSetSolid()) {
				for (AbstractSolid abstractSolid : solidArrayProperty.getSolid()) {
					abstractSolid.calcBoundingBox(min, max);
				}
			}
		}
	}

	public void addSolidMember(SolidProperty solidMember) {
		multiSolidType.getSolidMember().add(((SolidPropertyImpl)solidMember).getJAXBObject());
	}

	public void setSolidMember(List<SolidProperty> solidMember) {
		List<SolidPropertyType> solidPropertyList = new ArrayList<SolidPropertyType>();

		for (SolidProperty solidProperty : solidMember)
			solidPropertyList.add(((SolidPropertyImpl)solidProperty).getJAXBObject());

		multiSolidType.unsetSolidMember();
		multiSolidType.getSolidMember().addAll(solidPropertyList);
	}

	public void setSolidMembers(SolidArrayProperty solidMembers) {
		multiSolidType.setSolidMembers(((SolidArrayPropertyImpl)solidMembers).getJAXBObject());
	}

	public boolean isSetSolidMember() {
		return multiSolidType.isSetSolidMember();
	}

	public boolean isSetSolidMembers() {
		return multiSolidType.isSetSolidMembers();
	}

	public void unsetSolidMember() {
		multiSolidType.unsetSolidMember();
	}

	public void unsetSolidMembers() {
		multiSolidType.setSolidMembers(null);
	}

	public boolean unsetSolidMember(SolidProperty solidMember) {
		if (multiSolidType.isSetSolidMember())
			return multiSolidType.getSolidMember().remove(((SolidPropertyImpl)solidMember).getJAXBObject());
		
		return false;
	}

}
