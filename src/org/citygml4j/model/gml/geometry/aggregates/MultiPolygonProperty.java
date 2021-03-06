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
package org.citygml4j.model.gml.geometry.aggregates;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.geometry.GeometryProperty;

public class MultiPolygonProperty extends GeometryProperty<MultiPolygon> {
	
	public MultiPolygonProperty() {

	}

	public MultiPolygonProperty(MultiPolygon multiPolygon) {
		super(multiPolygon);
	}

	public MultiPolygonProperty(String href) {
		super(href);
	}
	
	public MultiPolygon getMultiPolygon() {
		return super.getGeometry();
	}

	public boolean isSetMultiPolygon() {
		return super.isSetGeometry();
	}

	public void setMultiPolygon(MultiPolygon multiPolygon) {
		super.setGeometry(multiPolygon);
	}

	public void unsetMultiPolygon() {
		super.unsetGeometry();
	}

	public GMLClass getGMLClass() {
		return GMLClass.MULTI_POLYGON_PROPERTY;
	}

	@Override
	public Class<MultiPolygon> getAssociableClass() {
		return MultiPolygon.class;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new MultiPolygonProperty(), copyBuilder);
	}

	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		MultiPolygonProperty copy = (target == null) ? new MultiPolygonProperty() : (MultiPolygonProperty)target;
		return super.copyTo(copy, copyBuilder);
	}

}
