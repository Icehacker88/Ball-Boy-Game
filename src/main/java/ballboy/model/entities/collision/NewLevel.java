package ballboy.model.entities.collision;

import ballboy.ConfigurationParser;
import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.GameEngineImpl;
import ballboy.model.Level;
import ballboy.model.factories.*;
import ballboy.model.levels.LevelImpl;
import ballboy.model.levels.PhysicsEngine;
import ballboy.model.levels.PhysicsEngineImpl;
import ballboy.view.GameWindow;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.stage.Stage;


//
//import ballboy.model.Entity;
//import ballboy.model.Level;
//import ballboy.model.factories.EntityFactory;
//import ballboy.model.levels.LevelImpl;
//import ballboy.model.levels.PhysicsEngine;
//import org.json.simple.JSONObject;
//
//
//
public class NewLevel {

//    private int levels;


//    public NewLevel(){
//
//    }

    public void newLevel() {
//        if (levels == 0) {//            this.levels = 1;
//        }

        final double frameDurationMilli = 17;
        PhysicsEngine engine = new PhysicsEngineImpl(frameDurationMilli);

        EntityFactoryRegistry entityFactoryRegistry = new EntityFactoryRegistry();
        entityFactoryRegistry.registerFactory("cloud", new CloudFactory());
        entityFactoryRegistry.registerFactory("enemy", new EnemyFactory());
        entityFactoryRegistry.registerFactory("background", new StaticEntityFactory(Entity.Layer.BACKGROUND));
        entityFactoryRegistry.registerFactory("static", new StaticEntityFactory(Entity.Layer.FOREGROUND));
        entityFactoryRegistry.registerFactory("finish", new FinishFactory());
        entityFactoryRegistry.registerFactory("hero", new BallboyFactory());

        ConfigurationParser configuration = new ConfigurationParser();
        JSONObject parsedConfiguration = null;
        parsedConfiguration = configuration.parseConfig("config.json");
//        Integer levelIndex = ((Number) parsedConfiguration.get("currentLevelIndex")).intValue();
        JSONArray levelConfigs = (JSONArray) parsedConfiguration.get("levels");
        JSONObject levelConfig = (JSONObject) levelConfigs.get(BallboyCollisionStrategy.count);
        Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, frameDurationMilli);
        GameEngine gameEngine = new GameEngineImpl(level);
//
        GameWindow window = new GameWindow(gameEngine, 1200, 900, frameDurationMilli);
        window.run();

//        primaryStage.setTitle("Ballboy");
//        primaryStage.setScene(window.getScene());
//        primaryStage.setResizable(false);
//        primaryStage.show();
//
//        window.run();

    }
}