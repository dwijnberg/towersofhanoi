import objectdraw.FilledRect;
import objectdraw.WindowController;

public class TowersOfHanoi extends WindowController {

	public void begin() {
		
		Pole p1 = new Pole(120, 250, 10, 350, canvas);
		p1.createDisks();
		Pole p2 = new Pole(360, 250, 10, 350, canvas);
		Pole p3 = new Pole(600, 250, 10, 350, canvas);
		int numOfMoves = 0;


		
	}
	
	
	public static void main(String[] args) {
		
		new TowersOfHanoi().startController(720, 1080);
		
	}
	
}
