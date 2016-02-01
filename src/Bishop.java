public class Bishop extends Piece {
    int row;
    int col;
    boolean isAlive;
    
    public Bishop(int row, int col) {
        super(row,col);
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
        return false;
    }
    
        public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
    }
}