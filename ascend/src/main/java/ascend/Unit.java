package ascend;

public abstract class Unit {
	//temporary set to 'U' to avoid nullPointers?
	char attribute = 'U';
	
	//the game this Unit is in.
	Game game;
	
	public Unit(Game game){
		this.game = game;
	}
	
}
