/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.pieces;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.moves.MainMoves;
import java.util.HashSet;

/**
 *
 * @author Toshiba
 */

public abstract class MainPiece {

    protected boolean isBlack;
    protected boolean isUpToDate;
    protected MainMoves referenceMainMoves;
    protected HashSet<AashishSquare> moveSet;

    public MainPiece(boolean colorChoice) {
        isBlack = colorChoice;
        isUpToDate = false;
        moveSet = new HashSet<>();
    }
    public boolean isBlack() {
        return this.isBlack;
    }

    public void updateMainMoves() {
        moveSet.clear();
        referenceMainMoves.requestUpdate();
        moveSet = referenceMainMoves.getMoves();
    }

    public abstract void learnMainMovesFrom(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame);
    public boolean canMoveTo(AashishSquare aashishSquare) {
        return moveSet.contains(aashishSquare);
    }
    public String getPathToImage() {
        String path = "src/chess/view/img/";
        if (isBlack())
            path += "black_";
        else
            path += "white_";
        path += this.getClass().getSimpleName().toLowerCase() + ".png";
        return path;
    }
    public void moveTo(int markA, int markB) {
        referenceMainMoves.declareMove(markA, markB);
    }


}
