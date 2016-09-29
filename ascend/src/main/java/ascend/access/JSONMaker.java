package ascend.access;

import ascend.Actor;
import ascend.Game;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//this class makes JSON object out fields provided by a Game.

@Named
@SessionScoped
@Path("/jsonmaker")
public class JSONMaker {

	private Game game;

	// default constructor makes new game
	public JSONMaker() {
		setGame();
	}

	// @param game: define <code>this.game</code>
	public JSONMaker(Game game) {
		this.game = game;
	}
	
	@GET
	@Path("/game")
	public String getGame() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();
		return gson.toJson(game.allTiles);
	}
	
	@GET
	@Path("/actors")
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