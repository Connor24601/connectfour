package connectfour.player;

import connectfour.graphics.Connect4Column;
import connectfour.graphics.Connect4Game;
import connectfour.graphics.Connect4Slot;

import java.util.Random;

public class MyAgent extends Agent
{
    Random r;
    Connect4Game game;
    boolean swich;
    boolean totals;
    boolean won;
    boolean defense;
    boolean doubletrapping;
    boolean doubletrapdefense;
    int bestColumn=3;
    /**
     * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
     * 
     * @param game The game the agent will be playing.
     * @param iAmRed True if the agent is Red, False if the agent is Yellow.
     */
    public MyAgent(Connect4Game game, boolean iAmRed)
    {
        super(game, iAmRed);
        r = new Random();
        this.game = game;
        swich = false;
        totals = false;
        won=false;
        defense = false;
        doubletrapping=false;
        doubletrapdefense=false;
    }

    /**
     * The move method is run every time it is this agent's turn in the game. You may assume that
     * when move() is called, the game has at least one open slot for a token, and the game has not
     * already been won.
     * 
     * By the end of the move method, the agent should have placed one token into the game at some
     * point.
     * 
     * After the move() method is called, the game engine will check to make sure the move was
     * valid. A move might be invalid if:
     * - No token was place into the game.
     * - More than one token was placed into the game.
     * - A previous token was removed from the game.
     * - The color of a previous token was changed.
     * - There are empty spaces below where the token was placed.
     * 
     * If an invalid move is made, the game engine will announce it and the game will be ended.
     * 
     */
    public void move()
    {
        
        System.out.println();
        //myRandomAgent
        /*
        int i = r.nextInt(game.getColumnCount());
        if (!game.getColumn(i).getIsFull()){
            moveOnColumn(i);
        }
        else {
            this.move();
        }
        */
        //myAgent
        int[] best = new int[this.playableSlots().length];
        bestColumn=3;
        won = false;
        defense=false;
        doubletrapping = false;
        doubletrapdefense=false;
        for (int n=0;n<this.playableSlots().length;n++) {
            //System.out.println(n + " " + this.playableSlots()[n]);
            if (n+3<this.playableSlots().length && (n-1>0 || n+4<this.playableSlots().length)) {
            
                if (this.yellowRunLength(n,this.playableSlots()[n],1,0)>=2 && this.playableSlots()[n]==this.playableSlots()[n+3] && this.playableSlots()[(n-1>0? n-1 :n+4)]==this.playableSlots()[n]) {
                    
                    defense = true;
                    bestColumn = n;
                    System.out.println("Double trap prevention on " + n);
                }
                    
            }
                
                
        
            
                
                
        
            
            
            if (this.redRunLength(n,this.playableSlots()[n],0,1) + this.redRunLength(n,this.playableSlots()[n],0,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                bestColumn=n;
                won=true;
                System.out.println("Winning on " + n);
                break;
            }
            else if (this.yellowRunLength(n,this.playableSlots()[n],0,1) + this.yellowRunLength(n,this.playableSlots()[n],0,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                defense=true;
                bestColumn = n;
                
            }
            else if (this.redRunLength(n,this.playableSlots()[n],1,1) + this.redRunLength(n,this.playableSlots()[n],-1,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                bestColumn=n;
                won=true;
                System.out.println("Winning on " + n);
                break;
            }
            else if (this.yellowRunLength(n,this.playableSlots()[n],1,1) + this.yellowRunLength(n,this.playableSlots()[n],-1,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                defense=true;
                bestColumn = n;
                
            }
            else if (this.redRunLength(n,this.playableSlots()[n],-1,1)+this.redRunLength(n,this.playableSlots()[n],1,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                bestColumn=n;
                won=true;
                System.out.println("Winning on " + n);
                break;
            }
            else if (this.yellowRunLength(n,this.playableSlots()[n],-1,1) + this.yellowRunLength(n,this.playableSlots()[n],1,-1)>=3 && (!game.getColumn(n).getIsFull())) {
                defense=true;
                bestColumn = n;
                
            }
            else if (this.redRunLength(n,this.playableSlots()[n],1,0) + this.redRunLength(n,this.playableSlots()[n],-1,0)>=3 && (!game.getColumn(n).getIsFull())) {
                bestColumn=n;
                won=true;
                System.out.println("Winning on " + n);
                break;
            }
            else if (this.yellowRunLength(n,this.playableSlots()[n],1,0) + this.yellowRunLength(n,this.playableSlots()[n],-1,0)>=3 && (!game.getColumn(n).getIsFull())) {
                defense=true;
                bestColumn = n;
                
            }
            
            if (!(this.playableSlots()[n]-2<0) && !defense && !won) {
                if ((this.redRunLength(n,this.playableSlots()[n]-1,0,1) + this.redRunLength(n,this.playableSlots()[n]-1,0,-1)>=3 ||
                this.redRunLength(n,this.playableSlots()[n]-1,1,1) + this.redRunLength(n,this.playableSlots()[n]-1,-1,-1)>=3 ||
                this.redRunLength(n,this.playableSlots()[n]-1,-1,1) + this.redRunLength(n,this.playableSlots()[n]-1,1,-1)>=3 ||
                this.redRunLength(n,this.playableSlots()[n]-1,1,0) + this.redRunLength(n,this.playableSlots()[n]-1,-1,0)>=3) &&
                !(this.yellowRunLength(n,this.playableSlots()[n]-1,0,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,0,-1)>=3 ||
                this.yellowRunLength(n,this.playableSlots()[n]-1,1,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,-1,-1)>=3 ||
                this.yellowRunLength(n,this.playableSlots()[n]-1,-1,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,1,-1)>=3 ||
                this.yellowRunLength(n,this.playableSlots()[n]-1,1,0) + this.yellowRunLength(n,this.playableSlots()[n]-1,-1,0)>=3)){
                    System.out.println("would win in 2 moves on " + n);
                    if (this.redRunLength(n,this.playableSlots()[n]-2,1,1) + this.redRunLength(n,this.playableSlots()[n]-2,-1,-1)>=3 ||
                    this.redRunLength(n,this.playableSlots()[n]-2,-1,1) + this.redRunLength(n,this.playableSlots()[n]-2,1,-1)>=3 ||
                    this.redRunLength(n,this.playableSlots()[n]-2,0,1) + this.redRunLength(n,this.playableSlots()[n]-2,0,-1)>=3 ||
                    this.redRunLength(n,this.playableSlots()[n]-2,1,0) + this.redRunLength(n,this.playableSlots()[n]-2,-1,0)>=3) {
                        bestColumn = defense ? bestColumn : n;
                        doubletrapping = true;
                        System.out.println("DoubleTrapping on :" + n);
                        
                    }
                    best[n]-=2;
                }
                
            }
            if (!defense && !won && !doubletrapping) {
                offensiveDoubletrap(n);
                defensiveDoubletrap(n);
            }
            totals = true;
            best[n]=(this.redRunLength(n,this.playableSlots()[n],0,1) + this.redRunLength(n,this.playableSlots()[n],0,-1))<0 ? 0 : this.redRunLength(n,this.playableSlots()[n],0,1) + this.redRunLength(n,this.playableSlots()[n],0,-1);
            best[n]+=(this.redRunLength(n,this.playableSlots()[n],-1,1) + this.redRunLength(n,this.playableSlots()[n],1,-1))<0 ? 0 : this.redRunLength(n,this.playableSlots()[n],-1,1) + this.redRunLength(n,this.playableSlots()[n],1,-1);
            best[n]+=(this.redRunLength(n,this.playableSlots()[n],-1,0) + this.redRunLength(n,this.playableSlots()[n],1,0))<0 ? 0 : this.redRunLength(n,this.playableSlots()[n],-1,0) + this.redRunLength(n,this.playableSlots()[n],1,0);
            best[n]+=(this.redRunLength(n,this.playableSlots()[n],1,1) + this.redRunLength(n,this.playableSlots()[n],-1,-1))<0 ? 0 : this.redRunLength(n,this.playableSlots()[n],-1,-1) + this.redRunLength(n,this.playableSlots()[n],1,1);
            totals = false;
            
            
            System.out.print("Slot: " + n + " offensive opportunity " + best[n]);
            best[n] *= 3;
            best[n] += this.getFutureOpponentOpportunityCost(n);
            System.out.print(" and defensive opportunity " + this.getFutureOpponentOpportunityCost(n));
            System.out.println(" for a total of " + best[n]);
        }
        
        
        
        
        System.out.println("Winning: " + won);
        System.out.println("Defense: " + defense);
        System.out.println("double trapping: " + doubletrapping);
        System.out.println("Double trap defense: " + doubletrapdefense);
        
        
        if (!won&&!defense && !doubletrapping && !doubletrapdefense) {
            
            int runs = 0;
            
            bestColumn = r.nextInt(game.getColumnCount());
            while (runs<50 && (game.getColumn(bestColumn).getIsFull() || ((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3)))) {
                runs++;
                bestColumn = r.nextInt(game.getColumnCount());
            }
            while (runs>=50 && game.getColumn(bestColumn).getIsFull()) {
                bestColumn = r.nextInt(game.getColumnCount());
            }
            int bestColumnLength=best[bestColumn];
            int futureOpponentOpportunity;
            
            
            System.out.println("random column choice: " + bestColumn);
            for (int n=0; n<best.length;n++) {
                if (best[n] > bestColumnLength && (!game.getColumn(n).getIsFull())) {
                    
                    if (!(this.yellowRunLength(n,this.playableSlots()[n]-1,1,0) + this.yellowRunLength(n,this.playableSlots()[n]-1,-1,0)>=3)
                    && !(this.yellowRunLength(n,this.playableSlots()[n]-1,-1,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,1,-1)>=3)
                    && !(this.yellowRunLength(n,this.playableSlots()[n]-1,1,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,-1,-1)>=3)
                    && !(this.yellowRunLength(n,this.playableSlots()[n]-1,0,1) + this.yellowRunLength(n,this.playableSlots()[n]-1,0,-1)>=3)) {
                        bestColumnLength = best[n];
                        bestColumn = n;
                        
                        
                    }
                    else {
                        System.out.println("aborted " + n + " due to yellow win");
                    }
                    
                }
            }
            
        }
       
        System.out.println("went in " + bestColumn);
        moveOnColumn(bestColumn);
        
    }

    /**
     * Drops a token into a particular column so that it will fall to the bottom of the column.
     * If the column is already full, nothing will change.
     * 
     * @param columnNumber The column into which to drop the token.
     */
    public void offensiveDoubletrap(int n) {
        //((col)>=game.getColumnCount() || (slot)>=game.getRowCount() || (col)<0 || (slot)<0)
        if ((n+2<game.getColumnCount() && n-2>=0 && this.playableSlots()[n+2]!=-1 && this.playableSlots()[n-2]!=-1) && ((this.playableSlots()[n+2]-this.playableSlots()[n-2]==0 && this.playableSlots()[n+2]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+2]-this.playableSlots()[n-2]==4 && this.playableSlots()[n+2]-this.playableSlots()[n]==2)
        || (this.playableSlots()[n+2]-this.playableSlots()[n-2]==-4 && this.playableSlots()[n+2]-this.playableSlots()[n]==-2)))
        {
            System.out.println("5 consecutive slots on " + n);
            //if there are 5 consecutive slots...^^^
            int slotchange = this.playableSlots()[n+2]-this.playableSlots()[n];
            slotchange /= 2;
            System.out.println("Slotchange " + slotchange);
            if (!game.getColumn(n-2).getSlot(this.playableSlots()[n] +(2*slotchange)).getIsFilled() && !game.getColumn(n+2).getSlot(this.playableSlots()[n]-(2*slotchange)).getIsFilled())
            {
                //and we have the 2 extremes empty
                System.out.println("2 extremes empty");
                
                if (iAmRed && game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsRed() && game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))) {
                    System.out.println("double trap");
                    bestColumn = n;
                    doubletrapping = true;
                    
                }
                if (!iAmRed && !game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsRed() && !game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsRed()
                && game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsFilled() && game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))) {
                    System.out.println("double trap");
                    bestColumn = n;
                    doubletrapping = true;
                    
                }
            }
        }
        if (n+3<game.getColumnCount() && n-1>=0 && this.playableSlots()[n+3]!=-1 && this.playableSlots()[n-1]!=-1 && ((this.playableSlots()[n+3]-this.playableSlots()[n-1]==0 && this.playableSlots()[n+3]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+3]-this.playableSlots()[n-1]==4 && this.playableSlots()[n+3]-this.playableSlots()[n]==3)
        || (this.playableSlots()[n+3]-this.playableSlots()[n-1]==-4 && this.playableSlots()[n+3]-this.playableSlots()[n]==-3))) {
            System.out.println("2 and 3 consecutive slots on " + n);
            int slotchange = this.playableSlots()[n+3]-this.playableSlots()[n];
            slotchange /= 3;
            System.out.println("slotchange " + slotchange);
            if (!game.getColumn(n-1).getSlot(this.playableSlots()[n] - slotchange).getIsFilled() && !game.getColumn(n+3).getSlot(this.playableSlots()[n]+(3*slotchange)).getIsFilled()) {
                System.out.println("2 extremes empty");
                
                if (iAmRed && game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsRed() && game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap?");
                    bestColumn=n;
                    doubletrapping=true;
                }
                if (!iAmRed && !game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsRed() && !game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsRed()
                && game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsFilled() && game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap?");
                    bestColumn=n;
                    doubletrapping=true;
                }
            }
        }
        if (n+1<game.getColumnCount() && n-3>=0 && this.playableSlots()[n+1]!=-1 && this.playableSlots()[n-3]!=-1 && ((this.playableSlots()[n+1]-this.playableSlots()[n-3]==0 && this.playableSlots()[n+1]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+1]-this.playableSlots()[n-3]==4 && this.playableSlots()[n+1]-this.playableSlots()[n]==1)
        || (this.playableSlots()[n+1]-this.playableSlots()[n-3]==-4 && this.playableSlots()[n+1]-this.playableSlots()[n]==-1))) {
            System.out.println("3 and 2 consecutive slots on " + n);
            int slotchange = this.playableSlots()[n-3]-this.playableSlots()[n];
            slotchange /= -3;
            System.out.println("slotchange " + slotchange);
            if (!game.getColumn(n-3).getSlot(this.playableSlots()[n]-(3*slotchange)).getIsFilled() && !game.getColumn(n+1).getSlot(this.playableSlots()[n]+slotchange).getIsFilled()) {
                System.out.println("2 extremes empty");
                
                if (iAmRed && game.getColumn(n-1).getSlot(this.playableSlots()[n] -(slotchange)).getIsRed() && game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap 2?");
                    bestColumn=n;
                    doubletrapping=true;
                }
                if (!iAmRed && !game.getColumn(n-1).getSlot(this.playableSlots()[n]-(slotchange)).getIsRed() && !game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsRed()
                && game.getColumn(n-1).getSlot(this.playableSlots()[n] -(slotchange)).getIsFilled() && game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap 2?");
                    bestColumn=n;
                    doubletrapping=true;
                }
            }
        }
    }
    public void defensiveDoubletrap(int n) {
        if ((n+2<game.getColumnCount() && n-2>=0 && this.playableSlots()[n+2]!=-1 && this.playableSlots()[n-2]!=-1) && ((this.playableSlots()[n+2]-this.playableSlots()[n-2]==0 && this.playableSlots()[n+2]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+2]-this.playableSlots()[n-2]==4 && this.playableSlots()[n+2]-this.playableSlots()[n]==2)
        || (this.playableSlots()[n+2]-this.playableSlots()[n-2]==-4 && this.playableSlots()[n+2]-this.playableSlots()[n]==-2)))
        {
            System.out.println("5 consecutive slots on " + n);
            //if there are 5 consecutive slots...^^^
            int slotchange = this.playableSlots()[n+2]-this.playableSlots()[n];
            slotchange /= 2;
            System.out.println("Slotchange " + slotchange);
            if (!game.getColumn(n-2).getSlot(this.playableSlots()[n] +(2*slotchange)).getIsFilled() && !game.getColumn(n+2).getSlot(this.playableSlots()[n]-(2*slotchange)).getIsFilled())
            {
                //and we have the 2 extremes empty
                System.out.println("2 extremes empty");
                
                if (!iAmRed && game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsRed() && game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))) {
                    System.out.println("double trap defense");
                    bestColumn = n;
                    doubletrapdefense = true;
                    
                }
                if (iAmRed && !game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsRed() && !game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsRed()
                && game.getColumn(n+1).getSlot(this.playableSlots()[n+2] + slotchange).getIsFilled() && game.getColumn(n-1).getSlot(this.playableSlots()[n-2] - slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))) {
                    System.out.println("double trap defense");
                    bestColumn = n;
                    doubletrapdefense = true;
                    
                }
            }
        }
        if (n+3<game.getColumnCount() && n-1>=0 && this.playableSlots()[n+3]!=-1 && this.playableSlots()[n-1]!=-1 && ((this.playableSlots()[n+3]-this.playableSlots()[n-1]==0 && this.playableSlots()[n+3]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+3]-this.playableSlots()[n-1]==4 && this.playableSlots()[n+3]-this.playableSlots()[n]==3)
        || (this.playableSlots()[n+3]-this.playableSlots()[n-1]==-4 && this.playableSlots()[n+3]-this.playableSlots()[n]==-3))) {
            System.out.println("2 and 3 consecutive slots on " + n);
            int slotchange = this.playableSlots()[n+3]-this.playableSlots()[n];
            slotchange /= 3;
            System.out.println("slotchange " + slotchange);
            if (!game.getColumn(n-1).getSlot(this.playableSlots()[n] - slotchange).getIsFilled() && !game.getColumn(n+3).getSlot(this.playableSlots()[n]+(3*slotchange)).getIsFilled()) {
                System.out.println("2 extremes empty");
                
                if (!iAmRed && game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsRed() && game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap defense?");
                    bestColumn=n;
                    doubletrapdefense=true;
                }
                if (iAmRed && !game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsRed() && !game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsRed()
                && game.getColumn(n+1).getSlot(this.playableSlots()[n+3] - (2*slotchange)).getIsFilled() && game.getColumn(n+2).getSlot(this.playableSlots()[n+3] - slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap defense?");
                    bestColumn=n;
                    doubletrapdefense=true;
                }
            }
        }
        if (n+1<game.getColumnCount() && n-3>=0 && this.playableSlots()[n+1]!=-1 && this.playableSlots()[n-3]!=-1 && ((this.playableSlots()[n+1]-this.playableSlots()[n-3]==0 && this.playableSlots()[n+1]-this.playableSlots()[n]==0)
        || (this.playableSlots()[n+1]-this.playableSlots()[n-3]==4 && this.playableSlots()[n+1]-this.playableSlots()[n]==1)
        || (this.playableSlots()[n+1]-this.playableSlots()[n-3]==-4 && this.playableSlots()[n+1]-this.playableSlots()[n]==-1))) {
            System.out.println("3 and 2 consecutive slots on " + n);
            int slotchange = this.playableSlots()[n-3]-this.playableSlots()[n];
            slotchange /= -3;
            System.out.println("slotchange " + slotchange);
            if (!game.getColumn(n-3).getSlot(this.playableSlots()[n]-(3*slotchange)).getIsFilled() && !game.getColumn(n+1).getSlot(this.playableSlots()[n]+slotchange).getIsFilled()) {
                System.out.println("2 extremes empty");
                
                if (!iAmRed && game.getColumn(n-1).getSlot(this.playableSlots()[n] -(slotchange)).getIsRed() && game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsRed()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap defense 2?");
                    bestColumn=n;
                    doubletrapdefense=true;
                }
                if (iAmRed && !game.getColumn(n-1).getSlot(this.playableSlots()[n]-(slotchange)).getIsRed() && !game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsRed()
                && game.getColumn(n-1).getSlot(this.playableSlots()[n] -(slotchange)).getIsFilled() && game.getColumn(n-2).getSlot(this.playableSlots()[n-3] + slotchange).getIsFilled()
                && !((this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1)>=3)
                    || (this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1)>=3))){
                    System.out.println("double trap defense 2?");
                    bestColumn=n;
                    doubletrapdefense=true;
                }
            }
        }
        
    }
    public int getFutureOpponentOpportunityCost(int slot) {
        int bestColumn=slot;
        int futureOpponentOpportunity;
        totals=true;
        if (this.getLowestEmptyIndex(game.getColumn(bestColumn)) -1 < 0) {
                futureOpponentOpportunity = this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,-1);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,-1);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,0);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],0,-1);
                futureOpponentOpportunity *= 2;
        }
        else
        {
                futureOpponentOpportunity = this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,-1);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,-1);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],-1,0);
                futureOpponentOpportunity += this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn],0,-1);
                futureOpponentOpportunity *= 2;
                futureOpponentOpportunity -= this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,-1);
                futureOpponentOpportunity -= this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,-1);
                futureOpponentOpportunity -= this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,1,0) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,-1,0);
                futureOpponentOpportunity -= this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,1) + this.yellowRunLength(bestColumn,this.playableSlots()[bestColumn]-1,0,-1);
        }
        totals=false;
        return futureOpponentOpportunity;
        
    }
    public void moveOnColumn(int columnNumber)
    {
        int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));   // Find the top empty slot in the column
                                                                                                  // If the column is full, lowestEmptySlot will be -1
        if (lowestEmptySlotIndex > -1)  // if the column is not full
        {
            Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);  // get the slot in this column at this index
            if (iAmRed) // If the current agent is the Red player...
            {
                lowestEmptySlot.addRed(); // Place a red token into the empty slot
            }
            else // If the current agent is the Yellow player (not the Red player)...
            {
                lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
            }
        }
    }
    public int[] playableSlots() {
        int[] slots = new int[game.getColumnCount()];
        for (int n=0; n < game.getColumnCount();n++) {
            slots[n] = this.getLowestEmptyIndex(game.getColumn(n));
        }
        return slots;
    }
    public int redRunLength(int col,int slot,int colchange,int slotchange) {
        if (!iAmRed && !this.swich) {
            swich = true;
            int returnval = this.yellowRunLength(col,slot,colchange,slotchange);
            swich = false;
            return returnval;
        }
        int length = 0;
        int islot = slot;
        int icol = col;
        int len = 0;
        boolean skip = false;
        while (true) {
            col += colchange;
            slot += slotchange;
            
            if ((col)>=game.getColumnCount() || (slot)>=game.getRowCount() || (col)<0 || (slot)<0)  {
                
                break;
            }
            //System.out.println("col" + col + "slot" + slot);
            if (!game.getColumn(col).getSlot(slot).getIsRed()){
                if (totals && !skip && !game.getColumn(col).getSlot(slot).getIsFilled()) {
                    skip = true;
                    length--;
                }
                else {
                   while ((skip && totals && (!game.getColumn(col).getSlot(slot).getIsFilled() || game.getColumn(col).getSlot(slot).getIsRed()))) {
                       col += colchange;
                       slot += slotchange;
                       len ++;
                       if ((col)>=game.getColumnCount() || (slot)>=game.getRowCount() || (col)<0 || (slot)<0) {
                           break;
                       }
                   }
                   break;
                }
               
            }
            length++;
            
        }
        
                
        while (true) {
            islot -= slotchange;
            icol -= colchange;
            if ((icol)>=game.getColumnCount() || (islot)>=game.getRowCount() || (icol)<0 || (islot)<0) {
                length = ((len + length) < 3 && totals)? 0 : length;
                break;
            }
            if (!game.getColumn(icol).getSlot(islot).getIsRed() && game.getColumn(icol).getSlot(islot).getIsFilled()){
                length = ((len + length) < 3 && totals)? 0 : length;
                break;
            }
            len ++;
        }
        return length>3 ? 3 : length;
    }
    public int yellowRunLength(int col,int slot,int colchange,int slotchange) {
        if (!iAmRed && !this.swich) {
            swich = true;
            int returnval = this.redRunLength(col,slot,colchange,slotchange);
            swich = false;
            return returnval;
        }
        int length = 0;
        int islot = slot;
        int icol = col;
        int len = 0;
        boolean skip = false;
        while (true) {
            col += colchange;
            slot += slotchange;
            
            if ((col)>=game.getColumnCount() || (slot)>=game.getRowCount() || (col)<0 || (slot)<0) {
                
               
                break;
            }
            //System.out.println("col" + col + "slot" + slot);
            
            if (game.getColumn(col).getSlot(slot).getIsRed() || !game.getColumn(col).getSlot(slot).getIsFilled()){
                if (totals && !skip && !game.getColumn(col).getSlot(slot).getIsFilled()) {
                    skip = true;
                    length--;
                }
                else {
                    while ((skip && totals && !game.getColumn(col).getSlot(slot).getIsRed())) {
                        col += colchange;
                        slot += slotchange;
                        len ++;
                        if ((col)>=game.getColumnCount() || (slot)>=game.getRowCount() || (col)<0 || (slot)<0) {
                            break;
                        }
                    }
                    break;
                }
                
            }
            length++;
            
        }
        
        while (true) {
            islot -= slotchange;
            icol -= colchange;
            if ((icol)>=game.getColumnCount() || (islot)>=game.getRowCount() || (icol)<0 || (islot)<0) {
                length = len + length < 3 && totals? 0 : length;
                break;
            }
            if (game.getColumn(icol).getSlot(islot).getIsRed()){
                length = len + length < 3 && totals? 0 : length;
                break;
            }
            len ++;
        }
        return length > 3 ? 3 : length;
    }
    /**
     * Returns the index of the top empty slot in a particular column.
     * 
     * @param column The column to check.
     * @return the index of the top empty slot in a particular column; -1 if the column is already full.
     */
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

    /**
     * Returns a random valid move. If your agent doesn't know what to do, making a random move
     * can allow the game to go on anyway.
     * 
     * @return a random valid move.
     */
    public int randomMove()
    {
        int i = r.nextInt(myGame.getColumnCount());
        while (getLowestEmptyIndex(myGame.getColumn(i)) == -1)
        {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }

    /**
     * Returns the column that would allow the agent to win.
     * 
     * You might want your agent to check to see if it has a winning move available to it so that
     * it can go ahead and make that move. Implement this method to return what column would
     * allow the agent to win.
     *
     * @return the column that would allow the agent to win.
     */
    public int iCanWin()
    {
        return 0;
    }

    /**
     * Returns the column that would allow the opponent to win.
     * 
     * You might want your agent to check to see if the opponent would have any winning moves
     * available so your agent can block them. Implement this method to return what column should
     * be blocked to prevent the opponent from winning.
     *
     * @return the column that would allow the opponent to win.
     */
    public int theyCanWin()
    {
        return 0;
    }

    /**
     * Returns the name of this agent.
     *
     * @return the agent's name
     */
    public String getName()
    {
        return "My connectfour.player.Agent";
    }
}
