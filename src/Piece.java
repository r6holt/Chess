public abstract class Piece {
    int row;
    int col;
    boolean isAlive;
    String team;

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
        this.isAlive = true;
        if(row>4) {
            team = "black";
        }
        else {
            team = "white";
        }
    }
    
        public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
    }

    public boolean isLegal(int row, int col) {
        return false;
    }

    public String getTeam() {
        return team;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public int getCount() {
        return 0;
    }

    public void kill() {
        isAlive= false;
    }
    
    public void changeColor() {
        if(this.team.equals("white")) {
            this.team="black";
        }
        else {
            this.team="white";
        }
    }
}