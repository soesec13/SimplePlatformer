package game.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 18/06/2017.
 */
public class WorldMap {


    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private static final float TILE_SIZE = 32;

    public WorldMap(TiledMap map) {
        this.map = map;
    }

    public static WorldMap load(String name)
    {
        TiledMap map = new TmxMapLoader().load(name);
        WorldMap wm = new WorldMap(map);
        return wm;
    }

    public Rectangle getBoundsByName(String name)
    {
        MapObjects objects = getObjects();
        MapObject object = objects.get(name);
        return WorldMap.getBoundsOf(object);
    }

    @Deprecated
    public List<Rectangle> getBoundsByType(String type)
    {
        final List<Rectangle> bounds = new ArrayList<Rectangle>();
        MapObjects objects = getObjects();
        for(MapObject oj:objects)
        {
            if(oj.getProperties().get("type").equals(type))
            {
                bounds.add(WorldMap.getBoundsOf(oj));
            }
        }
        return bounds;
    }

    public MapObjects getObjects()
    {
        return map.getLayers().get("objects").getObjects();
    }

    public Cell getCell(int x, int y)
    {
        return getForeground().getCell(x,y);
    }

    /***
     * Deprecated, this is handeled by the Collision Handler
     * @param x
     * @param y
     * @return
     */
    @Deprecated
    public boolean isCellBlocked(float x, float y)
    {
        TiledMapTileLayer collissionLayer = getForeground();
        int gridX = (int) (x / collissionLayer.getTileWidth());
        int gridY = (int) (y / collissionLayer.getTileHeight());
        Cell c = collissionLayer.getCell(gridX,gridY);
        return c!=null && c.getTile()!=null;
    }

    private TiledMapTileLayer getForeground()
    {
        return (TiledMapTileLayer) map.getLayers().get("foreground");
    }

    public OrthogonalTiledMapRenderer getRenderer()
    {
        if(renderer == null || !renderer.getMap().equals(map))
        {
            renderer = new OrthogonalTiledMapRenderer(map,1/TILE_SIZE);
        }
        return renderer;
    }

    public float getWidth()
    {
        MapProperties prop = map.getProperties();

        int mapWidth = prop.get("width", Integer.class);
        return mapWidth;
    }
    public float getHeight()
    {
        MapProperties prop = map.getProperties();
        int mapHeight = prop.get("height", Integer.class);
        return mapHeight;
    }

    public static Rectangle getBoundsOf(MapObject object)
    {
        Rectangle rect = ((RectangleMapObject)object).getRectangle();
        return new Rectangle(rect.x/TILE_SIZE,rect.y/TILE_SIZE,rect.width/TILE_SIZE,rect.height/TILE_SIZE);
    }
}
