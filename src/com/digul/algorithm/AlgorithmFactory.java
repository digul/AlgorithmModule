package com.digul.algorithm;

import java.io.FileNotFoundException;

public  enum AlgorithmFactory {
	FLOODED_AREA {
		public Algorithm getAlgorithm() {
			try {
				return new com.digul.algorithm.flooded_area.Main("D:\\file\\flooded_sample.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
	};
	
	abstract public Algorithm getAlgorithm();

}
