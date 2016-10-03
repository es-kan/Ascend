package ascend.access;

import javax.enterprise.context.ApplicationScoped;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ascend.Game;

//this class makes JSON object out fields provided by a Game.

@ApplicationScoped
public class JSONMaker {

	public String getGameJson(Game game) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();
		return gson.toJson(new InitGameJson(game));
	}

	public String getActorJsons(Game game) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(new UpdateGameJson(game));
	}
	
	public String getSimpleGameRepresentation(Game game) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(new SimpleGameRepresentation(game));
	}

}