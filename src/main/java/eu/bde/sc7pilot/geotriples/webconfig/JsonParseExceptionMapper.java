package eu.bde.sc7pilot.geotriples.webconfig;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonParseException;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
	@Override
	public Response toResponse(JsonParseException exception) {
		System.out.println("parse");
		ResponseMessage respMessage = new ResponseMessage();
		exception.printStackTrace();;
		try {
			System.out.println(exception.getProcessor().getCurrentName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respMessage.setMessage("This is an invalid json, line "+exception.getLocation().getLineNr()+" ,column "+exception.getLocation().getColumnNr());
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
	}

}
