package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Sebi on 17/06/2017.
 */
public class World {
    public Sprite player;
    public Sprite goal;

    public World()
    {
        initMapObjectSprites();
    }

    private void initMapObjectSprites()
    {
        player = new Sprite(new Texture(Gdx.files.internal("Player.png")));
        goal = new Sprite(new Texture(Gdx.files.internal("goal.png")));
    }
}
