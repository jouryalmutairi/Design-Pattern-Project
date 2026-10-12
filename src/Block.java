
import java.awt.*;

public class Block extends Rectangle{
	
	
	public Rectangle towerSquare;
	public Rectangle towerSquare2; // 2.kule
	public Rectangle towerSquare3; // 3.kule
	
	
	public int towerSquareSize = 130;   //tower range, ne kadar uzağa vurabilir.
	public int towerSquareSize2= 130;   //2. kule menzil
	public int towerSquareSize3= 200;  //3. kule menzil
	public int groundID;
	public int airID;
	public int loseTime=100, loseFrame =0; // hasar vurma aralığı, kulelerin
	
	
	public int shotMob = -1;// kulelerin alanındaki mobları vurması için
	public boolean shootingEnemy = false;
	
	
	
	public Block(int x, int y, int width, int height, int groundID, int airID) { // kule menzillerinin oyunun içinde tanımlanması
		
		setBounds(x,y,width, height);
		towerSquare = new Rectangle(x-(towerSquareSize/2), y-(towerSquareSize/2), width+(towerSquareSize), height+(towerSquareSize));
		towerSquare2 = new Rectangle(x-(towerSquareSize2/2), y-(towerSquareSize2/2), width+(towerSquareSize2), height+(towerSquareSize2));//2. kule
		towerSquare3= new Rectangle(x-(towerSquareSize3/2), y-(towerSquareSize3/2), width+(towerSquareSize3), height+(towerSquareSize3));//2. kule 
		this.groundID = groundID;
		this.airID = airID;
	} 
	
	public void draw(Graphics g) { // tileset air ve ground  içindeki png leri koyuyor..
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		
		if(airID != Value.airAir) {
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
			
			
			
			
			
			
		}
		
		
	}
	
	public void physic() {

    Rectangle activeRange = towerSquare;

    if (airID == Value.airTowerLaser2) {
        activeRange = towerSquare2;
    }
    else if (airID == Value.airTowerLaser3) {
        activeRange = towerSquare3;
    }

    // Check whether the current target is still valid and in range
    if (shotMob != -1
            && Screen.enemies[shotMob].inGame
            && activeRange.intersects(Screen.enemies[shotMob])) {

        shootingEnemy = true;

    } else {

        shootingEnemy = false;
        shotMob = -1;
    }

    // Find a new enemy if there is no current target
    if (!shootingEnemy) {

        if (airID == Value.airTowerLaser
                || airID == Value.airTowerLaser2
                || airID == Value.airTowerLaser3) {

            for (int i = 0; i < Screen.enemies.length; i++) {

                if (Screen.enemies[i].inGame
                        && activeRange.intersects(Screen.enemies[i])) {

                    shootingEnemy = true;
                    shotMob = i;
                    break;
                }
            }
        }
    }

    // Attack the selected enemy
    if (shootingEnemy && shotMob != -1) {

        if (loseFrame >= loseTime) {

            if (airID == Value.airTowerLaser) {
                Screen.enemies[shotMob].loseHealth(2);
            }
            else if (airID == Value.airTowerLaser2) {
                Screen.enemies[shotMob].loseHealth(4);
            }
            else if (airID == Value.airTowerLaser3) {
                Screen.enemies[shotMob].loseHealth(10);
            }

            loseFrame = 0;

        } else {
            loseFrame++;
        }

        if (Screen.enemies[shotMob].isDead()) {

            shootingEnemy = false;
            shotMob = -1;

            Screen.killed++;
            Screen.hasWon();
        }
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getMoney(int mobID) {
		
		 
			 Screen.coinage += Value.deathReward[mobID]; // moblar öldüğünde parayı asıl coinimize ekliyor
		 
		
	}
	
public void fight(Graphics g) {

    // Draw tower ranges in debug mode
    if (Screen.isDebug) {

        if (airID == Value.airTowerLaser) {
            g.drawRect(
                towerSquare.x,
                towerSquare.y,
                towerSquare.width,
                towerSquare.height
            );
        }

        else if (airID == Value.airTowerLaser2) {
            g.drawRect(
                towerSquare2.x,
                towerSquare2.y,
                towerSquare2.width,
                towerSquare2.height
            );
        }

        else if (airID == Value.airTowerLaser3) {
            g.drawRect(
                towerSquare3.x,
                towerSquare3.y,
                towerSquare3.width,
                towerSquare3.height
            );
        }
    }

    // Draw laser only when there is a valid target
    if (shootingEnemy && shotMob != -1) {

        if (airID == Value.airTowerLaser) {
            g.setColor(new Color(255, 255, 0));
        }

        else if (airID == Value.airTowerLaser2) {
            g.setColor(new Color(0, 153, 0));
        }

        else if (airID == Value.airTowerLaser3) {
            g.setColor(new Color(51, 153, 255));
        }

        g.drawLine(
            x + (width / 2),
            y + (height / 2),
            Screen.enemies[shotMob].x
                + (Screen.enemies[shotMob].width / 2),
            Screen.enemies[shotMob].y
                + (Screen.enemies[shotMob].height / 2)
        );
    }
}		
	}
	
	

