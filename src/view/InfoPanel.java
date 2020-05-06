package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class InfoPanel {
	private static List<Label> labels;

	public static void panelConfig(Composite shell) {
		List<Composite> groups = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Composite group1;
			if (i == 0) {
				group1 = new Composite(shell, SWT.BORDER);
				group1.setLayout(new RowLayout(SWT.VERTICAL));
				groups.add(group1);
				Label name = new Label(groups.get(0), SWT.VERTICAL);
				name.setText("Информация о графике");
				name.pack();
			} else {
				group1 = new Composite(groups.get(0), SWT.BORDER);
				group1.setLayout(new RowLayout(SWT.HORIZONTAL));
				groups.add(group1);
			}
		}

		RowData layoutData = new RowData();
		layoutData.width = 150;

		int[] bufMass = { 1, 2, 2, 3, 3 };
		labels = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Label label1 = new Label(groups.get(bufMass[i]), SWT.NONE);
			label1.pack();
			labels.add(label1);
		}

		List<String> list = new ArrayList<>();
		list.add("0");
		list.add("(3 * x) + 4");
		list.add("2 * x + a");
		updateConfig(list);

		
	}

	public static void updateConfig(List<String> list) {
		labels.get(0).setText("Информация о графике");
		
		final Color red = new Color(labels.get(0).getDisplay(), 0, 255, 0);
		final Color green = new Color(labels.get(0).getDisplay(), 255, 0, 0);

		labels.get(1).setForeground(red);
		labels.get(3).setForeground(green);

		labels.get(1).setText("---");
		labels.get(3).setText("---");

		labels.get(1).addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				red.dispose();
			}
		});

		labels.get(3).addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				green.dispose();
			}
		});
		if (list != null) {
			if (list.size() >= 1)
				labels.get(0).setText("Масштаб " + list.get(0));
			if (list.size() >= 2)
				labels.get(2).setText(" " + list.get(1));
			if (list.size() >= 3)
				labels.get(4).setText(" " + list.get(2));
		}
		labels.get(0).getShell().layout();
	}
}
