package sam.bouncingbilly.states;

import static sam.bouncingbilly.handlers.B2DVars.PPM;
import sam.bouncingbilly.entities.Player;
import sam.bouncingbilly.handlers.B2DVars;
import sam.bouncingbilly.handlers.GameStateManager;
import sam.bouncingbilly.handlers.MyContactListener;
import sam.bouncingbilly.handlers.MyInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer b2dr;

	private OrthographicCamera b2dCam;

	private MyContactListener cl;

	private TiledMap tileMap;
	private int tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Player player;

	public Play(GameStateManager gsm) {
		super(gsm);

		// set up box2d stuff
		world = new World(new Vector2(0, -9.81f), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();

		// create Player
		createPlayer();

		// create tiles
		createTiles();

		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, 1280 / PPM, 720 / PPM);

		// //////////////////////////////////////

	}

	@Override
	public void handleInput() {
		// play jump
		if (MyInput.isPressed(MyInput.BUTTON1)) {
			if (cl.isPlayerOnGround()) {
				player.getBody().applyForceToCenter(0, 300, true);
			}
		}

	}

	@Override
	public void update(float dt) {
		handleInput();

		world.step(dt, 6, 2);
		
		player.update(dt);

	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw tile map
		tmr.setView(cam);
		tmr.render();
		
		// draw player
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);

		// draw box2d world
		b2dr.render(world, b2dCam.combined);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	private void createPlayer() {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		/*
		 * // create falling box bdef.position.set(160 / PPM, 200 / PPM);
		 * bdef.type = BodyType.DynamicBody; playerBody =
		 * world.createBody(bdef);
		 * 
		 * shape.setAsBox(5 / PPM, 5 / PPM); fdef.shape = shape;
		 * fdef.filter.categoryBits = B2DVars.BIT_BOX; fdef.filter.maskBits =
		 * B2DVars.BIT_GROUND; // fdef.restitution = 0.8f;
		 * playerBody.createFixture(fdef).setUserData("box");
		 */

		// create ball
		bdef.position.set(160 / PPM, 100 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(1, 0);
		Body body = world.createBody(bdef);

		CircleShape cshape = new CircleShape();
		cshape.setRadius(15 / PPM);
		fdef.shape = cshape;
		// fdef.restitution = 0.6f; // <--- wie er wieder nach obe springt
		fdef.filter.categoryBits = B2DVars.BIT_BALL;
		fdef.filter.maskBits = B2DVars.BIT_RED;
		body.createFixture(fdef).setUserData("player");

		// create foot sensor
		shape.setRadius(5 / PPM);
		fdef.shape = cshape;
		fdef.filter.categoryBits = B2DVars.BIT_BALL;
		fdef.filter.maskBits = B2DVars.BIT_RED;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		// create player
		player = new Player(body);
		
		body.setUserData(player);

	}

	private void createTiles() {
		// load tile map
		tileMap = new TmxMapLoader().load("res/maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);

		tileSize =   (Integer) tileMap.getProperties().get("tilewidth");
		
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tileMap.getLayers().get("red");
		createLayer(layer, B2DVars.BIT_RED);
		layer = (TiledMapTileLayer) tileMap.getLayers().get("green");
		createLayer(layer, B2DVars.BIT_GREEN);
		layer = (TiledMapTileLayer) tileMap.getLayers().get("blue");
		createLayer(layer, B2DVars.BIT_BLUE);
		
	}

	private void createLayer(TiledMapTileLayer layer, short bits) {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if cell exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f)
						* tileSize / PPM);

				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);

				shape.setAsBox(tileSize / 2 / PPM, tileSize / 2 / PPM);

				// cs.createChain(v);
				fdef.friction = 0;
				fdef.shape = shape;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_BALL;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
				
			}
		}
	}

}
