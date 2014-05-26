package sam.bouncingbilly.handlers;

import java.util.Stack;

import sam.bouncingbilly.states.GameState;
import sam.bouncingbilly.states.Play;
import sam.softwaredeveloping.BouncingBilly;

public class GameStateManager {
	
	private BouncingBilly game;
	
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 912837;
	
	public GameStateManager(BouncingBilly game){
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public BouncingBilly game() {return game;}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	public void render(){
		gameStates.peek().render();
	}
	
	private GameState getState(int state){
		if(state == PLAY) return new Play(this);
		return null;
	}
	
	public void setState(int state)	{
		popState();
		pushState(state);
	}
	
	public void pushState(int state)	{
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}

}
