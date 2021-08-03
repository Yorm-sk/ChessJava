package Figures;

import Board.Board;

public class Queen extends Figure{
    private final String symbol; //queen has it own symbol

    public Queen(boolean color, int line, int column){
        super(color, line, column);
        if (color) symbol = "*q"; //for black
        else symbol = " q"; // for white
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    /**
     * Analyze input coordinates and check if figure can move there
     * Queen can move like a bishop and rook, so check their description
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
            return true;
        }
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
