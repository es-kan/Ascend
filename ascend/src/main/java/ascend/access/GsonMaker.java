package ascend.access;

import ascend.Actor;
import ascend.Game;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//this class makes JSON object out fields provided by a Game.

@Named
@SessionScoped
public class GsonMaker {

	private Game game;

	// default constructor makes new game
	public GsonMaker() {
		setGame();
	}

	// @param game: define <code>this.game</code>
	public GsonMaker(Game game) {
		this.game = game;
	}

	public String getGame() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();
		return gson.toJson(game.allTiles);
	}

	public String getActors() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		ArrayList<ActorJsonRepresentation> actorJsons = new ArrayList<ActorJsonRepresentation>(game.actors.size());
		for (Actor actor : game.actors) {
			actorJsons.add(new ActorJsonRepresentation(actor));
		}
		return gson.toJson(actorJsons);
	}

	public void setGame() {
		game = new Game();
	}
}