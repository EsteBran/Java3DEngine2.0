package Core;

import Entities.Camera;
import Entities.Entity;
import Models.TextureModel;
import Rendering.DisplayManager;
import Rendering.Loader;
import Models.RawModel;
import Rendering.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class MainGame {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        float[] vertices = {
                -0.5f,0.5f,0,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,0.5f,0,

                -0.5f,0.5f,1,
                -0.5f,-0.5f,1,
                0.5f,-0.5f,1,
                0.5f,0.5f,1,

                0.5f,0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1,
                0.5f,0.5f,1,

                -0.5f,0.5f,0,
                -0.5f,-0.5f,0,
                -0.5f,-0.5f,1,
                -0.5f,0.5f,1,

                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,

                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1

        };

        float[] textureCoords = { 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0, 0,0, 0,1, 1,1, 1,0
        };

        int[] indices = { 0,1,3,  3,1,2,  4,5,7,  7,5,6,  8,9,11,  11,9,10,  12,13,15,  15,13,14,  16,17,19,  19,17,18,  20,21,23,  23,21,22};

        RawModel model = loader.loadtoVAO(vertices, textureCoords,indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("bricks"));
        TextureModel staticModel = new TextureModel(model, texture);

        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);

        Camera camera = new Camera();

        while(!Display.isCloseRequested()) {

            camera.move();
            entity.increaseRotation(1, 1, 0);
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            //game logic
           renderer.render(entity, shader);
           shader.stop();
            DisplayManager.updateDisplay();
        }


        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
