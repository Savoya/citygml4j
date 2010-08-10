package org.citygml4j.builder.jaxb.unmarshal.citygml.waterbody;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.citygml4j.builder.jaxb.unmarshal.JAXBUnmarshaller;
import org.citygml4j.builder.jaxb.unmarshal.citygml.CityGMLUnmarshaller;
import org.citygml4j.impl.citygml.waterbody.BoundedByWaterSurfacePropertyImpl;
import org.citygml4j.impl.citygml.waterbody.WaterBodyImpl;
import org.citygml4j.impl.citygml.waterbody.WaterClosureSurfaceImpl;
import org.citygml4j.impl.citygml.waterbody.WaterGroundSurfaceImpl;
import org.citygml4j.impl.citygml.waterbody.WaterSurfaceImpl;
import org.citygml4j.jaxb.citygml._0_4.BoundedByWaterSurfacePropertyType;
import org.citygml4j.jaxb.citygml._0_4.WaterBodyType;
import org.citygml4j.jaxb.citygml._0_4.WaterClosureSurfaceType;
import org.citygml4j.jaxb.citygml._0_4.WaterGroundSurfaceType;
import org.citygml4j.jaxb.citygml._0_4.WaterSurfaceType;
import org.citygml4j.jaxb.citygml._0_4._WaterBoundarySurfaceType;
import org.citygml4j.jaxb.citygml._0_4._WaterObjectType;
import org.citygml4j.model.citygml.CityGML;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.waterbody.BoundedByWaterSurfaceProperty;
import org.citygml4j.model.citygml.waterbody.WaterBody;
import org.citygml4j.model.citygml.waterbody.AbstractWaterBoundarySurface;
import org.citygml4j.model.citygml.waterbody.WaterClosureSurface;
import org.citygml4j.model.citygml.waterbody.WaterGroundSurface;
import org.citygml4j.model.citygml.waterbody.AbstractWaterObject;
import org.citygml4j.model.citygml.waterbody.WaterSurface;
import org.citygml4j.model.module.citygml.WaterBodyModule;
import org.citygml4j.xml.io.reader.MissingADESchemaException;

public class WaterBody040Unmarshaller {
	private final WaterBodyModule module = WaterBodyModule.v0_4_0;
	private final JAXBUnmarshaller jaxb;
	private final CityGMLUnmarshaller citygml;

	public WaterBody040Unmarshaller(CityGMLUnmarshaller citygml) {
		this.citygml = citygml;
		jaxb = citygml.getJAXBUnmarshaller();
	}

	public CityGML unmarshal(JAXBElement<?> src) throws MissingADESchemaException {
		return unmarshal(src.getValue());
	}

	public CityGML unmarshal(Object src) throws MissingADESchemaException {
		if (src instanceof JAXBElement<?>)
			return unmarshal((JAXBElement<?>)src);

		CityGML dest = null;

		if (src instanceof BoundedByWaterSurfacePropertyType)
			dest = unmarshalBoundedByWaterSurfaceProperty((BoundedByWaterSurfacePropertyType)src);
		else if (src instanceof WaterBodyType)
			dest = unmarshalWaterBody((WaterBodyType)src);
		else if (src instanceof WaterClosureSurfaceType)
			dest = unmarshalWaterClosureSurface((WaterClosureSurfaceType)src);
		else if (src instanceof WaterGroundSurfaceType)
			dest = unmarshalWaterGroundSurface((WaterGroundSurfaceType)src);
		else if (src instanceof WaterSurfaceType)
			dest = unmarshalWaterSurface((WaterSurfaceType)src);

		return dest;
	}

	public void unmarshalWaterObject(_WaterObjectType src, AbstractWaterObject dest) throws MissingADESchemaException {
		citygml.getCore040Unmarshaller().unmarshalCityObject(src, dest);
	}

	public void unmarshalWaterBoundarySurface(_WaterBoundarySurfaceType src, AbstractWaterBoundarySurface dest) throws MissingADESchemaException {
		citygml.getCore040Unmarshaller().unmarshalCityObject(src, dest);

		if (src.isSetLod2Surface())
			dest.setLod2Surface(jaxb.getGMLUnmarshaller().unmarshalSurfaceProperty(src.getLod2Surface()));

		if (src.isSetLod3Surface())
			dest.setLod3Surface(jaxb.getGMLUnmarshaller().unmarshalSurfaceProperty(src.getLod3Surface()));

		if (src.isSetLod4Surface())
			dest.setLod4Surface(jaxb.getGMLUnmarshaller().unmarshalSurfaceProperty(src.getLod4Surface()));
	}

	public BoundedByWaterSurfaceProperty unmarshalBoundedByWaterSurfaceProperty(BoundedByWaterSurfacePropertyType src) throws MissingADESchemaException {
		BoundedByWaterSurfaceProperty dest = new BoundedByWaterSurfacePropertyImpl(module);
		jaxb.getGMLUnmarshaller().unmarshalFeatureProperty(src, dest);

		if (src.isSet_Object()) {
			Object object = jaxb.unmarshal(src.get_Object());
			if (object instanceof AbstractWaterBoundarySurface)
				dest.setObject((AbstractWaterBoundarySurface)object);
		}

		return dest;
	}

