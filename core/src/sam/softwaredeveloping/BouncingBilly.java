package sam.softwaredeveloping;

import sam.softwaredeveloping.screens.GameScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BouncingBilly extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
	}

}
