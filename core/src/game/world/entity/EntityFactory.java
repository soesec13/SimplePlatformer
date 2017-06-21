package game.world.entity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import game.world.WorldController;
import game.world.WorldMap;
import game.world.entity.functional.Goal;
import game.world.entity.obstacle.Spikes;

/**
 * Created by Sebi on 19/06/2017.
 */
public class EntityFactory {
    private WorldController controller;
    public EntityFactory(WorldController controller)
    {
        this.controller = controller;
    }
    public MapEntity createFromType(String type, Rectangle bounds)
    {
        if(type.equals("spikes"))
            return new Spikes(controller,bounds);
        if(type.equals("goal"))
            return new Goal  (controller,bounds);
         return null;
}

    public MapEntity createFromType(MapObject obj) {
        String name = obj.getName();
        String type = name == null ? (String) obj.getProperties().get("type") :name;
        Rectangle bounds = WorldMap.getBoundsOf(obj);
        return createFromType(type,bounds);
    }
}
