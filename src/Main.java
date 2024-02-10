import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите цифру:");
            System.out.println("1. Старт одиночной игры.");
            System.out.println("2. Старт игры с напарником.");
            System.out.println("3. Выход.");

            int num = scanner.nextInt();
            switch (num){
                case 1:
                    GameManager gameManager = new GameManager();
                    gameManager.startGame();
                    break;
                case 2:
                    GameManager gameManager2 = new GameManager();
                    gameManager2.startMultiGame();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

}
