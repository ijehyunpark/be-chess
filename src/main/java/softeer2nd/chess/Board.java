package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

/**
 * 체스 보드판을 나타낸다.
 */
public class Board {
    /**
     * 체스판의 row 객체를 나타낸다.
     */
    public static class Rank {
        public final ArrayList<Piece> rank = new ArrayList<>();
    }

    /**
     * 체스판 내의 위치 정보를 내부 인덱스 정보로 변환하는 유틸리티 객체이다.
     */
    private static class PositionUtils {
        /**
         * 체스판 위치 정보로부터 배열의 ROW(X) 좌표를 추출한다.
         * @param position 체스판 위치 정보
         * @return 체스판 내부 pieces객체와 연관되는 인덱스 x 좌표
         */
        public static int extractXPos(final String position){
            return position.charAt(0) - 'a';
        }

        /**
         * 체스판 위치 정보로부터 배열의 COL(Y) 좌표를 추출한다.
         * @param position 체스판 위치 정보
         * @return 체스판 내부 pieces 객체와 연관되는 인덱스 y 좌표
         */
        public static int extractYPos(final String position) {
            return COLUMN_NUMBER - Character.getNumericValue(
                    position.charAt(1));
        }
    }

    public static final int COLUMN_NUMBER = 8;
    public static final int ROW_NUMBER = 8;
    public static final Piece.Type[][] PIECE_MAP = {
            { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK },
            { PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN },
            { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK }
    };

    public static final Piece.Color[][] COLOR_MAP = {
            { BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, },
            { BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, },
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, },
            { WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, }
    };
    private final ArrayList<Rank> pieces = new ArrayList<>();

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @return 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount() {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() != NO_PIECE)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @param type 체스 말의 종류
     * @return 해당 type을 가지는 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount(Piece.Type type) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @param color 체스 말의 색깔
     * @return 해당 color를 가지는 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount(Piece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @param type 체스 말의 종류
     * @param color 체스 말의 색깔
     * @return 해당 type과 color에 해당하는 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount(Piece.Type type, Piece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type && piece.getColor() == color)
                .count();
    }

    /**
     * 초기 폰 객체를 배치한다.
     */
    public void initPieces() {
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            Rank rank = new Rank();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rank.rank.add(PIECE_MAP[col][row] == NO_PIECE ?
                                Piece.createBlank() : Piece.createPiece(PIECE_MAP[col][row], COLOR_MAP[col][row]));
            }
            pieces.add(rank);
        }
    }

    /**
     * 초기 폰 객체를 배치한다.
     * @param pieceAndColorMap 배치팔 폰 객체 정보
     */
    public void initPieces(String pieceAndColorMap) {
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            Rank rank = new Rank();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rank.rank.add(Piece.createPiece(
                        pieceAndColorMap.charAt(col * COLUMN_NUMBER + row)
                ));
            }
            pieces.add(rank);
        }
    }

    /**
     * 체스 보드판에서 모든 체스말을 제거한다.
     */
    public void initializeEmpty() {
        pieces.clear();
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            Rank rank = new Rank();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rank.rank.add(Piece.createBlank());
            }
            pieces.add(rank);
        }
    }

    /**
     * 체스 보드판을 초기화한다.
     */
    public void initialize() {
        pieces.clear();
        initPieces();
    }

    /**
     * 체스 보드판을 초기화한다.
     * @param pieceAndColorMap 배치팔 폰 객체 정보
     */
    public void initialize(String pieceAndColorMap) {
        pieces.clear();
        initPieces(pieceAndColorMap);
    }

    /**
     * 현재 체스 보드판의 상태를 문자열로 변환한다.
     * @return 현재 체스 보드판의 상태를 나타내는 문자열
     */
    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            for (int row = 0; row < ROW_NUMBER; row++) {
                Piece piece = pieces.get(col).rank.get(row);
                builder.append(piece.getRepresentation());
            }
            appendNewLine(builder);
        }
        return builder.toString();
    }


    /**
     * 특정 index의 Piece를 찾는다.
     * @param position 체스 보드판 내의 위치 (example "a5")
     * @return 체스 보드판에서 해당 위치애 위치한 Piece 객체
     */
    public Piece findPiece(String position) {
        return pieces.get(PositionUtils.extractYPos(position))
                .rank.get(PositionUtils.extractXPos(position));
    }

    /**
     * 체스 보드판에 특정 체스말을 추가한다.
     * @param position 체스 보드판 내의 위치 (example "a5")
     * @param piece 추가하고자 하는 체스말
     */
    public void move(String position, Piece piece) {
        pieces.get(PositionUtils.extractYPos(position))
                .rank.set(PositionUtils.extractXPos(position), piece);
    }

    /**
     * 체스 게임에서 특정 색깔의 현재 점수를 계산한다.
     * 각 기물의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
     * pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
     * king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
     * @param color 점수를 측정하는 색깔
     * @return 현재까지의 점수
     */
    public double calculatePoint(Piece.Color color) {
        // 기본 점수 계산
        double score = pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(value -> value.getPieceType().getDefaultScore())
                .sum();

        // 특수 점수(pawn 예외; 같은 세로줄의 같은 색의 폰의 경우 점수 0.5점 처리
        // 1. 해당 색깔의 폰의 개수를 찾는다.
        // 2. 그 후 만약 같은 column에 폰의 개수가 2 이상인 모든 폰의 개수를 찾는다.
        // 3. 찾은 폰의 개수만큼 점수에서 차감한다.
        int targetPawnNumber = 0;
        for (int row = 0; row < ROW_NUMBER; row++) {
            int count = 0;
            for (int col = 0; col < COLUMN_NUMBER; col++) {
                Piece target = pieces.get(col).rank.get(row);
                if(target.getColor() != color)
                    continue;
                if(target.getPieceType() != PAWN)
                    continue;
                count++;
            }
            targetPawnNumber += count > 1 ? count : 0;
        }
        score -= (double) targetPawnNumber * 0.5;
        return score;
    }
}
