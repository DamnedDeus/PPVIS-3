package model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import view.GraphicFunction;
import view.MainForm;

public class StreamsProcessing {

	public Integer x = 0;
	public Double z = 0d;

	public static void run(List<Double> parametrs) throws InterruptedException {	
		GraphicFunction.setStartValueY(2 * parametrs.get(0) + parametrs.get(2));
		List<Point> pointList1 = new ArrayList<Point>(), pointList2 = new ArrayList<Point>();
		
		LinearFunction linearFunction = new LinearFunction(parametrs.get(0), parametrs.get(1), pointList2, 0);
		LinearFunction function = new LinearFunction(parametrs.get(0), parametrs.get(1), parametrs.get(2), pointList1, 1);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(linearFunction);
		
		Future<Boolean> status1 = executorService.submit(function), status2 = executorService.submit(linearFunction);
		status1.isDone();
		status2.isDone();	
		
		PrintTaskTable printTask1 = new PrintTaskTable(function.getComputedValues(), status1);
		Future<Void> printEnded1 = executorService.submit(printTask1);
				
		PrintTask printTask2 = new PrintTask(linearFunction.getComputedValues(), status2);
		Future<Void> printEnded2 = executorService.submit(printTask2);

		while (!printEnded1.isDone() & !printEnded2.isDone()) {
			if (printEnded1.isDone() & printEnded2.isDone()) {
				executorService.shutdownNow();
				break;
			}
		}
		
		MainForm.tableConfig(function.getComputedValues());
		DataProcessing.setComputedValues(function.getComputedValues(), 0);
		DataProcessing.setComputedValues(linearFunction.getComputedValues(), 1);
		executorService.shutdown();
	}
}
