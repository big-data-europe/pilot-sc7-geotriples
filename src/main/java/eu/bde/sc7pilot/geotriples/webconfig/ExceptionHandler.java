package eu.bde.sc7pilot.geotriples.webconfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception>{
	
    @Override
    public Response toResponse(Exception exception) {
    	ResponseMessage respMessage = new ResponseMessage();
		respMessage.setMessage(exception.getMessage());
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
    }
}
