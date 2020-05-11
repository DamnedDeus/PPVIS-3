package com.controller;

import com.model.Graphic;
import com.model.Point;
import com.view.MainWindow;
import org.eclipse.swt.widgets.Display;

import java.util.ArrayList;
import java.util.List;

public class Controller implements Runnable {
	private final List<Integer> xList;
	private final Graphic graphic;
	private final MainWindow mainWindow;
	private Point addingPoint;

	public Controller(MainWindow mainWindow) {
		graphic = new Graphic();
		this.mainWindow = mainWindow;
		xList = new ArrayList<>();
	}

	@Override
	public void run() {
		for (Integer x : xList) {
			addPoint(calculateY(x, 1), 1);
			addPoint(calculateY(x, 2), 2);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Point calculateY(int x, int type) {
		if (type == 1)
			addingPoint = new Point(x,(int)(2* Math.pow((x/10)-5, 2)));
		if (type == 2)
			addingPoint = new Point(x, x + 10);
		return addingPoint;
	}
	
	// Сумма ряда незначительно изменяется
	private int anotherCalculateY(int xN) {
		double x = xN/10;
		double y = 0;
		double tempY = 0;
		for (int recursIndex = 0; recursIndex < 100000; recursIndex++) {
			int result = 1;
			for (int i = 1; i <= recursIndex; i++) {
				result = result * i;
			}
			tempY = Math.pow(-1, recursIndex) * Math.pow(x, recursIndex) / (result*result);
			if (tempY <= -0.0001|| tempY <= 0.0001)
				break;
			else {
				y = y + tempY;
			}
		}
		return (int)y;
	}

	public void addPoint(Point point, int type) {
		Display.getDefault().asyncExec(() -> {
			int index = graphic.addValueTo(point, type);
			mainWindow.updateShell(index, type);
		});
	}

	public List<Integer> getxList() {
		return xList;
	}

	public void addValToXList(int x) {
		xList.add(x);
	}

	public Graphic getGraphic() {
		return graphic;
	}

	public Point getPointFromGraphic(int index, int type) {
		return graphic.getPoint(index, type);
	}
}
