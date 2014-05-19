package sam.softwaredeveloping;

import sam.softwaredeveloping.screens.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BouncingBilly extends Game {
	
	public static final String TITLE = "Bouncing Billy", VERSION = "0.0.0";
	
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
	}

}
