package Strategies.WinningStrategy;

import Models.Board;
import Models.Move;

public interface WinningStrategy {
    boolean checkForWinner(Board b, Move lastMove);
}
