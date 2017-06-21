package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import game.game.MyGame;
import game.resources.Resources;
import game.screens.stages.LevelSelectStage;
import game.world.WorldController;

/**
 * Created by Sebi on 21/06/2017.
 */
public class LevelScreen implements Screen {
    private LevelSelectStage levelSelectStage;
    private WorldController controller;
    private final MyGame game;

    public LevelScreen(WorldController controller, MyGame game) {
        this.controller = controller;
        this.game = game;
    }

    @Override
    public void show() {
        levelSelectStage = new LevelSelectStage(Resources.WORLD.levels.values(), controller, game);
        Gdx.input.setInputProcessor(levelSelectStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelSelectStage.act();
        levelSelectStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        levelSelectStage.setPositions();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        levelSelectStage.dispose();
    }
}
