public class Player {
    String team;
    boolean isTurn;
    
    public Player(String t) {
        this.team=t;
        if(team.equals("white")) {
            isTurn=true;
        }
        else {
            isTurn=false;
        }
    }
    
    public void turn() {
        isTurn=!isTurn;
    }
    
    public String getTeam() {
        return team;
    }
}