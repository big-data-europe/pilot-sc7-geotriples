package eu.bde.sc7pilot.geotriples;

import java.util.ArrayList;
import java.util.List;

import be.ugent.mmlab.rml.main.MainTrans;

public class GeotriplesConverter {
	public static void tempGeotriples(String mapFilePath, String outputFilePath, String csvFilePath) {
		Process proc = null;
		List<String> arglist = new ArrayList<String>();

		arglist.add(mapFilePath);
		arglist.add(outputFilePath);

		String[] pipeargs = arglist.toArray(new String[arglist.size()]);
		try {
			MainTrans.main(pipeargs);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
//		try {
//			// proc = Runtime.getRuntime().exec(
//			// "java -jar
//			// /home/efi/geotriples/GeoTriples/target/geotriples-1.0.5-SNAPSHOT-cmd.jar
//			// generate_mapping -o "+ mapFilePath+" -b http://example.com/
//			// "+csvFilePath);
//			proc = Runtime.getRuntime()
//					.exec("java -jar /home/efi/geotriples/GeoTriples/target/geotriples-1.0.5-SNAPSHOT-cmd.jar dump_rdf -rml -o "
//							+ outputFilePath + " " + mapFilePath);
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		InputStream err = proc.getErrorStream();
//		// Then retreive the process output
//		InputStream in = proc.getInputStream();
//		BufferedReader br = null;
//		StringBuilder sb = new StringBuilder();
//
//		String line;
//		try {
//
//			br = new BufferedReader(new InputStreamReader(in));
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		System.out.println("error " + sb.toString());

	}
}
