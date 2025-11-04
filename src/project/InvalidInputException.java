package project;

/**
 * 잘못된 메뉴/행동 번호가 입력되었을 때 발생하는 사용자 정의 예외입니다.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}