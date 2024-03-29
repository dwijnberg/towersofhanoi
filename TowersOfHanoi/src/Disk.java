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
			disk.setColor(Color);
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
