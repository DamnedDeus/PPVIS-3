package model;

import java.util.List;

public class DataProcessing {
	private static final Integer length = 256;
	private static final Integer height = 512;
	private static Integer size = 10;
	private static List<Point> computedValues1;
	private static List<Point> computedValues2;
	
	public static void setComputedValues(List<Point> computedValues, int number) {
		if(number == 0)
			computedValues1 = computedValues;
		if(number == 1)
			computedValues2 = computedValues;
	}
	public static List<Point> getComputedValues(int number) {
		if(number == 0)
			return computedValues1;
		if(number == 1)
			return computedValues2;
		return null;
	}
	public static Integer getLength() {
		return length;
	}

	public static Integer getHeight() {
		return height;
	}

	public static Integer grProcessing(Double startValue, Double endValue) {
		for (int index = 8; index > 0; index--) {
			if ((endValue - startValue) <= (length / Math.pow(2, index))) {
				size = (int) Math.pow(2, index);
				return size;
			}
		}	
		size = 1;
		return size;
	}
	

}
