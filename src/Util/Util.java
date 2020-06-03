package Util;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
	String operator[] = {"<",">","=","+","-","*","%",";",",","(",")","[","]","{","}",".","/",":"};
	String keyWord[] = {"++","->","--","abstract","assert","boolean","break","byte","case","catch","char","class","const",
			"continue","default","do","double","else","enum","extends","final","finally","float","for","goto","if",
			"implements","import","instanceof","int","interface","long","native","new","package","private","protected",
			"public","return","short","static","super","switch","synchronized","this","throw","transient","try","void",
			"volatile","while"};

	ArrayList<String> operators = new ArrayList<String>(Arrays.asList(operator));
	ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(keyWord));
	public Result Split(String file) {
		Result result = new Result();
		String format="";
		 for (String line : file.split("\\n")) {
			 format+=line.trim()+"\n";
		 }
		 file="  \r\n" + 
		 		""+format;
		 while(file.indexOf("\n/*")!=-1) {
			result.blockCmts.add(file.substring(file.indexOf("\n/*"),2+file.indexOf("*/",2+file.indexOf("\n/*"))));
			file=file.substring(0,file.indexOf("\n/*")-1)+file.substring(2+file.indexOf("*/",2+file.indexOf("\n/*")));
			continue;
		}
		//nếu thấy cmt, cắt hết từ nó tới cuối dòng
		while(file.indexOf("\n//")!=-1) {
			result.lineCmts.add(file.substring(file.indexOf("\n//"),file.indexOf("\n",2+file.indexOf("\n//"))));
			file=file.substring(0,file.indexOf("//")-1)+file.substring(file.indexOf("\n",2+file.indexOf("//")));
			continue;
		}

		while(file.indexOf("\"")!=-1) {
			int begin = file.indexOf("\"");
			//tìm dấu hết string
			for(int i=begin+1 ; i<file.length();i++) {
				//i là điểm cần tìm
				if(file.charAt(i)=='\"'&&file.charAt(i-1)!='\\') {
					result.strs.add(file.substring(begin,i+1));
					file=file.substring(0,begin)+file.substring(i+1);
					break;
				}
			}
			continue;
		} 
	
	 
		file = file.replace("<", " < ");
		file = file.replace(">", " > ");
		file = file.replace("=", " = ");
		file = file.replace("+", " + ");
		file = file.replace("-", " - ");
		file = file.replace("*", " * ");
		file = file.replace("%", " % ");
		file = file.replace(";", " ; ");
		file = file.replace(",", " , ");
		file = file.replace("(", " ( ");
		file = file.replace(")", " ) ");
		file = file.replace("[", " [ ");
		file = file.replace("]", " ] ");
		file = file.replace("{", " { ");
		file = file.replace("}", " } ");
		file = file.replace("/", " / ");
		file = file.replace("^", " ^ ");
		file = file.replace("?", " ? ");
		file = file.replace("&", " & ");
		file = file.replace("|", " | ");
		file = file.replace("!", " ! ");
		file = file.replace("~", " ~ ");
		file = file.replace(":", " : ");
		  for (String word : file.split("\\s")) {
			  if(word.indexOf('.')!=-1){
				  if(isNumeric(""+word.charAt(word.indexOf('.')+1))){
						result.number.add(word);
				  }
			  }
		  }
		  for(String s : result.number){
				file = file.replace(s, "");
		  }
			file = file.replace(".", " . ");
		boolean check = false;//kiểm tra bắt đầu class
		  for (String line : file.split("\\n")) {
			  if(line.indexOf("class")!=-1) {
				  check = true;
			  }
			  if(check!=true) {
				  continue;
			  }
			  //tách từ, ký tự
			  for (String w : line.split("\\s")) {
				  if(w!="\\s"&&w!="\\t"&&w!="\\n") {
					  if(w.length()!=0) {
						  result.words.add(w);
					  }
				  }
			  }
		  }
		  
		return result;
	}
	public void Filter(ArrayList<String> words) {
		  for (int i=0;i<words.size();i++) {
			   String w = words.get(i);
			   w=w.trim();
			   if(!checkOperator(words,i)) {
				   System.out.println("Toán hạng  "+w);
			   }
//			   else {
//				   System.out.println("Toán tử  "+w);
//			   }
		  }
			

	}
	boolean checkOperator(ArrayList<String> words,int i) {
		if(operators.contains(words.get(i))||keyWords.contains(words.get(i)))
			return true;
		if(words.get(i).charAt(0)=='@')
			return true;
		else {
			if(i+1<=words.size()) {
				if(words.get(i+1).indexOf("(")!=-1) {
					return true;
				}
				if(words.get(i+1).indexOf("[")!=-1) {
					return true;
				}
				if(!operators.contains(words.get(i+1))&&!keyWords.contains(words.get(i+1))) {
					return true;
				}
			}
			
		}
			
		return false;
	}
	
	public boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}