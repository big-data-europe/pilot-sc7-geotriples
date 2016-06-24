package eu.bde.sc7pilot.geotriples.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class IdRetrieval {
	public static void main(String[] args) {
		try {
			getId(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static long getId(boolean change) throws Exception {
		String filePath = "/resources/id.txt";
		long id = 0;
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e1) {
			throw e1;
		}
		String line;
		StringBuilder input = new StringBuilder();
		int count = 0;
		try {
			while ((line = file.readLine()) != null) {
				if (change) {
					if (count == 0) {
						long oldId = Long.parseLong(line);
						id = oldId +1;
						line = line.replace(Long.toString(oldId), Long.toString(id));
					}
				} else {
					if (count == 1) {
						long oldId = Long.parseLong(line);
						id = oldId +1;
						line = line.replace(Long.toString(oldId), Long.toString(id));
					}
				}
				input.append(line).append('\n');
				++count;
			}
			FileOutputStream os = new FileOutputStream(filePath);
			os.write(input.toString().getBytes());
		} catch (NumberFormatException | IOException e) {
			throw e;
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				throw e;
			}
		}
		return id;
	}
}
