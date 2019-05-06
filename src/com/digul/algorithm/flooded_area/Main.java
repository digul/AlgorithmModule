package com.digul.algorithm.flooded_area;
import com.digul.algorithm.Algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 알고리즘 사이트에 정답 제출할 때 패키지와 extends Algorithm 삭제 후 제출
 */
public class Main extends Algorithm {
	private final int maxHeight = 100;
	private int areaSize;
	private int[][] inputArea;
	private char[][] safeArea;
	private boolean debugMode = false;

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

	public String calculate() {
		
		 int maxSafeAreaCnt = 1;
		 
		for (int floodedHeight = 0; floodedHeight < maxHeight; floodedHeight++) {
			safeArea = new char[areaSize][areaSize];
			
			int safeAreaCnt = 0;
			
			for(int row = 0; row < areaSize; row++) {
				for(int col = 0; col < areaSize; col++) {
					if(safeArea[row][col] != 0) {	// 이미 탐색된 영역
						continue;
					}
					if(floodedHeight >= inputArea[row][col] ) {	
						safeArea[row][col] = 'X';		// 잠긴 지역
						continue;
					}
					
					findSafeArea(row, col, floodedHeight);	// 탐색시작
					safeAreaCnt++;
				}
			}

			if(debugMode) {
				System.out.println(safeAreaCnt);
				printProcess();
			}
			
			if(safeAreaCnt > maxSafeAreaCnt) {
				maxSafeAreaCnt = safeAreaCnt;	// 최대값 갱신
			}

			
			if(safeAreaCnt == 0	) break;	// 모두 잠기면 그만			
		}
		return String.valueOf(maxSafeAreaCnt);
	}
	
private void  findSafeArea(int row, int col, int floodedHeight) {
		
		// 영역을 벗어나면 중단
		if(row < 0) return;	
		if(row == areaSize) return;
		if(col < 0) return;
		if(col == areaSize) return;

		//TODO 여기가 문제...  floodedHeight = 4인 경우 아래로 연결되는 영역을 찾지를 못한다.
		if(safeArea[row][col] != 0) {
			return;		// 이미 탐색한 지역이면 중단 
		}
		
		if(inputArea[row][col] <= floodedHeight) {
			safeArea[row][col] = 'X';	// 침수지역
			return;								// 탐색 중단
		}
		
		
		safeArea[row][col] = 'O';	// 안전영역
		
		
		
		// 미탐색 지역 탐색
		findSafeArea(row+1, col, floodedHeight);
		findSafeArea(row-1, col, floodedHeight);
		findSafeArea(row, col+1, floodedHeight);
		findSafeArea(row, col-1, floodedHeight);
	}
	
	/**
	 * 알고리즘 사이트 정답제출용 메인메소드
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args)  {
		Main main = new Main();
		System.out.println(main.calculate());
	}	
	
	/**
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
