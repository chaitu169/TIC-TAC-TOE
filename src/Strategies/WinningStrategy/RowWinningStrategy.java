package Strategies.WinningStrategy;

import Models.Board;
import Models.Move;

public class RowWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkForWinner(Board b, Move lastMove) {
        return false;
    }
}
