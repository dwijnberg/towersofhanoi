
public class Move {
	
	private Disk disk;
	private Pole prevPole;
	
	public Move (Disk d, Pole p) {
		
		disk = d;
		prevPole = p;
		
	}
	
	public Disk getDisk() {
		
		return disk;
		
	}
	
	public Pole getPole() {
		
		return prevPole;
		
	}

}
