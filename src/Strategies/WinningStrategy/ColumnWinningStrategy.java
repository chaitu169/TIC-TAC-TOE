package Strategies.WinningStrategy;

import Models.Board;
import Models.Cell;
import Models.CellStatus;
import Models.Move;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkForWinner(Board board, Move lastMove) {
        int size = board.getSize();
        int count = 0;
        for(int i=0; i<size; i++){
            Cell cell = board.getBoard().get(i).get(lastMove.getCell().getCol());
            if(cell.getCellStatus() == CellStatus.EMPTY){
                return false;
            }

            if(cell.getPlayer().getSymbol().getSymbol() == lastMove.getPlayer().getSymbol().getSymbol()){
                count++;
            }
        }

        return count == size;
    }
}
