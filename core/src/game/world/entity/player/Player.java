package game.world.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
public class Player extends MapEntity implements InputProcessor{
    private static final int LEFT=0;
    private static final int RIGHT=1;
    private static final int UP=2;

    private final Map<Integer,Boolean> mstate = new HashMap<Integer, Boolean>();

    public Player(WorldController controller) {
        super(controller);
        bounds = new Rectangle(0,0,0.8f,0.8f);
        horizontalSpeed = 8;
        maxSpeed = 18;
        isOnGround = false;
        invisible = false;
        mstate.put(LEFT,false);
        mstate.put(RIGHT,false);
        mstate.put(UP,false);
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
        if(mstate.get(RIGHT)) {
            direction = DIR_EAST;
        }
        else if(mstate.get(LEFT)) {
            direction = DIR_WEST;
        }

        else{
            direction = DIR_NONE;
        }
        if(mstate.get(UP))
        {
            if(state != STATE_JUMPING && state != STATE_FALLING)
            {
                velocity.y = 14.25f;
                state = STATE_JUMPING;
                isOnGround = false;
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A:   mstate.put(LEFT,true);     break;
            case Input.Keys.RIGHT: case Input.Keys.D:   mstate.put(RIGHT,true);    break;
            case Input.Keys.SPACE: case Input.Keys.W:   mstate.put(UP,true);       break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A:   mstate.put(LEFT,false);     break;
            case Input.Keys.RIGHT: case Input.Keys.D:   mstate.put(RIGHT,false);    break;
            case Input.Keys.SPACE: case Input.Keys.W:   mstate.put(UP,false);       break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float widht = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        if(screenX < widht/2 && screenY > height/2)
            mstate.put(LEFT,true);
        if(screenX > widht/2 && screenY > height/2)
            mstate.put(RIGHT,true);
        if(screenY < height/2)
            mstate.put(UP,true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (Map.Entry<Integer,Boolean> entry: mstate.entrySet()
             ) {
            entry.setValue(false);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
