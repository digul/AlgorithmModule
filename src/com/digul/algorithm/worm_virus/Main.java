package com.digul.algorithm.worm_virus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.digul.algorithm.Algorithm;

public class Main extends Algorithm {
	private boolean debugMode = false;

	public Main(String filePath) throws FileNotFoundException {
		debugMode = true;
		System.setIn(new FileInputStream(filePath));
		scanInput();
	}

	public Main() {
		// TODO Auto-generated constructor stub
	}

	protected void scanInput() {
		// TODO Auto-generated method stub

	}

	public String calculate() {
		// TODO Auto-generated method stub
		return "New Algorithm has just set";
	}

	public void printInput() {
		// TODO Auto-generated method stub
	}

	public void printProcess() {
		// TODO Auto-generated method stub

	}

}
