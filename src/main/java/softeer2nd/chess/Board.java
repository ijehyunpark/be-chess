package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 체스 보드판을 나타낸다.
 */
public class Board {
    public static final int COLUMN_NUMBER = 8;
    public static final int ROW_NUMBER = 8;
    public static final int INIT_PAWN_NUMBER = 8;
    private final List<Token> tokens = new ArrayList<>();
    private int[][] tokenPosition = new int[COLUMN_NUMBER][ROW_NUMBER];

    public Board() {
        Arrays.stream(tokenPosition).forEach(column -> Arrays.fill(column, -1));
    }

    /**
     * 체스 보드판에 새로운 게임 말을 추가한다.
     * @param token 추가할 새로운 게임 말(폰만 허용)
     */
    public void add(Token token) {
        this.tokens.add(token);
    }

    /**
     * 현재 체스 보드판에 존재하는 게임 말의 개수를 찾는다.
     * @return 체스 보드판 내의 게임 말 개수
     */
    public int size() {
        return tokens.size();
    }

    /**
     * 체스 보드판에 할당되어 있는 특정 게임 말을 찾는다.
     * @param i 게임 말이 할당되어 있는 인덱스
     * @return 만약, 게임 말이 존재할 경우 해당 게임말을 반환한다. 없을 경우 null을 반환하다.
     */
    public Pawn findPawn(int i) {
        if(this.tokens.size() <= i || i < 0)
            throw new IllegalArgumentException("잘못된 인덱스 접근입니다.");

        Token target = this.tokens.get(i);
        if(target.isPawn())
            return (Pawn) target;

        throw new IllegalArgumentException("폰 객체가 아닙니다.");
    }

    /**
     * 체스 보드판을 초기화한다.
     */
    public void initialize() {
        // 흰색, 검은색 폰을 초기 개수만큼 할당한다.
        for (int i = 0; i < INIT_PAWN_NUMBER; i++) {
            tokens.add(new Pawn(Color.BLACK));
            tokens.add(new Pawn(Color.WHITE));
        }

        for (int i = 0; i < ROW_NUMBER; i++) {
            tokenPosition[1][i] = i * 2;
            tokenPosition[6][i] = i * 2 + 1;
        }
    }

    /**
     * 해당 보드판 위치에 존재하는 게임 말을 찾는다.
     * @param column 보드판 세로 좌표
     * @param row 보드판 가로 좌표
     * @return 만약 해당 보드판 위치에 게임말이 존재할 경우 해당 게임말을 반환한다. 없는 경우 null을 반환한다.
     */
    public Optional<Token> getTokenByPosition(int column, int row) {
        Token result = tokenPosition[column][row] == -1 ? null : tokens.get(tokenPosition[column][row]);
        return Optional.ofNullable(result);
    }
}
