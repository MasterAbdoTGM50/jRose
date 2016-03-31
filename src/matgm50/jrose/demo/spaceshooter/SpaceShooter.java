package matgm50.jrose.demo.spaceshooter;

import matgm50.jrose.core.Game;
import matgm50.jrose.core.Scene;
import matgm50.jrose.core.display.Resolution;
import matgm50.jrose.core.gl.Graphics;
import matgm50.jrose.core.input.Key;
import matgm50.jrose.core.kiss.Entity;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class SpaceShooter extends Scene {

    boolean won = false;

    Entity ship = new Entity("demo/ship.png", 100, 100);
    List<Entity> balls = new ArrayList<>();
    List<Entity> targets = new ArrayList<>();
    Entity winMsg = new Entity("demo/win.png", 200, 200);

    public SpaceShooter(Game game) { super(game); }

    @Override
    public void init() {

        game.display.setTitle("jRose2D: Space Shooter Demo");
        game.display.switchResolution(new Resolution(600, 600));

        for(int i = 0; i < 5; i++) {

            Entity target = new Entity("demo/target.png", 100, 100);
            target.setPos((i * target.width + 10) + 40, 490);
            targets.add(target);

        }

        winMsg.setPos(200, 200);

        ship.setPos(250, 0);

        game.input.registerInputState(new Key("right", GLFW.GLFW_KEY_RIGHT));
        game.input.registerInputState(new Key("left", GLFW.GLFW_KEY_LEFT));
        game.input.registerInputState(new Key("shoot", GLFW.GLFW_KEY_SPACE));

    }

    @Override
    public void update(double delta) {

        Graphics.Shaders.base2D.bind();

        if(won) {

            winMsg.draw();

        } else {

            if(game.input.getKey("right").isHeld()) {

                ship.move((float)(200 * delta), 0);

            }

            if(game.input.getKey("left").isHeld()) {

                ship.move((float)(-200 * delta), 0);

            }

            if(game.input.getKey("shoot").isPressed()) {

                Entity ball = new Entity("demo/ball.png", 20, 20);
                ball.setPos(ship.getX() + 40, ship.getY() + 110);
                balls.add(ball);

            }

            for(int i = 0; i < balls.size(); i++) {

                boolean markedForRemoval = false;

                for(int j = 0; j < targets.size(); j++) {

                    if(balls.get(i).isColliding(targets.get(j))) {

                        targets.get(j).kill();
                        targets.remove(j);
                        markedForRemoval = true;

                    }

                }

                if(balls.get(i).getY() > 600) {

                    balls.get(i).kill();
                    markedForRemoval = true;

                }

                if (markedForRemoval) { balls.remove(i); }

            }

            for(Entity ball : balls) {

                ball.move(0, (float)(300 * delta));
                ball.draw();

            }

            targets.forEach(Entity::draw);

            ship.draw();

            if(targets.size() == 0) { won = true; }

        }

    }

    @Override
    public void onScreenResize(int width, int height) {}

    @Override
    public void deinit() {}

}
