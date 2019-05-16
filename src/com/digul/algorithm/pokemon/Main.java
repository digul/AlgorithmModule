package com.digul.algorithm.pokemon;
import com.digul.algorithm.Algorithm;


import java.io.*;
import java.util.*;



/**
 * TODO Delete package and extended class when upload this to the site.
 * 알고리즘 사이트에 정답 제출할 때 패키지와 extends Algorithm 삭제 후 제출
 */

public class Main extends Algorithm {
	private boolean debugMode = false;
	private int N;
	private Map<String, Integer> pK = new LinkedHashMap<>();
	private Map<String, Integer> pM = new HashMap<>();
	
	public Main(String filePath) throws Exception {
		debugMode = true;
		System.setIn(new FileInputStream(filePath));
		scanInput();
	}
	public Main() throws Exception {
		scanInput();
	}
	
	protected void scanInput() throws InputException  {
		Scanner sc = new Scanner(System.in);
		try {
			if((N = sc.nextInt()) < 1 || N > 70) {
				throw new InputException("N : " + N);
			}
			int i = 0;
			while(sc.hasNext()) {
				int K, M;
				String P = sc.next();
				
				if(P.length() > 20) {
					throw new InputException("P : ".concat(P));
				}
				if((K = sc.nextInt()) < 12 || K > 400) {
					throw new InputException("K : " + K);
				}
				if((M = sc.nextInt()) < 1 || K > Math.pow(10, 4)) {
					throw new InputException("M : " + M);
				}

				pK.put(P, K);
				pM.put(P, M);
				
				if(++i == N) break;
			}
			
			if(debugMode) {
				printInput();
			}
		} finally {
			sc.close();
		}
	}

	public String calculate() {
		int totalEvoluteCnt = 0, maxEvoluteCnt = 0;
		String maxEvolutedPokemonName = "";

		for(String p : pK.keySet()) {
			int evoluteCnt = getEvoluteCnt(pK.get(p), pM.get(p));
			totalEvoluteCnt += evoluteCnt; 
			if(evoluteCnt > maxEvoluteCnt) {
				maxEvolutedPokemonName = p;
				maxEvoluteCnt = evoluteCnt;
			}
			if(debugMode) {
				System.out.println("===".concat(p).concat(" : ").concat(String.valueOf(evoluteCnt)).concat("==="));
				System.out.println();
			}
		}
		
		
		return totalEvoluteCnt + "\r\n" + maxEvolutedPokemonName;
	}
	
	private int getEvoluteCnt(int K, int M) {
		int evoluteCnt = 0;
		while(K <= M) {
			M = M - K + 2;
			evoluteCnt++;
			if(debugMode) {
				System.out.println("remained candy : " + M);
			}
		}
		return evoluteCnt;
	}

	public void printInput() {
		pK.forEach((P, K) -> {
			System.out.print("Input : ");
			System.out.print(P);
			System.out.print(" / ");
			System.out.print(K);
			System.out.print(" / ");
			System.out.println(pM.get(P));
		});
		System.out.println();
	}

	public void printProcess() {
	}

	/**
	 * main method for upload to the site
	 * 알고리즘 사이트 정답제출용 메인메소드
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception  {
		try {
			Main main = new Main();
			System.out.println(main.calculate());
		} catch(InputException e) {
			System.out.println("[Runtime Exception] ".concat(e.getMessage()));
		}
	}	
}
