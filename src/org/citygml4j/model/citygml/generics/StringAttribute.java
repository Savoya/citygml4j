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
package org.citygml4j.model.citygml.generics;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.common.base.ModelType;
import org.citygml4j.model.module.citygml.GenericsModule;

public class StringAttribute extends AbstractGenericAttribute {
	private String value;
	
	public StringAttribute() {

	}

	public StringAttribute(GenericsModule module) {
		super(module);
	}
	
	public String getValue() {
		return value;
	}

	public boolean isSetValue() {
		return value != null;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void unsetValue() {
		value = null;
	}

	public ModelType getModelType() {
		return ModelType.CITYGML;
	}

	public CityGMLClass getCityGMLClass() {
		return CityGMLClass.STRING_ATTRIBUTE;
	}
	
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new StringAttribute(), copyBuilder);
	}

	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		StringAttribute copy = (target == null) ? new StringAttribute() : (StringAttribute)target;
		super.copyTo(copy, copyBuilder);
		
		if (isSetValue())
			copy.setValue(copyBuilder.copy(value));
		
		return copy;
	}

}
