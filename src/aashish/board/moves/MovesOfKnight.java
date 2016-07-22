/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.moves;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;

 
public class MovesOfKnight extends MainMoves {

    /**
     * Creates a new move set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MovesOfKnight(AashishSquare referenceSquare, AashishGame referenceBoard, boolean colorChoice) {
        super(referenceSquare, referenceBoard, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected void learnMoveSet() {
        for (int range = 1; range <= 2; range++) {
            addSquareWithReferenceAt(range, 3 - range);
            addSquareWithReferenceAt(range, range - 3);
            addSquareWithReferenceAt(-range, 3 - range);
            addSquareWithReferenceAt(-range, range - 3);
        }
    }

    /**
     * Tries to add a square with a specified distance to referenceSquare.
     *
     * @param distX the X-distance from a square to referenceSquare.
     * @param distY the Y-distance from a square to referenceSquare.
     */
    private void addSquareWithReferenceAt(int distX, int distY) {
        addAashishSquareAt(getRefX() + distX, getRefY() + distY);
    }

}
