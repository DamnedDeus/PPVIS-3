package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import control.ControllerAplication;
import model.Point;

public class MainForm {
	private static Shell shell;
	private static Table table;
	// private static Integer index = 0;

	public static Table getTable() {
		return table;
	}

	public static void configContainer() {
		Display display = new Display();
		//shell = new Shell(display);
		shell = new Shell(display, SWT.DIALOG_TRIM);
		shell.setText("SWT");
		shell.setSize(1300, 870);

		// RowLayout rowLayout = new RowLayout();
		// rowLayout.spacing = 50;
		// rowLayout.marginLeft = 10;
		// rowLayout.marginTop = 10;
		// shell.setLayout(rowLayout);
		shell.setLayout(new FillLayout());

		table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		List<Point> points = null;
		MainForm.tableConfig(points);

		MenagePanel.config(shell);
		GraphicFunction.runConfig();
		ControllerAplication.controll();
		
		 
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
		display.dispose();
	}

	public static void tableConfig(List<Point> points) {
		//System.out.println("---------------------------------");
		//System.out.println("|||||||||||||||||||||||||||||||||");
		table.removeAll();

		//if (points != null)
		//	System.out.println(points.size());

		String[] titles = { "номер", "x", "y" };
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(MainForm.table, SWT.CENTER);
			column.setAlignment(SWT.CENTER);
			column.setText(titles[loopIndex]);
		}

		if (points != null) {
			for (int index = 0; index < points.size(); index++) {
				if (points.get(index) != null) {
					TableItem item = new TableItem(table, SWT.NULL);
					item.setText(0, Integer.toString(index));
					item.setText(1, Double.toString(points.get(index).getX()));
					item.setText(2, Double.toString(points.get(index).getY()));
				}
			}
		}
		for (int index = 0; index < titles.length; index++)
			MainForm.table.getColumn(index).pack();

		MainForm.table.getShell().layout();

		//System.out.println("|||||||||||||||||||||||||||||||||");
		//System.out.println("---------------------------------");
	}

	public static Shell getShell() {
		return MainForm.shell;
	}

	public static void removeTable() {
		table.removeAll();
	}
}
