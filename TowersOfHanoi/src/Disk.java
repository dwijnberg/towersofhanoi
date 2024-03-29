import objectdraw.*;
import java.awt.*;
public class Disk extends ActiveObject {
	
	private static final double HEIGHT = 25;
	private FilledRect disk;
	private int size;
	private Pole rod;
	private DrawingCanvas canvas;
	private boolean running;
	
	public Disk(double x, double y, int s, Pole r, DrawingCanvas c) {
		size = s;
		rod = r;
		canvas = c;
		running = false;
		disk = new FilledRect(x,y,(40+size*20),HEIGHT,canvas);
		switch(size) {
		case 1:
			disk.setColor(Color.CYAN);
			break;
		case 2:
			disk.setColor(Color.GREEN);
			break;
		case 3:
			disk.setColor(Color.YELLOW);
			break;
		case 4:
			disk.setColor(Color.RED);
			break;
		case 5: 
			disk.setColor(Color.MAGENTA);
			break;
		case 6: 
			disk.setColor(Color.ORANGE);
			break;
		case 7:
			disk.setColor(Color.GRAY);
			break;
		}
		disk.sendToFront();
	}
	
	public void move(double dx, double dy) {
		disk.move(dx, dy);
	}
	
	public int getSize(){
		return size;
	}
	
	public FilledRect getDisk() {
		return disk;
	}
	
	public Pole getPole() {
		return rod;
	}
	
	public void setPole(Pole r) {
		rod = r;
	}
	
	public void autoTowerShift() {
		running = true;
		start();
	}
	
	public void run() {
		pause(500);
		System.out.println(canvas.getWidth() + " " + canvas.getHeight());
		while (running) {
			
			
			
		}
		
	}

}
