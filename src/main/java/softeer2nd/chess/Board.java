package softeer2nd.chess;

import softeer2nd.chess.pieces.*;

import java.util.*;

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
    public static class Position {
        private int xPos;
        private int yPos;

        public Position(String position) {
            yPos = COLUMN_NUMBER - Character.getNumericValue(
                    position.charAt(1));
            xPos = position.charAt(0) - 'a';
        }
//        public int getYPos() {
//            return yPos;
//        }
//
//        public int getXPos() {
//            return xPos;
//        }
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
    private final List<Rank> pieces = new ArrayList<>();
    private final List<Piece> sortedBlackPieces = new ArrayList<>(Arrays.asList(
            Pawn.createBlackPawn(), Knight.createBlackKnight(), Bishop.createBlackBishop(),
            Rook.createBlackRook(), King.createBlackKing(), Queen.createBlackQueen()));
    private final List<Piece> sortedWhitePieces = new ArrayList<>(Arrays.asList(
            Pawn.createWhitePawn(), Knight.createWhiteKnight(), Bishop.createWhiteBishop(),
            Rook.createWhiteRook(), King.createWhiteKing(), Queen.createWhiteQueen()));

    /**
     * 현재 보드판에 존재하는 모든 기물의 개수를 찾는다.
     */
    public int pieceCount() {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() != NO_PIECE)
                .count();
    }

    /**
     * 현재 보드판에 존재하는 특정 종류의 기물의 개수를 찾는다.
     */
    public int pieceCount(Piece.Type type) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 특정 색깔의 체스 말의 개수를 찾는다.
     */
    public int pieceCount(Piece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 특정 종류 및 색깔에 해당되는 체스 말의 개수를 찾는다.
     */
    public int pieceCount(Piece.Type type, Piece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type && piece.getColor() == color)
                .count();
    }

    /**
     * 기물을 배치한다. <br/>
     * 기물 종류를 나타내는 {@link Board#PIECE_MAP}과 색깔을 나타내는 {@link Board#COLOR_MAP}을 사용하여 초기 기물 배치를 수행한다. <br/>
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
     * 테스트를 사용하기 위한 메소드이다. <br/>
     * 기물을 입력 받아서 배치한다.
     * @param pieceAndColorMap 기물 배치를 나타낸다. 개행문자를 포함하지 않으며 적절한 길이 ({@link Board#COLUMN_NUMBER} * {@link Board#ROW_NUMBER})만큼 입력해야 한다.
     *
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
     * 기물이 없는 보드판을 배치한다.
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
     * 보드판을 초기화한다. 기본 체스판으로 초기화된다.
     */
    public void initialize() {
        pieces.clear();
        initPieces();
    }

    /**
     * 테스트를 위해 사용되는 메소드이다. <br/>
     * 체스 보드판을 초기화한다. 특정 체스판으로 초기화된다.
     */
    public void initialize(String pieceAndColorMap) {
        pieces.clear();
        initPieces(pieceAndColorMap);
    }

    /**
     * 현재 보드판의 상태를 문자열로 변환하여 반환한다.
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
     * 특정 위치의 기물을 찾는다.
     * @param position 체스 보드판 내의 위치 (example "a5")
     */
    public Piece findPiece(String position) {
        Position boardPosition = new Position(position);
        return pieces.get(boardPosition.yPos)
                .rank.get(boardPosition.xPos);
    }

    /**
     * 특정 보드판의 위치에 기물을 추가한다. <br />
     * Note: 해당 위치의 기존 기물를 무시하고 추가한다.
     */
    public void assignPiece(String position, Piece piece) {
        Position boardPosition = new Position(position);
        pieces.get(boardPosition.yPos)
                .rank.set(boardPosition.xPos, piece);
    }

    /**
     * 보드판의 기물을 교환한다. <br/>
     * 구체적으로 보드판에서 sourcePosition와 targetPosition의 위치를 교환한다.
     */
    public void move(String sourcePosition, String targetPosition) {
        Piece source = findPiece(sourcePosition);
        Piece target = findPiece(targetPosition);

        assignPiece(sourcePosition, target);
        assignPiece(targetPosition, source);
    }

    /**
     * 게임에서 특정 색깔의 현재 점수를 계산한다. <br/>
     * 각 기물의 점수는 다음과 같이 계산된다. <br/>
     * {@link Queen}: 9, {@link Rook}: 5, {@link Bishop}: 3, {@link Knight}: 2.5, {@link Pawn}: 1, {@link King}: 0 <br/>
     * 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다. <br/>
     * {@link King}의 경우 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
     */
    public double calculatePoint(Piece.Color color) {
        // 기본 점수 계산
        double score = pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(value -> value.getPieceType().getDefaultScore())
                .sum();


        // 특수 점수(Pawn에 대한 예외처리: 같은 세로줄의 같은 색의 폰의 경우 점수 0.5점 처리)
        // 같은 column의 개수가 2 이상인 해당 색깔의 폰을 찾은 후 그 개수만큼 점수에서 0.5점 차감한다.
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

    /**
     * 점수 순서대로 기물을 정렬한다.
     */
    public void sortPieces(Piece.Color color) {
        // 각 체스말 별로 점수 계산
        Map<Piece.Type, Double> scores = new HashMap<>(Map.of(
                PAWN, 0.0,
                KNIGHT, 0.0,
                BISHOP, 0.0,
                ROOK, 0.0,
                KING, 0.0,
                QUEEN, 0.0));

        pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .forEach(piece -> {
                    scores.computeIfPresent(piece.getPieceType(), (k, v) -> v + piece.getPieceType().getDefaultScore());
                });

        // 체스말 별로 점수 정렬
        List<Piece> targetSortedPieces = color == BLACK ?
                sortedBlackPieces : sortedWhitePieces;
        targetSortedPieces.sort(Comparator.comparingDouble(
                piece -> scores.get(piece.getPieceType())));
        Collections.reverse(targetSortedPieces);
    }

    public List<Piece> getSortedBlackPieces() {
        return sortedBlackPieces;
    }

    public List<Piece> getSortedWhitePieces() {
        return sortedWhitePieces;
    }
}
