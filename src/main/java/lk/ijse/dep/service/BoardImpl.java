package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private Piece[][] pieces;

    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;

        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        int count = 0; // <-

        for (int i = 0; i < pieces[i].length; i++) {
            if(!(pieces[col][i] == Piece.EMPTY)) {
                count++;
            }
        }

        if (count == 5) {
            count =-1;
        }
        return count;
    }

    @Override
    public boolean isLegalMove(int col) {

        int columnCount = findNextAvailableSpot(col);

        if (columnCount == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean existLegalMoves() { // check there is any space on column.

        boolean bool = false;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    bool = true;
                }
            }
        }
        return bool;
    }

    @Override
    public void updateMove(int col, Piece move) { // array eke adala column 1ta, adala patadagena update krnw.

        int columnCount = findNextAvailableSpot(col);

        pieces[col][findNextAvailableSpot(col)] = move;

    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        Piece win = Piece.EMPTY;
        int column1 = 0;
        int column2 = 0;
        int raw1 = 0;
        int raw2 = 0;

        for (int i = 0; i < pieces.length; i++) {
            if (findNextAvailableSpot(i) == 4 || findNextAvailableSpot(i) == -1) { // 5k pirila nm -1 liyala pennanne.
                if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3]){
                    win = pieces[i][0];
                    column1 = i;
                    column2 = i;
                    raw1 = 0;
                    raw2 = 3;
                } else if (pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][3] == pieces[i][4]) {
                    win = pieces[i][1];
                    column1 = i;
                    column2 = i;
                    raw1 = 1;
                    raw2 = 4;
                }
            }
        }

        for (int i = 0; i < pieces[i].length; i++) {
            if (findAvailableRaws(i) == 4 || findAvailableRaws(i) == 5 || findAvailableRaws(i) == -1){
                if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i]) {
                    win = pieces[0][i];
                    column1 = 0;
                    column2 = 3;
                    raw1 = i;
                    raw2 = i;
                } else if (pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i]) {
                    win = pieces[1][i];
                    column1 = 1;
                    column2 = 4;
                    raw1 = i;
                    raw2 = i;
                } else if (pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i] && pieces[4][i] == pieces[5][i]) {
                    win = pieces[2][i];
                    column1 = 2;
                    column2 = 5;
                    raw1 = i;
                    raw2 = i;
                }
            }
        }

        Winner winner;

        if (win == Piece.EMPTY) {
            winner = new Winner(win);
        } else {
            winner = new Winner(win, column1, raw1, column2, raw2);
        }
        return winner;
    }

    private int findAvailableRaws(int raw){

        int count = 0;

        for (int i = 0; i <pieces.length; i++) {
            if (!(pieces[i][raw] == Piece.EMPTY)) {
                count++;
            }
        }
        if (count == 6) {
            return -1;
        } else {
            return count;
        }
    }
}


