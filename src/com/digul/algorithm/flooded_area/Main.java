package com.digul.algorithm.flooded_area;
import com.digul.algorithm.Algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * TODO Delete package and extended class when upload this to the site.
 * 알고리즘 사이트에 정답 제출할 때 패키지와 extends Algorithm 삭제 후 제출
 */
public class Main extends Algorithm {
	private boolean debugMode = false;
	private final int maxHeight = 100;
	private int areaSize;
	private int[][] inputArea;
	private char[][] safeArea;

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
		areaSize = sc.nextInt();
		inputArea = new int[areaSize][areaSize];
		
		for(int i = 0; i < areaSize; i++) {
			for(int j = 0; j < areaSize; j++) {
				inputArea[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		if(debugMode) {
			printInput();
		}
	}

	public void calculate() {
		
		 int maxSafeAreaCnt = 1;
		 
		for (int floodedHeight = 0; floodedHeight < maxHeight; floodedHeight++) {
			safeArea = new char[areaSize][areaSize];
			
			int safeAreaCnt = 0;
			
			for(int row = 0; row < areaSize; row++) {
				for(int col = 0; col < areaSize; col++) {
					if(safeArea[row][col] != 0) {	// already searched // 이미 탐색된 영역
						continue;
					}
					if(floodedHeight >= inputArea[row][col] ) {	
						safeArea[row][col] = 'X';		// flooded area // 잠긴 지역
						continue;
					}
					
					findSafeArea(row, col, floodedHeight);	// start searching // 탐색시작
					safeAreaCnt++;
				}
			}

			if(debugMode) {
				System.out.println(safeAreaCnt);
				printProcess();
			}
			
			if(safeAreaCnt > maxSafeAreaCnt) {
				maxSafeAreaCnt = safeAreaCnt;	// update max value // 최대값 갱신
			}

			
			if(safeAreaCnt == 0	) break;	// stop when whole area is flooded // 모두 잠기면 그만			
		}
		System.out.println(maxSafeAreaCnt);
	}
	
private void  findSafeArea(int row, int col, int floodedHeight) {
		
		// stop where is out of the area // 영역을 벗어나면 중단
		if(row < 0) return;	
		if(row == areaSize) return;
		if(col < 0) return;
		if(col == areaSize) return;

		if(safeArea[row][col] != 0) {
			return;		// stop where is searched already  // 이미 탐색한 지역이면 중단 
		}
		
		if(inputArea[row][col] <= floodedHeight) {
			safeArea[row][col] = 'X';	// flooded area // 침수지역
			return;								// stop searching // 탐색 중단
		}
		
		
		safeArea[row][col] = 'O';	// safe area // 안전영역
		
		
		
		// search where isn't yet // 미탐색 지역 탐색
		findSafeArea(row+1, col, floodedHeight);
		findSafeArea(row-1, col, floodedHeight);
		findSafeArea(row, col+1, floodedHeight);
		findSafeArea(row, col-1, floodedHeight);
	}
	
	/**
	 * main method for upload to the site
	 * 알고리즘 사이트 정답제출용 메인메소드
	 */
	public static void main(String[] args)  {
		Main main = new Main();
		main.calculate();
	}	
	
	/**
	 * print input data for debugging
	 * 자체테스트용 입력값 찍기
	 */
	public void printInput() {
		for(int i = 0; i < areaSize; i++) {
			for(int j = 0; j < areaSize; j++) {
				System.out.print(inputArea[i][j] + " ");
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
		for(int i = 0; i < areaSize; i++) {
			for(int j = 0; j < areaSize; j++) {
				System.out.print(safeArea[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
