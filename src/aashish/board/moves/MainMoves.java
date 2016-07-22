/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.moves;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import java.util.HashSet;

/**
 *
 * @author Toshiba
 */
public abstract class MainMoves {
    


    protected HashSet<AashishSquare> possibleMoves;
    protected AashishSquare referenceAashishSquare;
    protected AashishGame referenceAashishGame;
    protected boolean colorChoice;
    private boolean isFirstMove;

    /**
     * Creates a new move set having a square, a board and a color as reference.
     *
     * @param referenceAashishSquare the square to be taken as reference for relative moves.
     * @param referenceAashishGame  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MainMoves(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        this.referenceAashishSquare = referenceAashishSquare;
        this.referenceAashishGame = referenceAashishGame;
        this.colorChoice = colorChoice;
        isFirstMove = true;
        possibleMoves = new HashSet<AashishSquare>();
        learnMoveSet();
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected abstract void learnMoveSet();

    /**
     * Returns a set with all the squares that a piece is allowed to move to.
     *
     * @return a HashSet with all the squares on the move set.
     */
    public HashSet<AashishSquare> getMoves() {
        return possibleMoves;
    }

    /**
     * Checks if a piece in a certain square of the chessboard (if any) is the player's piece.
     *
     * @param positionA X-coordinate of the piece to be compared.
     * @param positionB Y-coordinate of the piece to be compared.
     * @return <em>true</em> if there is a piece on that position and if the pieces have the same color.
     */
    protected boolean playerPieceAt(int positionA, int positionB) {
        return referenceAashishGame.hasChessPieceAt(positionA, positionB) && referenceAashishGame.getPieceAt(positionA, positionB).isBlack() ==
                this.colorChoice;
    }

    /**
     * Checks if a piece in a certain square of the chessboard (if any) is the opponent's piece.
     *
     * @param positionA X-coordinate of the piece to be compared.
     * @param positionB Y-coordinate of the piece to be compared.
     * @return <em>true</em> if there is a piece on that position and if the pieces have different colors.
     */
    protected boolean opponentPieceAt(int positionA, int positionB) {
        return referenceAashishGame.hasChessPieceAt(positionA, positionB) && referenceAashishGame.getPieceAt(positionA, positionB).isBlack() !=
                this.colorChoice;
    }

    /**
     * Adds a square to the move set having referenceAashishGame's origin as reference. For most pieces, you only need to
     * avoid squares that have a player's piece on it.
     *
     * @param positionA Cartesian X-coordinate of the square on the board.
     * @param positionB Cartesian Y-coordinate of the square on the board.
     */
    protected void addAashishSquareAt(int positionA, int positionB) {
        if (referenceAashishGame.hasSquareAt(positionA, positionB) && !playerPieceAt(positionA, positionB))
            possibleMoves.add(referenceAashishGame.getSquareAt(positionA, positionB));
    }

    /**
     * Returns the X-coordinate of the referenced square.
     *
     * @return the X-coordinate of referenceAashishSquare on the board.
     */
    protected int getRefX() {
        return referenceAashishSquare.getPosX();
    }

    /**
     * Returns the Y-coordinate of the referenced square.
     *
     * @return the Y-coordinate of referenceAashishSquare on the board.
     */
    protected int getRefY() {
        return referenceAashishSquare.getPosY();
    }

    /**
     * Checks whether this is the first move of a piece. Useful for pawns, which may move two squares instead of one on
     * the first turn.
     *
     * @return <em>true</em> if it is the first move of a piece.
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /**
     * Updates a piece's move set by request.
     */
    public void requestUpdate() {
        learnMoveSet();
    }

    /**
     * Effectively declares a move on the board. If the intended position for the move is available on the
     * chessboard, all variables involved change accordingly.
     *
     * @param positionA the intended X-coordinate of a move.
     * @param positionB the intended Y-coordinate of a move.
     */
    public void declareMove(int positionA, int positionB) {
        if (possibleMoves.contains(referenceAashishGame.getSquareAt(positionA, positionB))) {
            AashishSquare previousAashishSquare = referenceAashishSquare;
            changeReferenceAashishSquareTo(positionA, positionB);
            possibleMoves.clear();
            possibleMoves = new HashSet<AashishSquare>();
            notFirstMove();
            referenceAashishGame.movePieces(previousAashishSquare, positionA, positionB);
        }
    }

    /**
     * Helper method that turns the first-move boolean into <em>false</em>.
     */
    private void notFirstMove(){
        if(isFirstMove)
            isFirstMove = false;
    }

    /**
     * Changes the referenced square used for the moveSet to one of the squares on the referenced AashishGame.
     *
     * @param positionA desired X-coordinate of a square.
     * @param positionB desired Y-coordinate of a square.
     */
    private void changeReferenceAashishSquareTo(int positionA, int positionB) {
        referenceAashishSquare = referenceAashishGame.getSquareAt(positionA, positionB);
    }
}

