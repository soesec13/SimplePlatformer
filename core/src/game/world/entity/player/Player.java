package game.world.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import game.resources.Resources;
import game.util.CoordinateTranslator;
import game.world.WorldController;
import game.world.entity.MapEntity;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sebi on 17/06/2017.
 */
public class Player extends MapEntity {
    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int UP=2;

    private static final float WIDTH = 0.8f, HEIGHT = 0.8f;
    private static final float JUMP_HEIGHT = 14.25f;

    private final Map<Integer,Boolean> movementState = new HashMap<Integer, Boolean>();

    private boolean firing;
    private CoordinateTranslator.Coordinate firingLocation;

    public Player(WorldController controller) {
        super(controller);
        bounds = new Rectangle(0,0,WIDTH,HEIGHT);
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
        frames = Resources.WORLD.player;
        for(Sprite frame:frames) {
            frame.setSize(WIDTH, HEIGHT);
        }
        return frames;
    }

    @Override
    public void collideWith(MapEntity entity) {

    }

    @Override
    public void updateFrame() {
        if(direction == DIR_EAST)
            currentFrame = 2;
        else if(direction == DIR_NONE)
            currentFrame = 0;
        else if (direction == DIR_WEST)
            currentFrame = 1;
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
                velocity.y = JUMP_HEIGHT;
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

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public void setFiringLocation(CoordinateTranslator.Coordinate firingLocation) {
        this.firingLocation = firingLocation;
    }
}
