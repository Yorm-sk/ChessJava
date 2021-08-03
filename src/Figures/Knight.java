package Figures;

import Board.Board;

public class Knight extends Figure{
    private final String symbol;

    public Knight (boolean color, int line, int column){
        super(color, line, column);
        if (color) symbol = "*k"; //black
        else symbol = " k"; // white
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    /**
     * Analyze input coordinates and check if figure can move there
     * Check if there figure on input coordinates
     * Check if it opposite color
     * Check four direction
     * **/
    @Override
    public boolean legalMove(int lineWhere, int columnWhere){
        if (getColumn() + 1 == columnWhere){
            if (getLine() - 2 == lineWhere &&
                    (Board.board[lineWhere][columnWhere] == null
                            || Board.board[lineWhere][columnWhere].getColor() != getColor())){
                return true;
            }
            else return getLine() + 2 == lineWhere &&
                    (Board.board[lineWhere][columnWhere] == null
                            || Board.board[lineWhere][columnWhere].getColor() != getColor());
        }
        else if (getColumn() - 1 == columnWhere){
            if (getLine() - 2 == lineWhere &&
                    (Board.board[lineWhere][columnWhere] == null
                            || Board.board[lineWhere][columnWhere].getColor() != getColor())){
                return true;
            }
            else return getLine() + 2 == lineWhere &&
                    (Board.board[lineWhere][columnWhere] == null
                            || Board.board[lineWhere][columnWhere].getColor() != getColor());
        }
        return false;
    }
}
