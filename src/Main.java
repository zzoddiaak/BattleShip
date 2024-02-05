import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        GameBoard playerBoard = new GameBoard();
        GameBoard enemyBoard = new GameBoard();

        // Размещение кораблей игрока
        playerBoard.placeShips();

        // Размещение кораблей противника (бота)
        enemyBoard.placeRandomShips();

        // Игровой цикл
        while (!playerBoard.allShipsDestroyed() && !enemyBoard.allShipsDestroyed()) {
            System.out.println("Ваше поле:");
            playerBoard.printPlayerBoard();
            System.out.println("Поле противника:");
            playerBoard.printEnemyBoard();

            // Ход игрока
            playerBoard.playerMove(enemyBoard);

            // Ход противника (бота)
            enemyBoard.botMove(playerBoard);
        }

        // Определение победителя
        if (playerBoard.allShipsDestroyed()) {
            System.out.println("Вы проиграли! Ваши корабли разрушены.");
        } else {
            System.out.println("Вы победили! Все корабли противника разрушены.");
        }
    }
}
