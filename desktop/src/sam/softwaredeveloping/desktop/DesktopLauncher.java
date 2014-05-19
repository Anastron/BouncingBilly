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
		config.width = 1280;
		config.height = 720;
				
		new LwjglApplication(new BouncingBilly(), config);
	}
}
