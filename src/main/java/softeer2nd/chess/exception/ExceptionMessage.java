package softeer2nd.chess.exception;

public abstract class ExceptionMessage {

    public static final String OUT_OF_BOUND_INPUT = "보드판 범위를 벗어나는 입력입니다.";
    public static final String MOVE_NOT_MOVABLE_PIECE = "이동할 수 없는 기물입니다.";
    public static final String IMPOSSIBLE_MOVEMENT = "이동할 수 없는 위치입니다.";
    public static final String WRONG_ARGUMENT = "잘못된 입력값 입니다.";
    public static final String WRONG_TURN = "자신의 턴에는 상대 기물을 조작할 수 없습니다.";

}
