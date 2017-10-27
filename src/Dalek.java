
/**
 * This class models a Delek in the game. A Delek has a position and can advance
 * towards the Doctor.
 */
public class Dalek {

    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
        this.col = theCol;
        this.row = theRow;
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doc) {
        // if on the same row dont change the row value
        if (this.row == doc.getRow()) {
        } else {// if there not on the same row move closer to the doctor
            if (this.row - doc.getRow() < 0) {
                this.row++;
            } else if (this.row - doc.getRow() > 0) {
                this.row--;
            }
        }
        // if on the same column dont change the row value
        if (this.col == doc.getCol()) {
        } else {// if there not on the same column move closer to the doctor
            if (this.col - doc.getCol() < 0) {
                this.col++;
            } else if (this.col - doc.getCol() > 0) {
                this.col--;
            }
        }
    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash() {
        this.hasCrashed = true;
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed() {
        return this.hasCrashed;
    }
}
