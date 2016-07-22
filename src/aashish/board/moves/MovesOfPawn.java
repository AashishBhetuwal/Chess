/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.moves;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;

/**
 *
 * @author Toshiba
 */
public class MovesOfPawn extends MainMoves {

    /**
     * Creates a new move set for a Pawn having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MovesOfPawn(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceAashishSquare, referenceAashishGame, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to. For pawns, there is a specific
     * move set for each piece color.
     */
    protected void learnMoveSet() {
        if (colorChoice)
            learnBlackPawnMoveSet();
        else
            learnWhitePawnMoveSet();
    }

    
    private void learnWhitePawnMoveSet() {
        addSquareWithReferenceIfNotBlocked(0, 1);
        addSquareWithReferenceIfCanCapture(1, 1);
        addSquareWithReferenceIfCanCapture(-1, 1);
        if (isFirstMove() && !referenceAashishGame.hasChessPieceAt(getRefX(), getRefY() + 1))
            addSquareWithReferenceIfNotBlocked(0, 2);
    }

    
    private void learnBlackPawnMoveSet() {
        addSquareWithReferenceIfNotBlocked(0, -1);
        addSquareWithReferenceIfCanCapture(1, -1);
        addSquareWithReferenceIfCanCapture(-1, -1);
        if (isFirstMove() && !referenceAashishGame.hasChessPieceAt(getRefX(), getRefY() - 1))
            addSquareWithReferenceIfNotBlocked(0, -2);
    }

    /**
     * Tries to add a square with a specified distance to referenceSquare, but only if there is a piece to be captured.
     *
     * @param distX the X-distance from a square to referenceSquare.
     * @param distY the Y-distance from a square to referenceSquare.
     */
    private void addSquareWithReferenceIfCanCapture(int distX, int distY) {
        int refX = distX + getRefX();
        int refY = distY + getRefY();
        if (referenceAashishGame.hasChessPieceAt(refX, refY) && !playerPieceAt(refX, refY))
            addAashishSquareAt(refX, refY);
    }

    /**
     * Tries to add a square with a specified distance to referenceSquare, but only if there are no pieces on it.
     *
     * @param distX the X-distance from a square to referenceSquare.
     * @param distY the Y-distance from a square to referenceSquare.
     */
    private void addSquareWithReferenceIfNotBlocked(int distX, int distY) {
        int refX = distX + getRefX();
        int refY = distY + getRefY();
        if (!referenceAashishGame.hasChessPieceAt(refX, refY))
            addAashishSquareAt(refX, refY);
    }

}
