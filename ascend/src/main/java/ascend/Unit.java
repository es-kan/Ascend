package ascend;

public abstract class Unit implements Comparable<Unit>{
	//temporary set to 'U' to avoid nullPointers?
	public char attribute = 'U';
	int comparisonInteger = 0;
	public Tile currentPosition;
	
	//the game this Unit is in.
	Game game;
	
	public Unit(Game game){
		this.game = game;
	}
	
	public int compareTo(Unit u){
		if(this.comparisonInteger > u.comparisonInteger){
			return 1;
		} else if (this.comparisonInteger < u.comparisonInteger){
			return -1;
		} else {
			return 0;
		}
	}
}
