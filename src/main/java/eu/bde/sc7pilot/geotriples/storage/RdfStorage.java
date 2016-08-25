package eu.bde.sc7pilot.geotriples.storage;

public interface RdfStorage {
	public boolean storeRdf(String rdfPath) throws Exception;
	public String queryRdf(String query) throws Exception;
}
