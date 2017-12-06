package game.world.entity.player;

import game.util.CoordinateTranslator;
import game.world.WorldCamera;

public class ClickInputHandler{
    private CoordinateTranslator translator;
    private WorldCamera camera;
    private Player player;

    public ClickInputHandler(CoordinateTranslator translator, WorldCamera camera) {
        this.translator = translator;
        this.camera = camera;
    }

    public void onTouch(double screenX, double screenY)
    {
        CoordinateTranslator.Coordinate c = translator.translatePoint(screenX, screenY, camera.position.x, camera.position.y);
        player.setFiring(true);
        player.setFiringLocation(c);
    }

    public void onDrag(double newScreenX, double newScreenY)
    {
        CoordinateTranslator.Coordinate c = translator.translatePoint(newScreenX, newScreenY, camera.position.x, camera.position.y);
        player.setFiringLocation(c);
    }

    public void onRelease()
    {
        player.setFiring(false);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
