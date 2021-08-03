package Figures;

import Board.Board;

public class Bishop extends Figure {
    private final String symbol;

    public Bishop(boolean color, int line, int column) {
        super(color, line, column);
        if (color) symbol = "*b"; //black
        else symbol = " b"; //white
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    /**
     * Analyze input coordinates and check if figure can move there
     * Check if there figure on input coordinates
     * Check if it opposite color
     * Check four direction: for two diagonals
     * Check if there another pieces on it way
     * **/
    @Override
    public boolean legalMove(int lineWhere, int columnWhere) {
        if (Math.abs(getLine() - lineWhere) == Math.abs(getColumn() - columnWhere) &&
                (Board.board[lineWhere][columnWhere] == null ||
                        Board.board[lineWhere][columnWhere].getColor() != getColor())) {
            if (getColumn() > columnWhere) {
                int buffColumn = getColumn() - 1;
                if (getLine() > lineWhere) {
                    for (int i = getLine() - 1; i > lineWhere; i--) {
                        if (Board.board[i][buffColumn--] != null) return false;
                    }
                } else {
                    for (int i = getLine() + 1; i < lineWhere; i++) {
                        if (Board.board[i][buffColumn--] != null) return false;
                    }
                }
            }
            else {
                int buffColumn = getColumn() + 1;
                if (getLine() > lineWhere) {
                    for (int i = getLine() - 1; i > lineWhere; i--) {
                        if (Board.board[i][buffColumn++] != null) return false;
                    }
                } else {
                    for (int i = getLine() + 1; i < lineWhere; i++) {
                        if (Board.board[i][buffColumn++] != null) return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
