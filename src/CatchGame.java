
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    public Board game;
    public Doctor doc;
    public Dalek dale0;
    public Dalek dale1;
    public Dalek dale2;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        this.doc = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));
        // spawn the third dalek so its on a different square than the doctor
        this.dale0 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        while (doc.getRow() == dale0.getRow() && doc.getCol() == dale0.getCol()) {
            this.dale0 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        }// spawn the seccond dalek so its on a different square than the other dalek and the doctor
        this.dale1 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        while ((doc.getRow() == dale1.getRow() && doc.getCol() == dale1.getCol())
                || (dale1.getRow() == dale0.getRow() && dale1.getCol() == dale0.getCol())) {
            this.dale1 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        }// spawn the third dalek so its on a different square than the other daleks and the doctor
        this.dale2 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        while ((doc.getRow() == dale2.getRow() && doc.getCol() == dale2.getCol())
                || (dale2.getRow() == dale0.getRow() && dale2.getCol() == dale0.getCol())
                || (dale1.getRow() == dale2.getRow() && dale1.getCol() == dale2.getCol())) {
            this.dale2 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        }
        this.game = new Board(12, 12);
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        boolean lose = false;
        boolean win = false;
        game.putPeg(Color.GREEN, doc.getRow(), doc.getCol());
        game.putPeg(Color.BLACK, dale0.getRow(), dale0.getCol());
        game.putPeg(Color.BLACK, dale1.getRow(), dale1.getCol());
        game.putPeg(Color.BLACK, dale2.getRow(), dale2.getCol());
        // continue playing utill you win or lose
        while (!win) {
            // get where the user clicked
            Coordinate click = game.getClick();
            // move the doctor
            game.removePeg(doc.getRow(), doc.getCol());
            doc.move(click.getRow(), click.getCol());
            game.putPeg(Color.GREEN, doc.getRow(), doc.getCol());
            // see if the daleks can move
            if (!this.dale0.hasCrashed()) {
                game.removePeg(dale0.getRow(), dale0.getCol());
                this.dale0.advanceTowards(doc);
                game.putPeg(Color.BLACK, dale0.getRow(), dale0.getCol());
            }
            if (!this.dale1.hasCrashed()) {
                game.removePeg(dale1.getRow(), dale1.getCol());
                this.dale1.advanceTowards(doc);
                game.putPeg(Color.BLACK, dale1.getRow(), dale1.getCol());
            }
            if (!this.dale2.hasCrashed()) {
                game.removePeg(dale2.getRow(), dale2.getCol());
                this.dale2.advanceTowards(doc);
                game.putPeg(Color.BLACK, dale2.getRow(), dale2.getCol());
            }
            // do the crash if needed
            if (this.dale0.getRow() == this.dale1.getRow()
                    && this.dale0.getCol() == this.dale1.getCol()) {
                this.dale0.crash();
                game.putPeg(Color.RED, dale0.getRow(), dale0.getCol());
                this.dale1.crash();
                game.putPeg(Color.RED, dale1.getRow(), dale1.getCol());
            }
            if (this.dale1.getRow() == this.dale2.getRow()
                    && this.dale1.getCol() == this.dale2.getCol()) {
                this.dale1.crash();
                game.putPeg(Color.RED, dale1.getRow(), dale1.getCol());
                this.dale2.crash();
                game.putPeg(Color.RED, dale2.getRow(), dale2.getCol());
            }
            if (this.dale0.getRow() == this.dale2.getRow()
                    && this.dale0.getCol() == this.dale2.getCol()) {
                this.dale0.crash();
                game.putPeg(Color.RED, dale0.getRow(), dale0.getCol());
                this.dale2.crash();
                game.putPeg(Color.RED, dale2.getRow(), dale2.getCol());
            }
            // see if the doctor has been captured
            if (this.dale0.getRow() == this.doc.getRow()
                    && this.dale0.getCol() == this.doc.getCol()) {
                lose = true;
                break;
            }
            if (this.dale1.getRow() == this.doc.getRow()
                    && this.dale1.getCol() == this.doc.getCol()) {
                lose = true;
                break;
            }
            if (this.dale2.getRow() == this.doc.getRow()
                    && this.dale2.getCol() == this.doc.getCol()) {
                lose = true;
                break;
            }
            if (!this.dale0.hasCrashed()) {
                game.putPeg(Color.BLACK, dale0.getRow(), dale0.getCol());
            }
            if (!this.dale1.hasCrashed()) {
                game.putPeg(Color.BLACK, dale1.getRow(), dale1.getCol());
            }
            if (!this.dale2.hasCrashed()) {
                game.putPeg(Color.BLACK, dale2.getRow(), dale2.getCol());
            }
            //check to see if all the daleks are destroyed
            if (dale0.hasCrashed() && dale1.hasCrashed() && dale2.hasCrashed()) {
                win = true;
                break;
            }
        }// if you win dispay win message
        if (win) {
            this.game.displayMessage("All Daleks have been destoyed congrats!!");
        }// if you lose display game over message
        if (lose) {
            game.putPeg(Color.YELLOW, doc.getRow(), doc.getCol());
            this.game.displayMessage("Sorry you have been captued. :GAME OVER:");
        }
    }
}