package com.digul.algorithm;

public abstract class Algorithm {
	abstract protected void scanInput() throws InputException;	// set the input data	// 입력값 받기
	abstract public String calculate();		// run the algorithm for getting answer	// 알고리즘 돌려서 결과 내기
	abstract public void printInput();			// print input data for debugging	// 입력값 찍기
	abstract public void printProcess();		// print processed data for debugging	// 중간값 찍기


	protected class InputException extends Exception {
		private static final long serialVersionUID = 1L;
	
		public InputException(String str) {
			super("Input data is out of range. 입력이 범위를 벗어남> ".concat(str));
		}
	}
}
