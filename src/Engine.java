import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Engine extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;

    private int PlayerOneScore = 0;
    private String playeronescore = Integer.toString(PlayerOneScore);
    private int PlayerTwoScore = 0;
    private String playertwoscore = Integer.toString(PlayerTwoScore);

    private double TimeLeft = 90;
    private String strTimeLeft = Double.toString(TimeLeft);

    private int playerOneX = 150;
    private int playerOneY = 575;

    private int playerTwoX = 1100;
    private int playerTwoY = 575;

    private double newheight = 1.75;
    private boolean falling = true;
    private double acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
    private int counter = 10;
    private double ballVX = 0;
    private double ballVY = 0.5;
    private double ballVYInitial = 0;
    private double ballVXInitial = 0;

    Ball SoccerBall = new Ball(630,150);

    Player PlayerOne = new Player(20,80,150,575);
    Player PlayerTwo = new Player(20,80,1100,575);


    private Timer timer;
    private int delay = 8;

    public Engine() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        //background
        g.setColor(Color.cyan);
        g.fillRect(0,0,1300,750);

        g.setColor(Color.green);
        g.fillRect(0,650,1300,100);

        g.setColor(Color.white);
        g.fillRect(100,450,20,200 );
        g.fillRect(1180,450,20,200);

        //ball
        g.setColor(Color.white);
        g.fillOval(SoccerBall.BallX,SoccerBall.BallY,35,35);

        //player1
        g.setColor(Color.blue);
        g.fillRect(playerOneX,playerOneY,50,75);

        //player2
        g.setColor(Color.red);
        g.fillRect(playerTwoX, playerTwoY, 50,75);

        //timer
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(strTimeLeft.substring(0,2), 625, 100);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,50));
        g.drawString(playeronescore, 525, 100);
        g.drawString(playertwoscore, 750, 100);


        //endgame
        if (strTimeLeft == "0 ") {
            g.setFont(new Font("Arial",Font.BOLD,50));
            if(PlayerTwoScore > PlayerOneScore ) {
                g.drawString("Player 2 Wins!", 625, 200);

            }
            else if (PlayerOneScore > PlayerTwoScore) {
                g.drawString("Player 1 Wins!", 625, 200);
            }

            else {
                g.drawString("Draw!", 625,200);
            }
        }

    }

    public void ResetGame() {
        playerOneX = 0;
        playerOneY = 575;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play == true) {
            //timer countdown
            if(TimeLeft > 0) {
                TimeLeft = TimeLeft - 0.009;
                strTimeLeft = Double.toString(TimeLeft);
            }
            else {
                strTimeLeft = "0 ";

            }

            //scoring a goal
            if(new Rectangle(SoccerBall.BallX, SoccerBall.BallY, 35,35).intersects(new Rectangle(100,450,20,200))) {
                SoccerBall.BallY = 150;
                SoccerBall.BallX = 630;
                newheight = 1.75;
                falling = true;
                acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
                counter = 10;
                ballVX = 0;
                ballVY = 0.5;
                ballVYInitial = 0;
                ballVXInitial = 0;

                playerOneX = 150;
                playerTwoX = 1100;

                PlayerTwoScore++;
                playertwoscore = Integer.toString(PlayerTwoScore);

            }

            if(new Rectangle(SoccerBall.BallX,SoccerBall.BallY,35,35).intersects(new Rectangle(1180,450,20,200))) {
                SoccerBall.BallY = 150;
                SoccerBall.BallX = 630;
                newheight = 1.75;
                falling = true;
                acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
                counter = 10;
                ballVX = 0;
                ballVY = 0.5;
                ballVYInitial = 0;
                ballVXInitial = 0;

                playerOneX = 150;
                playerTwoX = 1100;

                PlayerOneScore++;
                playeronescore = Integer.toString(PlayerOneScore);
            }




            //ballPhysics Gravity
            if (SoccerBall.BallY <= 600 && falling == true) {
                //ballVY = Math.round(Math.sqrt(ballVYInitial*ballVYInitial + 0.02*(650 - SoccerBall.BallY) ));
                ballVY = ballVYInitial + acceleration*acceleration;
                acceleration = acceleration*acceleration;
                ballVYInitial = ballVY;
                SoccerBall.BallY += ballVY;
            }


            if(new Rectangle(SoccerBall.BallX, SoccerBall.BallY,35,35).intersects(new Rectangle(0,649,1300,1000))) {
                falling = false;
                ballVYInitial = ballVYInitial*-0.7;
                acceleration = 1.00000000000000000001;
            }

            if(SoccerBall.BallY > 0 && falling == false) {
                ballVY = ballVYInitial + Math.sqrt(acceleration);
                acceleration = Math.sqrt(acceleration);
                ballVYInitial = ballVY;
                SoccerBall.BallY -= ballVY;

                if(SoccerBall.BallY < 150*newheight) {
                    falling = true;
                    acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
                    newheight += 0.3;
                }
            }

            //BallCollision With PlayerOne
            if (new Rectangle(SoccerBall.BallX, SoccerBall.BallY, 35,35).intersects(new Rectangle(playerOneX,playerOneY,50,75))) {
               // if(SoccerBall.BallY > playerOneY) {
                // above is for if the ball lands ontop of the head

                if (SoccerBall.BallX > playerOneX+25) {
                    ballVXInitial = 15;
                } else if (SoccerBall.BallX < playerOneX+25) {
                    ballVXInitial = -15;
                }
                counter = 10;

            }

            //BallCollision With PlayerTwo
            if (new Rectangle(SoccerBall.BallX, SoccerBall.BallY, 35,35).intersects(new Rectangle(playerTwoX,playerTwoY,50,75))) {
                // if(SoccerBall.BallY > playerOneY) {
                // above is for if the ball lands ontop of the head

                if (SoccerBall.BallX > playerTwoX+25) {
                    ballVXInitial = 15;
                } else if (SoccerBall.BallX < playerTwoX+25) {
                    ballVXInitial = -15;
                }
                counter = 10;


            }

            //Vertical Deceleration

            if (ballVXInitial >= 1 ) {
                ballVX = Math.round(Math.sqrt(ballVXInitial * ballVXInitial - (1 + counter)));
                counter += 1;
                ballVXInitial = ballVX;
                SoccerBall.BallX += ballVX;
            }
            else if (ballVXInitial < 1) {
                ballVX = -1 * Math.round(Math.sqrt(ballVXInitial * ballVXInitial - (1 + counter)));
                counter += 1;
                ballVXInitial = ballVX;
                SoccerBall.BallX += ballVX;
            }

            //ballCollision with edges of screen
            if (new Rectangle(SoccerBall.BallX, SoccerBall.BallY,35,35).intersects(new Rectangle(-10,0,10,750))) {
                ballVXInitial = -ballVXInitial;
            }

            if (new Rectangle(SoccerBall.BallX,SoccerBall.BallY,35,35).intersects(new Rectangle(1300,0,10,750))) {
                ballVXInitial = -ballVXInitial;
            }


        }
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {

        //playerOneMovement
        if (e.getKeyCode() == KeyEvent.VK_D) {
            play = true;
            playerOneX = PlayerOne.moveRight();

        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            play = true;
            playerOneX = PlayerOne.moveLeft();

        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            play = true;
            playerOneY = PlayerOne.jump();

        }

        //playerOneKickMechanic
        if (e.getKeyCode() == KeyEvent.VK_S) {
            play = true;

            if(new Rectangle(SoccerBall.BallX,SoccerBall.BallY,35,35).intersects(new Rectangle(playerOneX+50, playerOneY,50,75))) {
                ballVXInitial = 25;
                ballVYInitial = 25;

                falling = false;
                newheight = 1.75;
                acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
                counter = 10;
                ballVX = 0.5;
                ballVY = 0.5;
            }
        }



        //playerTwoMovement
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            play = true;
            playerTwoX = PlayerTwo.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            play = true;
            playerTwoX = PlayerTwo.moveLeft();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            play = true;
            playerTwoY = PlayerTwo.jump();
        }

        //playerTwoKickMechanic
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            play = true;

            if(new Rectangle(SoccerBall.BallX,SoccerBall.BallY,35,35).intersects(new Rectangle(playerTwoX-50, playerTwoY,50,75))) {
                ballVXInitial = -25;
                ballVYInitial = 25;

                falling = false;
                newheight = 1.75;
                acceleration = 1.0000000000000000000000000000000000000000000000000000000001;
                counter = 10;
                ballVX = 0.5;
                ballVY = 0.5;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //PlayerTwoGravity
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerTwoY = PlayerTwo.fall();
        }

        //PlayerOneGravity
        if (e.getKeyCode() == KeyEvent.VK_W) {
            playerOneY = PlayerOne.fall();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
