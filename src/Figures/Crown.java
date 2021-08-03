package Figures;

import Board.Board;

public class Crown extends Figure {
    private final String symbol;
    private boolean isMoved;
    private boolean isCastling;

    public Crown(boolean color, int line, int column) {
        super(color, line, column);
        if (color) symbol = "*c"; //black
        else symbol = " c"; //white
        isMoved = false;
        isCastling = false;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        isCastling = castling;
    }

    /**
     * Analyze input coordinates and check if figure can move there
     * Check if there figure on input coordinates
     * Check if it opposite color
     * Check 8 direction
     * Check if king want to castle
     * Check if there another pieces on it way
     * **/
    @Override
    public boolean legalMove(int lineWhere, int columnWhere) {
        if (Board.board[lineWhere][columnWhere] == null || Board.board[lineWhere][columnWhere].getColor() != getColor()) {
            if (getColumn() + 1 == columnWhere || getColumn() - 1 == columnWhere) {
                if (getLine() + 1 == lineWhere || getLine() == lineWhere || getLine() - 1 == lineWhere) {
                    isMoved = true;
                    return true;
                }
            } else if (getColumn() == columnWhere) {
                if (getLine() + 1 == lineWhere || getLine() - 1 == lineWhere) {
                    isMoved = true;
                    return true;
                }
            }
        }
        if (getLine() == lineWhere && getColumn() + 2 == columnWhere) {
            if (!isMoved) {
                if (getColor() && getLine() == 0) {
                    if (Board.board[0][7] instanceof Rook) {
                        if (((Rook) Board.board[0][7]).isMoved()) {
                            for (int i = getColumn() + 1; i < 7; i++) {
                                if (Board.board[0][i] != null) return false;
                            }
                            isCastling = true;
                            return true;
                        }
                    }
                } else if (getLine() == 7) {
                    if (Board.board[0][7] instanceof Rook) {
                        if (((Rook) Board.board[7][7]).isMoved()) {
                            for (int i = getColumn() + 1; i < 7; i++) {
                                if (Board.board[7][i] != null) return false;
                            }
                            isCastling = true;
                            return true;
                        }
                    }
                }
            }
        }
        if (getLine() == lineWhere && getColumn() - 2 == columnWhere) {
            if (!isMoved) {
                if (getColor() && getLine() == 0) {
                    if (Board.board[0][0] instanceof Rook) {
                        if (((Rook) Board.board[0][0]).isMoved()) {
                            for (int i = getColumn() - 1; i > 0; i--) {
                                if (Board.board[0][i] != null) return false;
                            }
                            isCastling = true;
                            return true;
                        }
                    }
                } else if (getLine() == 7) {
                    if (Board.board[7][0] instanceof Rook) {
                        if (((Rook) Board.board[7][0]).isMoved()) {
                            for (int i = getColumn() - 1; i > 0; i--) {
                                if (Board.board[0][i] != null) return false;
                            }
                            isCastling = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
