package game.world.entity.functional;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.resources.Resources;
import game.world.WorldController;
import game.world.entity.MapEntity;
import game.world.entity.player.Player;

/**
 * Created by Sebi on 19/06/2017.
 */
public class Goal extends MapEntity {
    public Goal(WorldController controller, Rectangle bounds)
    {
        super(controller);
        this.bounds = bounds;
        this.affectedByGravity = false;
        currentFrame = 0;
        resizeFrames();
    }

    @Override
    protected Sprite[] loadFrames() {
        Sprite[] frames = new Sprite[1];
        frames[0] = Resources.WORLD.goal;
        return frames;
    }

    private void resizeFrames()
    {
        frames[0].setSize(bounds.width,bounds.height);
    }

    @Override
    public void collideWith(MapEntity entity) {
        if(entity instanceof Player)
        {
            System.out.println("Collided with GOAL");
            controller.setState(WorldController.GAME_OVER);
        }
    }

    @Override
    public void updateFrame() {

    }
}