	public void unmarshalWaterBody(WaterBodyType src, WaterBody dest) throws MissingADESchemaException {
		unmarshalWaterObject(src, dest);

		if (src.isSetClazz())
			dest.setClazz(src.getClazz());

		if (src.isSetFunction())
			dest.setFunction(src.getFunction());

		if (src.isSetUsage())
			dest.setUsage(src.getUsage());

		if (src.isSetLod0MultiSurface())
			dest.setLod0MultiSurface(jaxb.getGMLUnmarshaller().unmarshalMultiSurfaceProperty(src.getLod0MultiSurface()));

		if (src.isSetLod1MultiSurface())
			dest.setLod1MultiSurface(jaxb.getGMLUnmarshaller().unmarshalMultiSurfaceProperty(src.getLod1MultiSurface()));

		if (src.isSetLod0MultiCurve())
			dest.setLod0MultiCurve(jaxb.getGMLUnmarshaller().unmarshalMultiCurveProperty(src.getLod0MultiCurve()));

		if (src.isSetLod1MultiCurve())
			dest.setLod1MultiCurve(jaxb.getGMLUnmarshaller().unmarshalMultiCurveProperty(src.getLod1MultiCurve()));

		if (src.isSetLod1Solid())
			dest.setLod1Solid(jaxb.getGMLUnmarshaller().unmarshalSolidProperty(src.getLod1Solid()));

		if (src.isSetLod2Solid())
			dest.setLod2Solid(jaxb.getGMLUnmarshaller().unmarshalSolidProperty(src.getLod2Solid()));

		if (src.isSetLod3Solid())
			dest.setLod3Solid(jaxb.getGMLUnmarshaller().unmarshalSolidProperty(src.getLod3Solid()));

		if (src.isSetLod4Solid())
			dest.setLod4Solid(jaxb.getGMLUnmarshaller().unmarshalSolidProperty(src.getLod4Solid()));

		if (src.isSetBoundedBySurface()) {
			for (BoundedByWaterSurfacePropertyType boundedByWaterSurfaceProperty : src.getBoundedBySurface())
				dest.addBoundedBySurface(unmarshalBoundedByWaterSurfaceProperty(boundedByWaterSurfaceProperty));
		}
	}

	public WaterBody unmarshalWaterBody(WaterBodyType src) throws MissingADESchemaException {
		WaterBody dest = new WaterBodyImpl(module);
		unmarshalWaterBody(src, dest);

		return dest;
	}

	public void unmarshalWaterClosureSurface(WaterClosureSurfaceType src, WaterClosureSurface dest) throws MissingADESchemaException {
		unmarshalWaterBoundarySurface(src, dest);
	}

	public WaterClosureSurface unmarshalWaterClosureSurface(WaterClosureSurfaceType src) throws MissingADESchemaException {
		WaterClosureSurface dest = new WaterClosureSurfaceImpl(module);
		unmarshalWaterClosureSurface(src, dest);

		return dest;
	}

	public void unmarshalWaterGroundSurface(WaterGroundSurfaceType src, WaterGroundSurface dest) throws MissingADESchemaException {
		unmarshalWaterBoundarySurface(src, dest);
	}

	public WaterGroundSurface unmarshalWaterGroundSurface(WaterGroundSurfaceType src) throws MissingADESchemaException {
		WaterGroundSurface dest = new WaterGroundSurfaceImpl(module);
		unmarshalWaterGroundSurface(src, dest);

		return dest;
	}

	public void unmarshalWaterSurface(WaterSurfaceType src, WaterSurface dest) throws MissingADESchemaException {
		unmarshalWaterBoundarySurface(src, dest);

		if (src.isSetWaterLevel())
			dest.setWaterLevel(src.getWaterLevel());
	}

	public WaterSurface unmarshalWaterSurface(WaterSurfaceType src) throws MissingADESchemaException {
		WaterSurface dest = new WaterSurfaceImpl(module);
		unmarshalWaterSurface(src, dest);

		return dest;
	}
	
	public boolean assignGenericProperty(ADEComponent genericProperty, QName substitutionGroup, CityGML dest) {
		String name = substitutionGroup.getLocalPart();
		boolean success = true;
		
		if (dest instanceof AbstractWaterObject && name.equals("_GenericApplicationPropertyOfWaterObject"))
			((AbstractWaterObject)dest).addGenericApplicationPropertyOfWaterObject(genericProperty);		
		else if (dest instanceof AbstractWaterBoundarySurface && name.equals("_GenericApplicationPropertyOfWaterBoundarySurface"))
			((AbstractWaterBoundarySurface)dest).addGenericApplicationPropertyOfWaterBoundarySurface(genericProperty);		
		else if (dest instanceof WaterBody && name.equals("_GenericApplicationPropertyOfWaterBody"))
			((WaterBody)dest).addGenericApplicationPropertyOfWaterBody(genericProperty);		
		else if (dest instanceof WaterClosureSurface && name.equals("_GenericApplicationPropertyOfWaterClosureSurface"))
			((WaterClosureSurface)dest).addGenericApplicationPropertyOfWaterClosureSurface(genericProperty);		
		else if (dest instanceof WaterGroundSurface && name.equals("_GenericApplicationPropertyOfWaterGroundSurface"))
			((WaterGroundSurface)dest).addGenericApplicationPropertyOfWaterGroundSurface(genericProperty);		
		else if (dest instanceof WaterSurface && name.equals("_GenericApplicationPropertyOfWaterSurface"))
			((WaterSurface)dest).addGenericApplicationPropertyOfWaterSurface(genericProperty);		
		else
			success = false;
		
		return success;
	}

}
