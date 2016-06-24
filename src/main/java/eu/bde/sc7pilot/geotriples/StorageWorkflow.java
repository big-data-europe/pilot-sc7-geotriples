package eu.bde.sc7pilot.geotriples;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import eu.bde.sc7pilot.geotriples.model.Change;
import eu.bde.sc7pilot.geotriples.model.Event;

public class StorageWorkflow {
	private String outputDirectory="/resources/";
	private String STRABON_HOST="localhost";
	private int STRABON_PORT=5432;
	private String DB_NAME="events-changes";
	private String PASSWORD="postgres";
	private String USERNAME="postgres";
	public synchronized void  storeChanges(List<Change> changes) throws Exception {
		ObjectMapper objectMapper=new ObjectMapper().registerModule(new JtsModule()).registerModule(new JodaModule());
		String mappingFileName="change-mapping.ttl";
		String jsonFileName="changes.json";
		String rdfFileName="changes.nt";
		
		try {
			objectMapper.writer().writeValue(new File(outputDirectory,jsonFileName), changes);
		} catch (IOException e) {
			throw e;
		}
		store(mappingFileName,rdfFileName,jsonFileName);
	}
	
	public synchronized void storeEvent(Event event) throws Exception{
		ObjectMapper objectMapper=new ObjectMapper().registerModule(new JtsModule()).registerModule(new JodaModule());
		String mappingFileName="event-mapping.ttl";
		String jsonFileName="events.json";
		String rdfFileName="events.nt";
		
		try {
			objectMapper.writer().writeValue(new File(outputDirectory,jsonFileName), event);
		} catch (IOException e) {
			throw e;
		}
		store(mappingFileName,rdfFileName,jsonFileName);
	}
	private void store(String mappingFileName,String rdfFileName,String jsonFileName) throws Exception{
		new File(outputDirectory,jsonFileName).delete();
		new File(outputDirectory,rdfFileName).delete();
	}
	
}
