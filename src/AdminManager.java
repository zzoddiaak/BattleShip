import java.io.*;
import java.util.*;
class AdminManager {
    private Map<String, Player> players;

    public AdminManager(Map<String, Player> players) {
        this.players = players;
    }

    public void adminMode() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nРежим администратора");
            System.out.println("1. Просмотреть все игры");
            System.out.println("2. Удалить игру");
            System.out.println("3. Архивировать игру");
            System.out.println("4. Выйти из режима администратора");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

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
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("archive.txt", true))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
            System.out.println("Игры архивированы.");
        } catch (IOException e) {
            System.out.println("Ошибка при архивации игр: " + e.getMessage());
        }
    }
}
