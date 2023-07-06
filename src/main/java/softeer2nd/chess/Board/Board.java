package softeer2nd.chess.Board;

import softeer2nd.chess.pieces.*;

import java.util.*;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

public class Board {
    /**
     * 체스판의 row 객체를 나타낸다.
     */
    public static class Rank {
        public final ArrayList<abstrctPiece> rank = new ArrayList<>();
    }

    public static class Position {
        private int xPos;
        private int yPos;

        /**
         * @param position "a3" 과 같은 보드판 위치 정보를 사용해야 합니다. <br/>
         *                 이는 8 * 8 보드판 인덱스에서 (col = 5, row = 0)를 나타냅니다.
         */
        public Position(String position) {
            yPos = COLUMN_NUMBER - Character.getNumericValue(
                    position.charAt(1));
            xPos = position.charAt(0) - 'a';
        }

        public int getXPos() {
            return xPos;
        }

        public int getYPos() {
            return yPos;
        }
    }

    public static final int COLUMN_NUMBER = 8;
    public static final int ROW_NUMBER = 8;
    private final List<Rank> pieces = new ArrayList<>();
    private final List<abstrctPiece> sortedBlackPieces = new ArrayList<>(Arrays.asList(
            Pawn.createBlackPawn(), Knight.createBlackKnight(), Bishop.createBlackBishop(),
            Rook.createBlackRook(), King.createBlackKing(), Queen.createBlackQueen()));
    private final List<abstrctPiece> sortedWhitePieces = new ArrayList<>(Arrays.asList(
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
    public int pieceCount(abstrctPiece.Type type) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 특정 색깔의 체스 말의 개수를 찾는다.
     */
    public int pieceCount(abstrctPiece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getColor() == color)
                .count();
    }

    /**
     * 현재 체스 보드판에 존재하는 특정 종류 및 색깔에 해당되는 체스 말의 개수를 찾는다.
     */
    public int pieceCount(abstrctPiece.Type type, abstrctPiece.Color color) {
        return (int) pieces.stream()
                .flatMap(rank -> rank.rank.stream())
                .filter(piece -> piece.getPieceType() == type && piece.getColor() == color)
                .count();
    }

    public void clear() {
        pieces.clear();
    }

    /**
     * 테스트를 사용하기 위한 메소드이다. <br/>
     * 배치도에 따라 기물을 배치한다..
     * @param pieceAndColorMap 기물 배치를 나타낸다. 개행문자를 포함하지 않으며 적절한 길이 ({@link Board#COLUMN_NUMBER} * {@link Board#ROW_NUMBER})만큼 입력해야 한다.
     */
    public void initPieces(String pieceAndColorMap) {
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            Rank rank = new Rank();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rank.rank.add(abstrctPiece.createPiece(
                        pieceAndColorMap.charAt(col * COLUMN_NUMBER + row)
                ));
            }
            pieces.add(rank);
        }
    }

    /**
     * 배치도에 따라 기물을 배치한다. <br/>
     * 기물 종류를 나타내는  pieceMap과 색깔을 나타내는 colorMap을 사용하여 초기 기물 배치를 수행한다. <br/>
     * Note: 각 pieceMap과 colorMap은 {@link Board#COLUMN_NUMBER} * {@link Board#ROW_NUMBER} 의 크기를 가지고 있어야 한다.
     */
    @Deprecated
    public void initPieces(abstrctPiece.Type[][] pieceMap, abstrctPiece.Color[][] colorMap) {
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            Rank rank = new Rank();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rank.rank.add(pieceMap[col][row] == NO_PIECE ?
                        abstrctPiece.createBlank() : abstrctPiece.createPiece(pieceMap[col][row], colorMap[col][row]));
            }
            pieces.add(rank);
        }
    }

    public abstrctPiece findPiece(Position position) {
        return pieces.get(position.yPos)
                .rank.get(position.xPos);
    }

    public abstrctPiece findPiece(int column, int row) {
        return pieces.get(column)
                .rank.get(row);
    }

    /**
     * 특정 보드판의 위치에 기물을 추가한다. <br />
     * Note: 해당 위치의 기존 기물를 무시하고 추가한다.
     */
    public void assignPiece(Position position, abstrctPiece piece) {
        pieces.get(position.yPos)
                .rank.set(position.xPos, piece);
    }

    /**
     * 게임에서 특정 색깔의 현재 점수를 계산한다. <br/>
     * 각 기물의 점수는 다음과 같이 계산된다. <br/>
     * {@link Queen}: 9, {@link Rook}: 5, {@link Bishop}: 3, {@link Knight}: 2.5, {@link Pawn}: 1, {@link King}: 0 <br/>
     * 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다. <br/>
     * {@link King}의 경우 잡히는 경우 경기가 끝나기 때문에 점수가 없다.
     */
    public double calculatePoint(abstrctPiece.Color color) {
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
                abstrctPiece target = pieces.get(col).rank.get(row);
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
    public void sortPieces(abstrctPiece.Color color) {
        // 각 체스말 별로 점수 계산
        Map<abstrctPiece.Type, Double> scores = new HashMap<>(Map.of(
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
        List<abstrctPiece> targetSortedPieces = color == BLACK ?
                sortedBlackPieces : sortedWhitePieces;
        targetSortedPieces.sort(Comparator.comparingDouble(
                piece -> scores.get(piece.getPieceType())));
        Collections.reverse(targetSortedPieces);
    }

    public List<abstrctPiece> getSortedBlackPieces() {
        return sortedBlackPieces;
    }

    public List<abstrctPiece> getSortedWhitePieces() {
        return sortedWhitePieces;
    }
}
