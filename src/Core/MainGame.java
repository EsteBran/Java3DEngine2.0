package Core;

import Entities.*;
import Models.TexturedModel;
import Rendering.*;
import Models.RawModel;
import Textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGame {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Time time = new Time();

        int diffuseID = loader.loadTexture("container");
        int specularID = loader.loadTexture("bricks");

        RawModel model = OBJLoader.loadObjModel("plane", loader);
        RawModel model2 = OBJLoader.loadObjModel("monkeyHigh", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("bricks"));
        ModelTexture texture2 = new ModelTexture(loader.loadTexture("bricks"));

        TexturedModel staticModel = new TexturedModel(model, texture);
        TexturedModel staticModel2 = new TexturedModel(model2, texture2);

        Random rand = new Random();

        Material material = new Material(diffuseID,
                                         specularID, 32f);
        Material material2 = new Material(diffuseID,
                                          specularID, 32f);

        List<Entity> Models = new ArrayList<Entity>();

        //for (int i= 0; i < 2; i++) {
            Models.add(new Entity(staticModel,
                    new Vector3f(0, -5, -20),
                    rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 10, material));
            Models.add(new Entity(staticModel2,
                    new Vector3f(20*rand.nextFloat(), 20*rand.nextFloat(), -20*rand.nextFloat()),
                    0, 0, 0, 3, material2));
        //}


        DirLight dirLight = new DirLight(new Vector3f(-0.2f, -1f, -0.3f),   //Direction
                                new Vector3f(0.01f, 0.01f, 0.01f),           //ambient
                                new Vector3f(0.01f, 0.01f, 0.01f),           //diffuse
                                new Vector3f(0.01f, 0.01f, 0.01f));          //specular

        Camera camera = new Camera();

        PointLight[] pointLights = new PointLight[10];
            for (int a = 0; a < 10; a++) {
                pointLights[a] = new PointLight(new Vector3f(rand.nextFloat()*40+10, rand.nextFloat()*1, rand.nextFloat()*-40),
                                                new Vector3f(1, 1, 1),
                                                new Vector3f(1, 1,1),
                                                new Vector3f(1, 1, 1));
            }

        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {

            camera.move();
            dirLight.setDirection(new Vector3f(10.0f * (float)Math.sin(time.getTimeSecond()), 0, 10.0f * (float)Math.cos(time.getTimeSecond())));

            for (Entity entity: Models) {
                renderer.processEntity(entity);

            }
            renderer.render(dirLight, pointLights, camera);
            DisplayManager.updateDisplay();
        }


        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
