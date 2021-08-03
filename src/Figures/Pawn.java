package Figures;

import Board.Board;

public class Pawn extends Figure {
    private final String symbol;
    private boolean isMoved;

    public Pawn(boolean color, int line, int column) {
        super(color, line, column);
        if (color) symbol = "*p"; //black pawn
        else symbol = " p";//white pawn
        isMoved = false;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }
    /**
     * Analyze input coordinates and check if figure can move there
     * Check color of pawn
     * Check if there figure on input coordinates
     * Check if it first time figure move
     * Check if there another piece on it way (for first time)
     * Check color of figure on input coordinates
     * **/
    @Override
    public boolean legalMove(int lineWhere, int columnWhere) {
        if (getColor()) { //pawn is black
            if (!isMoved) { //it` first time pawn moves
                if (lineWhere == getLine() + 2 && columnWhere == getColumn()
                        && Board.board[lineWhere][columnWhere] == null) {  // move on two cells for the first time
                    if (Board.board[lineWhere - 1][columnWhere] == null) {
                        return true;
                    }
                }
            }
            if (lineWhere == getLine() + 1 && columnWhere == getColumn()
                    && Board.board[lineWhere][columnWhere] == null) { //move on one cell
                return true;
            }
            if (getLine() + 1 == lineWhere && getColumn() + 1 == columnWhere //beat that right from this pawn
                    && Board.board[lineWhere][columnWhere] != null && !(Board.board[lineWhere][columnWhere].getColor())) {
                return true;
            }
            return getLine() + 1 == lineWhere && getColumn() - 1 == columnWhere //beat that left from this pawn
                    && Board.board[lineWhere][columnWhere] != null && !(Board.board[lineWhere][columnWhere].getColor());
        } else { //pawn is white
            if (!isMoved) { //it` first time pawn moves
                if (lineWhere == getLine() - 2 && columnWhere == getColumn()
                        && Board.board[lineWhere][columnWhere] == null) {
                    if (Board.board[lineWhere + 1][columnWhere] == null) {
                        return true;
                    }
                }
            }
            if (lineWhere == getLine() - 1 && columnWhere == getColumn()
                    && Board.board[lineWhere][columnWhere] == null) { //move on one cell
                return true;
            }
            if (getLine() - 1 == lineWhere && getColumn() + 1 == columnWhere //beat that right from this pawn
                    && Board.board[lineWhere][columnWhere] != null && Board.board[lineWhere][columnWhere].getColor()) {
                return true;
            }
            return getLine() - 1 == lineWhere && getColumn() - 1 == columnWhere //beat that left from this pawn
                    && Board.board[lineWhere][columnWhere] != null && Board.board[lineWhere][columnWhere].getColor();
        }
    }
}