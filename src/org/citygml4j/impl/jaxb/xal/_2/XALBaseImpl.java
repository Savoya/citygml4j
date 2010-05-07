package org.citygml4j.impl.jaxb.xal._2;

import org.citygml4j.model.xal.GrPostal;
import org.citygml4j.model.xal.XALBase;
import org.citygml4j.model.xal.XALClass;

public abstract class XALBaseImpl implements XALBase {

	public abstract Object getJAXBObject();
	
	public abstract XALClass getXALClass();
	
	public boolean hasGrPostalGroup() {
		return this instanceof GrPostal;
	}

}