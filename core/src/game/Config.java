package game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Sebi on 17/06/2017.
 */
public class Config {

    public int WIDTH,HEIGHT;

    public boolean enableVsync;

    public void initDefaults(){
        WIDTH = 1080;
        HEIGHT = 720;
        enableVsync = true;
    }

    public void apply()
    {
        Gdx.graphics.setWindowedMode(WIDTH,HEIGHT);
        Gdx.graphics.setVSync(enableVsync);
    }

    //Singleton Implementation
    private static Config instance = new Config();

    public static Config get() {
        return instance;
    }

    private Config() {
        initDefaults();
    }
}
