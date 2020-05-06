package model;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import view.GraphicFunction;

public class PrintTaskTable implements Callable<Void> {
	private final List<Point> data;
	private final Future<Boolean> status;

	public PrintTaskTable(List<Point> data, Future<Boolean> status) {
		this.data = data;
		this.status = status;
	}

	@Override
	public Void call() {
		for (int i = 0; i < data.size() && (!status.isDone() || !data.isEmpty()); i++) {
			Point point = data.get(i);
			if (point != null) {
				//System.out.println("! " + point);
				GraphicFunction.getCanvas().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						GraphicFunction.addPoint("red", point);
					}
				});
				// GraphicFunction.addPoint("red", point);
			}
		}
		// MainForm.tableConfig(data);
		return null;
	}
}