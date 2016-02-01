public class Knight extends Piece {
    int row;
    int col;
    boolean isAlive;

    public Knight(int row, int col) {
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
        if(row2==row+2  || row2==row-2) {
            if(col2==col-1 || col2==col+1) {
                return true;
            }
        }
        else if(row2==row+1  || row2==row-1) {
            if(col2==col-2 || col2==col+2) {
                return true;
            }
        }
        return false;
    }
    
        public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
    }
}