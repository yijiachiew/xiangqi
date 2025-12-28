# Xiangqi Backend Game Logic – Test Coverage Plan

## Scope

This test coverage applies **only to the backend game logic** of a Xiangqi (Chinese Chess) game.  
It focuses on validating correctness, rule enforcement, and state integrity.

**In scope**
- Board representation and state management
- Piece movement and capture rules
- Turn handling
- Check and checkmate detection
- Game termination logic

---
## 1. Game State Initialization

### Coverage
- Board initializes with correct dimensions (9 × 10)
- All pieces are placed in correct starting positions
- Each side contains:
  - 1 General
  - 2 Advisors
  - 2 Elephants
  - 2 Horses
  - 2 Chariots
  - 2 Cannons
  - 5 Soldiers
- Red player moves first
- Neither General is in check at game start

---

## 2. Turn Management

### Coverage
- Only the current player can make a move
- Turn switches after a valid move
- Turn does not switch after an invalid move
- Same player cannot move twice consecutively
- Moves are rejected after the game has ended

---

## 3. General (King) Movement Rules

### Coverage
- General moves one step orthogonally
- Diagonal movement is rejected
- General cannot leave the palace
- General cannot move into check
- Flying General rule is enforced
- General cannot capture if it results in self-check

---

## 4. Advisor (Guard) Movement Rules

### Coverage
- Advisor moves exactly one step diagonally
- Advisor remains within palace boundaries
- Advisor cannot move outside the palace
- Advisor cannot jump over pieces
- Advisor cannot expose the General to check

---

## 5. Elephant (Minister) Movement Rules

### Coverage
- Elephant moves exactly two steps diagonally
- Elephant cannot cross the river
- Elephant’s eye blocking rule is enforced
- Elephant cannot move outside board boundaries
- Elephant cannot expose the General to check

---

## 6. Horse (Knight) Movement Rules

### Coverage
- Horse moves in an L-shape (2 + 1)
- Horse movement is blocked if the “leg” square is occupied
- Horse cannot jump over pieces
- Horse cannot move outside board boundaries
- Horse cannot expose the General to check

---

## 7. Chariot (Rook) Movement Rules

### Coverage
- Chariot moves horizontally or vertically
- Diagonal movement is rejected
- Chariot cannot jump over pieces
- Chariot can capture opponent pieces
- Chariot cannot capture friendly pieces
- Chariot cannot expose the General to check

---

## 8. Cannon Movement and Capture Rules

### Coverage
- Cannon moves like a Chariot when not capturing
- Cannon requires exactly one intervening piece to capture
- Capture without a screen is rejected
- Capture with more than one screen is rejected
- Cannon cannot jump when moving without capture
- Cannon cannot expose the General to check

---

## 9. Soldier (Pawn) Movement Rules

### Coverage
- Soldier moves forward one step
- Backward movement is rejected
- Sideways movement is rejected before crossing the river
- Sideways movement is allowed after crossing the river
- Diagonal movement is rejected
- Soldier cannot expose the General to check

---

## 10. Capture Rules (General)

### Coverage
- Captured pieces are removed from the board
- Captured pieces cannot be moved again
- Capturing own pieces is rejected
- Capture does not result in self-check
- Game ends correctly when checkmate occurs

---

## 11. Check Detection

### Coverage
- Detect check from:
  - Chariot
  - Horse
  - Cannon
  - Soldier
  - Flying General
- Check status is cleared after valid defensive moves
- Moves leaving the General in check are rejected

---

## 12. Checkmate Detection

### Coverage
- Checkmate when:
  - General has no legal moves
  - No piece can block the attack
  - No piece can capture the attacking piece
- Game ends immediately upon checkmate
- Winner is assigned correctly
- Further moves are rejected after checkmate

---

## 13. Stalemate and Draw Conditions (If Implemented)

### Coverage
- No legal moves while General is not in check
- Repetition-based draw detection (if supported)
- Move-limit draw detection (if supported)
- Correct draw state is returned

---

## 14. Illegal Move Handling

### Coverage
- Moves outside board boundaries
- Invalid source or destination coordinates
- Moving a non-existent piece
- Invalid move formats
- Moving opponent’s piece
- Null or malformed move commands

---

## 15. Game State Integrity Invariants

### Coverage
- Exactly one General per side always exists
- No overlapping pieces on the board
- Board never exceeds 90 positions
- Game state remains valid after every legal move
- Undo or rollback (if supported) restores a valid state

---

## 16. Full-Game Simulation Tests

### Coverage
- Simulated games always terminate
- Game state remains valid throughout simulation
- Random sequences of legal moves do not crash the logic

---

## Implementation Checklist

Use the checklist below to track whether each component has been **implemented and tested**.

### Core Game Setup
- [ ] Board initialized to 9 × 10
- [ ] Correct initial piece placement
- [ ] Correct piece counts per side
- [ ] Red moves first
- [ ] No initial check state

### Turn Handling
- [ ] Current player enforcement
- [ ] Turn switches only after valid move
- [ ] Invalid moves do not switch turns
- [ ] No moves allowed after game end

### Piece Movement Rules
- [ ] General movement and palace rules
- [ ] Flying General rule
- [ ] Advisor diagonal palace movement
- [ ] Elephant diagonal movement and river restriction
- [ ] Elephant eye blocking
- [ ] Horse L-shaped movement
- [ ] Horse leg blocking
- [ ] Chariot straight-line movement
- [ ] Cannon movement without capture
- [ ] Cannon capture with exactly one screen
- [ ] Soldier forward-only movement
- [ ] Soldier sideways movement after crossing river

### Capture Logic
- [ ] Opponent piece capture works correctly
- [ ] Friendly piece capture is rejected
- [ ] Captured pieces removed from board
- [ ] Capture never results in self-check

### Check and Checkmate
- [ ] Check detection from all piece types
- [ ] Flying General check detection
- [ ] Illegal self-check moves rejected
- [ ] Checkmate detection logic
- [ ] Game termination on checkmate
- [ ] Winner assignment

### Draw and Stalemate (If Supported)
- [ ] Stalemate detection
- [ ] Repetition-based draw detection
- [ ] Move-limit draw detection

### State Integrity
- [ ] Exactly one General per side at all times
- [ ] No overlapping pieces
- [ ] Board state always valid after moves
- [ ] Undo/rollback restores valid state (if supported)

### Simulation and Stress Testing
- [ ] Full-game simulations terminate
- [ ] Random legal move sequences do not crash logic
- [ ] No infinite loops detected

---
