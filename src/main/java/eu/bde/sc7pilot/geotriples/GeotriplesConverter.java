package eu.bde.sc7pilot.geotriples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import be.ugent.mmlab.rml.core.NodeRMLPerformer;
import be.ugent.mmlab.rml.core.RMLMappingFactory;
import be.ugent.mmlab.rml.function.Config;
import be.ugent.mmlab.rml.function.FunctionArea;
import be.ugent.mmlab.rml.function.FunctionAsGML;
import be.ugent.mmlab.rml.function.FunctionAsWKT;
import be.ugent.mmlab.rml.function.FunctionCentroidX;
import be.ugent.mmlab.rml.function.FunctionCentroidY;
import be.ugent.mmlab.rml.function.FunctionCoordinateDimension;
import be.ugent.mmlab.rml.function.FunctionDimension;
import be.ugent.mmlab.rml.function.FunctionEQUI;
import be.ugent.mmlab.rml.function.FunctionFactory;
import be.ugent.mmlab.rml.function.FunctionHasSerialization;
import be.ugent.mmlab.rml.function.FunctionIs3D;
import be.ugent.mmlab.rml.function.FunctionIsEmpty;
import be.ugent.mmlab.rml.function.FunctionIsSimple;
import be.ugent.mmlab.rml.function.FunctionLength;
import be.ugent.mmlab.rml.function.FunctionSpatialDimension;
import be.ugent.mmlab.rml.main.MainTrans;
import be.ugent.mmlab.rml.model.RMLMapping;
import be.ugent.mmlab.rml.model.TriplesMap;
import be.ugent.mmlab.rml.processor.RMLProcessor;
import be.ugent.mmlab.rml.processor.RMLProcessorFactory;
import be.ugent.mmlab.rml.processor.concrete.ConcreteRMLProcessorFactory;
import net.antidot.semantic.rdf.model.impl.sesame.SesameDataSet;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.InvalidR2RMLStructureException;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.InvalidR2RMLSyntaxException;
import net.antidot.semantic.rdf.rdb2rdf.r2rml.exception.R2RMLDataError;

public class GeotriplesConverter {
	public void registerFunctions() {
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/equi"),
				new FunctionEQUI()); // don't remove or change this line, it
										// replaces the equi join functionality
										// of R2RML

		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/asWKT"),
				new FunctionAsWKT());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/hasSerialization"),
				new FunctionHasSerialization());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/asGML"),
				new FunctionAsGML());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/isSimple"),
				new FunctionIsSimple());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/isEmpty"),
				new FunctionIsEmpty());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/is3D"),
				new FunctionIs3D());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/spatialDimension"),
				new FunctionSpatialDimension());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/dimension"),
				new FunctionDimension());
		FunctionFactory.registerFunction(
				new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/coordinateDimension"),
				new FunctionCoordinateDimension());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/area"),
				new FunctionArea());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/length"),
				new FunctionLength());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/centroidx"),
				new FunctionCentroidX());
		FunctionFactory.registerFunction(new URIImpl("http://www.w3.org/ns/r2rml-ext/functions/def/centroidy"),
				new FunctionCentroidY());

	}

	public void convertToRDF(String mapFilePath, String outputFilePath, String csvFilePath) throws RepositoryException, RDFParseException, InvalidR2RMLStructureException,
			InvalidR2RMLSyntaxException, R2RMLDataError, IOException {
		registerFunctions(); // register custom functions (e.g.,
		// dimension(geometry) )

		RMLMapping mapping = RMLMappingFactory.extractRMLMapping(mapFilePath); //edw einai to mapping file
		Config.EPSG_CODE = "4326";

		RDFFormat format = RDFFormat.NTRIPLES;

		SesameDataSet outputDataSet = new SesameDataSet();
		String fileName = csvFilePath; // afto einai to shapefile

		RMLProcessorFactory factory = new ConcreteRMLProcessorFactory();
		for (TriplesMap m : mapping.getTriplesMaps()) {
			RMLProcessor processor = factory.create(m.getLogicalSource().getReferenceFormulation());

			Collection<Statement> statements = processor.execute(outputDataSet, m, new NodeRMLPerformer(processor), fileName, false);
			
			System.out.println("The triples generated by one iteration:");
//			for(Statement st:statements){
//				System.out.println(st.toString());				
//			}
//			System.in.read();

		}
		
		outputDataSet.dumpRDF(outputFilePath, format);
	}

	public static void main(String[] args) throws RepositoryException, RDFParseException, InvalidR2RMLStructureException, InvalidR2RMLSyntaxException, R2RMLDataError, IOException {
		  String outputDirectory="/home/efi/SNAP/files/";
			//private String output = "/resources/";
			  String output="/home/efi/SNAP/files/";
//			String mappingFileName = "event-mapping.ttl";
//			String jsonFileName = "events.json";
//			String rdfFileName = "events.nt";
			  String mappingFileName = "change-mapping.ttl";
				String jsonFileName = "changes.json";
				String rdfFileName = "changes.nt";

		new GeotriplesConverter().convertToRDF(outputDirectory + mappingFileName, outputDirectory + rdfFileName, outputDirectory + jsonFileName);
	}
}
