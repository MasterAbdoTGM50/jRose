package matgm50.jrose.demo;

import matgm50.jrose.core.Game;

public class Launcher {

    public static void main(String[] args) {

        Game game = new Game();
        game.setActiveScene(new Countdown(game));
        game.run();

    }

}