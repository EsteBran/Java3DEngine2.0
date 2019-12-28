package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Light {

    private Vector3f position;
    private Vector3f color;

    private Vector3f ambient;
    private Vector3f diffuse;
    private Vector3f specular;

    public Light(Vector3f position, Vector3f color, Vector3f ambient, Vector3f diffuse, Vector3f specular) {
        this.position = position;
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }




    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
