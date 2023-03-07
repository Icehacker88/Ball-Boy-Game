package ballboy;

import ballboy.model.Entity;
import ballboy.model.GameEngine;
import ballboy.model.GameEngineImpl;
import ballboy.model.Level;
import ballboy.model.entities.collision.BallboyCollisionStrategy;
import ballboy.model.factories.BallboyFactory;
import ballboy.model.factories.CloudFactory;
import ballboy.model.factories.EnemyFactory;
import ballboy.model.factories.EntityFactoryRegistry;
import ballboy.model.factories.FinishFactory;
import ballboy.model.factories.StaticEntityFactory;
import ballboy.model.levels.LevelImpl;
import ballboy.model.levels.PhysicsEngine;
import ballboy.model.levels.PhysicsEngineImpl;
import ballboy.view.GameWindow;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

/*
 * Application root.
 *
 * Wiring of the dependency graph should be done manually in the start method.
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, String> params = getParameters().getNamed();

        String s = "Java 11 sanity check";
        if (s.isBlank()) {
            throw new IllegalStateException("You must be running Java 11+. You won't ever see this exception though" +
                    " as your code will fail to compile on Java 10 and below.");
        }

        ConfigurationParser configuration = new ConfigurationParser();
        JSONObject parsedConfiguration = null;
//        JSONObject levelConfiguration = null;
        try {
            parsedConfiguration = configuration.parseConfig("config.json");
//            levelConfiguration = configuration.parseConfig("config.json");
        } catch (ConfigurationParseException e) {
            System.out.println(e);
            System.exit(-1);
        }

        final double frameDurationMilli = 17;
        PhysicsEngine engine = new PhysicsEngineImpl(frameDurationMilli);

        EntityFactoryRegistry entityFactoryRegistry = new EntityFactoryRegistry();
        entityFactoryRegistry.registerFactory("cloud", new CloudFactory());
        entityFactoryRegistry.registerFactory("enemy", new EnemyFactory());
        entityFactoryRegistry.registerFactory("background", new StaticEntityFactory(Entity.Layer.BACKGROUND));
        entityFactoryRegistry.registerFactory("static", new StaticEntityFactory(Entity.Layer.FOREGROUND));
        entityFactoryRegistry.registerFactory("finish", new FinishFactory());
        entityFactoryRegistry.registerFactory("hero", new BallboyFactory());

//        Integer levelIndex = ((Number) levelConfiguration.get("currentLevelIndex")).intValue();

        Integer levelIndex = ((Number) parsedConfiguration.get("currentLevelIndex")).intValue();
        JSONArray levelConfigs = (JSONArray) parsedConfiguration.get("levels");
//        if (BallboyCollisionStrategy.count != 0) {
//            JSONObject levelConfig = (JSONObject) levelConfigs.get(BallboyCollisionStrategy.count);
//            Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, frameDurationMilli);
//            GameEngine gameEngine = new GameEngineImpl(level);
//
//            GameWindow window = new GameWindow(gameEngine, 900, 600, frameDurationMilli);
//            window.run();
//
//            primaryStage.setTitle("Ballboy");
//            primaryStage.setScene(window.getScene());
//            primaryStage.setResizable(false);
//            primaryStage.show();
//
//            window.run();
//        }else {
//            JSONObject levelConfig = (JSONObject) levelConfigs.get(levelIndex);
//            Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, frameDurationMilli);
//            GameEngine gameEngine = new GameEngineImpl(level);
//
//            GameWindow window = new GameWindow(gameEngine, 900, 600, frameDurationMilli);
//            window.run();
//
//            primaryStage.setTitle("Ballboy");
//            primaryStage.setScene(window.getScene());
//            primaryStage.setResizable(false);
//            primaryStage.show();
//
//            window.run();
//        }
//        Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, frameDurationMilli);
//        GameEngine gameEngine = new GameEngineImpl(level);
//        if (levelIndex == 0){
//
//        }

        JSONObject levelConfig = (JSONObject) levelConfigs.get(levelIndex);
        Level level = new LevelImpl(levelConfig, engine, entityFactoryRegistry, frameDurationMilli);
        GameEngine gameEngine = new GameEngineImpl(level);

        GameWindow window = new GameWindow(gameEngine, 900, 600, frameDurationMilli);
        window.run();

        primaryStage.setTitle("Ballboy");
        primaryStage.setScene(window.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
//
        window.run();
    }
}
