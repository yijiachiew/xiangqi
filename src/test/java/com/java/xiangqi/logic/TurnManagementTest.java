package com.java.xiangqi.logic;

import com.java.xiangqi.Board;
import helper.Colour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnManagementTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testTurnSwitching() {
        // Initial turn Red
        assertEquals(Colour.RED, board.getCurrentTurn());

        // Valid move for Red (Soldier forward)
        // Red Soldier at (6,0) -> (5,0)
        // playTurn takes string "6050"
        board.playTurn("6050");

        assertEquals(Colour.BLACK, board.getCurrentTurn(), "Turn should switch to Black after valid move");
    }

    @Test
    void testInvalidMoveDoesNotSwitchTurn() {
        assertEquals(Colour.RED, board.getCurrentTurn());

        // Invalid move (e.g., trying to move from empty space or invalid logic)
        // Red Chariot at 9,0 to 7,1 (Diagonal) - Invalid
        
        assertThrows(IllegalArgumentException.class, () -> {
            board.playTurn("9071");
        });

        assertEquals(Colour.RED, board.getCurrentTurn(), "Turn should remain Red after invalid move");
    }
}

