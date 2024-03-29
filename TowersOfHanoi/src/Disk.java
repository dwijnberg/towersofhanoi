import objectdraw.*;
import java.awt.*;
public class Disk extends ActiveObject {
	
	private static final int HEIGHT = 25;
	private FilledRect disk;
	private int size;
	private DrawingCanvas canvas;
	
	public Disk(double x, double y, double s, DrawingCanvas c) {
		
		disk = new FilledRect(x,y,s,HEIGHT,c);
		
	}
	
	

}
