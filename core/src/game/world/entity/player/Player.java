package game.world.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.resources.Resources;
import game.world.WorldController;
import game.world.entity.MapEntity;
import game.world.entity.functional.Goal;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sebi on 17/06/2017.
 */
public class Player extends MapEntity {
    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int UP=2;

    private final Map<Integer,Boolean> movementState = new HashMap<Integer, Boolean>();

    public Player(WorldController controller) {
        super(controller);
        bounds = new Rectangle(0,0,0.8f,0.8f);
        horizontalSpeed = 8;
        maxSpeed = 18;
        isOnGround = false;
        invisible = false;
        movementState.put(LEFT,false);
        movementState.put(RIGHT,false);
        movementState.put(UP,false);
    }

    @Override
    protected Sprite[] loadFrames() {
        Sprite[] frames = new Sprite[1];
        frames[0] = Resources.WORLD.player;
        frames[0].setSize(0.8f,0.8f);
        return frames;
    }

    @Override
    public void collideWith(MapEntity entity) {
        if(entity instanceof Goal)
        {
            System.out.println("Collided with GOAL");
            controller.setState(WorldController.GAME_OVER);
        }
    }

    @Override
    public void updateFrame() {

    }

    public void processInput()
    {
        if(movementState.get(RIGHT)) {
            direction = DIR_EAST;
        }
        else if(movementState.get(LEFT)) {
            direction = DIR_WEST;
        }

        else{
            direction = DIR_NONE;
        }
        if(movementState.get(UP))
        {
            if(state != STATE_JUMPING && state != STATE_FALLING)
            {
                velocity.y = 14.25f;
                state = STATE_JUMPING;
                isOnGround = false;
            }
        }
    }

    public void setMovementState(int direction,boolean active)
    {
        movementState.put(direction,active);
    }

    public Integer[] getMovementStates()
    {
        return movementState.keySet().toArray(new Integer[0]);
    }
}
