

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard{
    private List<SudokuRow> board = new ArrayList<>(9);

    public SudokuBoard() {
        for (int b=0; b<9; b++) {
            getBoard().add(new SudokuRow());
            for (int r=0; r<9; r++) {
                getBoard().get(b).getRow().add(new SudokuElement());
            }        
        }
    }

    public List<SudokuRow> getBoard() {
        return board;
    }

    @Override
    public String toString () {
        String stringOfElements="";
        for (int i=0;i<board.size();i++){
            stringOfElements += board.get(i).toString();
        }
        return stringOfElements;
    }
}
