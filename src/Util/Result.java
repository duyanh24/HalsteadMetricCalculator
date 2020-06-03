package Util;

import java.util.ArrayList;

public class Result {
	
	public Result() {
		blockCmts= new ArrayList<String>();
		number= new ArrayList<String>();
		lineCmts= new ArrayList<String>();
		strs= new ArrayList<String>();
		words= new ArrayList<String>();
	}
	public ArrayList<String> blockCmts;//các cmt dạng block /** */
	public ArrayList<String> lineCmts;//các cmt theo line //
	public ArrayList<String> strs;//các chuỗi có trong class
	public ArrayList<String> words;//các từ khóa còn lại
	public ArrayList<String> number;//Mảng số
}
