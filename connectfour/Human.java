
/**
 * Write a description of class Human here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import javax.swing.*;
public class Human extends Agent
{
    // instance variables - replace the example below with your own
    Connect4Game game;
    boolean red;
    int moves=0;
    /**
     * Constructor for objects of class Human
     */
    public Human(Connect4Game game, boolean iAmRed)
    {
        // initialise instance variables
        super(game,iAmRed);
        this.red=iAmRed;
        moves = 0;
    }

    public void move()
    {
        Scanner in = new Scanner(System.in);
        
        
        
        moves ++;
        String col = JOptionPane.showInputDialog(null,"Column? ");
        System.out.println("Move " + moves + " in col = " + col);
        moveOnColumn(Integer.parseInt(col));
        
        
        
    }
    public int getLowestEmptyIndex(Connect4Column column) {
        int lowestEmptySlot = -1;
        for  (int i = 0; i < column.getRowCount(); i++)
        {
            if (!column.getSlot(i).getIsFilled())
            {
                lowestEmptySlot = i;
            }
        }
        return lowestEmptySlot;
    }
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (red) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }
    public String getName()
    {
        return "Human";
    }
}
