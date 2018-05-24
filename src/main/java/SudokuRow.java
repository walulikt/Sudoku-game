

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {
    private SudokuElement sudokuElement;
    private List<SudokuElement> row = new ArrayList<>(9);

    public SudokuElement getSudokuElement() {
        return sudokuElement;
    }

    public void setSudokuElement(SudokuElement sudokuElement) {
        this.sudokuElement = sudokuElement;
    }

    public List<SudokuElement> getRow() {
        return row;
    }

    public void setRow(List<SudokuElement> row) {
        this.row = row;
    }

    @Override
    public String toString(){
       String stringOfElements="";
       for (int i=0;i<row.size();i++){
           stringOfElements += row.get(i).toString();
       }
        return stringOfElements;
    }
}
