package ascend;

import java.awt.event.KeyEvent;

public class KeyboardInput {
	
	private int dx;
	private int x;
	private int dy;
	private int y;
	
	public void move(){
		x += dx;
		y += dy;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT){
				dx = -1;
			}
			
			if(key == KeyEvent.VK_RIGHT){
				dx = 1;
			}
			
			if(key == KeyEvent.VK_UP){
				dy = -1;
			}
			
			if(key == KeyEvent.VK_DOWN){
				dy = 1;
				
			}
	
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(key == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
		if(key == KeyEvent.VK_UP){
			dy = 0;
		}
		
		if(key == KeyEvent.VK_DOWN){
			dy = 0;
			
		}
		
	}

}
