import java.awt.Color;
import java.util.Stack;

import objectdraw.*;

public class Pole {
	
	private FilledRect pole;
	private Stack<Disk> disks;
	private DrawingCanvas canvas;
	private int numDisks;
	
	
	public Pole(double x, double y, double wif, double lenf, DrawingCanvas canvas) {
		
		pole = new FilledRect(x,y,wif,lenf,canvas);
		pole.setColor(Color.black);
		disks = new Stack<Disk>();
		numDisks = 7;
		this.canvas = canvas;
		
	}
	
	public void addDisk(Disk d) {
		
		disks.add(d);
		
	}
	
	public Disk getDisk() {
		
		return disks.lastElement();
		
	}
	
	public boolean removeDisk(Disk d) {
		
		
		return disks.remove(d);
		
	}
	
	
	
	public int createDisks(int max) {
		
		numDisks = max;
		for (int i = max; i > 0; i--) {
			
			Disk d = new Disk(pole.getX()-(40+i*20-pole.getWidth())/2, pole.getY()+pole.getHeight()-(max+1-i)*25, i, this, canvas);
			disks.add(d);
			
		}
		return max;
		
	}
	
	public int createDisks() {
		
		for (int i = numDisks; i > 0; i--) {
			
			Disk d = new Disk(pole.getX()-(40+i*20-pole.getWidth())/2, pole.getY()+pole.getHeight()-(numDisks+1-i)*25, i, this, canvas);
			disks.add(d);
			
		}
		return numDisks;
		
	}
	
	public void reset() {
		
		while(!disks.empty()) {
			
			
			getDisk().getDisk().removeFromCanvas();
			removeDisk(getDisk());
			
		}
		
	}
	

}
