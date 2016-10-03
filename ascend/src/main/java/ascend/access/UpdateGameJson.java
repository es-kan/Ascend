package ascend.access;

import ascend.Game;

/*This class is basically useless now that simple game representations are being sent to the front instead.*/

public class UpdateGameJson {
	ActorJson[] newActorPositions;

	public UpdateGameJson(Game game) {
		// represent new position of actors
		this.newActorPositions = new ActorJson[game.actors.size()];
		for (int i = 0; i < game.actors.size(); i++) {
			newActorPositions[i] = new ActorJson(game.actors.get(i));
		}
	}
}
