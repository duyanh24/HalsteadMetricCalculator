package filereader;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile {
	public String convertFileToString(BufferedReader br){
		String file = "";
		if(br != null){
        	try {
        		String textInALine;
				while ((textInALine = br.readLine()) != null) {
					file += textInALine + "\n";
				}
        	}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
}
