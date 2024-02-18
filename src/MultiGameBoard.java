import java.util.Scanner;

public class MultiGameBoard extends  GameBoard{

    public char[][] getBoardMulti(boolean isPlayerMulti) {
        return isPlayerMulti ? playerBoard1 : playerBoard2;
    }


    public void printPlayerBoardMulti2() {
        printBoard(playerBoard2);
    }
    public void printPlayerBoardMulti1() {
        printBoard(playerBoard1);
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
    public boolean allShipsDestroyedMulti(boolean isPlayerMulti) {
        char[][] board = getBoardMulti(isPlayerMulti);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'O') {
                    return false; // Найден живой корабль
                }
            }
        }
        return true; // Все корабли мертвы
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
    public void playerMoveMulti1(MultiGameBoard playerBoard2) {
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

    public void playerMoveMulti2(MultiGameBoard playerBoard1) {
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
}
