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

    
public class MovesOfPrincess extends MainMoves {

    /**
     * Creates a new Princess move set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceAashishGame  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MovesOfPrincess(AashishSquare referenceSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceSquare, referenceAashishGame, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected void learnMoveSet() {
        MovesOfKnight knightMoves = new MovesOfKnight(referenceAashishSquare, referenceAashishGame, colorChoice);
        MainMoves bishopMoves = new MovesOfBishop(referenceAashishSquare, referenceAashishGame, colorChoice);
        knightMoves.learnMoveSet();
        bishopMoves.learnMoveSet();
        possibleMoves.addAll(knightMoves.getMoves());
        possibleMoves.addAll(bishopMoves.getMoves());
    }
}

