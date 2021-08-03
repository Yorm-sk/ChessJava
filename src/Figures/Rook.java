package Figures;

import Board.Board;

public class Rook extends Figure {
    private final String symbol;
    private boolean isMoved; //need for castling

    public Rook(boolean color, int line, int column) {
        super(color, line, column);
        if (color) symbol = "*r"; //black
        else symbol = " r"; //white
        isMoved = false;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    /**
     * Analyze input coordinates and check if figure can move there
     * Check if there figure on input coordinates
     * Check if it opposite color
     * Check two direction: for line and for column
     * Check if there another pieces on it way
     * **/
    @Override
    public boolean legalMove(int lineWhere, int columnWhere) {
        if (getColumn() == columnWhere &&
                (Board.board[lineWhere][columnWhere] == null ||
                        Board.board[lineWhere][columnWhere].getColor() == !this.getColor())){
            if (getLine() > lineWhere){
                for (int i = lineWhere + 1; i < getLine(); i++){
                    if (Board.board[i][columnWhere] != null) return false;
                }
            }
            else {
                for (int i = getLine() + 1; i < lineWhere; i++){
                    if (Board.board[i][columnWhere] != null) return false;
                }
            }
            isMoved = true;
            return true;
        }
        else if (getLine() == lineWhere &&
                (Board.board[lineWhere][columnWhere] == null ||
                        Board.board[lineWhere][columnWhere].getColor() != getColor())){
            if (getColumn() > columnWhere){
                for (int i = columnWhere + 1; i < getColumn(); i++){
                    if (Board.board[lineWhere][i] != null) return false;
                }
            }
            else {
                for (int i = getColumn() + 1; i < columnWhere; i++){
                    if (Board.board[lineWhere][columnWhere] != null) return false;
                }
            }
            isMoved = true;
            return true;
        }
        return false;
    }
}
