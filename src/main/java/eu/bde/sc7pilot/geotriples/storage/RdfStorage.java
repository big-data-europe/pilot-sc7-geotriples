package eu.bde.sc7pilot.geotriples.storage;

public interface RdfStorage {
	public void storeRdf(String rdfPath) throws Exception;
	public String queryRdf(String query) throws Exception;
}
