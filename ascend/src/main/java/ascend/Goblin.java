package ascend;

public class Goblin extends Enemy {
	public Goblin(Game game) {
		super(game);
		attribute = 'G';
		attackPower = 1;
		hp = 1;
	}

	// do specific Goblin stuff. Like hitting a Hero in the face for a damage.
	public void act() {
		boolean acted = false;
		for (Tile t : game.getSurroundingSquare(currentPosition)) {
			if (t.containsHero()) {
				this.attack((Actor) t.unit);
				acted = true;
			}
		}
		if (!acted) {
			if(this.isSharingRoomWithHero()){
				Tile possibleTarget = nextTileToGetTo(game.hero.currentPosition);
				if(!possibleTarget.checkOccupied()){
					move(possibleTarget);
				} else {
					//do nothing actually
				}
			} else {
				super.act();
			}
		}
	}
	
	//works
	public boolean isSharingRoomWithHero(){
		for(Room room : game.rooms){
			if(room.tiles.contains(currentPosition) && room.tiles.contains(game.hero.currentPosition)){
					return true;
			}
		}
		return false;
	}
	
	//test
	public Tile nextTileToGetTo(Tile targetTile){
		int x = currentPosition.x;
		int y = currentPosition.y;
		int tx = targetTile.x;
		int ty = targetTile.y;
		if(x > tx){
			x -= 1;
		} else if(x < tx){
			x += 1;
		}
		if(y > ty){
			y -= 1;
		} else if(y < ty){
			y += 1;
		}
		return game.field[y][x];
	}

	
}
