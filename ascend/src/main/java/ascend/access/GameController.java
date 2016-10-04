package ascend.access;


import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import ascend.Actor;

@Model
@SessionScoped
@Path("/controller")
public class GameController {
	//private static Game game;
	
	@Inject
	private JSONMaker jm;
	
	@Inject
	private GameRepository gr;
	
	//The following two methods are too cool to erase and too inefficient to use.
	//Path parameters are referred to in ascendjs.js, which is basically synonymous with utter crap.
	
//	@GET
//	@Path("/initialize")
//	public String initGame(){
//		System.out.println("I'm in a get request");
//		
//		
//		Game game = gr.getGame();
//		return jm.getGameJson(game);
//	}
//	
//	@POST
//	@Path("/move")
//	public String processRequest(String direction){
//		boolean moveSuccess = gr.getGame().hero.move(direction);
//		if(moveSuccess){
//			for(Actor actor : gr.getGame().actors){
//				actor.act();
//			}
//		}
//		return jm.getActorJsons(gr.getGame());
//	}
	
	
/*	the following two methods actually get the job done within a decent time frame but lack a certain je ne sais quoi.
	Basically they only return the field as it would be printed in the console, saving a ton of javascript processing.
	It's a pretty lackluster result after working two weeks on frontend really.
*/	
	
	//this method requests the game state without sending a parameter to update it with. Used for initialization.
	@GET
	@Path("initSimple")
	public String getSimpleRepresentation(){
		return jm.getSimpleGameRepresentation(gr.getGame());
	}
	
	//this method captains the entire backend to respond to input commands.
	@POST
	@Path("moveSimple")
	public String getSimpleRepresentation(String direction){
		boolean moveSuccess = gr.getGame().hero.move(direction);
		if(moveSuccess){
			for(Actor actor : gr.getGame().actors){
				actor.act();
			}
		}
		return jm.getSimpleGameRepresentation(gr.getGame());
	}
	
	@GET
	@Path("newGame")
	public String getNewGame(){
		return jm.getSimpleGameRepresentation(gr.getNewGame());
	}
}
