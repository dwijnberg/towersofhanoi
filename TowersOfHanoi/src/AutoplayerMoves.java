import java.util.Queue;

import objectdraw.*;

public class AutoplayerMoves extends ActiveObject{
	private History memory;
	private Queue<String[]> key; 
	private boolean stop = false;
	private int pace;
	private boolean loading;
	private Autoplayer iterator;
	
	public AutoplayerMoves(History h, Queue<String[]> k, int p, boolean l, Autoplayer i) {
		memory = h;
		key = k;
		pace = p;
		loading = l;
		if (loading) {
			pause(500);
		}
		iterator = i;
		start();
	}
	
	public void run() {
		while (key.size()>0 && !stop) {
			pause(pace);
			String[] temp = key.remove();
			if (loading) {
				memory.redoLoad(temp);
			} else {
				memory.redo(temp);
			}
		}
		if (!loading) {
			iterator.setFlag();
		}
	}
	
	public void pause() {
		stop = true;
	}
}