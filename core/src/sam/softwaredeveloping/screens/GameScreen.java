package sam.softwaredeveloping.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen{
	
	private World world;
	
	private Box2DDebugRenderer debugRenderer;
	
	private OrthographicCamera camera;


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(world, camera.combined);
		
		world.step(1/60f, 6, 2);
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -10), true); 
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		
		
		// Create a test ground 
		

		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	

	
}
