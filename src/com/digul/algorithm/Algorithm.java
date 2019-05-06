package com.digul.algorithm;

public abstract class Algorithm {
	abstract protected void scanInput();	// 입력값 받기
	abstract public String calculate();		// 알고리즘 돌려서 결과 내기
	abstract public void printInput();			// 입력값 찍기
	abstract public void printProcess();		// 중간값 찍기
}
