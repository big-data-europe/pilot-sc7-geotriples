package eu.bde.sc7pilot.geotriples.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.bde.sc7pilot.geotriples.model.Area;
import eu.bde.sc7pilot.geotriples.model.Change;
import eu.bde.sc7pilot.geotriples.model.Event;
import eu.bde.sc7pilot.geotriples.storage.StorageWorkflow;
import eu.bde.sc7pilot.geotriples.utils.IdRetrieval;
import eu.bde.sc7pilot.geotriples.webconfig.ResponseMessage;

@Path("/")
public class GeotriplesService {
	
	@POST
	@Path("/event")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveEvent(@Suspended final AsyncResponse asyncResponse, Event event) {
		new Thread(new Runnable() {
			@Override
			
			public void run() {
				ResponseMessage respMessage = new ResponseMessage();
				if (event == null) {
					respMessage.setMessage("request body should not be empty.");
					respMessage.setCode(400);
					//throw new WebApplicationException(
					//		Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
				}
				if (event.getId() == null || event.getId().trim().isEmpty()) {
					respMessage.setMessage("event id should not be null or empty.");
					respMessage.setCode(400);
					//throw new WebApplicationException(
						//	Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
				}
				for (Area area : event.getAreas()) {
					if (area == null) {
						respMessage.setMessage("all areas should have a name and a geometry");
						respMessage.setCode(400);
					//	throw new WebApplicationException(
							//	Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
					}
					if ((area.getName().trim().isEmpty()) || (area.getName() == null) || area.getGeometry() == null) {
						respMessage.setMessage("all areas should have a name and a geometry");
						respMessage.setCode(400);
					//	throw new WebApplicationException(
							//	Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
					}
				}
				StorageWorkflow storageWorkflow = new StorageWorkflow();
				try {
					for (Area a : event.getAreas()) {
						a.setId(IdRetrieval.getId(false));
					}
					storageWorkflow.storeEvent(event);
					respMessage.setMessage("event processed");
					respMessage.setCode(200);
				} catch (Exception e) {
					e.printStackTrace();
					respMessage.setMessage(e.getMessage());
					respMessage.setCode(500);
					//throw new WebApplicationException(
							//Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(respMessage).build());
				}
				asyncResponse.resume(Response.status(respMessage.getCode()).entity(respMessage).build());
			}
		}).start();
	}
	
	@POST
	@Path("/changes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveChanges(@Suspended final AsyncResponse asyncResponse, List<Change> changes) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ResponseMessage respMessage = new ResponseMessage();
				if (changes == null) {
					respMessage.setMessage("request body should not be empty.");
					respMessage.setCode(400);
				}
				StorageWorkflow storageWorkflow = new StorageWorkflow();
				try {
					storageWorkflow.storeChanges(changes);
					respMessage.setMessage("change processed successfully");
					respMessage.setCode(200);
				} catch (Exception e) {
					e.printStackTrace();
					respMessage.setMessage(e.getMessage());
					respMessage.setCode(500);
				}
				asyncResponse.resume(Response.status(respMessage.getCode()).entity(respMessage).build());
			}
		}).start();
	}
}
