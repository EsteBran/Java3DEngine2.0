package Shaders;

import Entities.Camera;
import Entities.DirLight;
import Entities.Material;
import Entities.PointLight;
import org.lwjgl.util.vector.Matrix4f;
import toolbox.Maths;

import java.util.Arrays;

public class StaticShader extends ShaderProgram {


    private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";

    //transforms
    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    //directional lights
    private int location_dirLightDirection;
    private int location_dirLightAmbient;
    private int location_dirLightDiffuse;
    private int location_dirLightSpecular;

    //point lights
    private int NUMBER_POINT_LIGHTS = 10;
    int[] location_pointLightPosition;
    int[] location_pointLightConstant;
    int[] location_pointLightLinear;
    int[] location_pointLightQuadratic;
    int[] location_pointLightAmbient;
    int[] location_pointLightDiffuse;
    int[] location_pointLightSpecular;


    //spotlights
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

        //directional light
        location_dirLightDirection = super.getUniformLocation("dirLight.direction");
        location_dirLightAmbient = super.getUniformLocation("dirLight.ambient");
        location_dirLightDiffuse = super.getUniformLocation("dirLight.diffuse");
        location_dirLightSpecular = super.getUniformLocation("dirLight.specular");

        //point lights
        location_pointLightPosition = new int[10];
        location_pointLightConstant = new int[10];
        location_pointLightLinear = new int[10];
        location_pointLightQuadratic = new int[10];
        location_pointLightAmbient = new int[10];
        location_pointLightDiffuse = new int[10];
        location_pointLightSpecular = new int[10];

        for (int i = 0; i < 10; i ++) {
           location_pointLightPosition[i] = super.getUniformLocation("pointLight[" + i + "].position");
           location_pointLightConstant[i] = super.getUniformLocation("pointLight[" + i + "].constant");
           location_pointLightLinear[i] = super.getUniformLocation("pointLight[" + i  +"].linear");
           location_pointLightQuadratic[i] = super.getUniformLocation("pointLight[" +i  +"].quadratic");
           location_pointLightAmbient[i] = super.getUniformLocation("pointLight[" + i + "].ambient");
           location_pointLightDiffuse[i] = super.getUniformLocation("pointLight[" + i + "].diffuse");
           location_pointLightSpecular[i] = super.getUniformLocation("pointLight[" + i  +"].specular");
       }

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

    public void loadDirLight(DirLight light) {
        super.loadVector(location_dirLightDirection, light.getDirection());
        super.loadVector(location_dirLightAmbient, light.getAmbient());
        super.loadVector(location_dirLightDiffuse, light.getDiffuse());
        super.loadVector(location_dirLightSpecular, light.getSpecular());
    }

    //max number of point lights set at 4 currently
    public void loadPointLight(PointLight[] pointLights){
        for (int j = 0; j <10; j++) {
            super.loadVector(location_pointLightPosition[j], pointLights[j].getPosition());
            super.loadFloat(location_pointLightConstant[j], pointLights[j].getConstant());
            super.loadFloat(location_pointLightLinear[j], pointLights[j].getLinear());
            super.loadFloat(location_pointLightQuadratic[j], pointLights[j].getQuadratic());
            super.loadVector(location_pointLightAmbient[j], pointLights[j].getAmbient());
            super.loadVector(location_pointLightDiffuse[j], pointLights[j].getDiffuse());
            super.loadVector(location_pointLightSpecular[j], pointLights[j].getSpecular());
        }

    }

    public void loadMaterial(Material material) {

        super.loadTexture(location_diffuse, 0,  material.getDiffuseMap());
        super.loadTexture(location_specular, 1, material.getSpecularMap());
        super.loadFloat(location_shininess, material.getShininess());
    }


}
