package application;

import java.util.Random;

public class Sudoku_Generaator {
    static int[][] mat;
    int read;
    int reajuur;
    int kuuega;
    static int puudu;
    static int[][] lahend;

    // Constructor
    Sudoku_Generaator(int read, int puudu) {
        this.read = read;
        this.puudu = puudu;
        Double reajuurd = Math.sqrt(read);
        kuuega = read/2;
        reajuur = reajuurd.intValue();
        mat = new int[read][read];
        lahend = new int[read][read];
    }

    public void tĆ¤ida() {
        tĆ¤idadiagonaal();
        tĆ¤idakohad(0, reajuur);
        eemalda();

    }
    // TĆ¤idab reajuur X reajuur maatriksi diagonaali
    void tĆ¤idadiagonaal() {
        for (int i = 0; i<read; i=i+reajuur)
            tĆ¤idakast(i, i);
    }

    // Kontrollib kas number on kastis
    boolean kastis(int rowStart, int colStart, int num) {
        if(read == 9){
        for (int i = 0; i<reajuur; i++)
            for (int j = 0; j<reajuur; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;}
        if(read == 6){
            for (int i = 0; i<3; i++)
                for (int j = 0; j<2; j++)
                    if (mat[rowStart+i][colStart+j]==num)
                        return false;
        }
        return true;
    }

    void tĆ¤idakast(int rida,int tulp) {
        int num;
        if(read == 9){
        for (int i=0; i<reajuur; i++) {
            for (int j=0; j<reajuur; j++) {
                do {
                    num = randomGenerator(read);
                }
                while (!kastis(rida, tulp, num));
                mat[rida+i][tulp+j] = num;
                lahend[rida+i][tulp+j] = num;
            }
        }
    }        if(read == 6){
            for (int i=0; i<3; i++) {
                for (int j=0; j<2; j++) {
                    do {
                        num = randomGenerator(read);
                    }
                    while (!kastis(rida, tulp, num));
                    mat[rida+i][tulp+j] = num;
                    lahend[rida+i][tulp+j] = num;
                }
            }
        }}
    // Random generator //https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
    int randomGenerator(int num) {
        if (0 >= num) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((num - 0) + 1) + 0;    }

    // kontrollib rida, tulpa ja kasti
    boolean kontroll(int i,int j,int num) {
        if(read == 9){
            int rowstart = i-i%3;
            int colstart = j-j%2;
        }
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

    // Kontrollib kas number on tulbas
    boolean tulp(int j,int num) {
        for (int i = 0; i<read; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    boolean tĆ¤idakohad(int i, int j) {
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
                lahend[i][j] = num;
                if (tĆ¤idakohad(i, j+1))
                    return true;
                mat[i][j] = 0;
                lahend[i][j] = 0;

            }
        }
        return false;
    }

    public void eemalda() {
        int Ć¤ra = puudu;
        if (read == 9){
        while (Ć¤ra != 0) {
            int i = randomGenerator(8);
            int j = randomGenerator(8);

            if (mat[i][j] != 0) {
                Ć¤ra--;
                mat[i][j] = 0;
            }}
        }
        if (read == 6){
            while (Ć¤ra != 0) {
                int i = randomGenerator(5);
                int j = randomGenerator(5);

                if (mat[i][j] != 0) {
                    Ć¤ra--;
                    mat[i][j] = 0;
                }}
        }
    }


    public void printSudoku() {
        for (int i = 0; i<read; i++) {
            for (int j = 0; j<read; j++)
                System.out.print(lahend[i][j] + " ");
            System.out.println();}
        System.out.println();
        for (int i = 0; i<read; i++) {
            for (int j = 0; j<read; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int read = 9, puudu = 10;
        Sudoku_Generaator sudoku = new Sudoku_Generaator(read, puudu);
        sudoku.tĆ¤ida();
        sudoku.printSudoku();
    }
    public static int kerge(){
        int puudu = 40;
        return puudu;
    }
    public static int keskmine(){
        int puudu = 50;
        return puudu;
    }
    public static int raske(){
        int puudu = 60;
        return puudu;
    }
    public static int[][] getmatriks(String tase) {
        int read = 0, puudu = 0;
        switch (tase){
            case "kerge":
                read = 9;
                puudu = kerge();
                break;
            case "keskmine":
                read = 9;
                puudu = keskmine();
                break;
            case "raske":
                read = 9;
                puudu = raske();
                break;}
        Sudoku_Generaator sudoku = new Sudoku_Generaator(read, puudu);
        sudoku.tĆ¤ida();
        return mat;
    }
    public static int[][] getLahendus(){
        int read = 9, puudu = 0;
        Sudoku_Generaator sudoku = new Sudoku_Generaator(read, puudu);
        sudoku.tĆ¤ida();
        return lahend;
    }
}
