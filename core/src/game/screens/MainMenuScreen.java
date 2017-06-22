package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import game.Config;
import game.game.MyGame;

/**
 * Created by Sebi on 17/06/2017.
 */
public class MainMenuScreen implements Screen {

    private final MyGame game;
    private final OrthographicCamera camera;

    public MainMenuScreen(final MyGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.get().WIDTH, Config.get().HEIGHT);
    }

    @Override
    public void show() {
        game.font.setColor(Color.FOREST);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.font.draw(game.batch,"Hello Menu",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        game.batch.end();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isTouched())
        {
            game.transitTo(game.levelScreen);
        }
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
