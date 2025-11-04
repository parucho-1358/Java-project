package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * (수정됨) 메인 메뉴 입력을 받습니다. 잘못된 입력 시 예외를 던집니다.
     */
    public int getMenuInput() {
        System.out.print("메뉴를 선택하세요: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.next();
            // 잘못된 타입 입력 시 예외 발생
            throw new InvalidInputException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }

        if (choice >= 0 && choice <= 2) {
            return choice;
        } else {
            // 잘못된 범위 입력 시 예외 발생
            throw new InvalidInputException("잘못된 입력입니다. (0, 1, 2 중 하나를 입력하세요)");
        }
    }

    /**
     * (수정됨) 전투 중 행동 입력을 받습니다. 잘못된 입력 시 예외를 던집니다.
     */
    public int getBattleInput() {
        System.out.print("행동을 선택하세요: ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.next();
            // 잘못된 타입 입력 시 예외 발생
            throw new InvalidInputException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }

        if (choice >= 1 && choice <= 3) {
            return choice;
        } else {
            // 잘못된 범위 입력 시 예외 발생
            throw new InvalidInputException("잘못된 입력입니다. (1, 2, 3 중 하나를 입력하세요)");
        }
    }

    /**
     * (수정됨) 인벤토리 선택 입력을 받습니다. 잘못된 입력 시 예외를 던집니다.
     */
    public int getInventoryChoiceInput(String prompt) {
        System.out.print(prompt);
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.next();
            // 잘못된 타입 입력 시 예외 발생
            throw new InvalidInputException("잘못된 입력입니다. 숫자를 입력해주세요.");
        }

        if (choice < 0) {
             // 0 미만 입력 시 예외 발생
             throw new InvalidInputException("잘못된 입력입니다. 0 이상의 숫자를 입력하세요.");
        }
        return choice;
    }
}