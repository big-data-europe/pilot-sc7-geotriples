package eu.bde.sc7pilot.geotriples.storage;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import eu.earthobservatory.runtime.postgis.Strabon;
import eu.earthobservatory.utils.Format;

public class StrabonRdfStorage implements RdfStorage{
	boolean closed = true;
	private Strabon strabon = null;
	private String db;
	private String username;
	private String password;
	private int port;
	private String host;
	public StrabonRdfStorage(String db,String username,String password,int port,String host) {
		this.db=db;
		this.username=username;
		this.password=password;
		this.port=port;
		this.host=host;
	}
	public void storeRdf(String rdfPath) throws Exception{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (strabon != null && !closed) {
					strabon.close();
				}
			}
		});
		try {
			strabon = new Strabon(db, username, password, port, host, true);
			strabon.storeInRepo(rdfPath, "NTRIPLES", false);
			// String[]
			// argrs={"localhost","5432","endpoint","postgres","postgres",rdfPath,"-f","
			// NTRIPLES"};
			// StoreOp.main(argrs);
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		} finally {
			if (strabon != null) {
				closed = true;
				strabon.close();
			}
		}
	}
	@Override
	public String queryRdf(String query) throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (strabon != null && !closed) {
					strabon.close();
				}
			}
		});
		try {
			Format resFormat=Format.XML;
			OutputStream stream=new ByteArrayOutputStream();
			strabon = new Strabon(db, username, password, port, host, true);
			strabon.query(query, resFormat,stream);
			System.out.println(stream.toString());
			return stream.toString();
			// String[]
			// argrs={"localhost","5432","endpoint","postgres","postgres",rdfPath,"-f","
			// NTRIPLES"};
			// StoreOp.main(argrs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (strabon != null) {
				closed = true;
				strabon.close();
			}
		}
		
	}
}
