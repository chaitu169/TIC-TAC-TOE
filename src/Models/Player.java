package Models;

import exceptions.InvalidInputException;

import java.util.Scanner;

public class Player {
    private String name;
    private Long id;
    private PlayerType playerType;
    private Symbol symbol;
    private Scanner scanner;

    public Player(String name, Long id, PlayerType playerType, Symbol symbol) {
        this.name = name;
        this.id = id;
        this.playerType = playerType;
        this.symbol = symbol;
        scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Move makeMove(Board board) throws InvalidInputException {
        //take input from user
        System.out.println(this.name + ", please type row number :- ");
        int row = scanner.nextInt();
        System.out.println(this.name + ", please type column number :- ");
        int col = scanner.nextInt();

        //validate input from user
        if(row >= board.getSize()){
            System.out.println("Entered row number is invalid, Please enter something between " + 0 + " and " + board.getSize());
            return null;
        }

        if(col >= board.getSize()){
            System.out.println("Entered row number is invalid, Please enter something between " + 0 + " and " + board.getSize());
            return null;
        }

        Cell cell = board.getBoard().get(row).get(col);
        if(cell.getCellStatus() == CellStatus.FILLED){
            System.out.println("Cell Corresponding to row - " + row + " and col - " + col + " is already filled, please enter another number");
            return null;
        }

        //update the cell in the board
        cell.setPlayer(this);
        cell.setCellStatus(CellStatus.FILLED);

        //add the move to the moves list
        Move move = new Move(new Cell(row, col), this, this.symbol);

        return move;
    }
}
