package ascend.access;

import javax.enterprise.context.ApplicationScoped;

import ascend.Game;

@ApplicationScoped
public class GameRepository {
	private Game game;
	public Game getGame(){
		if(game == null){
			game = new Game();
		}
		return game;
	}
	
	public Game getNewGame(){
		game = new Game();
		return game;
	}
}
