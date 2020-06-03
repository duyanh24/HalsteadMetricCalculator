package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CountOperand {
	String operator[] = {"<",">","=","+","-","*","%",";",",","(",")","[","]","{","}",".","/"};
	String keyWord[] = {"++","--","abstract","assert","boolean","break","byte","case","catch","char","class","const",
			"continue","default","do","double","else","enum","extends","final","finally","float","for","goto","if",
			"implements","import","instanceof","int","interface","long","native","new","package","private","protected",
			"public","return","short","static","super","switch","synchronized","this","throw","transient","try","void",
			"volatile","while"};

	public int countN2(BufferedReader br){
		int count = 0;
		String textInALine;
        if(br != null){
        	try {
				while ((textInALine = br.readLine()) != null) {
					StringBuffer stringBuffer = new StringBuffer(textInALine);
					//duyệt từng ký tự
				    StringTokenizer token = new StringTokenizer(textInALine);
				    //duyệt từng token
				    while(token.hasMoreTokens()){
				    	String value = token.nextToken();
				    	for(int i=0;i<keyWord.length;i++){
				    		if(value.contains(keyWord[i])==false){
				    			count++;
				    		}
				    	}
				    }
				                
					for(int i=0;i<stringBuffer.length();i++){
				    	for(int j = 0;j<operator.length;j++){
				    		if(String.valueOf(stringBuffer.charAt(i)).equals(operator[j]) ){
				    			count++;
				    		}
				    	}          
				    }                                           
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		return count;
	}

	public int countn2(BufferedReader br){
		int count = 0;
		return count;
	}

}
