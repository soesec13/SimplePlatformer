package game.util;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import game.world.WorldController;

/**
 * Created by Sebi on 22/06/2017.
 */
public class Clock extends TextArea{
    private float currentTime;
    private boolean isRunning;

    public Clock(Skin skin) {
        super("",skin);
        currentTime = 0;
        isRunning = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!isRunning)
            return;
        currentTime += WorldController.delta;
        this.setText(""+currentTime);
    }

    public void start()
    {
        currentTime = 0;
        isRunning = true;
    }

}
