package sam.bouncingbilly.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener{
	
	private int numFootContact;

	// called when two fixtures start to collide
	@Override
	public void beginContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")){
			numFootContact++;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("foot")){
			numFootContact++;
		}
		
	}

	// called when two fixtures no longer collide
	@Override
	public void endContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")){
			numFootContact--;
		}
		
		if(fb.getUserData() != null && fb.getUserData().equals("foot")){
			numFootContact--;
		}
		
	}
	
	public boolean isPlayerOnGround()
	{
		return numFootContact > 0;
	}

	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
