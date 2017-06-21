package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by Sebi on 17/06/2017.
 */
public class World {
    public Sprite[] player;
    public Sprite goal;

    private TextureAtlas objectAtlas;

    public static HashMap<Integer,String> levels;
    static{
        levels = new HashMap<Integer, String>();
        levels.put(1,"maps/test.tmx");
        levels.put(2,"maps/test2.tmx");
    }

    public World()
    {
        initObjectAtlas();
        initMapObjectSprites();

    }

    private void initObjectAtlas() {
        objectAtlas = new TextureAtlas(Gdx.files.internal("atlas/objects.atlas"));
    }

    private void initMapObjectSprites()
    {
        //player = new Sprite(new Texture(Gdx.files.internal("Player.png")));
        player = getSpritesOf("playerfront","playerleft","playerright");
        goal = getSpriteOf("goal");
    }

    public Sprite[] getSpritesOf(String... imageNames)
    {
        Sprite[] sprites = new Sprite[imageNames.length];
        for(int i = 0;i<sprites.length;i++)
        {
            sprites[i] = getSpriteOf(imageNames[i]);
        }
        return sprites;
    }

    public Sprite getSpriteOf(String imageName) {
        return objectAtlas.createSprite(imageName);
    }
}
