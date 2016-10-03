package ascend.access;

import ascend.Actor;

//this class represents Actors as a simplified JSON object with (so far) three primary values.

//also it's not being used anymore after the stunning realization that the front doesn't actually need these info dumps.

public class ActorJson {
	public int x, y;
	public char attribute;

	public ActorJson(Actor a) {
		this.x = a.currentPosition.x;
		this.y = a.currentPosition.y;
		this.attribute = a.attribute;
	}
}
