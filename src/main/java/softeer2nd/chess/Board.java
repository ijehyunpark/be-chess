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
     * 체스 보드판을 초기화한다.
     */
    public void initialize() {
        pieces.clear();
        initPieces();
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
}
