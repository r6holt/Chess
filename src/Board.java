import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
public class Board {
    int NUM_ROWS = 8;
    int NUM_COLS = 8;
    Piece[][] spots = new Piece[NUM_ROWS][NUM_COLS];
    
    JFrame game = new JFrame();
	JPanel board = new JPanel(new GridLayout(8,8));
	
	int fromx=-1;
	int fromy=-1;
	int tox=-1;
	int toy=-1;
	
	ImageIcon Pon = new ImageIcon(new ImageIcon(getClass().getResource("Images/Pon.jpg")).getImage());
	ImageIcon Rook = new ImageIcon(new ImageIcon(getClass().getResource("Images/Rook.jpg")).getImage());
	ImageIcon Knight = new ImageIcon(new ImageIcon(getClass().getResource("Images/Knight.jpg")).getImage());
	ImageIcon Bishop = new ImageIcon(new ImageIcon(getClass().getResource("Images/Bishop.jpg")).getImage());
	ImageIcon Queen = new ImageIcon(new ImageIcon(getClass().getResource("Images/Queen.jpg")).getImage());
	ImageIcon King = new ImageIcon(new ImageIcon(getClass().getResource("Images/King.jpg")).getImage());
	
	ImageIcon Pon2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/Pon2.jpg")).getImage());
	ImageIcon Rook2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/Rook2.jpg")).getImage());
	ImageIcon Knight2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/Knight2.jpg")).getImage());
	ImageIcon Bishop2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/Bishop2.jpg")).getImage());
	ImageIcon Queen2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/Queen2.jpg")).getImage());
	ImageIcon King2 = new ImageIcon(new ImageIcon(getClass().getResource("Images/King2.jpg")).getImage());

    public Board() {
        init();
        GUI();
    }

    public void init() {
        for(int i = 0; i<NUM_ROWS; i++) {
            for(int j = 0; j<NUM_COLS; j++) {
                if(i==1 || i==6) {
                    spots[i][j]=new Pon(i,j);
                }
                else if(i==0 || i==7) {
                    if(j==0 || j==7) {
                        spots[i][j]=new Rook(i,j);
                    }
                    else if(j==1 || j==6) {
                        spots[i][j]=new Knight(i,j);
                    }
                    else if(j==2 || j==5) {
                        spots[i][j]=new Bishop(i,j);
                    }
                    else if(j==3) {
                        spots[i][j]=new Queen(i,j);
                    }
                    else if(j==4) {
                        spots[i][j]=new King(i,j);
                    }
                }
                else {
                    spots[i][j] = null;
                }
            }
        }
    }
    
    JTextArea console = new JTextArea(8,20);
    JLabel time = new JLabel();
    long millis;
    int sec;
    int min;
    int seconds;
    JPanel hold = new JPanel(new BorderLayout());
    Timer timer;
    
    JButton pause = new JButton("Pause");
    JButton start = new JButton("Start");
    
    boolean paused = false;
    Font font = new Font(Font.SANS_SERIF, 20, 60);
    Font font2 = new Font(Font.SANS_SERIF, 10, 20);
    
