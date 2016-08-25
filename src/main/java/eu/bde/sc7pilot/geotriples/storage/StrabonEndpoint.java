package eu.bde.sc7pilot.geotriples.storage;

import java.net.MalformedURLException;
import java.net.URL;

import org.openrdf.query.resultio.stSPARQLQueryResultFormat;
import org.openrdf.rio.RDFFormat;

import eu.earthobservatory.org.StrabonEndpoint.client.EndpointResult;
import eu.earthobservatory.org.StrabonEndpoint.client.SPARQLEndpoint;

public class StrabonEndpoint implements RdfStorage{
	private SPARQLEndpoint endpoint;
	private URL data;
	public StrabonEndpoint(String host,String username,String password,int port, String strabonPath) throws MalformedURLException {
		endpoint = new SPARQLEndpoint(host, port, strabonPath);
		endpoint.setUser(username);
		endpoint.setPassword(password);
	}
	@Override
	public boolean storeRdf(String rdfPath) throws Exception {
		data = new URL(rdfPath);
		Boolean response = endpoint.store(data, RDFFormat.NTRIPLES , data);
		return response;
	}

	@Override
	public String queryRdf(String query) throws Exception {
		EndpointResult response = endpoint.query(query, stSPARQLQueryResultFormat.XML);
		return response.getResponse();
	}

}
