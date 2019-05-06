package com.digul.algorithm;

import java.io.FileNotFoundException;

public  enum AlgorithmFactory {
	FLOODED_AREA {
		public Algorithm getAlgorithm() throws FileNotFoundException {
			return new com.digul.algorithm.flooded_area.Main("D:\\file\\flooded_sample.txt");
		}
	},
	WORM_VIRUS {
		public Algorithm getAlgorithm() throws FileNotFoundException {
			return new com.digul.algorithm.worm_virus.Main("D:\\file\\worm_virus.txt");
		}
	};
	
	abstract public Algorithm getAlgorithm() throws FileNotFoundException;

}
