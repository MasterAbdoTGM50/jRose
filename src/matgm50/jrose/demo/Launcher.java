package matgm50.jrose.demo;

import matgm50.jrose.core.Game;
import matgm50.jrose.demo.spaceshooter.SpaceShooter;

public class Launcher {


    //This comment was added to follow the tradition of saying hello to Starlog
    public static void main(String[] args) {

        Game game = new Game();
        game.setActiveScene(new SpaceShooter(game));
        game.run();

    }

}