    public void GUI() {
    	timer = new Timer(500, new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			millis+=500;
    			sec=(int)(millis/1000);
    			min=sec/60;
    			seconds=sec%60;
    			if(seconds<10) {
    				time.setText("  "+min+":0"+seconds);
    			}
    			else {
    				time.setText("  "+min+":"+seconds);
    			}
    			time.setFont(font);
    		}
    	});
    	timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
        
        JPanel startstop = new JPanel(new FlowLayout());
        JLabel clock = new JLabel("           CLOCK");
        clock.setFont(font2);
        hold.add(BorderLayout.NORTH, clock);
        hold.add(BorderLayout.CENTER, time);
        startstop.add(start);
        startstop.add(pause);
        hold.add(BorderLayout.SOUTH, startstop);
        
        pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(paused==true) {
				}
				else {
					timer.stop();
					paused=true;
				}
			}
        });
        
        start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(paused==false) {
				}
				else {
					timer.start();
					paused=false;
				}
			}
        });
        
    	JPanel holder = new JPanel(new GridLayout(3,1,0,40));
    	game.setLayout(new BorderLayout());
    	console.setEditable(false);
    	
    	holder.add(console);
    	holder.add(hold);
    	game.add(BorderLayout.EAST, holder);
    	game.add(BorderLayout.CENTER, board);
    	//game.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	game.setSize(900,600);
		game.setLocationByPlatform(true);
		game.setVisible(true);
		game.setLayout(new BorderLayout());
		
		String type;
		int color=0;
		for(int i=7; i>=0; i--) {
			for(int j=0; j<8; j++) {
				if(spots[i][j]!=null) {
					type = spots[i][j].getClass().toString().substring(6);
					if(spots[i][j].getTeam().equals("white")) {
						color=1;
					}
					else if(spots[i][j].getTeam().equals("black")) {
						color=2;
					}
				}
				else {
					type="";
					color=3;
				}
				JButton piece = new JButton(type);
			//piece.setBorderPainted(false);
				piece.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!paused) {
							if(fromx==-1) {
								if(!piece.getText().equals("")) {
									piece.setBorderPainted(false);
									fromy = piece.getX()/73;
									fromx = (piece.getY())/70;
								}
							}
							else if(fromy==piece.getX()/73 && fromx==piece.getY()/70) {
								fromy=-1;
								fromx=-1;
								piece.setBorderPainted(true);
							}
							else {
								toy = piece.getX()/73;
								tox = (piece.getY())/70;
								move(spots[7-fromx][fromy],7-fromx,fromy,7-tox,toy);
								Update();
								fromx=-1;
								fromy=-1;
								tox=-1;
								toy=-1;
					
							}
						}
					
					}
				});
				
				if(color==2) {
					piece.setBackground(Color.BLACK);
					piece.setForeground(Color.WHITE);
				}
				else if(color==1) {
					piece.setBackground(Color.WHITE);
				}
				else {
					piece.setBackground(Color.GRAY);
				}
				//piece.setBorder(BorderFactory.createEmptyBorder());
				//piece.setContentAreaFilled(false);
				board.add(piece);
			}
		}
		game.setVisible(true);
    	
    }
    
    public void Update() {
    	board.removeAll();
    	
    	String type;
		int color=0;
		for(int i=7; i>=0; i--) {
			for(int j=0; j<8; j++) {
				if(spots[i][j]!=null) {
					type = spots[i][j].getClass().toString().substring(6);
					if(spots[i][j].getTeam().equals("white")) {
						color=1;
					}
					else if(spots[i][j].getTeam().equals("black")) {
						color=2;
					}
				}
				else {
					type="";
					color=3;
				}
				JButton piece = new JButton(type);
			//piece.setBorderPainted(false);
				piece.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!paused) {
							if(fromx==-1) {
								if(!piece.getText().equals("")) {
									piece.setBorderPainted(false);
									fromy = piece.getX()/73;
									fromx = (piece.getY())/70;
								}
							}
							else if(fromy==piece.getX()/73 && fromx==piece.getY()/70) {
								fromy=-1;
								fromx=-1;
								piece.setBorderPainted(true);
							}
							else {
								toy = piece.getX()/73;
								tox = (piece.getY())/70;
								move(spots[7-fromx][fromy],7-fromx,fromy,7-tox,toy);
								Update();
								fromx=-1;
								fromy=-1;
								tox=-1;
								toy=-1;
					
							}
						}
					
					}
				});
				
				if(color==2) {
					piece.setBackground(Color.BLACK);
					piece.setForeground(Color.WHITE);
				}
				else if(color==1) {
					piece.setBackground(Color.WHITE);
				}
				else {
					piece.setBackground(Color.GRAY);
				}
				//piece.setBorder(BorderFactory.createEmptyBorder());
				//piece.setContentAreaFilled(false);
				board.add(piece);
			}
		}
		game.setVisible(true);
		hasWon();
    	
    }

    public void move(Piece piece, int row, int col, int row2, int col2) {
        boolean check = false;
        if (spots[row][col]==null) {
            console.setText("A piece is not located in this position. Please move a piece.");
        }
        else if(piece.getTeam().equals("white")) {
            check = moveW(row,col,row2,col2);
        }
        else {
            check = moveB(row,col,row2,col2);
        }
        if(!check && spots[row2][col2].getClass()==Pon.class) {
            if(row2==7 && spots[row2][col2].getTeam()=="white") {
            	 int n=0;
                 String name="";
                 while(n==0) {
                 	name = JOptionPane.showInputDialog(game, "What do you want your pon to become?");
 	                if(name.substring(0,1)=="p") {
 	                    JOptionPane.showMessageDialog(game, "You can't make another pon.");
 	                }
 	                else if(name.substring(0,1)=="q") {
 	                    spots[row2][col2]= new Queen(row2, col2);
 	                    n++;
 	                }
 	                else if(name.substring(0,1)=="r") {
 	                    spots[row2][col2]= new Rook(row2, col2);
 	                    n++;
 	                }
 	                else if(name.substring(0,1)=="b") {
 	                    spots[row2][col2]= new Bishop(row2, col2);
 	                    n++;
 	                }
 	                else if(name.substring(0,1)=="k") {
 	                    spots[row2][col2]= new Knight(row2, col2);
 	                    n++;
 	                }
 	                else {
 	                	JOptionPane.showMessageDialog(game,"Invalid answer.");
 	                }
 	                spots[row2][col2].changeColor();
                 }
            }
            if(row2==7 && spots[row2][col2].getTeam()=="black") {
                int n=0;
                String name="";
                while(n==0) {
                	name = JOptionPane.showInputDialog(game, "What do you want your pon to become?");
	                if(name.substring(0,1)=="p") {
	                    JOptionPane.showMessageDialog(game, "You can't make another pon.");
	                }
	                else if(name.substring(0,1)=="q") {
	                    spots[row2][col2]= new Queen(row2, col2);
	                    n++;
	                }
	                else if(name.substring(0,1)=="r") {
	                    spots[row2][col2]= new Rook(row2, col2);
	                    n++;
	                }
	                else if(name.substring(0,1)=="b") {
	                    spots[row2][col2]= new Bishop(row2, col2);
	                    n++;
	                }
	                else if(name.substring(0,1)=="k") {
	                    spots[row2][col2]= new Knight(row2, col2);
	                    n++;
	                }
	                else {
	                	JOptionPane.showMessageDialog(game,"Invalid answer.");
	                }
	                spots[row2][col2].changeColor();
                }
            }
        }
    }

    public boolean moveW(int row1, int col1, int row2, int col2) {
        if((spots[row2][col2]==null || spots[row1][col1].getTeam()!=spots[row2][col2].getTeam()) && spots[row1][col1].isLegal(row2,col2) && 
        (spots[row1][col1].getClass()==Knight.class || empty(row1,col1,row2,col2)==true) && 
        (spots[row1][col1].getClass()!=Pon.class || empty2(row2, col2)==true)) {
            if(spots[row2][col2]!=null) {
                spots[row2][col2].kill();
            }
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==Pon.class && row1+1==row2 && col1+1==col2 && empty2(row2,col2)==false) {
            spots[row2][col2].kill();
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==Pon.class && row1+1==row2 && col1-1==col2 && empty2(row2,col2)==false) {
            spots[row2][col2].kill();
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==King.class && row1==row2 && Math.abs(col1-col2)==2 && spots[row1][col1].getCount()==0 &&
        ((col1-col2>0 && empty(row1,col1,row2,0) && spots[row1][0].getCount()==0) || (col1-col2<0 && empty(row1,col1,row2,7) && spots[row1][7].getCount()==0))) {
            spots[row2][col2]=spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1]=null;
            if(col1-col2>0) {
                spots[row2][3]=spots[row2][0];
                spots[row2][3].move(row2,3);
                spots[row2][0]=null;
            }
            else {
                spots[row2][5]=spots[row2][7];
                spots[row2][5].move(row2,5);
                spots[row2][7]=null;
            }
            return true;
        }
        else{
            console.setText("That is an illegal move. Try again.");
            return false;
        }
    }

    public boolean moveB(int row1, int col1, int row2, int col2) {
        if((spots[row2][col2]==null || spots[row1][col1].getTeam()!=spots[row2][col2].getTeam()) && spots[row1][col1].isLegal(row2,col2) && 
        (spots[row1][col1].getClass()==Knight.class || empty(row1,col1,row2,col2)==true) && 
        (spots[row1][col1].getClass()!=Pon.class || empty2(row2, col2)==true)) {
            if(spots[row2][col2]!=null) {
                spots[row2][col2].kill();
            }
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==Pon.class && row1-1==row2 && col1+1==col2 && empty2(row2,col2)==false) {
            spots[row2][col2].kill();
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==Pon.class && row1-1==row2 && col1-1==col2 && empty2(row2,col2)==false) {
            spots[row2][col2].kill();
            spots[row2][col2] = spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1] = null;
            return true;
        }
        else if(spots[row1][col1].getClass()==King.class && row1==row2 && Math.abs(col1-col2)==2 &&
        ((col1-col2>0 && empty(row1,col1,row2,0)) || (col1-col2<0 && empty(row1,col1,row2,7)))) {
            spots[row2][col2]=spots[row1][col1];
            spots[row2][col2].move(row2,col2);
            spots[row1][col1]=null;
            if(col1-col2>0) {
                spots[row2][3]=spots[row2][0];
                spots[row2][3].move(row2,3);
                spots[row2][0]=null;
            }
            else {
                spots[row2][5]=spots[row2][7];
                spots[row2][5].move(row2,5);
                spots[row2][7]=null;
            }
            return true;
        }
        else{
            console.setText("That is an illegal move. Try again.");
            return false;
        }
    }

    public boolean hasWon() {
        int count=0;
        int row=-1;
        int col=-1;
        for(int i=0; i<NUM_ROWS; i++) {
            for(int j=0; j<NUM_COLS; j++) {
                if(spots[i][j]==null) {
                }
                else if(spots[i][j].getClass()==King.class) {
                    count++;
                    row=i;
                    col=j;
                }
            }
        }
        if(count!=2) {
        	timer.stop();
            String winner = spots[row][col].getTeam();
            paused=true;
            start.setEnabled(false);
            console.setText(winner+" wins!");
            JOptionPane.showMessageDialog(game, "Congrats, "+winner+" won!");
            return true;
        }
        else{
            return false;
        }
    }

    public boolean empty(int r1, int c1, int r2, int c2) {
        if(c1>c2) {
            int temp = c1;
            c1=c2;
            c2=temp;
        }
        if(r1>r2) {
            int temp = r1;
            r1=r2;
            r2=temp;
        }
        if(r1==r2) {
            for(int i = c1+1; i<c2; i++) {
                if(spots[r1][i]!=null) {
                    return false;
                }
            }
        }
        else if(c1==c2) {
            for(int i = r1+1; i<r2; i++) {
                if(spots[i][c1]!=null) {
                    return false;
                }
            }
        }
        else if(Math.abs(c1-c2)==Math.abs(r1-r2)) {
            for(int i = 1; i<r1-r2; i++) {
                if(spots[r1+i][c1+i]!=null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean empty2(int row, int col) {
        if(spots[row][col]==null) {
            return true;
        }
        return false;
    }

}