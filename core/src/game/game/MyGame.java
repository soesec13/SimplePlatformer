package game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Config;
import game.screens.EndScreen;
import game.screens.LevelScreen;
import game.world.WorldRenderer;

public class MyGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public game.world.WorldController worldController;
	public WorldRenderer worldRenderer;

    public game.screens.GameScreen gameScreen;
    public game.screens.MainMenuScreen mainMenuScreen;
    public game.screens.EndScreen endScreen;
    public game.screens.LevelScreen levelScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		worldController = new game.world.WorldController();
		worldRenderer = new WorldRenderer(worldController);

		gameScreen = new game.screens.GameScreen(worldController,worldRenderer,this);
        mainMenuScreen = new game.screens.MainMenuScreen(this);
        endScreen = new EndScreen(this);
        levelScreen = new LevelScreen(worldController, this);

		Config.get().apply();

		this.setScreen(mainMenuScreen);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void transitTo(Screen screen)
    {
    	getScreen().dispose();
        this.setScreen(screen);
    }


}
