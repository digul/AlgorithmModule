package com.digul.algorithm.firebug;

import com.digul.algorithm.Algorithm;

import java.io.*;
import java.util.*;

/**
 * TODO Delete package and extended class when upload this to the site.
 * 알고리즘 사이트에 정답 제출할 때 패키지와 extends Algorithm 삭제 후 제출
 */
public class Main extends Algorithm {
	private boolean debugMode = false;
	private short caveLength;
	private short caveHeight;
	private short[] blocks;

	public Main(String filePath) throws Exception {
		debugMode = true;
		System.setIn(new FileInputStream(filePath));
		scanInput();
	}

	public Main()  throws Exception {
		scanInput();
	}
	//TODO 숫자를 배열에 저장하고, 동굴길이만큼 반복문돌리면서 array[i]가 length보다 작거나큰지(%로) 비교해서 증가시키자.
	protected void scanInput() throws InputException {
		Scanner sc = new Scanner(System.in);
		try {
			caveLength = sc.nextShort();
			if(caveLength < 2 || caveLength > 200000) {
				throw new InputException("caveLength : " + caveLength);
			}
			if(caveLength % 2 != 0) {
				throw new InputException("caveLength : " + caveLength);
			}

			caveHeight = sc.nextShort();
			if(caveHeight < 2 || caveHeight > 500000) {
				throw new InputException("caveHeight : " + caveHeight);
			}
			
			blocks =  new short[caveLength];
			
			short i = 0;
			while(sc.hasNext()) {
				short size = sc.nextShort();
				if(size >= caveHeight) {
					throw new InputException("size : " + size);
				}
				
				blocks[i++] = size;
				
				if(i == caveLength) break;
			}
			
		} finally {
			sc.close();
		}
		
		if(debugMode) {
			printInput();
		}

	}

	public void calculate() {
		short minBlocked = caveLength;
		short minBlockedCnt = 0;
		for(short i = 0; i < caveHeight; i++) {
			short block = 0;
			for(short j = 0; j < caveLength; j++ ) {
				if(j % 2 == 0) {
					if(blocks[j] > i) block++;
				} else {
					if(caveHeight - blocks[j] <= i) block++;
				}
			}
			if(block == minBlocked) {
				minBlockedCnt++;
			}
			if(block < minBlocked) {
				minBlockedCnt = 1;
				minBlocked = block;
			}
		}
		System.out.println(minBlocked + " " + minBlockedCnt);
	}
	

	/**
	 * print input data for debugging
	 * 자체테스트용 입력값 찍기
	 */
	public void printInput() {
		for(int i = 0; i < caveLength; i++) {
			System.out.print(blocks[i] + " ");
		}
		System.out.println();
	}

	/**
	 * print processed data for debugging
	 * 자체테스트용 중간값 찍기
	 */
	public void printProcess() {
	}
	/**
	 * main method for upload to the site
	 * 알고리즘 사이트 정답제출용 메인메소드
	 */
	public static void main(String[] args)   throws Exception  {
		try {
			Main main = new Main();
			main.calculate();
		} catch(InputException e) {
			System.out.println("[Runtime Exception] ".concat(e.getMessage()));
		}
	}	

}
