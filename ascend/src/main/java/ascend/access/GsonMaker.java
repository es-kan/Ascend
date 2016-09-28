package ascend.access;

import ascend.Game;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Named
@SessionScoped
public class GsonMaker {

	private Game game;
	
	//default constructor makes new game
	public GsonMaker(){
		setGame();
	}
	
	//@param game: define <code>this.game</code>
	public GsonMaker(Game game){
		this.game = game;
	}

	public String getGame() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.setExclusionStrategies(new GsonUnitExcluder());
		Gson gson = gsonBuilder.create();
		return gson.toJson(game.allTiles);
	}

	public void setGame() {
		game = Game.initGame();
	}
}