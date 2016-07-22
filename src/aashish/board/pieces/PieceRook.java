/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.pieces;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.moves.MainMoves;
import aashish.board.moves.MovesOfRook;

/**
 *
 * @author Toshiba
 */

    
public class PieceRook extends MainPiece {

    public PieceRook(boolean colorChoice) {
        super(colorChoice);
    }

    @Override
    public void learnMainMovesFrom(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame) {
        referenceMainMoves = new MovesOfRook(referenceAashishSquare, referenceAashishGame, this.isBlack);
    }
}

