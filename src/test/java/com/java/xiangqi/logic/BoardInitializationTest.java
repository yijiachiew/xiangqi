package com.java.xiangqi.logic;

import com.java.xiangqi.Board;
import helper.Colour;
import helper.PieceType;
import org.junit.jupiter.api.Test;
import pieces.ChessPiece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardInitializationTest {

    // --- EXISTING TEST FROM TestRunner.java MIGRATED ---

    @Test
    void testCheckInitialBoardSetupFromTestRunner() {
        Board board = new Board();
        // There should be total of 32 pieces on the board
        assertEquals(32, board.getAllPieces().size());
        // Initial turn is Red
        assertEquals(Colour.RED, board.getCurrentTurn());
        // Check if the game is not over
        assertFalse(board.isGameOver());
    }

    // --- DETAILED INITIALIZATION TESTS ---

    @Test
    void testDetailedBoardSetup() {
        Board board = new Board();

        // 1. Board representation and state management
        assertNotNull(board.getBoard(), "Board should be initialized");
        assertEquals(10, board.getBoard().size(), "Board should have 10 rows");
        assertEquals(9, board.getBoard().get(0).size(), "Board should have 9 columns");

        // 2. All pieces are placed in correct starting positions (count check)
        List<ChessPiece> pieces = board.getAllPieces();
        assertEquals(32, pieces.size(), "There should be exactly 32 pieces");

        // Count pieces by color
        Map<Colour, Long> piecesByColour = pieces.stream()
                .collect(Collectors.groupingBy(ChessPiece::getColour, Collectors.counting()));
        
        assertEquals(16, piecesByColour.get(Colour.RED), "Red should have 16 pieces");
        assertEquals(16, piecesByColour.get(Colour.BLACK), "Black should have 16 pieces");

        // Check specific counts for Red
        Map<PieceType, Long> redPieces = pieces.stream()
                .filter(p -> p.getColour() == Colour.RED)
                .collect(Collectors.groupingBy(ChessPiece::getPieceType, Collectors.counting()));
        
        assertEquals(1, redPieces.get(PieceType.GENERAL));
        assertEquals(2, redPieces.get(PieceType.ADVISOR));
        assertEquals(2, redPieces.get(PieceType.ELEPHANT));
        assertEquals(2, redPieces.get(PieceType.HORSE));
        assertEquals(2, redPieces.get(PieceType.CHARIOT));
        assertEquals(2, redPieces.get(PieceType.CANNON));
        assertEquals(5, redPieces.get(PieceType.SOLDIER));
    }
}
