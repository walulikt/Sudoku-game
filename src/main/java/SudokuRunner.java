

import java.util.Scanner;

public class SudokuRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameFinished = false;
        SudokuGame theGame = new SudokuGame();

        System.out.println(SudokuGame.MENU);

        while (!gameFinished){
            theGame.printSudokuBoard();
            System.out.println (SudokuGame.SET_INPUT_INFORMATION);
            String userInput = scanner.nextLine();
            userInput=userInput.toUpperCase();
            if (userInput.equals("SUDOKU")){
               // long begin = System.currentTimeMillis();
            	System.out.println("Sudoku w trakcie rozwi¹zywania...");
                boolean sudokuSolved = theGame.resolveSudoku(theGame.getSB());
              //  long end = System.currentTimeMillis();
              //  System.out.println("Czas rowiazania sudoku: " + (begin-end) + "ms");
                if(sudokuSolved){
                    theGame.printSudokuBoard();
                    System.out.println ("SUDOKU rozwiazane:). Jeœli chcesz zagrac jeszcze raz to wciœnij cokolwiek, jeœli nie to napisz \"EXIT\".");
                    userInput = scanner.nextLine();
                    userInput=userInput.toUpperCase();
                    if(!userInput.equals("EXIT")) {
                        theGame = new SudokuGame();
                        continue;
                    }else {
                        gameFinished=true;
                        System.out.println("Do zobaczenia.");
                    }
                }
            } else if (userInput.equals("EXIT")){
                gameFinished=true;
                System.out.println("Do zobaczenia.");
            } else {
                try {
                    theGame.setTheElement(userInput);
                } catch (NumberFormatException e) {
                    System.out.println("Nieprawidlowy format danych! Podaj liczby w formacie '1,3,4' lub napisz SUDOKU, zeby rozwiazac zadanie. Zeby zakonczyc gre wpisz EXIT.");
                }
            }
        }

        scanner.close();
    }
}
