import java.lang.*;

public class Sudoku_Generaator {
    int[] mat[];
    int read;
    int reajuur;
    int puudu;

    // Constructor
    Sudoku_Generaator(int read, int puudu) {
        this.read = read;
        this.puudu = puudu;
        Double reajuurd = Math.sqrt(read);
        reajuur = reajuurd.intValue();
        mat = new int[read][read];
    }

    public void täida() {
        täidadiagonaal();
        täidakohad(0, reajuur);
        int[] lahend[] = mat;
        eemalda();
    }
    // Täidab reajuur X reajuur maatriksi diagonaali
    void täidadiagonaal() {
        for (int i = 0; i<read; i=i+reajuur)
            täidakast(i, i);
    }

    // Kontrollib kas number on kastis
    boolean kastis(int rowStart, int colStart, int num) {
        for (int i = 0; i<reajuur; i++)
            for (int j = 0; j<reajuur; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;
        return true;
    }

    void täidakast(int rida,int tulp) {
        int num;
        for (int i=0; i<reajuur; i++) {
            for (int j=0; j<reajuur; j++) {
                do {
                    num = randomGenerator(read);
                }
                while (!kastis(rida, tulp, num));
                mat[rida+i][tulp+j] = num;
            }
        }
    }
    // Random generator
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random()*num+1));
    }

    // kontrollib rida, tulpa ja kasti
    boolean kontroll(int i,int j,int num) {
        return (rida(i, num) &&
                tulp(j, num) &&
                kastis(i-i%reajuur, j-j%reajuur, num));
    }

    // Kontrollib kas number on reas
    boolean rida(int i,int num) {
        for (int j = 0; j<read; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // Kontrollib kas number on kastis
    boolean tulp(int j,int num) {
        for (int i = 0; i<read; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    boolean täidakohad(int i, int j) {
        if (j>=read && i<read-1) {
            i = i + 1;
            j = 0;
        }
        if (i>=read && j>=read)
            return true;

        if (i < reajuur) {
            if (j < reajuur)
                j = reajuur;
        }
        else if (i < read-reajuur) {
            if (j==(int)(i/reajuur)*reajuur)
                j =  j + reajuur;
        }
        else {
            if (j == read-reajuur) {
                i = i + 1;
                j = 0;
                if (i>=read)
                    return true;
            }
        }

        for (int num = 1; num<=read; num++) {
            if (kontroll(i, j, num)) {
                mat[i][j] = num;
                if (täidakohad(i, j+1))
                    return true;
                mat[i][j] = 0;
            }
        }
        return false;
    }

    public void eemalda() {
        int ära = puudu;
        while (ära != 0) {
            int lahter = randomGenerator(read*read);
            int i = (lahter/read);
            int j = lahter%9;
            if (j != 0)
                j = j - 1;
            if (mat[i][j] != 0) {
                ära--;
                mat[i][j] = 0;
            }
        }
    }

    public void printSudoku() {
        for (int i = 0; i<read; i++) {
            for (int j = 0; j<read; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int read = 9, puudu = 20;
        Sudoku_Generaator sudoku = new Sudoku_Generaator(read, puudu);
        sudoku.täida();
        sudoku.printSudoku();
    }
}