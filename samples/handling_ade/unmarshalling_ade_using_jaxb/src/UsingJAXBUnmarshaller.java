
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.citygml4j.CityGMLContext;
import org.citygml4j.builder.jaxb.JAXBBuilder;
import org.citygml4j.builder.jaxb.JAXBContextPath;
import org.citygml4j.builder.jaxb.marshal.JAXBMarshaller;
import org.citygml4j.builder.jaxb.unmarshal.JAXBUnmarshaller;
import org.citygml4j.factory.GMLFactory;
import org.citygml4j.jaxb.gml._3_1_1.MultiSurfacePropertyType;
import org.citygml4j.jaxb.gml._3_1_1.MultiSurfaceType;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.MultiSurface;
import org.citygml4j.model.gml.StringOrRef;
import org.citygml4j.model.module.citygml.CityGMLVersion;
import org.citygml4j.model.module.citygml.CoreModule;
import org.citygml4j.xml.io.CityGMLInputFactory;
import org.citygml4j.xml.io.CityGMLOutputFactory;
import org.citygml4j.xml.io.reader.CityGMLReader;
import org.citygml4j.xml.io.writer.CityModelWriter;
import org.citygml4j.xml.schema.SchemaHandler;
import org.w3c.dom.Element;

import ade.sub.jaxb.AbstractBoundarySurfaceType;
import ade.sub.jaxb.BoundarySurfacePropertyType;
import ade.sub.jaxb.ClosureSurfaceType;
import ade.sub.jaxb.GroundSurfaceType;
import ade.sub.jaxb.ObjectFactory;
import ade.sub.jaxb.RelativeToTerrainType;
import ade.sub.jaxb.RoofSurfaceType;
import ade.sub.jaxb.TunnelType;
import ade.sub.jaxb.WallSurfaceType;


public class UsingJAXBUnmarshaller {

	public static void main(String[] args) throws Exception {
		CityGMLContext ctx = new CityGMLContext();
		JAXBBuilder builder = ctx.createJAXBBuilder();

		final SchemaHandler schemaHandler = SchemaHandler.newInstance();
		schemaHandler.parseSchema(new File("../../datasets/schemas/CityGML-SubsurfaceADE-0_9_0.xsd"));

		CityGMLInputFactory in = builder.createCityGMLInputFactory();
		in.setSchemaHandler(schemaHandler);

		CityGMLReader reader = in.createCityGMLReader(new File("../../datasets/LOD2_SubsurfaceStructureADE_v100.xml"));
		CityModel cityModel = (CityModel)reader.nextFeature();
		reader.close();
		
		ADEComponent ade = cityModel.getCityObjectMember().get(3).getGenericADEComponent();

		JAXBUnmarshaller unmarshaller = builder.createJAXBUnmarshaller();
		unmarshaller.setFreeJAXBElements(false);
		
		JAXBMarshaller marshaller = builder.createJAXBMarshaller();
		GMLFactory gml = new GMLFactory();
		
		ObjectFactory jaxbFactory = new ObjectFactory();
		String contextPath = JAXBContextPath.getContextPath(CityGMLVersion.v1_0_0, "ade.sub.jaxb");
		JAXBContext jaxbCtx = JAXBContext.newInstance(contextPath);
		Unmarshaller jaxbUmarshaller = jaxbCtx.createUnmarshaller();
		
		JAXBElement<?> element = (JAXBElement<?>)jaxbUmarshaller.unmarshal(ade.getContent());
		System.out.println("Unmarshalled JAXBElement: " + element.getName());
	
		if (element.getValue() instanceof TunnelType) {
			TunnelType tunnel = (TunnelType)element.getValue();
			System.out.println("ADE feature: Tunnel, gml:id='" + tunnel.getId() + "'");
			
			XMLGregorianCalendar creationDate = tunnel.getCreationDate();
			System.out.print("creation date (from CityObject): ");
			System.out.println(creationDate.getYear() + "-" + creationDate.getMonth() + "-" + creationDate.getDay());
			
			for (JAXBElement<Object> genericProperty : tunnel.get_GenericApplicationPropertyOfCityObject()) {
				if (genericProperty.getValue() instanceof RelativeToTerrainType) {
					RelativeToTerrainType type = (RelativeToTerrainType)genericProperty.getValue();
					System.out.println("relativeToTerrain: " + type.value());
				}					
			}
			
			for (BoundarySurfacePropertyType boundedBy : tunnel.getBoundedBySurface()) {
				AbstractBoundarySurfaceType boundarySurface = (AbstractBoundarySurfaceType)boundedBy.get_Object().getValue();
				
				if (boundarySurface instanceof RoofSurfaceType) 
					System.out.println("ADE feature: RoofSurface");
				else if (boundarySurface instanceof ClosureSurfaceType) 
					System.out.println("ADE feature: ClosureSurface");
				else if (boundarySurface instanceof WallSurfaceType) 
					System.out.println("ADE feature: WallSurface");
				else if (boundarySurface instanceof GroundSurfaceType) 
					System.out.println("ADE feature: GroundSurface");
							
				MultiSurfacePropertyType multiSurfacePropertyType = boundarySurface.getLod2MultiSurface();
				System.out.println("  Processing geometry: " + multiSurfacePropertyType.getMultiSurface());

				MultiSurface multiSurface = (MultiSurface)unmarshaller.unmarshal(multiSurfacePropertyType.getMultiSurface());
				StringOrRef description = gml.createStringOrRef();
				description.setValue("processed by citygml4j");
				multiSurface.setDescription(description);
				
				MultiSurfaceType multiSurfaceType = (MultiSurfaceType)marshaller.marshal(multiSurface);
				multiSurfacePropertyType.setMultiSurface(multiSurfaceType);
			}
					
			Element tunnelElement = marshaller.marshalDOMElement(jaxbFactory.createTunnel(tunnel), jaxbCtx);
			ADEComponent newADE = new ADEComponent(tunnelElement);
			cityModel.getCityObjectMember().get(3).setGenericADEComponent(newADE);
		}
		
		CityGMLOutputFactory out = builder.createCityGMLOutputFactory(CityGMLVersion.v1_0_0);
		out.setSchemaHandler(schemaHandler);
		
		CityModelWriter writer = out.createCityModelWriter(new File("LOD2_SubsurfaceStructureADE_JAXBUnmarshaller_v100.xml"));
		writer.setPrefixes(CityGMLVersion.v1_0_0);
		writer.setPrefix("sub", "http://www.citygml.org/ade/sub/0.9.0");
		writer.setDefaultNamespace(CoreModule.v1_0_0);
		writer.setSchemaLocation("http://citygml.org/ade/sub/0.9.0", "../../datasets/schemas/CityGML-SubsurfaceADE-0_9_0.xsd");
		writer.setIndentString("  ");

		writer.writeStartDocument();
		
		for (CityObjectMember member : cityModel.getCityObjectMember())
			if (member.isSetGenericADEComponent())
				writer.writeFeatureMember(member.getGenericADEComponent());
		
		writer.writeEndDocument();		
		writer.close();
	}

}
