package ballboy.model.entities.collision;

import ballboy.model.Entity;
import ballboy.model.Level;

/**
 * Collision logic for enemies.
 */
public class EnemyCollisionStrategy implements CollisionStrategy {
    private final Level level;

    public EnemyCollisionStrategy(Level level) {
        this.level = level;
    }

    @Override
    public void collideWith(
            Entity enemy,
            Entity hitEntity) {
        if (level.isHero(hitEntity)) {
            level.resetHero();
        }
    }
}
