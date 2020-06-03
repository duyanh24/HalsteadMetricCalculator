package calculator;

import java.util.ArrayList;
import java.util.HashMap;

public class CountOprerator {

	String characters[] = { "<", ">", "=", "+", "-", "*", "%", ";", ",", "(",")", "[","]", "{","}", ".", "/", "^", "?", "&", "|",
			"!", "~", ":" };

	String special[] = { ">=", "<=", "==", "++", "--", "+=", "-=", "*=", "/=", "%=", ">>", "<<", "!=", "->", "::", "&=",
			"|=", "^=", "&&", "||", "[]","<>" };

	String keyWord[] = { "abstract", "assert", "this", "throw", "throws", "transient", "try", "volatile", "while",
			"strictfp", "break", "case", "catch", "class", "continue", "default", "do", "else", "enum", "extends",
			"final", "finally", "for", "if", "implements", "import", "instanceof", "interface", "native", "new",
			"package", "private", "protected", "public", "return", "static", "super", "switch", "synchronized", };
	
	String primitive[] = {"int", "long", "byte", "short", "double", "boolean", "char", "float", "void"};

	HashMap<String, Integer> operatorMap = new HashMap<String, Integer>();
	HashMap<String, Integer> operandMap = new HashMap<String, Integer>();
	int countDup = 0;

	public int countTotalOperator() {
		int count = 0;
		
		for (String key : operatorMap.keySet()) {
			count += operatorMap.get(key);
			System.out.println(key + "  ....  " + operatorMap.get(key));
		}
		System.out.println("--------------------------");
		return count;
	}
	
	public int countOperator(){
		return operatorMap.size();
	}
	
	public int countTotalOperand() {
		int count = 0;
		
		for (String key : operandMap.keySet()) {
			count += operandMap.get(key);
			System.out.println(key + "  ....  " + operandMap.get(key));
		}
		return count;
	}
	
	public int countOperand(){
		if(countDup == 0){
			return operandMap.size();
		}else{
			return operandMap.size()+ countDup;
		}
		
	}

	public CountOprerator(ArrayList<String> words) {
		words.add("");
		words.add("");
		int lengthWords = words.size();
		
		
		
		// số kí tự "(" bị đếm thừa khi ép kiểu
		int countCast = 0;
		for (int i = 0; i < lengthWords; i++) {
			boolean checkOperator = false;
			for (int j = 0; j < keyWord.length; j++) {
				if (words.get(i).equals(keyWord[j])) {
					putValueOperator(keyWord[j]);
					checkOperator = true;
				}
			}
			for (int j = 0; j < characters.length; j++) {
				if (words.get(i).equals(characters[j])) {
					boolean check = false;
					for (int k = 0; k < special.length; k++) {
						if (i < lengthWords - 2 && (words.get(i) + words.get(i + 1)).equals(special[k])) {
							putValueOperator(special[k]);
							checkOperator = true;
							i += 2;
							check = true;
							break;
						} 
					}
					if(!check){
						putValueOperator(characters[j]);
						checkOperator = true;
					}
				}
			}
			
			
			// xét trường hợp biến nguyên thủy và ép kiểu biến nguyên thủy
			for (int j = 0; j < primitive.length; j++) {
				if(i==0){
					if(words.get(i).equals(primitive[j])){
						putValueOperator(primitive[j]);
						checkOperator = true;
					}
				}else{
					if(words.get(i).equals(primitive[j])){
						if(words.get(i - 1).equals("(") && words.get(i + 1).equals(")")){
							putValueOperator("(" + primitive[j] + ")");
							checkOperator = true;
							countCast++;
						}else{
							putValueOperator(primitive[j]);
							checkOperator = true;
						}
					}
				}
			}
			
			//ép kiểu biến tự định nghĩa
			if (i > 0 && i < lengthWords - 1 && words.get(i - 1).equals("(") && words.get(i + 1).equals(")")) {
				if (isUperCase(words.get(i))) {
					putValueOperator("(" + words.get(i) + ")");
					checkOperator = true;
					countCast++;
				}
			}
			
			// Trường hợp anotation
			if (i<lengthWords-2 && String.valueOf(words.get(i).charAt(0)).equals("@")) {
				putValueOperator(words.get(i));
				checkOperator = true;
			}
			
			// trường hợp là function 
			if(i>0 && i<lengthWords-1){
				if(!words.get(i-1).equals(">") && words.get(i+1).equals("(")){
					putValueOperator(words.get(i));
					checkOperator = true;
				}
			}
			
			// trường hợp là kiểu dữ liệu người dùng đặt
			// cần check thêm th tên class
			if (isUperCase(words.get(i)) && i>0 && i<lengthWords-1 && !isUperCaseEnd(words.get(i))) {
				if(!checkClassName(words.get(i-1)) && !words.get(i+1).equals(")") && !words.get(i+1).equals(".")){
					putValueOperator(words.get(i));
					checkOperator = true;
				}
			}
			
			if(i>0 && !checkOperator && !words.get(i).equals("")){
				if(isPrimitive(words.get(i-1)) || words.get(i-1).equals("]") || isUperCase(words.get(i-1))){
					if(!checkExitsOperandDup(words.get(i))){
						countDup++;
					}
				}
				putValueOperand(words.get(i));
			}
			
		}
		if (operatorMap.get("(") != null) {
			int tmpCount = operatorMap.get("(") - countCast;
			operatorMap.replace("(", tmpCount);
		}
		delete();
		
	}
	
	private boolean checkExitsOperandDup(String key){
		if (operandMap.get(key) == null){
			return true;
		}
		return false;
	}
	
	private void delete(){
		if(operatorMap.get(")") != null){
			operatorMap.remove(")");
		}
		if(operatorMap.get("]") != null){
			operatorMap.remove("]");
		}
		if(operatorMap.get("}") != null){
			operatorMap.remove("}");
		}
	}
	
	private boolean checkClassName(String str){
		if(str.equals("class") || str.equals("extends") || str.equals("implements")){
			return true;
		}
		return false;
	}
	
	private boolean isPrimitive(String str) {
		for(int i=0;i<primitive.length;i++){
			if(str.equals(primitive[i])){
				return true;
			}
		}
		return false;
	}

	public boolean isUperCase(String str) {
		if (str.length()>0 && Character.isUpperCase(str.charAt(0))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUperCaseEnd(String str) {
		if (str.length()>0 && Character.isUpperCase(str.charAt(str.length()-1))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void putValueOperator(String key) {
		if (operatorMap.get(key) == null) {
			operatorMap.put(key, 1);
		} else {
			int count = operatorMap.get(key);
			operatorMap.replace(key, count + 1);
		}
	}
	
	private void putValueOperand(String key) {
		if (operandMap.get(key) == null) {
			operandMap.put(key, 1);
		} else {
			int count = operandMap.get(key);
			operandMap.replace(key, count + 1);
		}
	}
}
