package com.java.xiangqi.logic;

import com.java.xiangqi.Board;
import helper.Colour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckCheckmateTest {

    // --- EXISTING TESTS FROM TestRunner.java MIGRATED ---

    @Test
    void testCheckCheckmateFromTestRunner() {
        Board board = new Board();
        assertFalse(board.evaluateCheck(Colour.RED));
        assertFalse(board.evaluateCheck(Colour.BLACK));

        // Check Mate Scenario for Black
        String[][] test1 = {
                {"·", "·", "·", "·", "將", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "俥", "俥", "俥", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "帥", "·", "·", "·", "·"},
        };
        Board testBoard1 = new Board(test1, Colour.BLACK);
        testBoard1.evaluateCheckMate();
        assertTrue(testBoard1.isInCheck(Colour.BLACK), "Black should be in check");
        assertTrue(testBoard1.isInCheckMate(Colour.BLACK), "Black should be in checkmate");


        // Check Mate Scenario for Red
        String[][] test2 = {
                {"·", "·", "·", "·", "將", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "車", "車", "車", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
                {"·", "·", "·", "·", "帥", "·", "·", "·", "·"},
        };
        Board testBoard2 = new Board(test2, Colour.RED);
        testBoard2.evaluateCheckMate();
        assertTrue(testBoard2.isInCheck(Colour.RED), "Red should be in check");
        assertTrue(testBoard2.isInCheckMate(Colour.RED), "Red should be in checkmate");
    }

    // --- OTHER CHECK TESTS ---

    @Test
    void testCheckDetection() {
        // Simple check scenario: Chariot facing General
        String[][] checkBoard = {
            {"·", "·", "·", "·", "將", "·", "·", "·", "·"}, // General at 0,4
            {"·", "·", "·", "·", "俥", "·", "·", "·", "·"}, // Red Chariot at 1,4 (Check)
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"}, 
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "帥", "·", "·", "·", "·"},
        };

        Board board = new Board(checkBoard, Colour.BLACK); // Black to move
        
        assertTrue(board.evaluateCheck(Colour.BLACK), "Black General should be in check");
    }

    @Test
    void testCheckmate() {
        // Surrounded General
        String[][] mateBoard = {
            {"·", "·", "·", "俥", "將", "俥", "·", "·", "·"}, 
            {"·", "·", "·", "·", "俥", "·", "·", "·", "·"}, 
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "·", "·", "·", "·", "·"},
            {"·", "·", "·", "·", "帥", "·", "·", "·", "·"},
        };
        
        Board board = new Board(mateBoard, Colour.BLACK);
        board.evaluateCheckMate(); // Updates internal state
        
        assertTrue(board.isInCheck(Colour.BLACK), "Black should be in check");
        assertTrue(board.isInCheckMate(Colour.BLACK), "Black should be in checkmate");
    }
}
