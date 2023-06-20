import java.util.*;

public class ConnectFour {

    public String[][] buildBoard() {
        /** 
         * Function builds the grid with spaces in token places
         * no parameters
         *  returns a 2D array with Strings in the indexes
         */
        String[][] grid = new String[6][7];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = " ";
            }
        } 
        return grid;
    }

    public void board(String[][] grid) {
        /**
         * Function prints out board to the terminal with correct formatting
         * takes 2D array grid as parameter
         * no return statement
         */
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.print("|");
            System.out.println("");
        } 
        System.out.println("-".repeat(15));
    }

    public String playerTurn(int turn) {
        /**
         * Function returns correct player based on the turn counter
         * takes int counter that iterates every turn
         * returns a String with player R or Y
         */
        String player = "";
        if (turn % 2 == 1) {
            player = "R";
        } else {
            player = "Y";
        }
        return player;
    }

    public void placeToken (String[][] grid, int num, String player) {
        /**
         * Function places the token in the grid if empty place
         * takes 2D array grid, integer num, String player as parameters
         * no return statement
         */
        // places token in the grid
        for (int i = grid.length-1; i >= 0; i--) {
            if (grid[i][num-1] == " ") {
                grid[i][num-1] = player;
                i = 0;
            }
        }
    }

    public int numWins (Scanner scanner) {
        /**
         * Function asks user how many tokens it takes to win and checks validity of input
         * takes scanner as parameter
         * returns an integer of how many tokens it takes to win (wins)
         */
        System.out.print("How many tokens to win (Enter 3, 4, or 5): ");
        String wins = scanner.nextLine();
        boolean valid = false;
        while (!valid) { // checks validity
            try { // if not within number bounds iterates again
                if (Integer.parseInt(wins) <= 2 || Integer.parseInt(wins) >= 6) {
                    System.out.print("Placement out of bounds. Please try again: ");
                    wins = scanner.nextLine();
                } 
                else {
                    valid = true;
                }
                continue;
            } catch (NumberFormatException e) { // if input not an integer asks again
                System.out.print("Not a valid input. Please try again: ");
                wins = scanner.nextLine();
            }

        }
    return Integer.parseInt(wins);
    }

    public String printPlayer (String player) {
        /**
         * Function returns the player full color based on whose turn it is
         * takes String parameter player
         * returns a string Red or Yellow depending on the turn
         */
        // translates letters to the colors
        if (player.equals("R")) {
            return "Red";
        }
        else {
            return "Yellow";
        }
    }
    
    public boolean horizontalWin (String [][] grid, String player, int wins) {
        /**
         * Function iterates through the grid checks for horizontal wins
         * takes 2D array grid, the String player, and integer wins as parameters
         * returns boolean
         */
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == player) { // adds to horizontal count as tokens match
                    count++;
                }
                else { // if count does not reach target amount, restarts
                    count=0;
                }
                if (count>=wins) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verticalWin (String [][] grid, String player, int wins) {
        /**
         * Function iterates through the grid checks for vertical wins
         * takes 2D array grid, the String player, and integer wins as parameters
         * returns boolean
         */
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid.length-1; j >= 0; j--) {
                if (grid[j][i] == player) { // adds to vertical count as tokens match
                    count++;
                }
                else { // if count does not reach target amount, restarts
                    count=0;
                }
                if (count>=wins) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean diagonalWin (String [][] grid, String player, int wins) {
        /**
         * Function iterates through the grid checks for diagonal wins
         * takes 2D array grid, the String player, and integer wins as parameters
         * returns boolean
         */
        // diagonal upward check
        for (int i = wins-1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (wins == 3) { // checks outward three spaces in upward diagonal direction
                    if (j+1 < grid[0].length && j+2 < grid[0].length) {
                        if (grid[i][j].equals(player) && 
                            grid[i-1][j+1].equals(player) &&
                            grid[i-2][j+2].equals(player)) {
                                return true;
                            }
                        }
                }
                else if (wins == 4) { // checks outward four spaces in upward diagonal direction
                    if (j+1 < grid[0].length && j+2 < grid[0].length && j+3 < grid[0].length) {
                        if (grid[i][j].equals(player) && 
                            grid[i-1][j+1].equals(player) &&
                            grid[i-2][j+2].equals(player) &&
                            grid[i-3][j+3].equals(player)) {
                                return true;
                            }
                        }
                    }
                else if (wins == 5) { // checks outward five spaces in upward diagonal direction
                    if (j+1 < grid[0].length && j+2 < grid[0].length && j+3 < grid[0].length && j+4 < grid[0].length) {
                        if (grid[i][j].equals(player) && 
                            grid[i-1][j+1].equals(player) &&
                            grid[i-2][j+2].equals(player) &&
                            grid[i-3][j+3].equals(player) &&
                            grid[i-4][j+4].equals(player)) {
                                return true;
                            }
                        }
                }
                }
            } 
        
        // diagonal downward check
        for (int i = 0; i < grid.length - (wins-1); i++) {
            for (int j = 0; j < grid[0].length - (wins-1); j++) {
                if (wins == 3) { // checks outward three spaces in downward diagonal direction
                    if (grid[i][j].equals(player) &&
                    grid[i+1][j+1].equals(player) &&
                    grid[i+2][j+2].equals(player)) {
                        return true;
                    }  
                }
                else if (wins == 4) { // checks outward four spaces in downward diagonal direction
                    if (grid[i][j].equals(player) &&
                    grid[i+1][j+1].equals(player) &&
                    grid[i+2][j+2].equals(player) &&
                    grid[i+3][j+3].equals(player)) {
                        return true;
                    }
                }
                else if (wins == 5) { // checks outward five spaces in downward diagonal direction
                    if (grid[i][j].equals(player) &&
                    grid[i+1][j+1].equals(player) &&
                    grid[i+2][j+2].equals(player) &&
                    grid[i+3][j+3].equals(player) &&
                    grid[i+4][j+4].equals(player)) {
                        return true;
                    }
                }
            }
        }
    return false;
    }

    public boolean newGame (Scanner scanner) {
        /**
         * Function asks player if they would like to play again - if not quits program
         * takes scanner as parameter
         * returns boolean
         */
        System.out.println(" Would you like to play again? (Type yes/no): ");
        String nextGame = scanner.nextLine();
        boolean validInput = false;
        while (!validInput) { // checks validity of the input
            if (!nextGame.equals("yes") && !nextGame.equals("no")) {
                System.out.print("That is not a valid input. Please try again: ");
                nextGame = scanner.nextLine();
            }
            else {
                validInput = true;
            }
            }
        if (nextGame.equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isDraw (String[][] grid) {
        /**
         * Function checks if a draw exists meaning board is full and there are no winners
         * takes 2D array grid as parameter
         * returns boolean
         */
        int count = 0;
        for (int i = 0; i < grid.length; i++) { // iterates through whole grid for empty spaces
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals(" ")) {
                    count++;
                }
                }
            }
        if (count > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void main (String[] args) {

        // sets up the game with initializations
        ConnectFour test = new ConnectFour();
        Scanner scanner = new Scanner(System.in);
        String [][] grid = test.buildBoard();
        int curTurn = 1;
        boolean keepGoing = true;
        int wins = test.numWins(scanner);

        // the continuous game loop
        while(keepGoing) {
            test.board(grid);
            String curPlayer = test.playerTurn(curTurn);
            System.out.print(test.printPlayer(curPlayer) + " to play. Pick a column (1-7): ");

            // checks validity of input
            String place = scanner.nextLine();
            boolean valid = false;
            while (!valid) {
                try {
                    if (Integer.parseInt(place) > 7 || Integer.parseInt(place) < 1) {
                        System.out.print("Placement out of bounds. Please try again: ");
                        place = scanner.nextLine();
                    } 
                    else if (grid[0][Integer.parseInt(place)-1] != " ") {
                        while (grid[0][Integer.parseInt(place)-1] != " ") {
                            System.out.println("That slot is full. Try Again.");
                            System.out.print(curPlayer + " to play. Pick a column (1-7): ");
                            place = scanner.nextLine();
                        }
                    } 
                    else {
                        valid = true;
                    }
                    continue;
                } catch (NumberFormatException e) {
                    System.out.print("Not a valid input. Please try again: ");
                   place = scanner.nextLine();
                }
            }

            // places token on board and checks for wins
            test.placeToken(grid, Integer.parseInt(place), curPlayer);
            if (test.horizontalWin(grid, curPlayer, wins) || test.verticalWin(grid, curPlayer, wins)
                || test.diagonalWin(grid, curPlayer, wins) || test.isDraw(grid)) {
                    test.board(grid);
                    if (test.isDraw(grid)) {
                        System.out.print("It's a draw!");
                    }
                    else {
                        System.out.print(test.printPlayer(curPlayer) + " wins!");
                    }
                    if (!test.newGame(scanner)) {
                        System.out.println("Goodbye!");
                        System.exit(0);
                    }
                    else {
                        grid = test.buildBoard();
                        curTurn = 0;
                        wins = test.numWins(scanner);
                    }
                }

            curTurn++;
        }
    } 
}