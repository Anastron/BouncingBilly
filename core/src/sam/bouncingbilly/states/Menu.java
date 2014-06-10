package sam.bouncingbilly.states;



import static sam.bouncingbilly.handlers.B2DVars.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import sam.softwaredeveloping.BouncingBilly;
import sam.bouncingbilly.entities.B2DSprite;
import sam.bouncingbilly.handlers.Animation;
import sam.bouncingbilly.handlers.Background;
import sam.bouncingbilly.handlers.GameButton;
import sam.bouncingbilly.handlers.GameStateManager;


public class Menu extends GameState {
	
	private boolean debug = false;
	
	private Background bg;
	private Animation animation;
	private GameButton playButton;
	
	private World world;
	private Box2DDebugRenderer b2dRenderer;
	
	private Array<B2DSprite> blocks;
	
	public Menu(GameStateManager gsm) {
		
		super(gsm);
		
		Texture tex = BouncingBilly.res.getTexture("menu");
		bg = new Background(new TextureRegion(tex), cam, 1f);
		bg.setVector(-20, 0);
		
		tex = BouncingBilly.res.getTexture("billy");
		TextureRegion[] reg = new TextureRegion[4];
		for(int i = 0; i < reg.length; i++) {
			reg[i] = new TextureRegion(tex, i * 32, 0, 32, 32);
		}
		animation = new Animation(reg, 1 / 12f);
		
		tex = BouncingBilly.res.getTexture("hud");
		playButton = new GameButton(new TextureRegion(tex, 0, 34, 58, 27), 160, 100, cam);
		
		cam.setToOrtho(false, BouncingBilly.V_WIDTH, BouncingBilly.V_HEIGHT);
		
		world = new World(new Vector2(0, -9.8f * 5), true);
		//world = new World(new Vector2(0, 0), true);
		b2dRenderer = new Box2DDebugRenderer();
		
//		createTitleBodies();
		
	}

	@Override
	public void handleInput() {
		// mouse/touch input
		if(playButton.isClicked()) {
			BouncingBilly.res.getSound("crystal").play();
//			gsm.setState(GameStateManager.LEVEL_SELECT);
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
		world.step(dt / 5, 8, 3);
		
		bg.update(dt);
		animation.update(dt);
		
		playButton.update(dt);
		
	}

	@Override
	public void render() {
		sb.setProjectionMatrix(cam.combined);
		
		// draw background
		bg.render(sb);
		
		// draw button
		playButton.render(sb);
		
		// draw bunny
		sb.begin();
		sb.draw(animation.getFrame(), 146, 31);
		sb.end();
		
		// debug draw box2d
		if(debug) {
			cam.setToOrtho(false, BouncingBilly.V_WIDTH / PPM, BouncingBilly.V_HEIGHT / PPM);
			b2dRenderer.render(world, cam.combined);
			cam.setToOrtho(false, BouncingBilly.V_WIDTH, BouncingBilly.V_HEIGHT);
		}
		
/*		// draw title
		for(int i = 0; i < blocks.size; i++) {
			blocks.get(i).render(sb);
		}
*/		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}