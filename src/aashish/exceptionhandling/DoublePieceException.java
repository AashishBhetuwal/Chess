/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.exceptionhandling;

/**
 *
 * @author Toshiba
 */
public class DoublePieceException extends RuntimeException {

    /**
     * Throws an InGameException and returns a message to the user.
     *
     * @param message an error message related to the exception clause.
     */
    public DoublePieceException(String message) {
        super(message);
    }
}