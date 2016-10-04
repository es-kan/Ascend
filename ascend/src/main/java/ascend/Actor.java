package ascend;

import ascend.Tile.TileType;

public abstract class Actor extends Unit {
	//ATT: only one per tile.
	//actor = all enemies + hero
	public int hp;
	public int attackPower = 1;
	
	public Actor(Game game){
		super(game);
		comparisonInteger = 1000;
	}
	
	public abstract void act();
	
	public boolean move(Tile newLocation){
		if(!newLocation.checkOccupied() && newLocation.attribute != Tile.typeAttributes.get(TileType.WALL_TILE)){
			currentPosition.pullUnit();
			this.currentPosition = newLocation;
			this.currentPosition.pushUnit(this);
			return true;
		} else {
			return false;
			//movement failed!
		}
		
	}
	
	public void attack(Actor target) {
		target.receiveAttack(attackPower);
	}
	
	public void receiveAttack(int attackPower) {
		hp = hp > attackPower ? hp - attackPower : 0;
	}

	public void die() {
		currentPosition.pullUnit();
	}
}


