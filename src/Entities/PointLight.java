package Entities;

import org.lwjgl.util.vector.Vector3f;

public class PointLight {


    private int NUMBER_POINT_LIGHTS = 10;
    private final float constant = 1.0f;
    private final float linear = 0.35f;
    private final float quadratic = 0.44f;

    private Vector3f position;
    private Vector3f ambient;
    private Vector3f diffuse;
    private Vector3f specular;

    public PointLight(Vector3f position, Vector3f ambient, Vector3f diffuse, Vector3f specular) {
        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public float getConstant() {
        return constant;
    }

    public float getLinear() {
        return linear;
    }

    public float getQuadratic() {
        return quadratic;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public void setAmbient(Vector3f ambient) {
        this.ambient = ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Vector3f diffuse) {
        this.diffuse = diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public void setSpecular(Vector3f specular) {
        this.specular = specular;
    }

    public void setNUMBER_POINT_LIGHTS(int NUMBER_POINT_LIGHTS) {
        this.NUMBER_POINT_LIGHTS = NUMBER_POINT_LIGHTS;
    }

    public int getNUMBER_POINT_LIGHTS() {
        return NUMBER_POINT_LIGHTS;
    }
}
