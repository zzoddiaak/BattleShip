import java.util.*;
class GameManager {

    private Map<String, Player> players;
    private Player currentAdmin;

    public GameManager() {
        this.players = new HashMap<>();
        this.currentAdmin = null;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя игрока: ");
        String playerName = scanner.next();

        if (playerName.equalsIgnoreCase("admin")) {
            System.out.print("Введите пароль для режима администратора: ");
            String adminPassword = scanner.next();

            if (adminPassword.equals("admin123")) {
                currentAdmin = new Player("admin");
                adminMode();
            } else {
                System.out.println("Неверный пароль. Игра завершена.");
            }
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
            switch (place){
                case 1:
                    playerBoard.placeShips();
                break;
                case 2:
                    playerBoard.placeRandomShips(true);
                break;
                default:
                    System.out.println("Неверный выбор");
            }
            // Размещение кораблей противника (бота)
            enemyBoard.placeRandomShips(false);

            // Игровой цикл
            while (!playerBoard.allShipsDestroyed()) {
                System.out.println("Ваше поле:");
                playerBoard.printPlayerBoard();
                System.out.println("Поле противника:");
                enemyBoard.printEnemyBoard();


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

    private void adminMode() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nРежим администратора");
            System.out.println("1. Просмотреть все игры");
            System.out.println("2. Удалить игру");
            System.out.println("3. Архивировать игру");
            System.out.println("4. Выйти из режима администратора");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewAllGames();
                    break;
                case 2:
                    deleteGame();
                    break;
                case 3:
                    archiveGame();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void viewAllGames() {
        System.out.println("\nСписок всех сыгранных игр:");
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            System.out.println("Игрок: " + player.getName());
            List<GameBoard> games = player.getPlayedGames();
            for (int i = 0; i < games.size(); i++) {
                System.out.println("Игра " + (i + 1));
                games.get(i).printPlayerBoard();
                System.out.println();
            }
        }
    }

    private void deleteGame() {
        viewAllGames();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя игрока: ");
        String playerName = scanner.next();

        Player player = players.get(playerName);
        if (player != null) {
            System.out.print("Введите номер игры для удаления: ");
            int gameNumber = scanner.nextInt();
            List<GameBoard> games = player.getPlayedGames();
            if (gameNumber >= 1 && gameNumber <= games.size()) {
                games.remove(gameNumber - 1);
                System.out.println("Игра удалена.");
            } else {
                System.out.println("Неверный номер игры.");
            }
        } else {
            System.out.println("Игрок с именем " + playerName + " не найден.");
        }
    }

    private void archiveGame() {
        viewAllGames();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя игрока: ");
        String playerName = scanner.next();

        Player player = players.get(playerName);
        if (player != null) {
            System.out.print("Введите номер игры для архивации: ");
            int gameNumber = scanner.nextInt();
            List<GameBoard> games = player.getPlayedGames();
            if (gameNumber >= 1 && gameNumber <= games.size()) {
                GameBoard gameToArchive = games.get(gameNumber - 1);
                // Добавить логику архивации (например, сохранение в файл или другую структуру данных)
                // В данном примере просто удалим игру из списка игр у игрока
                games.remove(gameToArchive);
                System.out.println("Игра архивирована.");
            } else {
                System.out.println("Неверный номер игры.");
            }
        } else {
            System.out.println("Игрок с именем " + playerName + " не найден.");
        }
    }
}