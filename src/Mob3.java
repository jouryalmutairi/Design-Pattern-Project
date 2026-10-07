import java.awt.*;

public class Mob3 extends Enemy{

	
	  public Mob3() {
		   
		  super(3);
	    }
	
  
	   
	   
	   
	  public void draw(Graphics g) {
		  
		    	
		    	g.drawImage(Screen.tileset_mobbb[mobID], x, y, width, height, null);
		    	
		    	
		    	// Health bar
		    	g.setColor(new Color(80,50,50));
		    	g.fillRect(x,y- (healthSpace + healthHeight),  width, healthHeight);
		    	
		    	
		    	g.setColor(new Color(50,180,50));
		    	g.fillRect(x,y- (healthSpace + healthHeight),  health, healthHeight);
		    	
		    	
		    	g.setColor(new Color(0,0,0));
		    	g.drawRect(x,y- (healthSpace + healthHeight),  health-1, healthHeight-1);
		 
	  }
	  
	  
	  
}

