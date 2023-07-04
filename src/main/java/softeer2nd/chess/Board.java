package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceType;

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
    private final List<Piece> pieces = new ArrayList<>();
    private final int[][] piecePosition = new int[COLUMN_NUMBER][ROW_NUMBER];

    /**
     * 체스 보드판에 새로운 게임 말을 추가한다.
     * @param piece 추가할 새로운 게임 말
     * @param column 추가할 새로운 게임 말의 세로축 위치
     * @param row 추가할 새로운 게임 말의 가로축 위치
     */
    public void add(Piece piece, int column, int row) {
        if(piecePosition[column][row] != -1)
            throw new IllegalArgumentException("이미 다른 게임 말이 존재하는 위치입니다.");
        this.pieces.add(piece);
        this.piecePosition[column][row] = this.size() - 1;
    }


    /**
     * 현재 체스 보드판에 존재하는 게임 말의 개수를 찾는다.
     * @return 체스 보드판 내의 게임 말 개수
     */
    public int size() {
        return pieces.size();
    }

    /**
     * 체스 보드판에 할당되어 있는 특정 게임 말을 찾는다.
     * @param i 게임 말이 할당되어 있는 인덱스
     * @return 만약, 게임 말이 존재할 경우 해당 게임말을 반환한다. 없을 경우 null을 반환하다.
     */
    public Piece findPiece(int i) {
        if(this.pieces.size() <= i || i < 0)
            throw new IllegalArgumentException("잘못된 인덱스 접근입니다.");

        return this.pieces.get(i);
    }

    /**
     * 토큰 정보를 초기화한다.
     */
    public void clear() {
        // 할당된 토큰을 모두 삭제한다.
        pieces.clear();
        // 토큰의 위치 정보를 제거한다.
        Arrays.stream(piecePosition).forEach(column -> Arrays.fill(column, -1));
    }

    /**
     * 초기 폰 객체를 배치한다.
     */
    public void initPawn() {
        // 흰색, 검은색 폰을 초기 개수만큼 할당한다.
        for (int i = 0; i < INIT_PAWN_NUMBER; i++) {
            add(new Pawn(Color.BLACK), 1, i);
            add(new Pawn(Color.WHITE), 6, i);
        }
    }

    /**
     * 체스 보드판을 초기화한다.
     */
    public void initialize() {
        clear();
        initPawn();
    }

    /**
     * 해당 보드판 위치에 존재하는 게임 말을 찾는다.
     * @param column 보드판 세로 좌표
     * @param row 보드판 가로 좌표
     * @return 만약 해당 보드판 위치에 게임말이 존재할 경우 해당 게임말을 반환한다. 없는 경우 null을 반환한다.
     */
    public Optional<Piece> getPieceByPosition(int column, int row) {
        Piece result = piecePosition[column][row] == -1 ? null : pieces.get(piecePosition[column][row]);
        return Optional.ofNullable(result);
    }

    /**
     * 현재 체스 보드판의 상태를 문자열로 변환한다.
     * @return 현재 체스 보드판의 상태를 나타내는 문자열
     */
    public String print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < COLUMN_NUMBER; i++) {
            for (int j = 0; j < ROW_NUMBER; j++) {
                Optional<Piece> piece = getPieceByPosition(i, j);
                builder.append(piece.map(Piece::getRepresentation).orElse('.'));
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
