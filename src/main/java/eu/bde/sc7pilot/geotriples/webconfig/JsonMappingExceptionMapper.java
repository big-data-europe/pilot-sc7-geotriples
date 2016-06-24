package eu.bde.sc7pilot.geotriples.webconfig;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		ResponseMessage respMessage = new ResponseMessage();
		String message = "This is an invalid request. ";
		System.out.println("map");
		List<Reference> refs = exception.getPath();
		if (refs.size() > 0) {
			message +="Path to error: ";
			for (int i = 0; i < refs.size(); i++) {
				System.out.println(refs.get(i).getFieldName()+" "+refs.get(i).getIndex());
				if (refs.get(i).getFieldName() != null) {
					if (i == refs.size() - 1)
						message += refs.get(i).getFieldName();
					else
						message += refs.get(i).getFieldName() + "->";
				}

			}
		} else {
			message += exception.getLocation().getLineNr() + " ,column " + exception.getLocation().getColumnNr();
		}
		respMessage.setMessage(message);
		respMessage.setCode(400);
		return Response.status(Response.Status.BAD_REQUEST).entity(respMessage).type(MediaType.APPLICATION_JSON)
				.build();
	}

}
