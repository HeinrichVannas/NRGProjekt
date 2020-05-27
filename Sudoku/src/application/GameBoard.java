package application;

public class GameBoard {
    private int[][] initial;
    private int[][] player;

    public GameBoard(String tase) {
        switch (tase){
            case "kerge":
                initial = Sudoku_Generaator.getmatriks("kerge");
                break;
            case "keskmine":
                initial = Sudoku_Generaator.getmatriks("keskmine");
                break;
            case "raske":
                initial = Sudoku_Generaator.getmatriks("raske");
                break;
        }
        player = new int[9][9];
    }
    public int[][] getInitial() {
        return initial;
    }
    public int[][] getPlayer() {
        return player;
    }

    public void modifyPlayer(int val, int row, int col) {
        if (initial[row][col] == 0) {
            if(val >=0 && val <= 9)
                player[row][col] = val;
            else
                System.out.println("Value passed to player falls out of range");
        }
    }
    public boolean checkForSuccess() {
        int[][] combined = new int[9][9];
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col <9; col++) {
                if(initial[row][col]!=0) {
                    combined[row][col] = initial[row][col];
                } else {
                    combined[row][col] = player[row][col];
                }
            }
        }
        for(int row = 0; row<9; row++) {
            int sum = 0;
            for(int col = 0; col<9; col++) {
                sum = sum + combined[row][col];
            }
            if(sum!=45) {
                return false;
            }
        }
        for(int col = 0; col<9; col++) {
            int sum = 0;
            for(int row = 0; row<9; row++) {
                sum = sum + combined[row][col];
            }
            if(sum!=45) {
                return false;
            }
        }

        for (int row_offset = 0; row_offset < 9; row_offset+=3) {
            for(int col_offset = 0; col_offset <9; col_offset+=3) {
                int sum = 0;
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        sum = sum + combined[row + row_offset][col + col_offset];
                    }
                }
                if(sum!=45) {
                    return false;
                }
            }
        }
        return true;
    }
}
