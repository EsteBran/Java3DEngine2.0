package Rendering;

import Entities.Camera;
import Entities.Entity;
import Entities.DirLight;
import Entities.PointLight;
import Models.TexturedModel;
import Shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private StaticShader shader = new StaticShader();
    private Renderer renderer = new Renderer(shader);
    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

    public void render(DirLight sun, PointLight[] pointLights, Camera camera) {
        renderer.prepare();
        shader.start();
        shader.loadDirLight(sun);
        shader.loadPointLight(pointLights);
        shader.loadViewMatrix(camera);

        renderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public void processEntity(Entity entity){
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void cleanUp() {
        shader.cleanUp();
    }
}
