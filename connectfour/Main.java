import connectfour.graphics.Connect4Frame;
import connectfour.graphics.Connect4Game;
import connectfour.player.Agent;
import connectfour.player.MyAgent;
import connectfour.player.Human;
/**
 * The main driver of the program. This file will create the game, create the two agents,
 * and create the window for the game. After that, connectfour.graphics.Connect4Frame runs everything.
 * 
 * You should only modify this class to change which agents are playing.
 */
public class Main
{
    public static void main(String[] args)
    {
        connectfour.graphics.Connect4Game game = new connectfour.graphics.Connect4Game(7, 6); // create the game; these sizes can be altered for larger or smaller games
        //connectfour.player.Agent yellowPlayer = new connectfour.player.NestedAgent(game, false,3); // create the yellow player, any subclass of connectfour.player.Agent
        connectfour.player.Agent redPlayer = new connectfour.player.Human(game,true);
        connectfour.player.Agent yellowPlayer = new connectfour.player.MyAgent(game,false);
        connectfour.graphics.Connect4Frame mainframe = new connectfour.graphics.Connect4Frame(game, redPlayer, yellowPlayer); // create the game window
    }
}
    