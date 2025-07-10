package coordinate;

import pieces.ChessPiece;

import javax.print.DocFlavor;

public class Position {
    private int x;
    private int y;

    private ChessPiece piece;

    public Position(int x, int y) {
        this.x = 0;
        this.y = 0;
        this.piece = null;
    }

    public Position(int x, int y , ChessPiece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isEmpty() {
        return piece == null;
    }

    public void emptyPiece() {
        this.piece = null;
    }
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public String display(){
        if (isEmpty()){
            return "·";
        }
        return piece.getSymbol();
    }
    @Override
    public String toString() {
        return "Coordinate.Position{" +
                "x=" + x +
                ", y=" + y
                ;
    }
}
