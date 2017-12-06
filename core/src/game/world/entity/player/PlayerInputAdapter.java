package game.world.entity.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by Sebi on 21/06/2017.
 */
public class PlayerInputAdapter extends InputAdapter {
    private Player player;
    private ClickInputHandler clickInputHandler;
    private long touchDownTimestamp;

    public PlayerInputAdapter() {
    }

    public void setClickInputHandler(ClickInputHandler clickInputHandler) {
        this.clickInputHandler = clickInputHandler;
    }

    public void setPlayer(Player player) {
        this.player = player;
        clickInputHandler.setPlayer(player);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A: player.setMovementState(Player.LEFT,  true);  break;
            case Input.Keys.RIGHT: case Input.Keys.D: player.setMovementState(Player.RIGHT, true);  break;
            case Input.Keys.SPACE: case Input.Keys.W: case Input.Keys.UP:
                                                      player.setMovementState(Player.UP,    true);  break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:  case Input.Keys.A: player.setMovementState(Player.LEFT,  false); break;
            case Input.Keys.RIGHT: case Input.Keys.D: player.setMovementState(Player.RIGHT, false); break;
            case Input.Keys.SPACE: case Input.Keys.W: case Input.Keys.UP:
                                                      player.setMovementState(Player.UP,    false); break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        float width = Gdx.graphics.getWidth();
//        float height = Gdx.graphics.getHeight();
//        if(screenX < width/2 && screenY > height/2)
//            player.setMovementState(Player.LEFT,true);
//        if(screenX > width/2 && screenY > height/2)
//            player.setMovementState(Player.RIGHT,true);
//        if(screenY < height/2)
//            player.setMovementState(Player.UP,true);
//        touchDownTimestamp = System.nanoTime();
        clickInputHandler.onTouch(screenX, screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        for (int state: player.getMovementStates()) {
//            player.setMovementState(state,false);
//        }
        clickInputHandler.onRelease();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        clickInputHandler.onDrag(screenX, screenY);
        return true;
    }
}
