package game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import game.world.entity.MapEntity;

/**
 * Created by Sebi on 17/06/2017.
 */
public class WorldRenderer {
    private final game.world.WorldController controller;
    private final game.world.WorldCamera camera;

    public WorldRenderer(game.world.WorldController controller) {
        this.controller = controller;
        this.camera = controller.getCamera();
    }

    public void render(SpriteBatch spriteBatch)
    {
        clear();
        drawMap();
        spriteBatch.begin();
        {
            spriteBatch.setProjectionMatrix(camera.combined);
            drawEntitites(spriteBatch);
        }
        spriteBatch.end();
    }

    private void clear()
    {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawMap() {
        OrthogonalTiledMapRenderer renderer = controller.getMap().getRenderer();
        renderer.setView(camera);
        renderer.render();
    }

    private void drawEntitites(SpriteBatch spriteBatch)
    {
        for(MapEntity e:controller.getEntities())
        {
            e.draw(spriteBatch);
        }
    }

    public void dispose() {

    }
}
