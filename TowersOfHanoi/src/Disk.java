import objectdraw.*;
import java.awt.*;
public class Disk extends ActiveObject {
	
	private static final int HEIGHT = 25;
	private FilledRect disk;
	private int size;
	private Pole rod;
	private DrawingCanvas canvas;
	private boolean running;
	
	public Disk(int x, int y, int s, Pole r, DrawingCanvas c) {
		size = s;
		rod = r;
		canvas = c;
		disk = new FilledRect(x,y,(40+size*20),HEIGHT,canvas);
		switch(size) {
		case 1:
			disk.setColor(Color.CYAN);
		case 2:
			disk.setColor(Color.GREEN);
		case 3:
			disk.setColor(Color.YELLOW);
		case 4:
			disk.setColor(Color.RED);
		case 5: 
			disk.setColor(Color.MAGENTA);
		case 6: 
			disk.setColor(Color.ORANGE);
		case 7:
			disk.setColor(Color.GRAY);
		
		}
		
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
	
	public void run() {
		while (running) {
			
		}
		
	}

}
