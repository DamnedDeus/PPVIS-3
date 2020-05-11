package com.view;

import com.model.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableWithValues {
    private Table tableWithValues;

    public void createTable(Composite shell) {
        tableWithValues = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        tableWithValues.setHeaderVisible(true);
        tableWithValues.setLinesVisible(true);
        tableWithValues.setLayoutData(new RowData(220, 500));
        TableColumn columnWithInputX = new TableColumn(tableWithValues, SWT.NONE);
        columnWithInputX.setText("x");
        columnWithInputX.setWidth(50);

        TableColumn columnWithResult = new TableColumn(tableWithValues, SWT.NONE);
        columnWithResult.setText("F(x)");
        columnWithResult.setWidth(150);

        tableWithValues.setSize(220, 500);
    }

    public void updateTable(Point point) {
        TableItem tableItem = new TableItem(tableWithValues, SWT.NONE);
        tableItem.setText(0, String.valueOf((float)point.getX()/10));
        tableItem.setText(1, String.valueOf((float)point.getY()/10));
    }

    public void removeAll() {
        tableWithValues.removeAll();
    }

}
