import java.util.*;
public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру:");
        System.out.println("1. Старт одиночной игры.");
        System.out.println("2. Старт игры с напарником.");
        System.out.println("3. Выход.");
        int num = scanner.nextInt();
        switch (num){
            case 1:
                gameManager.startGame();
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");

        }
    }
}
