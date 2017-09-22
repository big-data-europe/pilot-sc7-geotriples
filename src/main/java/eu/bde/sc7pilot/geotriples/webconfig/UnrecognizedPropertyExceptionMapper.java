package eu.bde.sc7pilot.geotriples.webconfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException>{

    @Override
    public Response toResponse(UnrecognizedPropertyException exception)
    {
    	Logger.getAnonymousLogger().severe("This is an invalid request. The field " + exception.getPropertyName() + " is not recognized by the system.");
    	ResponseMessage respMessage = new ResponseMessage();
		respMessage.setMessage("This is an invalid request. The field " + exception.getPropertyName() + " is not recognized by the system.");
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
    }
    
}
