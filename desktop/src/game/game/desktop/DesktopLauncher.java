package game.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.Config;
import game.game.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Config.get().initDefaults();

		config.width = Config.get().WIDTH;
		config.height = Config.get().HEIGHT;
		config.title = "Simple Platformer";
		new LwjglApplication(new MyGame(), config);
	}
}
