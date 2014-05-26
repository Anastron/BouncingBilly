package sam.bouncingbilly.states;

import static sam.bouncingbilly.handlers.B2DVars.PPM;
import sam.bouncingbilly.handlers.GameStateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
public class Play extends GameState{

	private World world;
	private Box2DDebugRenderer b2dr;
	
	private OrthographicCamera b2dCam;
	
	public Play(GameStateManager gsm){
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		b2dr = new Box2DDebugRenderer();
		
		// create platform
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM , 120 / PPM);
		// static body - dont move, unaffected by forces
		// kinematic body - dont get affected forces 
		// dynamic body - alwayy get affected by forces
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		// create falling box
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		
		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		fdef.restitution = 0.8f;
		body.createFixture(fdef);
		
		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, 1280 / PPM , 720 / PPM);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		
	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// draw box2d world
		b2dr.render(world, b2dCam.combined);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
