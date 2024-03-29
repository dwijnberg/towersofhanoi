import objectdraw.FilledRect;
import objectdraw.WindowController;

public class TowersOfHanoi extends WindowController {

	public void begin() {
		
		Pole p1 = new Pole(120, 250, 10, 350, canvas);
		Pole p2 = new Pole(360, 250, 10, 350, canvas);
		Pole p3 = new Pole(600, 250, 10, 350, canvas);


		
	}
	
	
	public static void main(String[] args) {
		
		new TowersOfHanoi().startController(720, 1080);
		
	}
	
}
