public class King extends Piece {
    int row;
    int col;
    boolean isAlive;
    int count = 0;
    
    public King(int row, int col) {
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
        if(1-(Math.abs(row-row2))>=0) {
            if(1-(Math.abs(col-col2))>=0) {
                return true;
            }
        }
        return false;
    }
    
        public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
        this.count++;
    }
    
    public int getCount() {
        return this.count;
    }
}