package com.view;

import com.controller.Controller;
import com.model.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import static org.eclipse.swt.SWT.SHELL_TRIM;

public class MainWindow {
	private final Display display = new Display();
	private final TableWithValues tableWithValues;
	private final Controller controller;
	private final GraphicWindow graphicWindow;
	private Thread thread;

	public MainWindow() {
		Shell shell = new Shell(display, SHELL_TRIM);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		shell.setSize(1300, 900);
		Composite composite = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		composite.setLayout(rowLayout);

		tableWithValues = new TableWithValues();
		tableWithValues.createTable(composite);

		controller = new Controller(this);

		Button inputBtn = new Button(composite, SWT.PUSH);
		inputBtn.setText("Input information");

		Button drawGraphic = new Button(composite, SWT.PUSH);
		drawGraphic.setText("Draw graphic");

		Button removeGraphic = new Button(composite, SWT.PUSH);
		removeGraphic.setText("Remove graphic");

		Label zoomRatio = new Label(composite, SWT.NONE);

		graphicWindow = new GraphicWindow(display, shell, zoomRatio);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 800;
		gridData.widthHint = 1000;
		graphicWindow.setLayoutData(gridData);

		inputBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableWithValues.removeAll();
				graphicWindow.removeAll();
				controller.getxList().clear();
				inputBtnShell(gridLayout);
			}
		});

		drawGraphic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				thread = new Thread(controller);
				thread.start();
			}
		});

		removeGraphic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableWithValues.removeAll();
				graphicWindow.removeAll();
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private void inputBtnShell(GridLayout gridLayout) {
		Shell inputBorderDialog = new Shell(display, SWT.DIALOG_TRIM);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		inputBorderDialog.setLayout(rowLayout);

		Composite bottomBord = new Composite(inputBorderDialog, SWT.NONE);
		bottomBord.setLayout(gridLayout);

		Label inputBottomBordLabel = new Label(bottomBord, SWT.NONE);
		inputBottomBordLabel.setText(" Bootom x:");

		Text inputBottomBordText = new Text(bottomBord, SWT.NONE);

		Composite topBord = new Composite(inputBorderDialog, SWT.NONE);
		topBord.setLayout(gridLayout);

		Label inputTopBordLabel = new Label(topBord, SWT.NONE);
		inputTopBordLabel.setText("        Top x:");

		Text inputTopBordText = new Text(topBord, SWT.NONE);

		Button inputOkButton = new Button(inputBorderDialog, SWT.PUSH);
		inputOkButton.setText("Input");

		inputOkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!inputBottomBordText.getText().equals("") && !inputTopBordText.getText().equals("")) {
					int xMin = (Integer.parseInt(inputBottomBordText.getText())),
							xMax = Integer.parseInt(inputTopBordText.getText());
					if (xMin >= 0 & xMax <= 50) {
						xMin *= 10;
						xMax *= 10;
						for (int xMassIndex = xMin; xMassIndex <= xMax; xMassIndex++) {
							controller.addValToXList(xMassIndex);
						}
						inputBorderDialog.close();
					}
					else {
						MessageBox messageBox = new MessageBox(inputBorderDialog, SWT.APPLICATION_MODAL);
						messageBox.setMessage("Error value!");
						messageBox.open();
					}
				} else {
					MessageBox messageBox = new MessageBox(inputBorderDialog, SWT.APPLICATION_MODAL);
					messageBox.setMessage("Error input!");
					messageBox.open();
				}
			}
		});

		inputBorderDialog.setSize(400, 200);
		inputBorderDialog.open();
	}

	public void updateShell(int index, int type) {
		Point showingPoint = controller.getPointFromGraphic(index, type);
		if (showingPoint == null)
			return;
		if (type == 1) {
			tableWithValues.updateTable(showingPoint);
			graphicWindow.addPointToPointViewList(new Point(showingPoint.getX(), showingPoint.getY()));
		}
		if (type == 2)
			graphicWindow.anotherAddPointToPointViewList(new Point(showingPoint.getX(), showingPoint.getY()));

	}
}
