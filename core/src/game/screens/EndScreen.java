package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import game.Config;
import game.game.MyGame;

/**
 * Currently Unused
 * Created by Sebi on 19/06/2017.
 */

public class EndScreen implements Screen {
    private final MyGame game;
    private OrthographicCamera camera;

    public EndScreen(MyGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.get().WIDTH, Config.get().HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        Color prv = game.font.getColor();

        game.batch.begin();
        game.font.setColor(1,1,0,1);
        game.font.draw(game.batch,"The End",10,10);
        game.batch.end();

        game.font.setColor(prv);
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
