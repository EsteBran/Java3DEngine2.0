package Entities;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Material {

    private int diffuseMap;
    private int specularMap;


    float shininess;

    public Material( int diffuseMap ,int specularMap, float shininess) {
        this.diffuseMap = diffuseMap;
        this.specularMap = specularMap;
        this.shininess = shininess;
    }

    public int getDiffuseMap() {
        return diffuseMap;
    }

    public void setDiffuseMap(int diffuseMap) {
        this.diffuseMap = diffuseMap;
    }

    public int getSpecularMap() {
        return specularMap;
    }

    public void setSpecularMap(int specularMap) {
        this.specularMap = specularMap;
    }

    public float getShininess() {
        return shininess;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }
}
