package pieces;
import helper.Colour;
import helper.PieceType;
import coordinate.Position;
import coordinate.Move;

import java.util.List;

public abstract class ChessPiece {
    private Colour colour;
    private String symbol;

    public ChessPiece(Colour colour , String symbol) {
        this.colour = colour;
        this.symbol = symbol;
    }

    public Colour getColour() {
        return colour;
    }
    public abstract PieceType getPieceType();
    public String getSymbol(){
        return symbol;
    };
    public abstract List<Move> getValidMoves(Position pos, List<List<Position>> board);
    public static boolean canCapture(ChessPiece sourcePiece, ChessPiece targetPiece){
        return sourcePiece != null && targetPiece != null &&
                sourcePiece.getColour() != targetPiece.getColour();
    }
    @Override
    public String toString(){
        return "ChessPiece{" +
                "colour=" + colour +
                ", symbol='" + symbol + '\'' +
                '}';
    }

}
