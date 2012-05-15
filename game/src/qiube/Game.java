package qiube;

import qiube.util.Configuration;
import qiube.util.ServiceContainer;
import qiube.util.configuration.TreeInterface;
import qiube.util.configuration.TreeNode;

public class Game {

    protected ServiceContainer serviceContainer;

    public Game(Configuration configuration) {
        this.serviceContainer = new ServiceContainer(configuration);
        this.init();
    }

    public void init() {

    }

    public void run() {

    }

    public static void main(String[] $args) {
        try {
            Configuration configuration = new Configuration();
            Game game = new Game(configuration);
            game.run();
        } catch (Exception e) {
            System.err.print(String.format("Abort game ! %s", e.getMessage()));
        }
    }
}
