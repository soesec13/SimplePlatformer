package game.screens;

import com.badlogic.gdx.Screen;
import game.game.MyGame;
import game.screens.stages.GameStage;
import game.world.WorldController;
import game.world.WorldRenderer;
import game.world.entity.MapEntity;

/**
 * Created by Sebi on 17/06/2017.
 */
public class GameScreen implements Screen {
    private WorldController controller;
    private WorldRenderer worldRenderer;
    private GameStage gameStage;

    private final MyGame game;

    public GameScreen(WorldController controller, WorldRenderer worldRenderer, MyGame game) {
        this.controller = controller;
        this.worldRenderer = worldRenderer;
        this.game = game;
    }

    @Override
    public void show() {
        if(controller.getState() == WorldController.GAME_WON)
        {
            game.transitTo(game.endScreen);
        }
        if(controller.getMap() == null)
        {
            game.transitTo(game.mainMenuScreen);
        }
        MapEntity player = new game.world.entity.player.Player(controller);
        controller.addEntity(player);
        controller.setPlayer(player);
        controller.loadEntities();
        controller.setState(WorldController.GAME_RUNNING);
        controller.movePlayerToSpawn();
        gameStage = new GameStage(game);
    }

    @Override
    public void render(float delta) {
        if(controller.getState() == WorldController.GAME_WON)
        {
            game.transitTo(game.endScreen);
            return;
        }
        controller.update();
        worldRenderer.render(game.batch);
        gameStage.act();
        gameStage.draw();
        if(controller.getState() == WorldController.GAME_OVER)
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
        System.out.println("Dispose");
        controller.dispose();
        worldRenderer.dispose();
        gameStage.dispose();
    }


}
