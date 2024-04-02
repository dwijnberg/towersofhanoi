import java.awt.Color;
import java.util.Stack;

import objectdraw.*;

public class Pole {
	
	FilledRect pole;
	Stack<Disk> disks;
	DrawingCanvas canvas;
	
	public Pole(double x, double y, double wif, double lenf, DrawingCanvas canvas) {
		
		pole = new FilledRect(x,y,wif,lenf,canvas);
		pole.setColor(Color.black);
		disks = new Stack<Disk>();
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
		
		for (int i = max; i > 0; i--) {
			
			Disk d = new Disk(pole.getX()-(40+i*20-pole.getWidth())/2, pole.getY()+pole.getHeight()-(max+1-i)*25, i, this, canvas);
			disks.add(d);
			
		}
		return max;
		
	}
	
	public void reset() {
		
		while(!disks.empty()) {
			
			
			getDisk().getDisk().removeFromCanvas();
			removeDisk(getDisk());
			
		}
		
	}
	

}
