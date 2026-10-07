import java.awt.*;

public class Mob extends Enemy{
	
	
	  public Mob() {
		  
		  super(20);
	  	}
	
    
	   
		
	   
	  public void draw(Graphics g) {
		  
		    	
		    	g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null); // mobun boyutu
		    	
		    	
		    	// Health bar, yeşil
		    	g.setColor(new Color(80,50,50));
		    	g.fillRect(x,y- (healthSpace + healthHeight),  width, healthHeight);
		    	
		    	
		    	g.setColor(new Color(50,180,50));// health bar , kırmızı
		    	g.fillRect(x,y- (healthSpace + healthHeight),  health, healthHeight);
		    	
		    	
		    	g.setColor(new Color(0,0,0));// health bar // health barın çerçevesi
		    	g.drawRect(x,y- (healthSpace + healthHeight),  health-1, healthHeight-1);
		 
	  }
	  
	  
	  
}









