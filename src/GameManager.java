import java.util.*;
import java.io.*;
class GameManager {
    private ClearConsole clearConsole = new ClearConsole();

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
            while (true) {
                if (enemyBoard.allShipsDestroyed(false)) {
                    System.out.println("Победил игрок");
                    break;
                }
                System.out.println("Ваше поле:");
                playerBoard.printPlayerBoard();
                System.out.println("Поле противника:");
                enemyBoard.printEnemyBoard();

                playerBoard.playerMove(enemyBoard);
                if (playerBoard.allShipsDestroyed(true)) {
                    System.out.println("Победил бот");
                    break;
                }
                enemyBoard.botMove(playerBoard);


            }
            Date endTime = new Date();

            String logEntry = "Игра завершена. Игрок  " + playerName + ", Начало: " + startTime + ", конец: " + endTime;
            gameLog.writeLog(logEntry);


        }
    }
    public void startMultiGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя первого игрока или 'exit' для выхода: ");
        String playerName1 = scanner.next();
        System.out.print("Введите имя второго игрока или 'exit' для выхода: ");
        String playerName2 = scanner.next();
        if (playerName1.equalsIgnoreCase("admin")) {
            adminManager.adminMode();
        } else if (playerName1.equalsIgnoreCase("exit")) {
            System.exit(0);
        } else {
            Player player = players.get(playerName1);
            if (player == null)  {
                player = new Player(playerName1);
                player = new Player(playerName2);
                players.put(playerName1, player);
                players.put(playerName2,player);
            }
            MultiGameBoard playerBoard2 = new MultiGameBoard();
            MultiGameBoard playerBoard1 = new MultiGameBoard();
            player.addGame(playerBoard1);
            System.out.println("Выберете расстановку кораблей " + playerName1);
            System.out.println("1. Ручная");
            System.out.println("2. Автоматическая");
            int place = scanner.nextInt();
            switch (place) {
                case 1:
                    playerBoard1.placeShipsMulti1();
                    break;
                case 2:
                    playerBoard1.placeRandomShipsMulti(true);
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
            player.addGame(playerBoard2);
            System.out.println("Выберете расстановку кораблей " + playerName2);
            System.out.println("1. Ручная");
            System.out.println("2. Автоматическая");
            int place1 = scanner.nextInt();
            switch (place1) {
                case 1:
                    playerBoard2.placeShipsMulti2();
                    break;
                case 2:
                    playerBoard2.placeRandomShipsMulti(false);
                    break;
                default:
                    System.out.println("Неверный выбор");
            }

            Date startTime = new Date();

            while (true) {
                if (playerBoard2.allShipsDestroyedMulti(false)) {
                    System.out.println("Победил " + playerName1);
                    break;
                }
                System.out.println(playerName1 + " ваше поле:");
                playerBoard1.printPlayerBoardMulti1();
                System.out.println("Поле противника:");
                playerBoard2.printEnemyBoardMulti2();
                String turnLog1 = "Ход игрока " + playerName1 + " в " + new Date();
                gameLog.writeLog(turnLog1);

                playerBoard1.playerMoveMulti1(playerBoard2);
                clearConsole.clearConsole();
                if (playerBoard1.allShipsDestroyedMulti(true)) {
                    System.out.println("Победил " + playerName2);
                    break;
                }
                System.out.println(playerName2 + " ваше поле:");
                playerBoard2.printPlayerBoardMulti2();
                System.out.println("Поле противника:");
                playerBoard1.printEnemyBoardMulti1();
                String turnLog2 = "Ход игрока " + playerName2 + " в " + new Date();
                gameLog.writeLog(turnLog2);
                playerBoard2.playerMoveMulti2(playerBoard1);
                clearConsole.clearConsole();



            }
            Date endTime = new Date();

            String logEntry = "Игра завершена. Игрок " + playerName1 + " против " + playerName2 +  ", Начало игры: " + startTime + ", Конец игры: " + endTime;

            gameLog.writeLog(logEntry);

        }
    }


}
