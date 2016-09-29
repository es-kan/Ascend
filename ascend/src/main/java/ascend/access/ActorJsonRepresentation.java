package ascend.access;

import ascend.Actor;

//this class represents Actors as a simplified JSON object with (so far) three primary values.

public class ActorJsonRepresentation {
	public int x, y;
	public char attribute;
	
	public ActorJsonRepresentation(Actor a) {
		this.x = a.currentPosition.x;
		this.y = a.currentPosition.y;
		this.attribute = a.attribute;
	}
}
