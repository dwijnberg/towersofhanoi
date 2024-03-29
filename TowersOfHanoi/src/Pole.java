import java.awt.Color;
import java.util.Stack;

import objectdraw.*;

public class Pole {
	
	FilledRect pole;
	Stack<Disk> disks;
	
	public Pole(double x, double y, double wif, double lenf, DrawingCanvas canvas) {
		
		pole = new FilledRect(x,y,wif,lenf,canvas);
		pole.setColor(Color.black);
		disks = new Stack<Disk>();
		
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

}
