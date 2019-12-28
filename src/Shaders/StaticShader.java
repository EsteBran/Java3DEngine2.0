package Shaders;

import Entities.Camera;
import Entities.Light;
import Entities.Material;
import org.lwjgl.util.vector.Matrix4f;
import toolbox.Maths;

public class StaticShader extends ShaderProgram {


    private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    private int location_lightColor;
    private int location_lightPosition;
    private int location_lightAmbient;
    private int location_lightDiffuse;
    private int location_lightSpecular;


    private int location_diffuse;
    private int location_specular;
    private int location_shininess;



    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "textureCoords");
        super.bindAttributes(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        //matrix transforms
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

        //light
        location_lightColor = super.getUniformLocation("lightColor");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightAmbient = super.getUniformLocation("light.ambient");
        location_lightDiffuse = super.getUniformLocation("light.diffuse");
        location_lightSpecular = super.getUniformLocation("light.specular");

        //material
        location_diffuse = super.getUniformLocation("material.diffuse");
        location_specular = super.getUniformLocation("material.specular");
        location_shininess = super.getUniformLocation("material.shininess");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projection) {
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadLight(Light light) {
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColor, light.getColor());
        super.loadVector(location_lightAmbient, light.getAmbient());
        super.loadVector(location_lightDiffuse, light.getDiffuse());
        super.loadVector(location_lightSpecular, light.getSpecular());
    }

    public void loadMaterial(Material material) {

        super.loadTexture(location_diffuse, 0,  material.getDiffuseMap());
        super.loadTexture(location_specular, 1, material.getSpecularMap());
        super.loadFloat(location_shininess, material.getShininess());
    }

}
