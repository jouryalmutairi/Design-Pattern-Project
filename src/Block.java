
import java.awt.*;

public class Block extends Rectangle{
	
	
	public int groundID;
	public int airID;
	public int loseTime=100, loseFrame =0; // hasar vurma aralığı, kulelerin
	private Tower tower;
	private int configuredAirID = -1;

	public int shotMob = -1;// kulelerin alanındaki mobları vurması için
	public boolean shootingEnemy = false;
	
	
	
	public Block(int x, int y, int width, int height, int groundID, int airID) { // kule menzillerinin oyunun içinde tanımlanması
		
		setBounds(x,y,width, height);
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

    configureTower();

    if (tower == null) {
        return;
    }

    int range = tower.getRange();

    Rectangle activeRange = new Rectangle(
        x - (range / 2),
        y - (range / 2),
        width + range,
        height + range
    );

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

        for (int i = 0; i < Screen.enemies.length; i++) {

            if (Screen.enemies[i].inGame
                    && activeRange.intersects(Screen.enemies[i])) {

                shootingEnemy = true;
                shotMob = i;
                break;
            }
        }
    }

    // Attack the selected enemy
    if (shootingEnemy && shotMob != -1) {

        if (loseFrame >= loseTime) {

            tower.attack(Screen.enemies[shotMob]);

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

    Screen.coinage += Value.deathReward[mobID];
}

public void fight(Graphics g) {

    configureTower();

    if (tower == null) {
        return;
    }

    int range = tower.getRange();

    Rectangle activeRange = new Rectangle(
        x - (range / 2),
        y - (range / 2),
        width + range,
        height + range
    );

    // Draw tower range in debug mode
    if (Screen.isDebug) {
        g.drawRect(
            activeRange.x,
            activeRange.y,
            activeRange.width,
            activeRange.height
        );
    }

    // Draw laser only when there is a valid target
    if (shootingEnemy && shotMob != -1) {

        g.setColor(tower.getColor());

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
private void configureTower() {

    if (configuredAirID == airID) {
        return;
    }

    configuredAirID = airID;

    if (airID == Value.airTowerLaser) {
        tower = new LaserTower(new BasicLaserAttack());
    }
    else if (airID == Value.airTowerLaser2) {
        tower = new LaserTower(new MediumLaserAttack());
    }
    else if (airID == Value.airTowerLaser3) {
        tower = new LaserTower(new StrongLaserAttack());
    }
    else {
    tower = null;
    shootingEnemy = false;
    shotMob = -1;
}
}
}
	
	

