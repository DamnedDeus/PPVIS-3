package view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

import model.DataProcessing;
import model.Point;

public class GraphicFunction {
	private static Canvas canvas;
	private static ScrolledComposite graphicGroups;
	private static Composite hes;
	private static Image red;
	private static Image green;
	private static Integer size = 10;
	private static Double startValue = 0d;
	private static Double startValueY = 0d;
	private static Double endValue;
	private static Integer mode = 1;
	
	private static Integer xs = 0;
	private static Integer ys = 0;
	
	public static Integer getMode() {
		return mode;
	}
	public static Canvas getCanvas() {
		return canvas;
	}

	public static void runConfig() {
		Shell shell = MainForm.getShell();
		red = new Image(shell.getDisplay(), "G:/PPvIS/laba3/laba3/src/resources/image/redPoint.png");
		green = new Image(shell.getDisplay(), "G:/PPvIS/laba3/laba3/src/resources/image/greenPoint.png");
		hes = new Composite(shell, SWT.BORDER);
		hes.setLayout(new RowLayout());
		hes.setLayoutData(new RowData(DataProcessing.getLength(), DataProcessing.getHeight())); // ????
		graphicPreConfig();
	}

	public static void graphicPreConfig() {
		// SWT.H_SCROLL | SWT.V_SCROLL
		//DataProcessing.getLength()*mode, DataProcessing.getHeight()*mode

	    
		graphicGroups = new ScrolledComposite(hes, SWT.V_SCROLL | SWT.H_SCROLL | SWT.NONE);
		graphicGroups.setLayout(new GridLayout());
		graphicGroups.setAlwaysShowScrollBars(true);
		graphicGroups.setExpandHorizontal(true);
		graphicGroups.setExpandVertical(true);
	    graphicGroups.setMinSize(DataProcessing.getLength(), DataProcessing.getHeight()); 
	    //graphicGroups.setSize(DataProcessing.getLength()*mode, DataProcessing.getHeight()*mode);
		//graphicGroups.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true));
		
		canvas = new Canvas(graphicGroups,   SWT.NONE | SWT.NO_REDRAW_RESIZE | SWT.BORDER);
		RowLayout layout = new RowLayout(SWT.HORIZONTAL | SWT.VERTICAL); 
	    layout.wrap = false; 
	    
	    //composite.setLayout(layout);
		/*
		GridData data = new GridData();
		data.widthHint = DataProcessing.getLength()*mode;
		data.heightHint = DataProcessing.getHeight()*mode;
	    data.grabExcessHorizontalSpace = true;
	    data.grabExcessVerticalSpace = true;
	    composite.setLayoutData(data);*/
		/*
		GridData compositeData = new GridData(GridData.FILL, GridData.FILL, true, true); 
		compositeData.heightHint = DataProcessing.getLength()*mode;
		compositeData.minimumHeight = DataProcessing.getHeight()*mode;
		composite.setLayoutData(compositeData); 
		*/
		canvas.setLayoutData(new RowData(DataProcessing.getLength()*mode, DataProcessing.getHeight()*mode));
		
		graphicGroups.setContent( canvas );
		
		
		/*
		ScrollBar hBar = canvas.getHorizontalBar();
		ScrollBar vBar = canvas.getVerticalBar();
		
        hBar.setMaximum(DataProcessing.getLength()*mode);
        hBar.setMinimum(0);
        
        vBar.setMaximum(DataProcessing.getHeight()*mode);
        vBar.setMinimum(0);
        
        //hBar.setThumb(Math.min(2120, client.width));
        //vBar.setThumb(Math.min(2000, client.height));      
        hBar.addListener(SWT.Selection, new Listener() {  	 
            @Override
            public void handleEvent(Event event) {
                int hSelection = -hBar.getSelection();
                int destX = hSelection - xs;
                canvas.scroll(destX, 0, 0, 0, DataProcessing.getLength()*mode, DataProcessing.getHeight()*mode, false);
                xs = hSelection;
            }
 
        });
        
        vBar.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                int vSelection = -vBar.getSelection();
                int destY = vSelection - ys;
                canvas.scroll(0, destY, 0, 0, DataProcessing.getLength()*mode, DataProcessing.getHeight()*mode, false);
                ys = vSelection;
            }
 
        });
        */
        /*
        scrolledComposite.addListener(SWT.MouseWheel, new Listener() {
            public void handleEvent(Event event) {
                int wheelCount = event.count;
                wheelCount = (int) Math.ceil(wheelCount / 3.0f);
                while (wheelCount < 0) {
                    scrolledComposite.getVerticalBar().setIncrement(4);
                    wheelCount++;
                }

                while (wheelCount > 0) {
                    scrolledComposite.getVerticalBar().setIncrement(-4);
                    wheelCount--;
                }
            }
        });
        scroll.addListener(SWT.MouseWheel, new Listener() {
            public void handleEvent(Event event) {
            	scroll.getVerticalBar().setIncrement(event.csount*3);
            }
        });
        */
		hes.layout();
		canvas.redraw();
	}

