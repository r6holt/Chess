public class Queen extends Piece {
    int row;
    int col;
    boolean isAlive;

    public Queen(int row, int col) {
        super(row, col);
        this.row=row;
        this.col=col;
        this.isAlive = true;
        if(row>4) {
            team = "black";
        }
        else {
            team = "white";
        }
    }

    public boolean isLegal(int row2, int col2) {
        if(Math.abs(row-row2)==Math.abs(col-col2)) {
            return true;
        }
        else if(row==row2) {
            return true;
        }
        else if(col==col2) {
            return true;
        }
        else {
            return false;
        }
    }

    public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
    }

}