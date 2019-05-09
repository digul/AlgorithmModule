package com.digul.algorithm.worm_virus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.digul.algorithm.Algorithm;

/**
 * TODO Delete package and extended class when upload this to the site.
 * 알고리즘 사이트에 정답 제출할 때 패키지와 extends Algorithm 삭제 후 제출
 */
public class Main extends Algorithm {
	private boolean debugMode = false;
	private final int FIRST_COM_NO = 1;
	private int comCnt;
	private Map<Integer, Set<Integer>> computers = new HashMap<Integer, Set<Integer>>();
	private Set<Integer> countedCom = new HashSet<Integer>();

	public Main(String filePath) throws FileNotFoundException {
		debugMode = true;
		System.setIn(new FileInputStream(filePath));
		scanInput();
	}

	public Main() {
		scanInput();
	}

	protected void scanInput() {
		Scanner sc = new Scanner(System.in);
		comCnt = sc.nextInt();
		int linkCnt = sc.nextInt();
		
		for(int i = 0; i < linkCnt; i++) {
			int firstComNo = sc.nextInt();
			int secondComNo = sc.nextInt();
			Set<Integer> firstNet = computers.get(firstComNo);
			Set<Integer> secondNet = computers.get(secondComNo);
			
			if(firstNet == null) {
				firstNet = new HashSet<Integer>();
				computers.put(firstComNo, firstNet);
			}
			if(secondNet == null) {
				secondNet = new HashSet<Integer>();
				computers.put(secondComNo, secondNet);
			}
			
			firstNet.add(secondComNo);
			secondNet.add(firstComNo);
			
		}
		sc.close();
		
		if(debugMode) {
			printInput();
		}

	}

	public String calculate() {
		countInfected(FIRST_COM_NO);
		return String.valueOf(countedCom.size() - 1);	// count except first computer // 1번컴퓨터는 제외하고 센다.
	}
	
	private void countInfected(int comNo) {
		if(countedCom.size() == comCnt) {
			return;
		}
		if(countedCom.contains(comNo)) {
			return;
		}

		countedCom.add(comNo);
		Set<Integer> networks = computers.get(comNo);		
		for(int i : networks) {
			countInfected(i);
		}
		if(debugMode) {
			printProcess();
		}
	}

	/**
	 * print input data for debugging
	 * 자체테스트용 입력값 찍기
	 */
	public void printInput() {
		for(int i : computers.keySet()) {
			System.out.print(i + " : " );
			Set<Integer> networks = computers.get(i);
			for(int j : networks) {
				System.out.print(j + " / ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * print processed data for debugging
	 * 자체테스트용 중간값 찍기
	 */
	public void printProcess() {
		for(int i : countedCom) {
			System.out.print(i + " / ");
		}
		System.out.println();
	}
	/**
	 * main method for upload to the site
	 * 알고리즘 사이트 정답제출용 메인메소드
	 */
	public static void main(String[] args)  {
		Main main = new Main();
		System.out.println(main.calculate());
	}	

}
