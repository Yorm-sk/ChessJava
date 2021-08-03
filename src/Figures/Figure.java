/*
* Abstract class for all figure
* Each figure has color, coordinate (line, column)
* Each figure must be checked on legal move
* */

package Figures;

public abstract class Figure {
    private final boolean color; //false - white, true - black
    private int line, column;

    Figure(boolean color, int line, int column){
        this.color = color;
        this.line = line;
        this.column = column;
    }

    public boolean getColor(){
        return color;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    abstract public boolean legalMove(int lineWhere, int columnWhere);
    abstract public String getSymbol();
}
