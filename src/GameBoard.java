import java.util.Scanner;

class GameBoard {
    private static final int SIZE = 16;
    private char[][] playerBoard;
    private char[][] enemyBoard;
    private char[][] playerBoard2;
    private char[][] playerBoard1;
    private Ship[] ships;

    public GameBoard() {
        playerBoard = new char[SIZE][SIZE];
        enemyBoard = new char[SIZE][SIZE];
        playerBoard1 = new char[SIZE][SIZE];
        playerBoard2 = new char[SIZE][SIZE];
        ships = new Ship[]{
                new Ship(6),
                new Ship(5),
                new Ship(5),
                new Ship(4),
                new Ship(4),
                new Ship(4),
                new Ship(3),
                new Ship(3),
                new Ship(3),
                new Ship(3),
                new Ship(2),
                new Ship(2),
                new Ship(2),
                new Ship(2),
                new Ship(2),
                new Ship(1),
                new Ship(1),
                new Ship(1),
                new Ship(1),
                new Ship(1),
                new Ship(1)
        };
        initializeBoard(playerBoard);
        initializeBoard(enemyBoard);
        initializeBoard(playerBoard1);
        initializeBoard(playerBoard2);
    }

    private void initializeBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printPlayerBoard() {
        printBoard(playerBoard);
    }
    public void printPlayerBoardMulti2() {
        printBoard(playerBoard2);
    }
    public void printPlayerBoardMulti1() {
        printBoard(playerBoard1);
    }

