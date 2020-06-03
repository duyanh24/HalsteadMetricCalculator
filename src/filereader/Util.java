package filereader;

public final class Util {
	public static String prettyClass(String str) {
		  while(str.indexOf("/*")!=-1&&str.indexOf("*/")!=-1) {
			  str = str.substring(0,str.indexOf("/*"))+str.substring(str.indexOf("*/")+2);
		  }
		  String result="";
		  boolean checkClass=false;
		  String[] currents = str.split("\\n");
		  for (String c : currents) {
			  if(!checkClass) {
				  if(c.indexOf("class")!=-1)
					  checkClass=true;
			  }
			  if(checkClass) {
			  if(c.indexOf("//")!=-1) {
			  	c=c.substring(0,c.indexOf("//"));
			  }
			  result +=c+"\n";
			  }
		  }
		  return result;
	}
}
