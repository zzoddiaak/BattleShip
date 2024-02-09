import java.util.*;
import java.io.*;
class GameManager {
    private Map<String, Player> players;
    private AdminManager adminManager;
    private GameLog gameLog;

    public GameManager() {
        this.players = new HashMap<>();
        this.adminManager = new AdminManager(this.players);
        this.gameLog = new GameLog();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя игрока или 'exit' для выхода: ");
        String playerName = scanner.next();

        if (playerName.equalsIgnoreCase("admin")) {
            adminManager.adminMode();
        } else if (playerName.equalsIgnoreCase("exit")) {
            System.exit(0);
        } else {
            Player player = players.get(playerName);
            if (player == null) {
                player = new Player(playerName);
                players.put(playerName, player);
            }
            GameBoard enemyBoard = new GameBoard();
            GameBoard playerBoard = new GameBoard();
            player.addGame(playerBoard);
            System.out.println("Выберете расстановку кораблей");
            System.out.println("1. Ручная");
            System.out.println("2. Автоматическая");
            int place = scanner.nextInt();
            switch (place) {
                case 1:
                    playerBoard.placeShips();
                    break;
                case 2:
                    playerBoard.placeRandomShips(true);
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
            enemyBoard.placeRandomShips(false);

            Date startTime = new Date();
            while (!playerBoard.allShipsDestroyed()) {
                System.out.println("Ваше поле:");
                playerBoard.printPlayerBoard();
                System.out.println("Поле противника:");
                enemyBoard.printEnemyBoard();

                playerBoard.playerMove(enemyBoard);
                if (enemyBoard.allShipsDestroyed()) {
                    break; // Выходим из цикла, если все корабли противника уничтожены
                }
                // Ход противника (бота)
                enemyBoard.botMove(playerBoard);
                if (playerBoard.allShipsDestroyed()) {
                    break;
                }

            }
            Date endTime = new Date();

            String logEntry = "Game finished: Player: " + playerName + ", Start Time: " + startTime + ", End Time: " + endTime;
            gameLog.writeLog(logEntry);

            if (playerBoard.allShipsDestroyed()) {
                System.out.println("Вы проиграли! Ваши корабли разрушены.");
            } else {
                System.out.println("Вы победили! Все корабли противника разрушены.");

            }
        }
    }
}
