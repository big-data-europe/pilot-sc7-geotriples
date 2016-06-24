package eu.bde.sc7pilot.geotriples.webconfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;
@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException>{
	
    @Override
    public Response toResponse(JsonProcessingException exception) {
    	ResponseMessage respMessage = new ResponseMessage();
		respMessage.setMessage("test");
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
    }
}
