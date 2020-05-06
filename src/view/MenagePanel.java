package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import model.StreamsProcessing;

public class MenagePanel {
	private static Boolean startCheck = false;

	public static void config(Shell shell) {
		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Некорректный ввод");
		errorMessege.setMessage("Некорректный ввод параметров");

		Composite groupsAll = new Composite(shell, SWT.BORDER);
		groupsAll.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		List<Composite> groups = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Composite group1;
			if (i == 0)
				group1 = new Composite(groupsAll, SWT.BORDER);
			else
				group1 = new Composite(groups.get(0), SWT.BORDER);
			group1.setLayout(new RowLayout(SWT.VERTICAL));
			groups.add(group1);
		}

		RowData layoutData = new RowData();
		layoutData.width = 150;

		List<Text> texts = new ArrayList<>();
		List<Label> labels = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Label label1 = new Label(groups.get(1), SWT.NONE);
			label1.pack();
			labels.add(label1);

			Text text1 = new Text(groups.get(1), SWT.BORDER);
			text1.setLayoutData(layoutData);
			text1.setText("");
			text1.pack();
			texts.add(text1);
		}

		labels.get(0).setText("Начальное значение x");
		labels.get(1).setText("Конечное значение x ");
		labels.get(2).setText("Параметр a          ");

		Button startButton = new Button(groups.get(2), SWT.PUSH);
		startButton.setText("Start processing");
		startButton.pack();

		Button stopButton = new Button(groups.get(2), SWT.PUSH);
		stopButton.setText("Stop processing");
		stopButton.pack();
		
		Button plusButton = new Button(groups.get(3), SWT.PUSH);
		plusButton.setText("Увеличить масштаб");
		plusButton.pack();

		Button minusButton = new Button(groups.get(3), SWT.PUSH);
		minusButton.setText("Уменьшить масштаб");
		minusButton.pack();
		
		InfoPanel.panelConfig(groupsAll);
		
		startButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (startCheck == false) {			
					List<Double> parametrs = new ArrayList<>();
					try {
						for (int i = 0; i < 3; i++) {
							Double value = Double.valueOf(texts.get(i).getText());
							parametrs.add(value);
						}
					} catch (NumberFormatException e) {
						errorMessege.open();
						return;
					}
					try {
						GraphicFunction.graphicConfig(parametrs.get(0), parametrs.get(1));
						StreamsProcessing.run(parametrs);
						startCheck = true;
					} catch (InterruptedException e) {
						errorMessege.setMessage("Ошибка при передаче параметров");
						errorMessege.open();
					}
				}
				else {
					errorMessege.setMessage("Функция уже выбрана. Нажмите \"Start processing\" чтобы обновить график.");
					errorMessege.open();
				}
			}
		});
		
		stopButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GraphicFunction.redrawGraphic();
				MainForm.removeTable();
				startCheck = false;
			}
		});
		
		plusButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GraphicFunction.updateGraphic(true);
			}
		});
		
		minusButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GraphicFunction.updateGraphic(false);
			}
		});

	}
}
