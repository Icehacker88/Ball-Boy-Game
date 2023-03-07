package ballboy.model.entities.collision;

import ballboy.model.Entity;
import ballboy.model.Level;
import javafx.stage.Stage;

/**
 * Non-physical collision behaviour for the hero. This is delegated to by the
 * hero instance whenever it collides with another entity, static or dynamic.
 */
public class BallboyCollisionStrategy implements CollisionStrategy {
    public static int count = 0;
    private final Level level;
//    public int count = 0;

    public BallboyCollisionStrategy(Level level) {
        this.level = level;
    }

    @Override
    public void collideWith(
            Entity ballboy,
            Entity hitEntity) {
        NewLevel newlevel = new NewLevel();

        if (level.isFinish(hitEntity)) {
//            Integer levelIndex = ((Number) parsedConfiguration.get("currentLevelIndex")).intValue();
//            levelIndex += 1;
            count += 1;

//            JSONArray levelConfigs = (JSONArray) parsedConfiguration.get("levels");
//            JSONObject levelConfig = (JSONObject) levelConfigs.get(levelIndex+1);
            if (count > 2){
                level.finish();
            }else {
                newlevel.newLevel();
            }

        }
    }

//    public void setLevel(){
//
//    }
}
