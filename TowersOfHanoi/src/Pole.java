import java.awt.Color;
import java.util.Stack;

import objectdraw.*;

public class Pole {
	
	private FilledRect pole;
	private Stack<Disk> disks;
	private DrawingCanvas canvas;
	private int numDisks;
	private Integer poleNum;
	private FramedRect poleHitbox;
	
	
	public Pole(double x, double y, double wif, double lenf, Integer poleNum, DrawingCanvas canvas) {
		
		pole = new FilledRect(x,y,wif,lenf,canvas);
		pole.setColor(Color.black);
		poleHitbox = new FramedRect(x-85,y,180,lenf,canvas);
		poleHitbox.hide();
		disks = new Stack<Disk>();
		numDisks = 7;
		this.poleNum = poleNum;
		this.canvas = canvas;
		
	}
	
	public void addDisk(Disk d) {
		
		disks.add(d);
		System.out.print(disks.size());
		
	}
	
	public FilledRect getPole() {
		
		return pole;
		
	}
	
	public Disk getDisk() {
		
		return disks.peek();
		
	}
	
	public boolean removeDisk(Disk d) {
		
		
		return disks.remove(d);
		
	}
	
	public int numDisks() {
		
		return disks.size();
		
	}
	
	public void moveDisk(Disk d, Pole newPole) {
		
		if (disks.size() > 0 && disks.peek().equals(d) && !newPole.equals(this)) {
			
			FilledRect disk = d.getDisk();
			disk.moveTo(newPole.getPole().getX()-(40+d.getSize()*20-newPole.getPole().getWidth())/2, pole.getY()+pole.getHeight()-25*(newPole.getDisks().size()+1));
			disks.remove(d);
			d.setPole(newPole);
			
			newPole.addDisk(d);
			
		} else if (newPole.getPole().getLocation().equals(pole.getLocation())) {
			
			FilledRect disk = d.getDisk();
			disk.moveTo(newPole.getPole().getX()-(40+d.getSize()*20-newPole.getPole().getWidth())/2, pole.getY()+pole.getHeight()-25*(disks.size()));
			
		}
		
	}
	
	public int createDisks(int max) {
		
		numDisks = max;
		for (int i = max; i > 0; i--) {
			
			Disk d = new Disk(pole.getX()-(40+i*20-pole.getWidth())/2, pole.getY()+pole.getHeight()-(max+1-i)*25, i, this, canvas);
			disks.add(d);
			
		}
		return max;
		
	}
	
	public void createDisk(int size) {
					
			Disk d = new Disk(pole.getX()-(40+size*20-pole.getWidth())/2, pole.getY()+pole.getHeight()-(disks.size()+1)*25, size, this, canvas);
			disks.add(d);
		
		
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
	
	
	public boolean diskContains(Location point) {
		if(disks.size() == 0) {
			return false;
		} else {
			
			return disks.peek().getDisk().contains(point);
		}
		
	}
	
	public boolean contains(Location point) {
		return(poleHitbox.contains(point));
	}
	
	public Stack<Disk> getDisks() {
		
		return disks;
		
	}
	
	public String toString() {
		
		return poleNum.toString();
		
		
	}
	
	public boolean addable(Disk d) {
		if(disks.size()==0) {
			return true;
		} else {
			return(d.getSize()<=disks.peek().getSize());
		}
	}
	

}
