package Board;

import Figures.*;
import Players.Player;
import Exceptions.*;

public final class Board {
    public static Figure[][] board = new Figure[8][8]; //create boars where we can place figures

    /**
     * Fill board with all figures
     * **/
    public static void startNewGame(){
        for (int i = 0; i < board.length; i++){ board[1][i] = new Pawn(true, 1, i);}
        for (int i = 0; i < board.length; i++){ board[6][i] = new Pawn(false, 6, i);}
        for (int i = 0; i < board.length; i+=7){board[0][i] = new Rook(true, 0, i);}
        for (int i = 0; i < board.length; i+=7){board[7][i] = new Rook(false, 7, i);}
        for (int i = 1; i < board.length; i+=5){board[0][i] = new Knight(true, 0, i);}
        for (int i = 1; i < board.length; i+=5){board[7][i] = new Knight(false, 7, i);}
        for (int i = 2; i < board.length; i+=3){board[0][i] = new Bishop(true, 0, i);}
        for (int i = 2; i < board.length; i+=3){board[7][i] = new Bishop(false, 7, i);}
        board[0][3] = new Queen(true, 0, 3);
        board[7][3] = new Queen(false, 7, 3);
        board[0][4] = new Crown(true, 0, 4);
        board[7][4] = new Crown(false, 7, 4);
    }

    /**
     * Draw board with coordinates and figures on it
     * **/
    public static void showBoard(){
        char start = '8';
        System.out.println("  ---------------------------------");
        for (Figure[] line : board){
            System.out.print(start-- + " !");
            for (Figure figure : line){
                if (!(figure == null)) System.out.print(figure.getSymbol() + " !");
                else System.out.print("   !");
            }
            System.out.println();
        }
        System.out.println("  ---------------------------------");
        System.out.print(" ");
        for (char a = 'a'; a <= 'h'; a++) System.out.print("   " + a + "");
        System.out.println();
    }

    /**
     * Analyze input coordinates and players color
     * If it is wright return figure that was chosen
     * **/
    public static Figure chooseFigure(int line, int column, Player player){
        if (column < 0 || column > 7 || line < 0 || line > 7) throw new WrongCoordinate();
        if (board[line][column] == null) throw new NoFigure();
        if (board[line][column].getColor() != player.getColor()) throw new WrongFigure();
        return board[line][column];
    }

    /**
     * Analyze input coordinates and figures
     * If coordinates wright move figure to this coordinates
     * If figure - pawn or king change it state of movement
     * If figure - king check if want and can castle
     * **/
    public static boolean moveFigure(Figure figure, int lineWhere, int columnWhere){
        if (columnWhere < 0 || columnWhere > 7 || lineWhere < 0 || lineWhere > 7) throw new WrongCoordinate();
        if (figure.legalMove(lineWhere, columnWhere)) {
            if (figure instanceof  Crown){
                if (((Crown) figure).isCastling()){
                    if (figure.getColumn() < columnWhere){
                        if (board[figure.getLine()][7] instanceof Rook) ((Rook) board[figure.getLine()][7]).setMoved(true);
                        board[lineWhere][7].setColumn(columnWhere - 1);
                        board[lineWhere][columnWhere - 1] = board[figure.getLine()][7];
                        board[lineWhere][7] = null;
                    }
                    else {
                        if (board[figure.getLine()][0] instanceof Rook) ((Rook) board[figure.getLine()][7]).setMoved(true);
                        board[lineWhere][0].setColumn(columnWhere + 1);
                        board[lineWhere][columnWhere + 1] = board[figure.getLine()][0];
                        board[lineWhere][0] = null;
                    }
                    ((Crown) figure).setCastling(false);
                }
            }
            if (figure instanceof Pawn){((Pawn) figure).setMoved(true);}
            board[figure.getLine()][figure.getColumn()] = null;
            figure.setLine(lineWhere);
            figure.setColumn(columnWhere);
            if (board[lineWhere][columnWhere] instanceof Crown) {
                board[lineWhere][columnWhere] = figure;
                return true;
            }
            board[lineWhere][columnWhere] = figure;
        }
        else throw new IllegalMove();
        return false;
    }
}
