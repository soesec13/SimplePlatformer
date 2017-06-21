package game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import game.world.entity.EntityFactory;
import game.world.entity.MapEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 17/06/2017.
 */
public class WorldController {

    public static float delta;

    public static final int GAME_RUNNING=1;
    public static final int GAME_OVER=2;
    public static final int GAME_WON = 3;

    private WorldMap map;
    private List<MapEntity> mapObjects;
    private WorldCamera camera;
    private game.world.entity.player.Player player;
    private CollisionHandler collisionHandler;
    private int state;

    public WorldController()
    {
        camera = new WorldCamera();
        mapObjects = new ArrayList<MapEntity>();
        collisionHandler = new CollisionHandler();
    }

    public void loadMap(String name)
    {
        map = WorldMap.load(name);
        if(map == null)
        {
            this.setState(GAME_WON);
            return;
        }
        collisionHandler.setMap(map);
        camera.setMapSize(map.getWidth(),map.getHeight());
    }

    public void loadEntities()
    {
        if(map == null)
            return;
        EntityFactory factory = new EntityFactory(this);
        for(MapObject obj: map.getObjects())
        {
            if(obj.getName() != null && obj.getName().equals("spawn"))
                continue;
            MapEntity e = factory.createFromType(obj);
            mapObjects.add(e);
            collisionHandler.register(e);
        }
    }

    public void update()
    {
        delta = Gdx.graphics.getRawDeltaTime();
        updateCamera();
        updatePlayer();
        updateMapObjects();

    }

    public void movePlayerToSpawn()
    {
        Rectangle spawn = map.getBoundsByName("spawn");
        player.getBounds().x = spawn.x;
        player.getBounds().y = spawn.y;
    }

    private void updatePlayer() {
        player.processInput();
    }

    private void updateCamera()
    {
        camera.update();
    }

    private void updateMapObjects()
    {
        for(MapEntity o:mapObjects)
        {
            o.update();
            collisionHandler.update();
            o.move();
        }
    }

    public WorldMap getMap() {
        return map;
    }

    public List<MapEntity> getEntities() {
        return mapObjects;
    }

    public void addEntity(MapEntity entity) {
        mapObjects.add(entity);
        collisionHandler.register(entity);
    }

    public WorldCamera getCamera() {
        return camera;
    }

    public void setPlayer(MapEntity player) {
        this.player = (game.world.entity.player.Player) player;
        getCamera().setTarget(player);
        Gdx.input.setInputProcessor((InputProcessor) player);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void dispose() {
        collisionHandler.clear();
        mapObjects.clear();
        map = null;
    }
}
