package ascend.access;

import ascend.Game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonMaker {

	private Game game;

	public void printGame() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();

		System.out.println(gson.toJson(game.allTiles));

	}

	public void setGame(Game game) {
		this.game = game;
	}
}
