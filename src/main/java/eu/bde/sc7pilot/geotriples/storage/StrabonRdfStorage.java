package eu.bde.sc7pilot.geotriples.storage;

public class StrabonRdfStorage {
//	boolean closed = true;
//	private Strabon strabon = null;
//	private String db;
//	private String username;
//	private String password;
//	private int port;
//	private String host;
//	public StrabonRdfStorage(String db,String username,String password,int port,String host) {
//		this.db=db;
//		this.username=username;
//		this.password=password;
//		this.port=port;
//		this.host=host;
//	}
//	public boolean storeRdf(String rdfPath) throws Exception{
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				if (strabon != null && !closed) {
//					closed = true;
//					strabon.close();
//				}
//			}
//		});
//		try {
//			strabon = new Strabon(db, username, password, port, host, false);
//			strabon.storeInRepo(rdfPath, "NTRIPLES", false);
//			closed = false;
//			// String[]
//			// argrs={"localhost","5432","endpoint","postgres","postgres",rdfPath,"-f","
//			// NTRIPLES"};
//			// StoreOp.main(argrs);
//		} catch (Exception e) {
//			//e.printStackTrace();
//			throw e;
//		} finally {
//			if (strabon != null) {
//				closed = true;
//				strabon.close();
//			}
//		}
//	}
//	@Override
//	public String queryRdf(String query) throws Exception {
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			public void run() {
//				if (strabon != null && !closed) {
//					closed = true;
//					strabon.close();
//				}
//			}
//		});
//		String result=null;
//		try {
//			Format resFormat=Format.XML;
//			OutputStream stream=new ByteArrayOutputStream();
//			strabon = new Strabon(db, username, password, port, host, true);
//			strabon.query(query, resFormat,stream);
//			System.out.println(stream.toString());
//			closed = false;
//			result=stream.toString();
//			// String[]
//			// argrs={"localhost","5432","endpoint","postgres","postgres",rdfPath,"-f","
//			// NTRIPLES"};
//			// StoreOp.main(argrs);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if (strabon != null) {
//				closed = true;
//				strabon.close();
//			}
//		}
//		return result;
//	}
}
