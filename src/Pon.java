public class Pon extends Piece{
    int row;
    int col;
    boolean isAlive;
    String team;
    int count = 0;

    public Pon(int row, int col) {
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
        if(team.equals("white")){
            if(row2==row+2 && (this.count==0)) {
                if(col==col2) {
                    return true;
                }
                return false;
            }
            else if(row2==row+1) {
            	if(col==col2) {
            		return true;
            	}
            	return false;
            }
            else {
            	return false;
            }
        }
        else {    
            if(row2==row-2 && (this.count==0)) {
                if(col==col2) {
                    return true;
                }
                return false;
            }
            else if(row2==row-1) {
            	if(col==col2) {
            		return true;
            	}
            	return false;
            }
            else {
            	return false;
            }
        }

    }

    public void move(int row2, int col2) {
        this.row=row2;
        this.col=col2;
        this.count++;
    }
}