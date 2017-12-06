package game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import game.Config;
import game.util.CoordinateTranslator;
import game.world.entity.EntityFactory;
import game.world.entity.MapEntity;
import game.world.entity.player.ClickInputHandler;
import game.world.entity.player.PlayerInputAdapter;

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
    private game.world.entity.player.Player player;

    private final List<MapEntity> mapObjects;
    private final WorldCamera camera;
    private final CollisionHandler collisionHandler;
    private final PlayerInputAdapter inputAdapter;

    private int state;

    public WorldController()
    {
        camera = new WorldCamera();
        mapObjects = new ArrayList<>();
        collisionHandler = new CollisionHandler();
        inputAdapter = new PlayerInputAdapter();
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
        CoordinateTranslator translator = new CoordinateTranslator(Config.get().WIDTH, Config.get().HEIGHT, camera.viewportWidth, camera.viewportHeight);
        ClickInputHandler clickInputHandler = new ClickInputHandler(translator, camera);
        inputAdapter.setClickInputHandler(clickInputHandler);
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
            if(e.isCollidable())
            {
                collisionHandler.register(e);
            }
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
        //Only Access Player Specific Properties
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
        if(entity.isCollidable()) {
            collisionHandler.register(entity);
        }
    }

    public WorldCamera getCamera() {
        return camera;
    }

    public void setPlayer(MapEntity player) {
        this.player = (game.world.entity.player.Player) player;
        getCamera().setTarget(player);
        inputAdapter.setPlayer(this.player);
        Gdx.input.setInputProcessor(inputAdapter);
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
