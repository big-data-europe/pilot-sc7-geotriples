package eu.bde.sc7pilot.geotriples.webconfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException>{

    @Override
    public Response toResponse(UnrecognizedPropertyException exception)
    {
    	System.out.println("unrec");
    	ResponseMessage respMessage = new ResponseMessage();
		respMessage.setMessage("This is an invalid request. The field " + exception.getPropertyName() + " is not recognized by the system.");
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
    }
    
}
