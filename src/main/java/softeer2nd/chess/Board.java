package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;
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
     * @param position 체스 보드판 내의 인덱스
     * @return 체스 보드판에서 해당 index애 위치한 Piece 객체
     */
    public Piece findPiece(String position) {
        int xPos = position.charAt(0) - 'a';
        int yPos = COLUMN_NUMBER - Character.getNumericValue(
                position.charAt(1));

        return pieces.get(yPos).rank.get(xPos);
    }

    /**
     * 체스 보드판에 특정 체스말을 추가한다.
     * @param position 체스 보드판 내의 위치 (exampel "a5")
     * @param piece 추가하고자 하는 체스말
     */
    public void move(String position, Piece piece) {
        int xPos = position.charAt(0) - 'a';
        int yPos = COLUMN_NUMBER - Character.getNumericValue(
                position.charAt(1));

        pieces.get(yPos).rank.set(xPos, piece);
    }
}
