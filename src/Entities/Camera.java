package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private Vector3f direction = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera() {

    }

    //moves the world in the opposite direction of the direction of the keys in the view matrix
    public void move() {
        //translation
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.z -= 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            position.x -= 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.z += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            position.x += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            position.y += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            position.y -= 0.2f;
        }


        //rotation
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            yaw += 0.5f;
        } if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            yaw -= 0.5f;
        } if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
           pitch -= 0.5f;
        } if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            pitch += 0.5f;
        }



    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
}
