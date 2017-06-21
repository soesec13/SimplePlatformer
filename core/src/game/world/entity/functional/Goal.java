package game.world.entity.functional;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.resources.Resources;
import game.world.WorldController;
import game.world.entity.MapEntity;

/**
 * Created by Sebi on 19/06/2017.
 */
public class Goal extends MapEntity {
    public Goal(WorldController controller, Rectangle bounds)
    {
        super(controller);
        this.bounds = bounds;
        this.affectedByGravity = false;
    }

    @Override
    protected Sprite[] loadFrames() {
        Sprite[] frames = new Sprite[1];
        frames[0] = Resources.WORLD.goal;
        frames[0].setSize(bounds.width,bounds.height);
        return frames;
    }

    @Override
    public void collideWith(MapEntity entity) {
    }

    @Override
    public void updateFrame() {

    }
}
