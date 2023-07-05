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
    private final ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @return 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount() {
        return (int) pieces.stream()
                .flatMap(ArrayList::stream)
                .filter(piece -> piece.getPieceType() != NO_PIECE)
                .count();
    }

    /**
     * 초기 폰 객체를 배치한다.
     */
    public void initPieces() {
        for (int i = 0; i < COLUMN_NUMBER; i++) {
            ArrayList<Piece> row = new ArrayList<>();
            for (int j = 0; j < ROW_NUMBER; j++) {
                row.add(PIECE_MAP[i][j] == NO_PIECE ?
                                Piece.createBlank() : Piece.createPiece(PIECE_MAP[i][j], COLOR_MAP[i][j]));
            }
            pieces.add(row);
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
        for (int i = 0; i < COLUMN_NUMBER; i++) {
            for (int j = 0; j < ROW_NUMBER; j++) {
                Piece piece = pieces.get(i).get(j);
                builder.append(piece.getRepresentation());
            }
            appendNewLine(builder);
        }
        return builder.toString();
    }
}
