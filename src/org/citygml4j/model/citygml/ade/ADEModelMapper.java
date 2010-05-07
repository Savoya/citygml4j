package org.citygml4j.model.citygml.ade;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public interface ADEModelMapper {
	public ADEComponent toADEComponent(Object jaxbObject, QName name);
	public JAXBElement<?> toJAXB(ADEComponent adeComponent);
}