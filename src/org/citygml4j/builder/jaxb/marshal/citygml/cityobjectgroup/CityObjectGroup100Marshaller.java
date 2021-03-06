/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2012
 * Institute for Geodesy and Geoinformation Science
 * Technische Universität Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 */
package org.citygml4j.builder.jaxb.marshal.citygml.cityobjectgroup;

import javax.xml.bind.JAXBElement;

import net.opengis.citygml._1.AbstractCityObjectType;
import net.opengis.citygml.cityobjectgroup._1.CityObjectGroupMemberType;
import net.opengis.citygml.cityobjectgroup._1.CityObjectGroupParentType;
import net.opengis.citygml.cityobjectgroup._1.CityObjectGroupType;
import net.opengis.citygml.cityobjectgroup._1.ObjectFactory;

import org.citygml4j.builder.jaxb.marshal.JAXBMarshaller;
import org.citygml4j.builder.jaxb.marshal.citygml.CityGMLMarshaller;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.cityobjectgroup.CityObjectGroup;
import org.citygml4j.model.citygml.cityobjectgroup.CityObjectGroupMember;
import org.citygml4j.model.citygml.cityobjectgroup.CityObjectGroupModuleComponent;
import org.citygml4j.model.citygml.cityobjectgroup.CityObjectGroupParent;
import org.citygml4j.model.common.base.ModelObject;
import org.citygml4j.model.gml.basicTypes.Code;
import org.w3._1999.xlink.ActuateType;
import org.w3._1999.xlink.ShowType;
import org.w3._1999.xlink.TypeType;

public class CityObjectGroup100Marshaller {
	private final ObjectFactory grp = new ObjectFactory();
	private final JAXBMarshaller jaxb;
	private final CityGMLMarshaller citygml;
	
	public CityObjectGroup100Marshaller(CityGMLMarshaller citygml) {
		this.citygml = citygml;
		jaxb = citygml.getJAXBMarshaller();
	}

	public JAXBElement<?> marshalJAXBElement(Object src) {
		JAXBElement<?> dest = null;
		
		if (src instanceof CityObjectGroupModuleComponent)
			src = marshal((CityObjectGroupModuleComponent)src);
		
		if (src instanceof CityObjectGroupType)
			dest = grp.createCityObjectGroup((CityObjectGroupType)src);
		
		return dest;
	}
	
	public Object marshal(ModelObject src) {
		Object dest = null;
		
		if (src instanceof CityObjectGroup)
			dest = marshalCityObjectGroup((CityObjectGroup)src);
		else if (src instanceof CityObjectGroupMember)
			dest = marshalCityObjectGroupMember((CityObjectGroupMember)src);
		else if (src instanceof CityObjectGroupParent)
			dest = marshalCityObjectGroupParent((CityObjectGroupParent)src);
		
		return dest;
	}
	
	public void marshalCityObjectGroup(CityObjectGroup src, CityObjectGroupType dest) {
		citygml.getCore100Marshaller().marshalAbstractCityObject(src, dest);

		if (src.isSetClazz())
			dest.setClazz(src.getClazz().getValue());

		if (src.isSetFunction()) {
			for (Code function : src.getFunction())
				dest.getFunction().add(function.getValue());
		}

		if (src.isSetUsage()) {
			for (Code usage : src.getUsage())
				dest.getUsage().add(usage.getValue());
		}

		if (src.isSetGeometry())
			dest.setGeometry(jaxb.getGMLMarshaller().marshalGeometryProperty(src.getGeometry()));

		if (src.isSetGroupMember()) {
			for (CityObjectGroupMember cityObjectGroupMember : src.getGroupMember())
				dest.getGroupMember().add(marshalCityObjectGroupMember(cityObjectGroupMember));
		}

		if (src.isSetGroupParent())
			dest.setParent(marshalCityObjectGroupParent(src.getGroupParent()));	
		
		if (src.isSetGenericApplicationPropertyOfCityObjectGroup()) {
			for (ADEComponent adeComponent :src.getGenericApplicationPropertyOfCityObjectGroup())
				if (adeComponent.isSetContent())
					dest.get_GenericApplicationPropertyOfCityObjectGroup().add(citygml.ade2jaxbElement(adeComponent));
		}
	}

	public CityObjectGroupType marshalCityObjectGroup(CityObjectGroup src) {
		CityObjectGroupType dest = grp.createCityObjectGroupType();
		marshalCityObjectGroup(src, dest);

		return dest;
	}
		
	@SuppressWarnings("unchecked")
	public CityObjectGroupMemberType marshalCityObjectGroupMember(CityObjectGroupMember src) {
		CityObjectGroupMemberType dest = grp.createCityObjectGroupMemberType();

		if (src.isSetCityObject()) {
			JAXBElement<?> elem = jaxb.marshalJAXBElement(src.getCityObject());
			if (elem != null && elem.getValue() instanceof AbstractCityObjectType)
				dest.set_CityObject((JAXBElement<? extends AbstractCityObjectType>)elem);
		}
		
		if (src.isSetGenericADEComponent() && src.getGenericADEComponent().isSetContent())
			dest.set_ADEComponent(src.getGenericADEComponent().getContent());
		
		if (src.isSetGroupRole())
			dest.setGroupRole(src.getGroupRole());
		
		if (src.isSetRemoteSchema())
			dest.setRemoteSchema(src.getRemoteSchema());

		if (src.isSetType())
			dest.setType(TypeType.fromValue(src.getType().getValue()));

		if (src.isSetHref())
			dest.setHref(src.getHref());

		if (src.isSetRole())
			dest.setRole(src.getRole());

		if (src.isSetArcrole())
			dest.setArcrole(src.getArcrole());

		if (src.isSetTitle())
			dest.setTitle(src.getTitle());

		if (src.isSetShow())
			dest.setShow(ShowType.fromValue(src.getShow().getValue()));

		if (src.isSetActuate())
			dest.setActuate(ActuateType.fromValue(src.getActuate().getValue()));

		return dest;
	}
	
	@SuppressWarnings("unchecked")
	public CityObjectGroupParentType marshalCityObjectGroupParent(CityObjectGroupParent src) {
		CityObjectGroupParentType dest = grp.createCityObjectGroupParentType();

		if (src.isSetCityObject()) {
			JAXBElement<?> elem = jaxb.marshalJAXBElement(src.getCityObject());
			if (elem != null && elem.getValue() instanceof AbstractCityObjectType)
				dest.set_CityObject((JAXBElement<? extends AbstractCityObjectType>)elem);
		}
		
		if (src.isSetGenericADEComponent() && src.getGenericADEComponent().isSetContent())
			dest.set_ADEComponent(src.getGenericADEComponent().getContent());

		if (src.isSetRemoteSchema())
			dest.setRemoteSchema(src.getRemoteSchema());

		if (src.isSetType())
			dest.setType(TypeType.fromValue(src.getType().getValue()));

		if (src.isSetHref())
			dest.setHref(src.getHref());

		if (src.isSetRole())
			dest.setRole(src.getRole());

		if (src.isSetArcrole())
			dest.setArcrole(src.getArcrole());

		if (src.isSetTitle())
			dest.setTitle(src.getTitle());

		if (src.isSetShow())
			dest.setShow(ShowType.fromValue(src.getShow().getValue()));

		if (src.isSetActuate())
			dest.setActuate(ActuateType.fromValue(src.getActuate().getValue()));

		return dest;
	}
	
}