    public void printEnemyBoard() {
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                char status = enemyBoard[i][j];
                if (status == 'X' || status == '.') {
                    System.out.print(status + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printEnemyBoardMulti1() {
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                char status = playerBoard1[i][j];
                if (status == 'X' || status == '.') {
                    System.out.print(status + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printEnemyBoardMulti2() {
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                char status = playerBoard2[i][j];
                if (status == 'X' || status == '.') {
                    System.out.print(status + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    private void printBoard(char[][] board) {
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeShips() {
        Scanner scanner = new Scanner(System.in);

        for (Ship ship : ships) {
            printPlayerBoard();
            System.out.println("Разместите корабль размером " + ship.getSize());

            int row, col;
            boolean isHorizontal, isValidPlacement;
            do {
                System.out.print("Введите координату начала (например, A3): ");
                String input = scanner.next().toUpperCase();
                col = input.charAt(0) - 'A';
                row = Integer.parseInt(input.substring(1)) - 1;

                System.out.print("Выберите направление (H - горизонтально, V - вертикально): ");
                char direction = scanner.next().toUpperCase().charAt(0);
                isHorizontal = (direction == 'H');

                isValidPlacement = ship.placeShip(row, col, playerBoard, isHorizontal);
                if (!isValidPlacement) {
                    System.out.println("Неверное размещение корабля. Попробуйте еще раз.");
                }
            } while (!isValidPlacement);
        }
    }
    public char[][] getBoard(boolean isPlayer) {
        return isPlayer ? playerBoard : enemyBoard;
    }


    public void placeRandomShips(boolean isPlayer) {
        char[][] board = getBoard(isPlayer);
        for (Ship ship : ships) {
            boolean isValidPlacement;
            do {
                int row = (int) (Math.random() * SIZE);
                int col = (int) (Math.random() * SIZE);
                boolean isHorizontal = Math.random() < 0.5;
                isValidPlacement = ship.placeShip(row, col, board, isHorizontal);
            } while (!isValidPlacement);
        }
    }
    public char[][] getBoardMulti(boolean isPlayerMulti) {
        return isPlayerMulti ? playerBoard1 : playerBoard2;
    }

    public void placeRandomShipsMulti(boolean isPlayerMulti) {
        char[][] board = getBoardMulti(isPlayerMulti);
        for (Ship ship : ships) {
            boolean isValidPlacement;
            do {
                int row = (int) (Math.random() * SIZE);
                int col = (int) (Math.random() * SIZE);
                boolean isHorizontal = Math.random() < 0.5;
                isValidPlacement = ship.placeShip(row, col, board, isHorizontal);
            } while (!isValidPlacement);
        }
    }
    public void placeShipsMulti1() {
        Scanner scanner = new Scanner(System.in);

        for (Ship ship : ships) {
            printPlayerBoardMulti1();
            System.out.println("Разместите корабль размером " + ship.getSize());

            int row, col;
            boolean isHorizontal, isValidPlacement;
            do {
                System.out.print("Введите координату начала (например, A3): ");
                String input = scanner.next().toUpperCase();
                col = input.charAt(0) - 'A';
                row = Integer.parseInt(input.substring(1)) - 1;

                System.out.print("Выберите направление (H - горизонтально, V - вертикально): ");
                char direction = scanner.next().toUpperCase().charAt(0);
                isHorizontal = (direction == 'H');

                isValidPlacement = ship.placeShip(row, col, playerBoard1, isHorizontal);
                if (!isValidPlacement) {
                    System.out.println("Неверное размещение корабля. Попробуйте еще раз.");
                }
            } while (!isValidPlacement);
        }
    }
    public void placeShipsMulti2() {
        Scanner scanner = new Scanner(System.in);

        for (Ship ship : ships) {
            printPlayerBoardMulti2();
            System.out.println("Разместите корабль размером " + ship.getSize());

            int row, col;
            boolean isHorizontal, isValidPlacement;
            do {
                System.out.print("Введите координату начала (например, A3): ");
                String input = scanner.next().toUpperCase();
                col = input.charAt(0) - 'A';
                row = Integer.parseInt(input.substring(1)) - 1;

                System.out.print("Выберите направление (H - горизонтально, V - вертикально): ");
                char direction = scanner.next().toUpperCase().charAt(0);
                isHorizontal = (direction == 'H');

                isValidPlacement = ship.placeShip(row, col, playerBoard2, isHorizontal);
                if (!isValidPlacement) {
                    System.out.println("Неверное размещение корабля. Попробуйте еще раз.");
                }
            } while (!isValidPlacement);
        }
    }


    public boolean allShipsDestroyed() {
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                return false;
            }
        }
        return true;
    }
    private void checkIfShipKilled(int row, int col, char[][] board) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE && board[i][j] == 'O') {
                    return;
                }
            }
        }

        System.out.println("Корабль убит!");
        markSurroundingDots(row,col,board);
    }

    private boolean isValidMove(int row, int col, char[][] board) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] != '.' && board[row][col] != 'X';
    }

    public void playerMove(GameBoard enemyBoard) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean isValidMove;

        do {
            System.out.print("Ваш ход! Введите координату (например, A3): ");
            String input = scanner.next().toUpperCase();
            col = input.charAt(0) - 'A';
            row = Integer.parseInt(input.substring(1)) - 1;

            isValidMove = isValidMove(row, col, enemyBoard.enemyBoard);
            if (!isValidMove) {
                System.out.println("Неверный ход. Попробуйте еще раз.");
            }
        } while (!isValidMove);

        if (enemyBoard.enemyBoard[row][col] == ' ') {
            System.out.println("Мимо!");
            enemyBoard.enemyBoard[row][col] = '.';
            return;
        } else {
            System.out.println("Ранил!");
            enemyBoard.enemyBoard[row][col] = 'X';
            checkIfShipKilled(row, col, enemyBoard.enemyBoard);
            enemyBoard.printEnemyBoard();
            playerMove(enemyBoard);
        }
    }

    public void botMove(GameBoard playerBoard) {
        int row, col;
        boolean isValidMove;
        do {
            boolean foundHit = false;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (playerBoard.playerBoard[i][j] == 'X') {
                        foundHit = true;
                        row = i;
                        col = j;
                        for (int r = -1; r <= 1; r++) {
                            for (int c = -1; c <= 1; c++) {
                                if ((r == 0 || c == 0) && !(r == 0 && c == 0)) {
                                    int newRow = row + r;
                                    int newCol = col + c;
                                    isValidMove = isValidMove(newRow, newCol, playerBoard.playerBoard);
                                    if (isValidMove) {
                                        if (playerBoard.playerBoard[newRow][newCol] == ' ') {
                                            System.out.println("Ход противника: Мимо!");
                                            playerBoard.playerBoard[newRow][newCol] = '.';
                                        } else {
                                            System.out.println("Ход противника: Ранил!");
                                            playerBoard.playerBoard[newRow][newCol] = 'X';
                                            checkIfShipKilled(newRow, newCol, playerBoard.playerBoard);
                                            return;
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            row = (int) (Math.random() * SIZE);
            col = (int) (Math.random() * SIZE);
            isValidMove = isValidMove(row, col, playerBoard.playerBoard);
        } while (!isValidMove);

        if (playerBoard.playerBoard[row][col] == ' ') {
            System.out.println("Ход противника: Мимо!");
            playerBoard.playerBoard[row][col] = '.';
        } else {
            System.out.println("Ход противника: Ранил!");
            playerBoard.playerBoard[row][col] = 'X';
            checkIfShipKilled(row, col, playerBoard.playerBoard);
            botMove(playerBoard);
        }
    }
    public void playerMoveMulti1(GameBoard playerBoard2) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean isValidMove;

        do {
            System.out.print("Ваш ход! Введите координату (например, A3): ");
            String input = scanner.next().toUpperCase();
            col = input.charAt(0) - 'A';
            row = Integer.parseInt(input.substring(1)) - 1;

            isValidMove = isValidMove(row, col, playerBoard2.playerBoard2);
            if (!isValidMove) {
                System.out.println("Неверный ход. Попробуйте еще раз.");
            }
        } while (!isValidMove);

        if (playerBoard2.playerBoard2[row][col] == ' ') {
            System.out.println("Мимо!");
            playerBoard2.playerBoard2[row][col] = '.';
            return;
        } else {
            System.out.println("Ранил!");
            playerBoard2.playerBoard2[row][col] = 'X';
            checkIfShipKilled(row, col, playerBoard2.playerBoard2);
            playerBoard2.printEnemyBoardMulti2();
            playerMoveMulti1(playerBoard2);
        }
    }

    public void playerMoveMulti2(GameBoard playerBoard1) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean isValidMove;

        do {
            System.out.print("Ваш ход! Введите координату (например, A3): ");
            String input = scanner.next().toUpperCase();
            col = input.charAt(0) - 'A';
            row = Integer.parseInt(input.substring(1)) - 1;

            isValidMove = isValidMove(row, col, playerBoard1.playerBoard1);
            if (!isValidMove) {
                System.out.println("Неверный ход. Попробуйте еще раз.");
            }
        } while (!isValidMove);

        if (playerBoard1.playerBoard1[row][col] == ' ') {
            System.out.println("Мимо!");
            playerBoard1.playerBoard1[row][col] = '.';
            return;
        } else {
            System.out.println("Ранил!");
            playerBoard1.playerBoard1[row][col] = 'X';
            checkIfShipKilled(row, col, playerBoard1.playerBoard1);
            playerBoard1.printEnemyBoardMulti1();
            playerMoveMulti2(playerBoard1);
        }
    }




    private void markSurroundingDots(int row, int col, char[][] board) {
        int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < dr.length; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            if (isValidMove(newRow, newCol, board) && board[newRow][newCol] != 'X') {
                board[newRow][newCol] = '.';
            }
        }
    }

}