package ascend.access;

import ascend.Game;
import ascend.Tile;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonMaker {

	private Game game;
	private ArrayList<String> tileJSONs = new ArrayList<String>();

	public void printGame() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();

		for (Tile t : game.allTiles) {
			tileJSONs.add(gson.toJson(t));
		}
		
		for(String tile : tileJSONs){
			System.out.println(tile);
		}
		
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
