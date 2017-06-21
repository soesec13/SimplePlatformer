package game.world.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Sebi on 17/06/2017.
 */
public abstract class MapEntity {

    public static final int STATE_STANDING = 0;
    public static final int STATE_RUNNING  = 1;
    public static final int STATE_JUMPING  = 2;
    public static final int STATE_FALLING  = 3;

    public static final int DIR_NONE = 0;
    public static final int DIR_EAST = 1;
    public static final int DIR_WEST = 2;

    public static final float GRAVITY = -0.4f;

    //Body
    protected Rectangle bounds;

    //Movement
    protected float horizontalSpeed;
    protected float maxSpeed;
    protected Vector2 velocity;
    protected boolean affectedByGravity;

    //Rendering
    protected Sprite[] frames;
    protected int currentFrame;
    protected long frameTimer;
    protected boolean invisible;
    protected boolean ignoresTiles;
    private float alpha;


    //States
    protected int direction;
    protected int state;
    protected boolean isOnGround;
    protected boolean isAlive;

    protected game.world.WorldController controller;

    public MapEntity(game.world.WorldController controller)
    {
        this.controller = controller;
        isAlive = true;
        direction = DIR_NONE;
        alpha = 1f;
        currentFrame = 0;
        velocity = new Vector2(0,0);
        bounds = new Rectangle(-1,-1,1,1);
        affectedByGravity = true;
        frames = loadFrames();
    }

    protected abstract Sprite[] loadFrames();

    public void draw (SpriteBatch batch)
    {
        if(invisible || frames[currentFrame] == null)
            return;
        frames[currentFrame].setPosition(bounds.x,bounds.y);
        frames[currentFrame].draw(batch,alpha);
    }

    public void update()
    {
        updateState();
        updateFrame();
        updateVelocity();
        //System.out.println(isOnGround);
        //printState();
    }

    private void updateVelocity()
    {
        velocity.x = 0;
        if(direction == DIR_WEST)
            velocity.x = -horizontalSpeed;
        if(direction == DIR_EAST)
            velocity.x = +horizontalSpeed;
            //System.out.println("Added gravity");
            velocity.y += GRAVITY;

        if(velocity.len() > maxSpeed) {
            velocity = velocity.nor().scl(maxSpeed);
        }
    }

    public void move() {
        bounds.x += velocity.x * game.world.WorldController.delta;
        bounds.y += velocity.y * game.world.WorldController.delta;
        //System.out.println(bounds.x+" "+bounds.y);
        keepInBounds();
    }

    private void keepInBounds()
    {
        if(bounds.y <= 0) {
            bounds.y = 0;
            velocity.y = 0;
            isOnGround = true;
        }
        else if(bounds.y + bounds.height > game.Config.get().HEIGHT)
        {
            bounds.y = game.Config.get().HEIGHT - bounds.height;
            velocity.y = 0;
        }
        if(bounds.x < 0)
        {
            bounds.x = 0;
            velocity.x = 0;
            direction = DIR_NONE;
        }
        else if(bounds.x + bounds.width > game.Config.get().WIDTH)
        {
            bounds.x = game.Config.get().WIDTH - bounds.width;
            velocity.x = 0;
            direction = DIR_NONE;
        }
    }

    public abstract void collideWith(MapEntity entity);

    public abstract void updateFrame();

    public void collideVertical(int newXPos)
    {
        final float boundsadjustment = velocity.x > 0 ? 1- bounds.width:0;
        bounds.x = newXPos+boundsadjustment;
        velocity.x = 0;
    }
    public void collideHorizontal(int newYPos)
    {
        final float boundsadjustment = velocity.y > 0 ? 1- bounds.height:0;
        bounds.y = newYPos+boundsadjustment;
        if(velocity.y < 0)
            isOnGround = true;
        velocity.y = 0;
    }

    private final void updateState()
    {
        if(velocity.y != 0)
            isOnGround = false;

        if(isOnGround)
        {
            if(direction == DIR_NONE) {
                state = STATE_STANDING;
            }
            else if((direction == DIR_EAST || direction == DIR_WEST))
            {
                state = STATE_RUNNING;
            }
        }
        else
        {
            if(velocity.y > 0)
                state = STATE_JUMPING;
            else if(velocity.y < 0)
                state = STATE_FALLING;
            else if(state == STATE_FALLING && velocity.y == 0)
            {
                System.out.println("Landed");
                isOnGround = true;
            }
        }
    }

    private void printState()
    {
        String st="else",dir="else";
        if(direction == DIR_EAST)
            dir = "East";
        else if(direction == DIR_WEST)
            dir = "West";
        else
            dir = "None";
        switch (state)
        {
            case STATE_FALLING:  st = "Falling"; break;
            case STATE_JUMPING:  st = "Jumping"; break;
            case STATE_RUNNING:  st = "Running"; break;
            case STATE_STANDING: st = "Standing"; break;
        }
        System.out.println(st+" "+dir);
    }

    public boolean isIgnoresTiles() {
        return ignoresTiles;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
