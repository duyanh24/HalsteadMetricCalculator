package calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Util.Result;
import Util.Util;
import filereader.ReadFile;
import oracle.jrockit.jfr.tools.ConCatRepository;

public class HalsteadCalculator {
	
	public float calculate_N(int N1, int N2) {
		// N (Program Length) = N1 + N2 
		return (float) N1 + N2;
	}
	
	public float calculate_n(int n1, int n2) {
		// n (Program Vocabulary) = n1 + n2 
		return (float) n1 + n2;
	}
	
	public float calculate_H(int n1, int n2) {
		// H (Halstead Program Length) = n1 * log2(n1) + n2 * log2(n2)
		return (float) n1*log2(n1) + n2*log2(n2);
	}
	
	public float calculate_V(float N, float n) {
		// V (Program Volume) = N * log2(n)
		return (float) N * log2(n);
	}
	
	public float calculate_D(int n1, int n2, float N2) {
		// D (Program Difficulty) = (n1 / 2) * (N2 / n2)
		return (float) (n1 / 2) * (N2 / n2);
	}
	
	public float calculate_L(float D) {
		// L (Program Level) = 1 / D
		return (float) 1 / D;
	}
	
	public float calculate_E(float D, float V) {
		// E (Effort) = D * V
		return (float) D * V;
	}
	
	public float calculate_T(float E) {
		// T (Time to program) = E / 18
		return (float) E / 18.0f;
	}
	
	public float calculate_B_FirstWay(float E) {
		// B (Number of delivered bugs) = E ^ (2/3) / 3000
		return (float) Math.pow(E, 2/3) / 3000.0f;
	}
	
	public float calculate_B_SecondWay(float V) {
		// B (Number of delivered bugs) = V / 3000
		return (float) V / 3000.0f;
	}
	
	// Utility
	
	private static float log2(float x) {
		return (float) (Math.log(x) / Math.log(2));
	}
}
