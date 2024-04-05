import java.util.*;
public class CLI {
	private String temp;
	
	public void main(String [] args) {
		TowersOfHanoi t = new TowersOfHanoi();
		Scanner s = new Scanner(System.in);
		try {
			int num = Integer.parseInt(s.next());
			while(s.hasNext()) {
				temp = s.next();
				temp.trim();
				t.move(temp.charAt(0), temp.charAt(1));
			}
		} catch(Exception e) {
			
		}
		
	}

}
