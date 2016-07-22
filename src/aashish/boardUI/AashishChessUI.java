/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.boardUI;

import aashish.board.model.AashishGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @author Toshiba
 */
public class AashishChessUI extends AashishBoardInterface {

    public AashishChessUI(AashishGame chessGameModel) {
        super(chessGameModel);
    }

   

   



    /**
     * Creates a complete GUI for the traditional chessboard game.
     *
     * @param chessGameModel a chessboard taken as reference for square creation and piece positioning.
     */
    
    protected void setChessLayout() {
        gameLayout = new GridLayout(8, 0);
    }

    /**
     * Creates a JButton for each square on the board.
     */
    protected void setChessBoardAsButtons(JButton[][] gameButtons) {
        gameButtons = new JButton[8][8];
        for (int positionB = 7; positionB >= 0; positionB--) {
            for (int positionA = 0; positionA < 8; positionA++) {
                gameButtons[positionA][positionB] = newSquareButton(positionA, positionB);
                gamePanel.add(gameButtons[positionA][positionB]);
            }
        }
    }

    /**
     * Resets colors on the chessboard squares.
     */
    protected void resetColors() {
        for (int aPosition = 0; aPosition < 8; aPosition++) {
            for (int bPosition = 0; bPosition < 8; bPosition++) {
                final JButton button = gameButtons[aPosition][bPosition];
                button.setBackground(chessGameModel.getSquareAt(aPosition, bPosition).getSquareColor());
                button.setIcon(null);
                addPieceAt(button, aPosition, bPosition);
            }
        }
    }

    /**
     * Creates a new JButton directly related to a square on the chessboard.
     *
     * @param positionA X-coordinate of a square on the chessboard.
     * @param positionB Y-coordinate of a square on the chessboard.
     * @return a JButton with all the needed properties of the square.
     */
    private JButton newSquareButton(int positionA, int positionB) {
        JButton newButton = new JButton();
        newButton.setMinimumSize(new Dimension(110, 110));
        newButton.setPreferredSize(new Dimension(110, 110));
        newButton.setBorder(new LineBorder(Color.BLACK));
        newButton.setBackground(chessGameModel.getSquareAt(positionA, positionB).getSquareColor());
        addPieceAt(newButton, positionA, positionB);
        return newButton;
    }

    /**
     * Adds a piece to a square on the GUI board, if existent.
     *
     * @param button a button that may receive the piece image.
     * @param positionA   X-coordinate of the square on the board where a piece may be.
     * @param positionB   Y-coordinate of the square on the board where a piece may be.
     */
    private void addPieceAt(JButton button, int positionA, int positionB) {
        if (chessGameModel.hasChessPieceAt(positionA, positionB)) {
            try {
                Image img = ImageIO.read(new File(chessGameModel.getSquareAt(positionA, positionB).getSquarePiece().getPathToImage()));
                button.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                System.out.println("You tried to draw a non-existent image for a piece.");
            }
        }
    }

    @Override
    protected void setChessBoardAsButtons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}