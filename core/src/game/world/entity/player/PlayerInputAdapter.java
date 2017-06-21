package game.world.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Sebi on 21/06/2017.
 */
public class PlayerInputAdapter implements InputProcessor {
    private Player player;

    public PlayerInputAdapter() {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A:   player.setMovementState(Player.LEFT,true);     break;
            case Input.Keys.RIGHT: case Input.Keys.D:   player.setMovementState(Player.RIGHT,true);    break;
            case Input.Keys.SPACE: case Input.Keys.W:   player.setMovementState(Player.UP,true);       break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A:   player.setMovementState(Player.LEFT,false);     break;
            case Input.Keys.RIGHT: case Input.Keys.D:   player.setMovementState(Player.RIGHT,false);    break;
            case Input.Keys.SPACE: case Input.Keys.W:   player.setMovementState(Player.UP,false);       break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        if(screenX < width/2 && screenY > height/2)
            player.setMovementState(Player.LEFT,true);
        if(screenX > width/2 && screenY > height/2)
            player.setMovementState(Player.RIGHT,true);
        if(screenY < height/2)
            player.setMovementState(Player.UP,true);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (int state: player.getMovementStates()) {
            player.setMovementState(state,false);
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
