

import java.util.stream.Collectors;

public class ElementValidator {

    private SudokuBoard sudokuBoard;

    public ElementValidator(SudokuBoard sudokuBoard) {
        this.sudokuBoard=sudokuBoard;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public boolean userInputCheck (int columnNumber, int rowNumber, int elementValue){
        if ((columnNumber>8||columnNumber<0)||(rowNumber>8||rowNumber<0)||(elementValue>9||elementValue<1)){
            System.out.println("Jedna z podanych liczb (kolumna, wiersz lub warosc) jest z poza zakresu 1-9. Sprobuj ponownie");
            return false;
        }
        return true;
    }

    public boolean columnElementValidator(int columnNumber, int rowNumber, int elementValue){
        boolean elementValueChecked=false;
        for (int rowIndex=0 ; rowIndex<9; rowIndex++) {
            if (sudokuBoard.getBoard().get(rowIndex).getRow().get(columnNumber).getValue() == elementValue) {
                System.out.println("Niedozwolona wartosc (" + elementValue + ") dla pozycji kolumna: " + (columnNumber + 1) + ", wiersz: " + (rowNumber + 1) + '\n'
                        + "Taka wartosc wystepuje juz w tej kolumnie. Wprowadz dane jeszcze raz!");
                elementValueChecked = false;
                break;
            } else {
                elementValueChecked = true;
            }
        }
        return elementValueChecked;
    }

    public boolean rowElementValidator(int columnNumber, int rowNumber, int elementValue){
        SudokuRow sudokuRow = sudokuBoard.getBoard().get(rowNumber);
        if(sudokuRow.getRow().stream().map(sE->sE.getValue()).collect(Collectors.toList()).contains(elementValue)){
            System.out.println("Niedozwolona wartosc (" + elementValue + ") dla pozycji kolumna: " + (columnNumber+1) + ", wiersz: " + (rowNumber+1) + '\n'
                    + "Taka wartosc wystepuje juz w tym wierszu. Wprowadz dane jeszcze raz!");
            return false;
        } return true;
    }

    private boolean checkColumnsInSquere(int rowToCheck, int columnToCheck, int elemntValue){
        if (sudokuBoard.getBoard().get(rowToCheck).getRow().get(columnToCheck).getValue()==elemntValue)
            return true;
        else return false;
    }

    public boolean positionEValidator(int valColumnIndex, int valRowIndex, int elementValue){
        boolean elementValueChecked=true;
        if ((valColumnIndex==0 || valColumnIndex==3 || valColumnIndex==6) &&
                (valRowIndex==0||valRowIndex==3 ||valRowIndex==6)){
            for (int r = valRowIndex+1; r<(valRowIndex+3); r++){
                if (checkColumnsInSquere(r,(valColumnIndex+1),elementValue)||
                        checkColumnsInSquere(r,(valColumnIndex+2),elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==1 || valColumnIndex==4 || valColumnIndex==7) &&
                (valRowIndex==1|| valRowIndex==4 || valRowIndex==7)){
            for(int r = valRowIndex-1; r<(valRowIndex+2); r+=2) {
                if (checkColumnsInSquere(r,valColumnIndex-1,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex+1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==2 ||valColumnIndex==5 ||valColumnIndex==8) &&
                (valRowIndex==2||valRowIndex==5 ||valRowIndex==8)){
            for(int r = valRowIndex-2; r<valRowIndex; r++) {
                if (checkColumnsInSquere(r,valColumnIndex-2,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex-1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if((valColumnIndex==0 ||valColumnIndex==3 ||valColumnIndex==6) &&
                (valRowIndex==1||valRowIndex==4 ||valRowIndex==7)) {
            for(int r = valRowIndex-1; r<valRowIndex+2; r+=2) {
                if (checkColumnsInSquere(r,valColumnIndex+1,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex+2,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==0 ||valColumnIndex==3 ||valColumnIndex==6) &&
                (valRowIndex==2||valRowIndex==5 ||valRowIndex==8)){
            for(int r = valRowIndex-2; r<valRowIndex; r++) {
                if (checkColumnsInSquere(r,valColumnIndex+1,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex+2,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==1 ||valColumnIndex==4 ||valColumnIndex==7) &&
                (valRowIndex==0||valRowIndex==3 ||valRowIndex==6)) {
            for(int r = valRowIndex+1; r<valRowIndex+3; r++) {
                if (checkColumnsInSquere(r,valColumnIndex-1,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex+1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==1 ||valColumnIndex==4 ||valColumnIndex==7) &&
                (valRowIndex==2||valRowIndex==5 ||valRowIndex==8)){
            for(int r = valRowIndex-2; r<valRowIndex; r++) {
                if (checkColumnsInSquere(r,valColumnIndex-1,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex+1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==2 || valColumnIndex==5 ||valColumnIndex==8) &&
                (valRowIndex==0||valRowIndex==3 ||valRowIndex==6)){
            for(int r = valRowIndex+1; r<valRowIndex+3; r++) {
                if (checkColumnsInSquere(r,valColumnIndex-2,elementValue)||
                        checkColumnsInSquere(r,valColumnIndex-1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        } else if ((valColumnIndex==2 ||valColumnIndex==5 ||valColumnIndex==8) &&
                (valRowIndex==1||valRowIndex==4 ||valRowIndex==7)) {
            for(int r = valRowIndex-1; r<valRowIndex+2; r+=2) {
                if (checkColumnsInSquere(r,valColumnIndex-2,elementValue) ||
                checkColumnsInSquere(r,valColumnIndex-1,elementValue)){
                    return elementValueChecked = false;
                }
            }
        }
    return elementValueChecked;
    }

    public boolean columnResElValidator(int valColumnIndex, int elementValue){
        boolean validated = true;
        for (int rowIndex=0 ; rowIndex<9; rowIndex++) {
            if (sudokuBoard.getBoard().get(rowIndex).getRow().get(valColumnIndex).getValue() == elementValue) {
                validated= false;
                break;
            } else validated =true;
        }
        return validated;
    }

    public boolean rowResElValidator(int valRowIndex, int elementValue){
        SudokuRow sudokuRow = sudokuBoard.getBoard().get(valRowIndex);
        if(sudokuRow.getRow().stream().map(sE->sE.getValue()).collect(Collectors.toList()).contains(elementValue))return false;
        return true;
    }
}
