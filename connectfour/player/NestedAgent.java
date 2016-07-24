package connectfour.player;

import connectfour.graphics.Connect4Column;
import connectfour.graphics.Connect4Game;
import connectfour.graphics.Connect4Slot;

import java.util.Random;
import java.lang.Math;
public class NestedAgent extends Agent
{
    
    Connect4Game game;
    boolean red;
    char[][] board;
    Random r= new Random();
    int pow;
    int lvl;
    /**
     * Constructor for objects of class connectfour.player.Human
     */
    public NestedAgent(Connect4Game game, boolean iAmRed, int lvl)
    {
        
        super(game,iAmRed);
        this.red=iAmRed;
        this.game=game;
        this.lvl = lvl;
        pow = (int) Math.pow(10,lvl);
        this.pow=pow;
        System.out.println(pow);
    }

    public void move() {
        
        double[] totals = new double[game.getColumnCount()];
        board = game.getBoardMatrix();
        for (char[] n : board) {
            for (char i : n) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        boolean over;
        boolean won;
        boolean lost;
        int random;
        int column;
        int slot;
        int broken=0;
        int percent=-1;
        for (int col=0;col<game.getColumnCount()*pow; col++)
        {
            board = game.getBoardMatrix();
            over = false;
            won = false;
            lost=false;
            column=col%game.getColumnCount();
            if (percent != col*100/(game.getColumnCount()*pow)) {
                percent = col*100/(game.getColumnCount()*pow);
                //System.out.println(percent + "% done");
            }
            while (board[0][column]!=('B')){
                column = r.nextInt(game.getColumnCount());
            }
            random=column;
            double moves=0;
            while (available(board)&&!over) {
                moves++;
                
                //System.out.println("2 moves");
            
                
                slot=0;
                /*
                for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }*/
                //System.out.println("available " + available());
                while (board[slot][column]=='B' && slot<board.length-1) {
                    slot++;
                }
                //System.out.println("We're going in col " + column + " slot " + slot);
                
                if (board[slot][column]=='B') {
                    assert (slot==board.length-1);
                    board[slot][column]= iAmRed ? 'R' : 'Y';
                }
                else {
                    assert (board[slot][column]!='B');
                    slot--;
                    assert (board[slot][column]=='B');
                    board[slot][column]= iAmRed ? 'R' : 'Y';
                }
                /*for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }
                */
                //System.out.println("We went in col " + column + " slot " + slot);
                if (column-3>=0) {
                    if (board[slot][column]==board[slot][column-1] && board[slot][column-1]==board[slot][column-2] && board[slot][column-2]==board[slot][column-3]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (board[slot][column]==board[slot-1][column] && board[slot-1][column]==board[slot-2][column] && board[slot-2][column]==board[slot-3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column-1] && board[slot-1][column-1]==board[slot-2][column-2] && board[slot-2][column-2]==board[slot-3][column-3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (board[slot][column]==board[slot+1][column] && board[slot+1][column]==board[slot+2][column] && board[slot+2][column]==board[slot+3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column-1] && board[slot+1][column-1]==board[slot+2][column-2] && board[slot+2][column-2]==board[slot+3][column-3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column+3<game.getColumnCount()) {
                    if (board[slot][column]==board[slot][column+1] && board[slot][column+1]==board[slot][column+2] && board[slot][column+2]==board[slot][column+3]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (board[slot][column]==board[slot-1][column] && board[slot-1][column]==board[slot-2][column] && board[slot-2][column]==board[slot-3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column+1] && board[slot-1][column+1]==board[slot-2][column+2] && board[slot-2][column+2]==board[slot-3][column+3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (board[slot][column]==board[slot+1][column] && board[slot+1][column]==board[slot+2][column] && board[slot+2][column]==board[slot+3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column+1] && board[slot+1][column+1]==board[slot+2][column+2] && board[slot+2][column+2]==board[slot+3][column+3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column + 2 < game.getColumnCount() && column-1>=0) {
                    if (board[slot][column]==board[slot][column-1] && board[slot][column]==board[slot][column+1] && board[slot][column]==board[slot][column+2]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (board[slot][column]==board[slot-1][column-1] && board[slot][column]==board[slot+1][column+1] && board[slot][column]==board[slot+2][column+2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot+2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (board[slot][column]==board[slot+1][column-1] && board[slot][column]==board[slot-1][column+1] && board[slot][column]==board[slot-2][column+2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot-2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                }
                if (column + 1 < game.getColumnCount() && column-2>=0) {
                    if (board[slot][column]==board[slot][column+1] && board[slot][column]==board[slot][column-1] && board[slot][column]==board[slot][column-2]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (board[slot][column]==board[slot-1][column+1] && board[slot][column]==board[slot+1][column-1] && board[slot][column]==board[slot+2][column-2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot+2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (board[slot][column]==board[slot+1][column+1] && board[slot][column]==board[slot-1][column-1] && board[slot][column]==board[slot-2][column-2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot-2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                }
        
                //System.out.println("About to run");
                column = r.nextInt(game.getColumnCount());
                while (board[0][column]!=('B') && available(board)){
                    //System.out.println(column + "c");
                    column = r.nextInt(game.getColumnCount());
                }
                column= pow%10 >1 ? go(board,pow/10) : column;
                //System.out.println(column);
                slot=0;
                while (slot<board.length-1 && board[slot][column]=='B' && available(board)) {
                    slot++;
                }
                /*
                for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }*/
                
                if (board[slot][column]=='B' && available(board) && !over) {
                    
                    assert (slot==board.length-1);
                    board[slot][column]= !iAmRed ? 'R' : 'Y';
                }
                else  if (available(board) && !over){
                    
                    //System.out.println("Slot " + slot + " col " + column);
                    slot--;
                    assert (board[slot][column]=='B');
                    board[slot][column]= !iAmRed ? 'R' : 'Y';
                }
                /*for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }*/
                //System.out.println("slot " + slot + " col " + column + " is now filled by enemy");
                //System.out.println(board[slot][column]);
            
                if (column-3>=0 && !over) {
                    if (board[slot][column]==board[slot][column-1] && board[slot][column-1]==board[slot][column-2] && board[slot][column-2]==board[slot][column-3]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (board[slot][column]==board[slot-1][column] && board[slot-1][column]==board[slot-2][column] && board[slot-2][column]==board[slot-3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column-1] && board[slot-1][column-1]==board[slot-2][column-2] && board[slot-2][column-2]==board[slot-3][column-3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (board[slot][column]==board[slot+1][column] && board[slot+1][column]==board[slot+2][column] && board[slot+2][column]==board[slot+3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column-1] && board[slot+1][column-1]==board[slot+2][column-2] && board[slot+2][column-2]==board[slot+3][column-3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        
                    }
                    
                }
            
                if (column+3<game.getColumnCount() && !over) {
                    if (board[slot][column]==board[slot][column+1] && board[slot][column+1]==board[slot][column+2] && board[slot][column+2]==board[slot][column+3]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (board[slot][column]==board[slot-1][column] && board[slot-1][column]==board[slot-2][column] && board[slot-2][column]==board[slot-3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column+1] && board[slot-1][column+1]==board[slot-2][column+2] && board[slot-2][column+2]==board[slot-3][column+3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (board[slot][column]==board[slot+1][column] && board[slot+1][column]==board[slot+2][column] && board[slot+2][column]==board[slot+3][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column+1] && board[slot+1][column+1]==board[slot+2][column+2] && board[slot+2][column+2]==board[slot+3][column+3]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column + 2 < game.getColumnCount() && column-1>=0 && !over) {
                    if (board[slot][column]==board[slot][column-1] && board[slot][column]==board[slot][column+1] && board[slot][column]==board[slot][column+2]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (board[slot][column]==board[slot-1][column-1] && board[slot][column]==board[slot+1][column+1] && board[slot][column]==board[slot+2][column+2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot+2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (board[slot][column]==board[slot+1][column-1] && board[slot][column]==board[slot-1][column+1] && board[slot][column]==board[slot-2][column+2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot-2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                }
                if (column + 1 < game.getColumnCount() && column-2>=0 && !over) {
                    if (board[slot][column]==board[slot][column+1] && board[slot][column]==board[slot][column-1] && board[slot][column]==board[slot][column-2]) {
                        over = true;
                        won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                        lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (board[slot][column]==board[slot-1][column+1] && board[slot][column]==board[slot+1][column-1] && board[slot][column]==board[slot+2][column-2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot+2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (board[slot][column]==board[slot-1][column-1] && board[slot][column]==board[slot+1][column+1] && board[slot][column]==board[slot-2][column-2]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                        if (board[slot][column]==board[slot+1][column] && board[slot][column]==board[slot-1][column] && board[slot][column]==board[slot-2][column]) {
                            over = true;
                            won = (iAmRed && board[slot][column]=='R') || (!iAmRed && board[slot][column]=='Y');
                            lost = !(iAmRed && board[slot][column]=='R') || !(!iAmRed && board[slot][column]=='Y');
                        }
                    }
                }
                column = r.nextInt(game.getColumnCount());
                while (board[0][column]!=('B') && available(board)){
                    //System.out.println(column + "A");
                    column = r.nextInt(game.getColumnCount());
                }
                /*
                for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }*/
                //System.out.println(pow);
                column= pow%10 >1 ? go(board,pow/10) : column;
            }
            
            
            if (won) {
                totals[random] += 1;///moves;
                //System.out.println("won");
            }
            else if (!won && over){
                totals[random] -= 1;///moves;
                //System.out.println("lost");
            }
            
            
        }
        column= r.nextInt(game.getColumnCount());
        while (game.getColumn(column).getIsFull()) {
            column= r.nextInt(game.getColumnCount());
        }
        double best=Integer.MIN_VALUE;
        for (int n=0; n<totals.length;n++) {
            column = totals[n]>best && !game.getColumn(n).getIsFull() ? n : column;
            best = totals[n]>best && !game.getColumn(n).getIsFull() ? totals[n] : best;
        }
        double max=0;
        for (double i:totals) {
            System.out.print(i + " ");
            max += i;
        }
        System.out.println();
        moveOnColumn(column);
        System.out.println("went in " + column + " with opp " + totals[column]);
        
        
        
    }
    public int go(char[][] bord,int iter)
    
    {
        //System.out.println("running" + iter);
        double[] totals = new double[game.getColumnCount()];
        char[][] bard = game.getBoardMatrix();
        for (int n=0;n< bord.length;n++) {
            for (int i=0; i<bord[n].length;i++) {
                bard[n][i]=bord[n][i];
            }
            
        }
        boolean over;
        boolean won;
        boolean lost;
        int random;
        int column;
        int slot;
        int broken=0;
        
        for (int col=0;col<game.getColumnCount()*iter; col++)
        {
            for (int n=0;n< bord.length;n++) {
                for (int i=0; i<bord[n].length;i++) {
                    bord[n][i]=bard[n][i];
                }
                    
            }
            over = false;
            won = false;
            lost=false;
            column=col%game.getColumnCount();
            
            while (bord[0][column]!=('B') && available(bord)){
                column = r.nextInt(game.getColumnCount());
            }
            random=column;
            //assert bord[0][random]=='B';
            double moves=0;
            while (available(bord)&&!over) {
                moves++;
                //System.out.println(moves);
            
                
                slot=0;
                //System.out.println("available " + available());
                while (bord[slot][column]=='B' && slot<bord.length-1) {
                    slot++;
                }
                //System.out.println("We're going in col " + column + " slot " + slot);
                if (bord[slot][column]=='B') {
                    assert (slot==bord.length-1);
                    bord[slot][column]= iAmRed ? 'R' : 'Y';
                }
                else {
                    assert (bord[slot][column]!='B');
                    slot--;
                    assert (bord[slot][column]=='B');
                    bord[slot][column]= iAmRed ? 'R' : 'Y';
                }
                /*for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }
                */
                //System.out.println("We went in col " + column + " slot " + slot);
                if (column-3>=0) {
                    if (bord[slot][column]==bord[slot][column-1] && bord[slot][column-1]==bord[slot][column-2] && bord[slot][column-2]==bord[slot][column-3]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot-1][column]==bord[slot-2][column] && bord[slot-2][column]==bord[slot-3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot-1][column-1]==bord[slot-2][column-2] && bord[slot-2][column-2]==bord[slot-3][column-3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot+1][column]==bord[slot+2][column] && bord[slot+2][column]==bord[slot+3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column-1] && bord[slot+1][column-1]==bord[slot+2][column-2] && bord[slot+2][column-2]==bord[slot+3][column-3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column+3<game.getColumnCount()) {
                    if (bord[slot][column]==bord[slot][column+1] && bord[slot][column+1]==bord[slot][column+2] && bord[slot][column+2]==bord[slot][column+3]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot-1][column]==bord[slot-2][column] && bord[slot-2][column]==bord[slot-3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column+1] && bord[slot-1][column+1]==bord[slot-2][column+2] && bord[slot-2][column+2]==bord[slot-3][column+3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot+1][column]==bord[slot+2][column] && bord[slot+2][column]==bord[slot+3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column+1] && bord[slot+1][column+1]==bord[slot+2][column+2] && bord[slot+2][column+2]==bord[slot+3][column+3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column + 2 < game.getColumnCount() && column-1>=0) {
                    if (bord[slot][column]==bord[slot][column-1] && bord[slot][column]==bord[slot][column+1] && bord[slot][column]==bord[slot][column+2]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot][column]==bord[slot+1][column+1] && bord[slot][column]==bord[slot+2][column+2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot+2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (bord[slot][column]==bord[slot+1][column-1] && bord[slot][column]==bord[slot-1][column+1] && bord[slot][column]==bord[slot-2][column+2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot-2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                }
                if (column + 1 < game.getColumnCount() && column-2>=0) {
                    if (bord[slot][column]==bord[slot][column+1] && bord[slot][column]==bord[slot][column-1] && bord[slot][column]==bord[slot][column-2]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (bord[slot][column]==bord[slot-1][column+1] && bord[slot][column]==bord[slot+1][column-1] && bord[slot][column]==bord[slot+2][column-2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot+2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot][column]==bord[slot+1][column+1] && bord[slot][column]==bord[slot-2][column-2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot-2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                }
        
        
                column = r.nextInt(game.getColumnCount());
                while (bord[0][column]!=('B') && available(bord)){
                    //System.out.println(column + "c");
                    column = r.nextInt(game.getColumnCount());
                }
                column= iter%10 >1 ? go(board,iter/10) : column;
                slot=0;
                while (slot<bord.length-1 && bord[slot][column]=='B') {
                    slot++;
                }
            
                
                if (bord[slot][column]=='B') {
                    
                    assert (slot==bord.length-1);
                    bord[slot][column]= !iAmRed ? 'R' : 'Y';
                }
                else  if (available(bord)){
                    slot--;
                    assert (bord[slot][column]=='B');
                    bord[slot][column]= !iAmRed ? 'R' : 'Y';
                }
                /*for (char[] n : board) {
                    System.out.print("[");
                    for (char i : n) {
                        System.out.print(i + " ");
                    }
                    System.out.println("]");
                    
                }*/
                //System.out.println("slot " + slot + " col " + column + " is now filled by enemy");
                //System.out.println(board[slot][column]);
            
                if (column-3>=0 && !over) {
                    if (bord[slot][column]==bord[slot][column-1] && bord[slot][column-1]==bord[slot][column-2] && bord[slot][column-2]==bord[slot][column-3]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot-1][column]==bord[slot-2][column] && bord[slot-2][column]==bord[slot-3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot-1][column-1]==bord[slot-2][column-2] && bord[slot-2][column-2]==bord[slot-3][column-3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot+1][column]==bord[slot+2][column] && bord[slot+2][column]==bord[slot+3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column-1] && bord[slot+1][column-1]==bord[slot+2][column-2] && bord[slot+2][column-2]==bord[slot+3][column-3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column+3<game.getColumnCount() && !over) {
                    if (bord[slot][column]==bord[slot][column+1] && bord[slot][column+1]==bord[slot][column+2] && bord[slot][column+2]==bord[slot][column+3]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot-3>=0) {
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot-1][column]==bord[slot-2][column] && bord[slot-2][column]==bord[slot-3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column+1] && bord[slot-1][column+1]==bord[slot-2][column+2] && bord[slot-2][column+2]==bord[slot-3][column+3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot+3<game.getRowCount()){
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot+1][column]==bord[slot+2][column] && bord[slot+2][column]==bord[slot+3][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column+1] && bord[slot+1][column+1]==bord[slot+2][column+2] && bord[slot+2][column+2]==bord[slot+3][column+3]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        
                    }
                    
                }
                if (column + 2 < game.getColumnCount() && column-1>=0 && !over) {
                    if (bord[slot][column]==bord[slot][column-1] && bord[slot][column]==bord[slot][column+1] && bord[slot][column]==bord[slot][column+2]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot][column]==bord[slot+1][column+1] && bord[slot][column]==bord[slot+2][column+2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot+2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (bord[slot][column]==bord[slot+1][column-1] && bord[slot][column]==bord[slot-1][column+1] && bord[slot][column]==bord[slot-2][column+2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot-2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                }
                if (column + 1 < game.getColumnCount() && column-2>=0 && !over) {
                    if (bord[slot][column]==bord[slot][column+1] && bord[slot][column]==bord[slot][column-1] && bord[slot][column]==bord[slot][column-2]) {
                        over = true;
                        won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                        lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                    }
                    if (slot + 2 < game.getRowCount() && slot-1>=0) {
                        if (bord[slot][column]==bord[slot-1][column+1] && bord[slot][column]==bord[slot+1][column-1] && bord[slot][column]==bord[slot+2][column-2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot+2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                    if (slot-2>=0 && slot+1<game.getRowCount()) {
                        if (bord[slot][column]==bord[slot-1][column-1] && bord[slot][column]==bord[slot+1][column+1] && bord[slot][column]==bord[slot-2][column-2]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                        if (bord[slot][column]==bord[slot+1][column] && bord[slot][column]==bord[slot-1][column] && bord[slot][column]==bord[slot-2][column]) {
                            over = true;
                            won = (iAmRed && bord[slot][column]=='R') || (!iAmRed && bord[slot][column]=='Y');
                            lost = !(iAmRed && bord[slot][column]=='R') || !(!iAmRed && bord[slot][column]=='Y');
                        }
                    }
                }
                column = r.nextInt(game.getColumnCount());
                while (bord[0][column]!=('B') && available(bord)){
                    //System.out.println(column + "A");
                    column = r.nextInt(game.getColumnCount());
                }
                column= iter%10 >1 ? go(board,iter/10) : column;
            }
            
            
            if (won) {
                totals[random] += 1;///moves;
                //System.out.println("won");
            }
            else if (!won && over){
                totals[random] -= 1;///moves;
                //System.out.println("lost");
            }
            
            
        }
        column= r.nextInt(game.getColumnCount());
        while (bard[0][column]!='B' && available(bard)) {
            column= r.nextInt(game.getColumnCount());
        }
        double best=Integer.MIN_VALUE;
        for (int n=0; n<totals.length;n++) {
            column = (totals[n]>best && bard[0][n]=='B') ? n : column;
            best = (totals[n]>best && bard[0][n]=='B') ? totals[n] : best;
        }
        /*
        for (double i:totals) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("went in " + column + " with opp " + totals[column]);
        
        for (char[] n : bard) {
            System.out.print("[");
            for (char i : n) {
                System.out.print(i + " ");
            }
            System.out.println("]");
            
        }*/
        for (int n=0;n< bord.length;n++) {
            for (int i=0; i<bord[n].length;i++) {
                bord[n][i]=bard[n][i];
            }
            
        }
        //System.out.println("Returning " + column);
        //assert bord[0][column]=='B';
        return column;
    }
    
    public boolean available(char[][]bord) {
        boolean returnval=false;
        for (int n=0; n < game.getColumnCount();n++) {
            returnval = returnval || bord[0][n]==('B');
        }
        return returnval;
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
        return "connectfour.player.NestedAgent lvl " + lvl;
    }
}
