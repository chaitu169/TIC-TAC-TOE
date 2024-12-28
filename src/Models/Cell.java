package Models;

public class Cell {
    private int row;
    private int col;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellStatus = CellStatus.EMPTY;
        this.player = null;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
