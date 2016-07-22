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

    
public class MovesOfEmpress extends MainMoves {

    /**
     * Creates a new move Empress set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MovesOfEmpress(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceAashishSquare, referenceAashishGame, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected void learnMoveSet() {
        MainMoves knightMoves = new MovesOfKnight(referenceAashishSquare, referenceAashishGame, colorChoice);
        MainMoves rookMoves = new MovesOfRook(referenceAashishSquare, referenceAashishGame, colorChoice);
        knightMoves.learnMoveSet();
        rookMoves.learnMoveSet();
        possibleMoves.addAll(knightMoves.getMoves());
        possibleMoves.addAll(rookMoves.getMoves());
    }
}
