package sam.softwaredeveloping.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TexturesManager {
	
	private static TextureRegion bG;
	private static TextureRegion btnUp;
	private static TextureRegion btnDown;
	private static Skin skin;
	
	public static void loadTextures(){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/textures/textures.atlas"));
		
		bG = atlas.findRegion("Bg");
		btnUp = atlas.findRegion("btn.up");
		btnDown = atlas.findRegion("btn.down");
		
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
	}

	public static TextureRegion getbG() {
		return bG;
	}

	public static TextureRegion getBtnUp() {
		return btnUp;
	}

	public static TextureRegion getBtnDown() {
		return btnDown;
	}

	public static Skin getSkin(){
		return skin;
	}
}

