package game.world.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.world.WorldController;
import game.world.entity.MapEntity;
import game.world.entity.player.Player;

/**
 * Created by Sebi on 19/06/2017.
 */
public class Spikes extends MapEntity {
    public Spikes(WorldController controller, Rectangle spike) {
        super(controller);
        bounds = spike;
        invisible = true;
    }

    @Override
    protected Sprite[] loadFrames() {
        return new Sprite[0];
    }

    @Override
    public void collideWith(MapEntity entity) {
        if(entity instanceof Player)
        {
            controller.movePlayerToSpawn();
        }
    }

    @Override
    public void updateFrame() {

    }
}
