package Core;

import Entities.Camera;
import Entities.Entity;
import Entities.DirLight;
import Entities.Material;
import Models.TexturedModel;
import Rendering.*;
import Models.RawModel;
import Textures.ModelTexture;
import org.lwjgl.opengl.Display;
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

        RawModel model = OBJLoader.loadObjModel("monkeyHigh", loader);
        RawModel model2 = OBJLoader.loadObjModel("monkeyHigh", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("bricks"));
        ModelTexture texture2 = new ModelTexture(loader.loadTexture("bricks"));

        TexturedModel staticModel = new TexturedModel(model, texture);
        TexturedModel staticModel2 = new TexturedModel(model2, texture2);

        Random random = new Random();
        Material material = new Material(diffuseID,
                                         specularID, 32f);
        Material material2 = new Material(diffuseID,
                                          specularID, 32f);

        List<Entity> Models = new ArrayList<Entity>();


            Models.add(new Entity(staticModel,
                       new Vector3f(5,0,-20),
                    random.nextFloat(), random.nextFloat(), random.nextFloat(),3, material));
            Models.add(new Entity(staticModel2,
                    new Vector3f(-5,0,-20),
                    0,0,0,3, material2));



        DirLight dirLight = new DirLight(new Vector3f(-0.2f, -1f, -0.3f),   //Direction
                                new Vector3f(0.2f, 0.2f, 0.2f),             //ambient
                                new Vector3f(0.5f, 0.5f, 0.5f),             //diffuse
                                new Vector3f(1.0f, 1.0f, 1.0f));            //specular
        Camera camera = new Camera();


        MasterRenderer renderer = new MasterRenderer();

        while(!Display.isCloseRequested()) {

            camera.move();
            dirLight.setDirection(new Vector3f(10.0f * (float)Math.sin(time.getTimeSecond()), 0, 10.0f * (float)Math.cos(time.getTimeSecond())));


            for (Entity entity: Models) {
                renderer.processEntity(entity);

            }
            renderer.render(dirLight, camera);
            DisplayManager.updateDisplay();
        }


        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