	public static void graphicConfig(Double startVal, Double endVal) {
		startValue = startVal;
		endValue = endVal;
		size = DataProcessing.grProcessing(startValue, endValue);

		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent event) {
				event.gc.drawLine(10 + size, 15, 10 + size, 20);
				event.gc.drawText("1", 10 + size, 0);

				event.gc.drawLine(10, 15 + size, 15, 15 + size);
				event.gc.drawText("1", 2, 0 + size);

				event.gc.drawText(Double.toString(startVal), 2, 0);
				// event.gc.drawText("S", 0, 0);
			}
		});
		List<String> list = new ArrayList<>();
		list.add(Integer.toString(mode));
		InfoPanel.updateConfig(list);
		drawAxis();
		canvas.redraw();
	}

	public static void addPoint(String color, Point point) {
		Integer x = (int) ((point.getX() - startValue) * size), y = (int) ((point.getY() - startValueY) * size);
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent event) {
				/*
				if (color.equals("red") & ((10 + x) < xOfEnd) & ((15 + y) < yOfEnd) & ((10 + x) > 10) & ((15 + y) > 15))
					event.gc.drawImage(red, 10 + x, 15 + y);
				if (color.equals("green") & ((10 + x) < xOfEnd) & ((15 + y) < yOfEnd) & ((10 + x) > 10)
						& ((15 + y) > 15))
					event.gc.drawImage(green, 10 + x, 15 + y);
					*/
				if (color.equals("red")  & ((10 + x) > 10) & ((15 + y) > 15))
					event.gc.drawImage(red, 10 + x, 15 + y);
				if (color.equals("green")  & ((10 + x) > 10) & ((15 + y) > 15))
					event.gc.drawImage(green, 10 + x, 15 + y);
			}
		});
		canvas.redraw();
	}

	private static void drawAxis() {
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent event) {
				event.gc.drawLine(10, 15, 10, DataProcessing.getHeight());
				event.gc.drawLine(10, DataProcessing.getHeight(), 13, DataProcessing.getHeight() - 3);
				event.gc.drawLine(10, DataProcessing.getHeight(), 7, DataProcessing.getHeight() - 3);

				event.gc.drawLine(10, 15, DataProcessing.getLength(), 15);
				event.gc.drawLine(DataProcessing.getLength(), 15, DataProcessing.getLength() - 3, 18);
				event.gc.drawLine(DataProcessing.getLength(), 15, DataProcessing.getLength() - 3, 12);

				event.gc.drawText("Y", 0, DataProcessing.getHeight() - 18);
				event.gc.drawText("X", DataProcessing.getLength() - 12, 0);
				
			}
		});
		canvas.redraw();
	}

	public static void redrawGraphic() {
		mode = 1;
		canvas.dispose();
		graphicGroups.dispose();
		graphicPreConfig();
	}

	public static void updateGraphic(Boolean modeClick) {
		List<Point> list1 = DataProcessing.getComputedValues(0), list2 = DataProcessing.getComputedValues(1);
		if (list1 == null | list2 == null)
			return;
		if (modeClick == true & mode < 8) {
			mode *= 2;
			canvas.dispose();
			graphicGroups.dispose();
			graphicPreConfig();
			graphicConfig(startValue / 2, endValue / 2);
		}
		else if (modeClick == false & mode > 1) {
			mode = mode / 2;
			canvas.dispose();
			graphicGroups.dispose();
			graphicPreConfig();
			graphicConfig(startValue * 2, endValue * 2);
		}
		else
			return;
	
		System.out.println(list1.size() + " " + list2.size() + " " + mode);

		for (int i = 0; i < list1.size(); i++)
			if (list1.get(i) != null)
				addPoint("red", list1.get(i));
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i) != null)
				addPoint("green", list2.get(i));
		}
		hes.layout();
		canvas.redraw();
	}

	public static void setStartValueY(Double value) {
		startValueY = value;
	}
}
