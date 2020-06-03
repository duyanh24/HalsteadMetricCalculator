package recommendation;

public class RecommendationOutputer {
	
	private float value_V;
	private float value_B;
	private float value_N;
	private float value_H;

	String resultArray[] = {"Chương trình có các thông số hợp lý.",
							"Thể tích của class quá lớn, cần giảm bớt thao tác trong class!",
							"Class có độ dài vượt quá mức cần thiết để xử lý cùng các tác vụ đã cho. Cần xem lại luồng xử lý.",
							"Có nhiều bug tiềm tàng trong code cần được giải quyết."};
	
	public RecommendationOutputer (float V, float B, float N, float H) {
		this.value_V = V;
		this.value_B = B;
		this.value_N = N;
		this.value_H = H;
	}
	
	public String checkClass() {
		String result = "";
		if (check_V() && check_N() && check_B()) {
			return resultArray[0];
		} else {
			if (!check_V()) 
				result += resultArray[1] + "\r\n";
			if (!check_N()) 
				result += resultArray[2] + "\r\n";
			if (!check_B()) 
				result += resultArray[3] + "\r\n";
			
			return result;
		}
	}
	
	// V nên <= 8000 
	private boolean check_V() {
		if (value_V > 8000.0f) {
			return false;
		}
		return true;
	}
	
	// N <= 1.5 H
	private boolean check_N() {
		if (value_N > 1.5 * value_H) {
			return false;
		}
		return true;
	}
	
	// B nên <= 2
	private boolean check_B() {
		if (value_B > 2.0f) {
			return false;
		}
		return true;
	}
	
}
