package eu.bde.sc7pilot.geotriples.storage;

import java.io.File;
import java.util.List;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import eu.bde.sc7pilot.geotriples.GeotriplesConverter;
import eu.bde.sc7pilot.geotriples.model.Change;
import eu.bde.sc7pilot.geotriples.model.Event;

public class StorageWorkflow {
	private String outputDirectory = "/resources/";
	 //private String outputDirectory="/home/efi/SNAP/files/";
	private String output = "/resources/";
	 //private String output="/home/efi/SNAP/files/";
	//private String STRABON_HOST = "postgis";
	 private String STRABON_HOST = "localhost";
	private int STRABON_PORT = 5432;
	private String DB_NAME = "endpoint";
	//private String DB_NAME = "events-changes";
	private String PASSWORD = "postgres";
	private String USERNAME = "postgres";

	public synchronized void storeChanges(List<Change> changes) throws Exception {
		try {
			ObjectMapper objectMapper = new ObjectMapper().registerModule(new JtsModule())
					.registerModule(new JodaModule());
			String mappingFileName = "change-mapping.ttl";
			String jsonFileName = "changes.json";
			String rdfFileName = "changes.nt";

			objectMapper.writer().writeValue(new File(outputDirectory, jsonFileName), changes);

			store(mappingFileName, rdfFileName, jsonFileName);
		} catch (Exception e) {
			throw e;
		}
	}

	public synchronized void storeEvent(Event event) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JtsModule()).registerModule(new JodaModule());
		String mappingFileName = "event-mapping.ttl";
		String jsonFileName = "events.json";
		String rdfFileName = "events.nt";

		try {
			objectMapper.writer().writeValue(new File(outputDirectory, jsonFileName), event);
			store(mappingFileName, rdfFileName, jsonFileName);
		} catch (Exception e) {
			throw e;
		}
	}

	private void store(String mappingFileName, String rdfFileName, String jsonFileName) throws Exception {
		try {
			new GeotriplesConverter().convertToRDF(outputDirectory + mappingFileName, outputDirectory + rdfFileName, outputDirectory + jsonFileName);
//		GeotriplesConverter.tempGeotriples(outputDirectory + mappingFileName, outputDirectory + rdfFileName,
//				outputDirectory + jsonFileName);
		//RdfStorage rdfStorage = new StrabonRdfStorage(DB_NAME, USERNAME, PASSWORD, STRABON_PORT, STRABON_HOST);
		GeotriplesConverter conv=new GeotriplesConverter();
		conv.convertToRDF(outputDirectory + mappingFileName, outputDirectory + rdfFileName, outputDirectory + jsonFileName);
		
		RdfStorage rdfStorage=new StrabonEndpoint("localhost", "endpoint", "3ndpo1nt", 8193, "strabon/Store");
		rdfStorage.storeRdf("file://"+outputDirectory+rdfFileName);
		} catch (Exception e) {
			e.printStackTrace();;
			throw e;
		}
		// new File(outputDirectory,jsonFileName).delete();
		// new File(outputDirectory,rdfFileName).delete();
	}

}
