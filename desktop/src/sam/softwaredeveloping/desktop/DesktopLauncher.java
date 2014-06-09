package sam.softwaredeveloping.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sam.softwaredeveloping.BouncingBilly;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	
		config.title = BouncingBilly.TITLE + " v" + BouncingBilly.VERSION;
		config.vSyncEnabled = true;
		config.useGL30 = true;
		config.width = BouncingBilly.V_WIDTH * BouncingBilly.SCALE;
		config.height = BouncingBilly.V_HEIGHT * BouncingBilly.SCALE;

				
		new LwjglApplication(new BouncingBilly(), config);
	}
}
