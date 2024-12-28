package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size){
        this.size = size;
        initializeBoard();
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    private void initializeBoard(){
        this.board = new ArrayList<>();
        for(int i=0; i<size; i++){
            List<Cell> r = new ArrayList<>();
            for(int j=0; j<size; j++){
                r.add(new Cell(i, j));
            }
            board.add(r);
        }
    }

    public void display(){
        System.out.println();

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                char symbolToPrint = board.get(i).get(j).getPlayer() != null ? board.get(i).get(j).getPlayer().getSymbol().getSymbol() : ' ';
                System.out.print(symbolToPrint);

                if(j < size-1){
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }

        System.out.println();
    }
}
