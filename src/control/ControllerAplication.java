package control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import view.GraphicFunction;
import view.MainForm;

public class ControllerAplication {
	private static Boolean keyCheak = false;
	public static void controll() {
		Display display = MainForm.getShell().getDisplay();	
		
		display.addFilter(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.CTRL) != 0) {
					keyCheak = true;
					System.out.println("-");
				}
					
			}
		});
		
		display.addFilter(SWT.KeyUp, new Listener() {
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.CTRL) != 0) {
					keyCheak = false;
					System.out.println("!");
				}			
			}
		});
		
		display.addListener(SWT.MouseWheel, new Listener() {
            public void handleEvent(Event event) {
                int wheelCount = event.count;
                System.out.println(keyCheak);
                wheelCount = (int) Math.ceil(wheelCount / 3.0f);
                while (wheelCount < 0  & keyCheak == true) {
                	GraphicFunction.updateGraphic(true);
                }

                while (wheelCount > 0  & keyCheak == true) {
                	GraphicFunction.updateGraphic(false);
                }
            }
        });
	}
}
