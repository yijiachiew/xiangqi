package pieces;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;
import helper.Colour;

public class PieceFactory {
    // HashMap to map symbols to piece Instances
    private static final Map<String, Supplier<ChessPiece>> pieceMap = new HashMap<>();

    static {
        pieceMap.put("仕", () -> new Advisor(Colour.RED, "仕"));
        pieceMap.put("士", () -> new Advisor(Colour.BLACK, "士"));
        pieceMap.put("相", () -> new Elephant(Colour.RED, "相"));
        pieceMap.put("象", () -> new Elephant(Colour.BLACK, "象"));
        pieceMap.put("傌", () -> new Horse(Colour.RED, "傌"));
        pieceMap.put("馬", () -> new Horse(Colour.BLACK, "馬"));
        pieceMap.put("俥", () -> new Chariot(Colour.RED, "俥"));
        pieceMap.put("車", () -> new Chariot(Colour.BLACK, "車"));
        pieceMap.put("炮", () -> new Cannon(Colour.RED, "炮"));
        pieceMap.put("砲", () -> new Cannon(Colour.BLACK, "砲"));
        pieceMap.put("兵", () -> new Soldier(Colour.RED, "兵"));
        pieceMap.put("卒", () -> new Soldier(Colour.BLACK, "卒"));
        pieceMap.put("帥", () -> new General(Colour.RED, "帥"));
        pieceMap.put("將", () -> new General(Colour.BLACK, "將"));
        pieceMap.put("·", () -> null); // Empty position
    }

    public static ChessPiece createPiece(String symbol) {
        Supplier<ChessPiece> pieceSupplier = pieceMap.get(symbol);
        return (pieceSupplier != null) ? pieceSupplier.get() : null;
    }
}
