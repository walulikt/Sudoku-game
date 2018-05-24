


public class SudokuGame {

    public static final String MENU = "Witam. Gra polega na wstawieniu do Sudoku wybranych przez siebie liczb i umozliwieniu rozwiazania calej lamiglowki programowi poprzez wpisanie komendy SUDOKU."+ '\n' +
            " Przyklad: '1,2,7' - oznacza, ze w kolumnie 1, wierszu 2 ma zostac wprowadzona liczba 7. Pamietaj o wpisaniu ','." + '\n' +
            "Jak juz wprowadzisz wszystkie liczby wpisz slowo 'SUDOKU' a program zajmie sie reszta :)." + '\n' +
            "W celu zakonczenia gry wpisz 'EXIT'"+'\n'+"Zaczynamy!";
    public static final String SET_INPUT_INFORMATION="Wybierz nr kolumny, nr wiersz i wartosc pola, albo napis SUDOKU aby rozwiazac";
    private SudokuBoard sB = new SudokuBoard();
    private ElementValidator elementValidator=new ElementValidator(sB);
    private int columnIndex;
    private int rowIndex;
    private int elementValue;

    public SudokuBoard getSB() {
        return sB;
    }

    public boolean setTheElement(String userInput) throws NumberFormatException{
        boolean isSet = true;
        String[] elementData = userInput.split(",");
        columnIndex = Integer.parseInt(elementData[0])-1;
        rowIndex = Integer.parseInt(elementData[1])-1;
        elementValue = Integer.parseInt(elementData[2]);
        if(elementValidator.userInputCheck(columnIndex, rowIndex,elementValue)) {
            if ((elementValidator.columnElementValidator(columnIndex, rowIndex, elementValue)) &&
                    (elementValidator.rowElementValidator(columnIndex, rowIndex, elementValue)) &&
                    (elementValidator.positionEValidator(columnIndex, rowIndex, elementValue))) {
                sB.getBoard().get(rowIndex).getRow().get(columnIndex).setValue(elementValue);
                System.out.println("Dane wprowadzono");
                isSet=true;
            } else {
                System.out.println("Nie mozna wprowadzic wartosci: " + (elementValue) + " w polu: kolumna: " + (columnIndex + 1) + ", wiersz: " + (rowIndex + 1));
                isSet=false;
            }
        }
        return isSet;
    }

    public boolean resolvingElementValidator(int valColumnIndex, int valRowIndex, int elementValue){
            if (elementValidator.columnResElValidator(valColumnIndex, elementValue) && elementValidator.rowResElValidator(valRowIndex, elementValue) &&
                    elementValidator.positionEValidator(valColumnIndex, valRowIndex, elementValue)) {
                sB.getBoard().get(valRowIndex).getRow().get(valColumnIndex).setValue(elementValue);
                return true;
            }
             return false;
    }

    public void printSudokuBoard (){
        for (SudokuRow r: sB.getBoard())
            System.out.println(r);
    }

    public boolean resolveSudoku(SudokuBoard sB) {
        for (int row=0;row<9;row++){
            for (int column=0; column<9; column++){
                if(sB.getBoard().get(row).getRow().get(column).getValue()==0) {
                    for (int e = 1; e < 10; e++) {
                        if (resolvingElementValidator(column, row, e) &&
                                resolveSudoku(sB)) {
                            return true;
                        }
                        sB.getBoard().get(row).getRow().get(column).setValue(0);
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
