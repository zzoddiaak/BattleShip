class Ship implements ShipPlacement {
    private int size;
    private int hits;
    private boolean[] segments;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
        this.segments = new boolean[size];
    }

    @Override
    public boolean placeShip(int row, int col, char[][] board, boolean isHorizontal) {
        if (isValidPlacement(row, col, board, isHorizontal)) {
            for (int i = 0; i < size; i++) {
                segments[i] = true;
                if (i == 0) {
                    board[row][col] = 'O';
                } else {
                    if (isHorizontal) {
                        board[row][col + i] = 'O';
                    } else {
                        board[row + i][col] = 'O';
                    }
                }
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean isValidPlacement(int row, int col, char[][] board, boolean isHorizontal) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (isHorizontal) {
            if (col + size > board[0].length) {
                return false;
            }
        } else {
            if (row + size > board.length) {
                return false;
            }
        }
        for (int i = row - 1; i <= row + size; i++) {
            for (int j = col - 1; j <= col + size; j++) {
                if (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
                    if (board[i][j] != ' ') {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    public boolean isDestroyed() {
        return hits == size;
    }

    public int getSize() {
        return size;
    }
}
