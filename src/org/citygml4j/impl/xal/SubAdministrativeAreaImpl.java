package org.citygml4j.impl.xal;

import java.util.List;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.commons.child.ChildList;
import org.citygml4j.model.xal.AddressLine;
import org.citygml4j.model.xal.Locality;
import org.citygml4j.model.xal.PostOffice;
import org.citygml4j.model.xal.PostalCode;
import org.citygml4j.model.xal.SubAdministrativeArea;
import org.citygml4j.model.xal.SubAdministrativeAreaName;
import org.citygml4j.model.xal.XALClass;
import org.citygml4j.visitor.XALFunction;
import org.citygml4j.visitor.XALVisitor;

public class SubAdministrativeAreaImpl implements SubAdministrativeArea {
	private List<AddressLine> addressLine;
	private List<SubAdministrativeAreaName> subAdministrativeAreaName;
	private Locality locality;
	private PostOffice postOffice;
	private PostalCode postalCode;
	private String type;
	private String usageType;
	private String indicator;
	private Object parent;	
	
	public void addAddressLine(AddressLine addressLine) {
		if (this.addressLine == null)
			this.addressLine = new ChildList<AddressLine>(this);

		this.addressLine.add(addressLine);
	}

	public void addSubAdministrativeAreaName(SubAdministrativeAreaName subAdministrativeAreaName) {
		if (this.subAdministrativeAreaName == null)
			this.subAdministrativeAreaName = new ChildList<SubAdministrativeAreaName>(this);

		this.subAdministrativeAreaName.add(subAdministrativeAreaName);
	}

	public List<AddressLine> getAddressLine() {
		if (addressLine == null)
			addressLine = new ChildList<AddressLine>(this);

		return addressLine;
	}

	public String getIndicator() {
		return indicator;
	}

	public Locality getLocality() {
		return locality;
	}

	public PostOffice getPostOffice() {
		return postOffice;
	}

	public PostalCode getPostalCode() {
		return postalCode;
	}

	public List<SubAdministrativeAreaName> getSubAdministrativeAreaName() {
		if (subAdministrativeAreaName == null)
			subAdministrativeAreaName = new ChildList<SubAdministrativeAreaName>(this);

		return subAdministrativeAreaName;
	}

	public String getType() {
		return type;
	}

	public String getUsageType() {
		return usageType;
	}

	public boolean isSetAddressLine() {
		return addressLine != null && !addressLine.isEmpty();
	}

	public boolean isSetIndicator() {
		return indicator != null;
	}

	public boolean isSetLocality() {
		return locality != null;
	}

	public boolean isSetPostOffice() {
		return postOffice != null;
	}

	public boolean isSetPostalCode() {
		return postalCode != null;
	}

	public boolean isSetSubAdministrativeAreaName() {
		return subAdministrativeAreaName != null && !subAdministrativeAreaName.isEmpty();
	}

	public boolean isSetType() {
		return type != null;
	}

	public boolean isSetUsageType() {
		return usageType != null;
	}

	public void setAddressLine(List<AddressLine> addressLine) {
		this.addressLine = new ChildList<AddressLine>(this, addressLine);
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public void setLocality(Locality locality) {
		if (locality != null)
			locality.setParent(this);

		this.locality = locality;
	}

	public void setPostOffice(PostOffice postOffice) {
		if (postOffice != null)
			postOffice.setParent(this);

		this.postOffice = postOffice;
	}

	public void setPostalCode(PostalCode postalCode) {
		if (postalCode != null)
			postalCode.setParent(this);

		this.postalCode = postalCode;
	}

	public void setSubAdministrativeAreaName(List<SubAdministrativeAreaName> subAdministrativeAreaName) {
		this.subAdministrativeAreaName = new ChildList<SubAdministrativeAreaName>(this, subAdministrativeAreaName);
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public void unsetAddressLine() {
		if (isSetAddressLine())
			addressLine.clear();

		addressLine = null;
	}

	public boolean unsetAddressLine(AddressLine addressLine) {
		return isSetAddressLine() ? this.addressLine.remove(addressLine) : false;
	}

	public void unsetIndicator() {
		indicator = null;
	}

	public void unsetLocality() {
		if (isSetLocality())
			locality.unsetParent();

		locality = null;
	}

	public void unsetPostOffice() {
		if (isSetPostOffice())
			postOffice.unsetParent();

		postOffice = null;
	}

	public void unsetPostalCode() {
		if (isSetPostalCode())
			postalCode.unsetParent();

		postalCode = null;
	}

	public void unsetSubAdministrativeAreaName() {
		if (isSetSubAdministrativeAreaName())
			subAdministrativeAreaName.clear();

		subAdministrativeAreaName = null;
	}

	public boolean unsetSubAdministrativeAreaName(SubAdministrativeAreaName subAdministrativeAreaName) {
		return isSetSubAdministrativeAreaName() ? this.subAdministrativeAreaName.remove(subAdministrativeAreaName) : false;
	}

	public void unsetType() {
		type = null;
	}

	public void unsetUsageType() {
		usageType = null;
	}

	public XALClass getXALClass() {
		return XALClass.SUB_ADMINISTRATIVE_AREA;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public boolean isSetParent() {
		return parent != null;
	}

	public void unsetParent() {
		parent = null;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new SubAdministrativeAreaImpl(), copyBuilder);
	}

	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		SubAdministrativeArea copy = (target == null) ? new SubAdministrativeAreaImpl() : (SubAdministrativeArea)target;
		
		if (isSetType())
			copy.setType(copyBuilder.copy(type));

		if (isSetUsageType())
			copy.setUsageType(copyBuilder.copy(usageType));
		
		if (isSetIndicator())
			copy.setIndicator(copyBuilder.copy(indicator));
		
		if (isSetAddressLine()) {
			for (AddressLine part : addressLine) {
				AddressLine copyPart = (AddressLine)copyBuilder.copy(part);
				copy.addAddressLine(copyPart);

				if (part != null && copyPart == part)
					part.setParent(this);
			}
		}
		
		if (isSetSubAdministrativeAreaName()) {
			for (SubAdministrativeAreaName part : subAdministrativeAreaName) {
				SubAdministrativeAreaName copyPart = (SubAdministrativeAreaName)copyBuilder.copy(part);
				copy.addSubAdministrativeAreaName(copyPart);

				if (part != null && copyPart == part)
					part.setParent(this);
			}
		}
		
		if (isSetLocality()) {
			copy.setLocality((Locality)copyBuilder.copy(locality));
			if (copy.getLocality() == locality)
				locality.setParent(this);
		}
		
		if (isSetPostOffice()) {
			copy.setPostOffice((PostOffice)copyBuilder.copy(postOffice));
			if (copy.getPostOffice() == postOffice)
				postOffice.setParent(this);
		}
		
		if (isSetPostalCode()) {
			copy.setPostalCode((PostalCode)copyBuilder.copy(postalCode));
			if (copy.getPostalCode() == postalCode)
				postalCode.setParent(this);
		}
		
		copy.unsetParent();

		return copy;
	}
	
	public void visit(XALVisitor visitor) {
		visitor.accept(this);
	}
	
	public <T> T visit(XALFunction<T> visitor) {
		return visitor.accept(this);
	}

}