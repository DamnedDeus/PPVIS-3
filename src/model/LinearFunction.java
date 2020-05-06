package model;

//import com.sun.source.doctree.SerialDataTree;
import java.util.List;
import java.util.concurrent.Callable;

import view.GraphicFunction;

public class LinearFunction implements Callable<Boolean> {
	private final Double startValue;
	private final Double endValue;
	private Double currentValue;
	private Double argument;
	private final List<Point> computedValues;
	private final int typeFunction;

	public LinearFunction(Double startValue, Double endValue, Double argument, List<Point> computedValues,
			int typeFunction) {
		this.startValue = startValue;
		this.endValue = endValue;
		this.currentValue = startValue;
		this.computedValues = computedValues;
		this.typeFunction = typeFunction;
		this.argument = argument;
	}

	public LinearFunction(Double startValue, Double endValue, List<Point> computedValues, int typeFunction) {
		this.startValue = startValue;
		this.endValue = endValue;
		this.currentValue = startValue;
		this.computedValues = computedValues;
		this.typeFunction = typeFunction;
		this.argument = 0d;
	}

	@Override
	public Boolean call() {
		while (currentValue <= endValue + 0.1) {
			this.apply(currentValue);
			currentValue += 0.1;
		}
		return true;
	}

	public Point apply(Double x) {
		Double value = 0d;
		if (this.typeFunction == 0)
			value = (3 * x) + 4;

		else if (this.typeFunction == 1)
			value = 2 * x + this.argument;

		Point point = new Point(x, value);

		computedValues.add(point);

		return point;
	}

	public List<Point> getComputedValues() {
		return this.computedValues;
	}
	/*
	 * public Double getStartValue() { return this.startValue; }
	 * 
	 * public Double getEndValue() { return this.endValue; }
	 */
}
