package project;

import java.util.Scanner;

public class Game {
    private Scanner scanner;
    private Player player;
    private InputHandler inputHandler;
    private BattleManager battleManager;

    public Game() {
        scanner = new Scanner(System.in);
        player = new Player("용사", 100, 15);

        inputHandler = new InputHandler(scanner);
        battleManager = new BattleManager(player, inputHandler);
    }

    public void start() {
        while (true) {
            showTitle();
            showMenu();
            
            // 예외 처리 통합: InvalidInputException 처리.
            int choice = -1;
            try {
                choice = inputHandler.getMenuInput();
                
            } catch (InvalidInputException e) {
                // 잘못된 입력 메시지를 출력하고, 턴을 넘기지 않고 다시 메뉴를 보여줍니다.
                System.out.println("❌ " + e.getMessage());
                continue; // while 루프의 처음으로 돌아가 메뉴를 다시 보여줍니다.
            }

            switch (choice) {
                case 1:
                    battleManager.startBattle();
                    break;
                case 2:
                    player.showInventory();
                    break;
                case 0:
                    System.out.println("게임을 종료합니다.");
                    return;
            }
        }
    }

    private void showTitle() {
        System.out.println("=====================");
        System.out.println("  간단 텍스트 RPG");
        System.out.println("=====================");
    }

    private void showMenu() {
        System.out.println("1. 전투 시작");
        System.out.println("2. 인벤토리 보기");
        System.out.println("0. 종료");
    }

    // 게임 시작 지점.(main)
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}