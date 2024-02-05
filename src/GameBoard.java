import java.util.Scanner;

class GameBoard {
    private static final int SIZE = 16;
    private char[][] playerBoard;
    private char[][] enemyBoard;
    private Ship[] ships;

    public GameBoard() {
        playerBoard = new char[SIZE][SIZE];
        enemyBoard = new char[SIZE][SIZE];
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

    public void printEnemyBoard() {
        printBoard(enemyBoard);
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

    public void placeRandomShips() {
        for (Ship ship : ships) {
            boolean isValidPlacement;
            do {
                int row = (int) (Math.random() * SIZE);
                int col = (int) (Math.random() * SIZE);
                boolean isHorizontal = Math.random() < 0.5;
                isValidPlacement = ship.placeShip(row, col, enemyBoard, isHorizontal);
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
        } else {
            System.out.println("Ранил!");
            enemyBoard.enemyBoard[row][col] = 'X';
            enemyBoard.updateShips(row, col);
        }
    }

    private boolean isValidMove(int row, int col, char[][] board) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] != '.' && board[row][col] != 'X';
    }

    public void botMove(GameBoard playerBoard) {
        int row, col;
        boolean isValidMove;

        do {
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
            playerBoard.updateShips(row, col);
        }
    }

    public void updateShips(int row, int col) {
        for (Ship ship : ships) {
            ship.hit(row, col);
        }
    }
}