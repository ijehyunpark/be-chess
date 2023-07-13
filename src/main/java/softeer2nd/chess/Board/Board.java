package softeer2nd.chess.Board;

import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceFactory;
import softeer2nd.chess.pieces.concrete.*;

import java.util.*;
import java.util.stream.Collectors;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.NEW_LINE;

public class Board {

    public static class Rank {

        public final List<RankPiece> rankPieces;

        private static class RankPiece {

            public Piece piece;
            public Boolean isMoved;

            public RankPiece(Piece piece) {
                this.piece = piece;
                this.isMoved = false;
            }

        }

        private Rank(List<Piece> pieces) {
            this.rankPieces = pieces.stream()
                    .map(RankPiece::new)
                    .collect(Collectors.toList());
        }

        public Piece getPiece(int x) {
            return rankPieces.get(x).piece;
        }

        public void setPiece(int x, Piece piece) {
            rankPieces.get(x).piece = piece;
        }

        public static Rank createBlankRank() {
            return createCustomRank("........");
        }

        public static Rank createCustomRank(String representation) {
            if (representation.length() != ROW_NUMBER)
                throw new IllegalArgumentException(ExceptionMessage.WRONG_ARGUMENT);

            List<Piece> pieces = new ArrayList<>();
            for (int i = 0; i < ROW_NUMBER; i++) {
                pieces.add(PieceFactory.createPiece(representation.charAt(i)));
            }

            return new Rank(pieces);
        }

        public boolean isMoved(int xPos) {
            return rankPieces.get(xPos).isMoved;
        }

        public void setMoved(int xPos) {
            rankPieces.get(xPos).isMoved = true;
        }

    }

    public static final int COLUMN_NUMBER = 8;
    public static final int ROW_NUMBER = 8;
    private List<Rank> ranks;
    private final List<Piece> sortedBlackPieces = new ArrayList<>(Arrays.asList(
            Pawn.createBlackPawn(), Knight.createBlackKnight(), Bishop.createBlackBishop(),
            Rook.createBlackRook(), King.createBlackKing(), Queen.createBlackQueen()));
    private final List<Piece> sortedWhitePieces = new ArrayList<>(Arrays.asList(
            Pawn.createWhitePawn(), Knight.createWhiteKnight(), Bishop.createWhiteBishop(),
            Rook.createWhiteRook(), King.createWhiteKing(), Queen.createWhiteQueen()));

    public int pieceCount() {
        return (int) ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getPieceType() != NO_PIECE)
                .count();
    }

    public int pieceCount(Piece.Type type) {
        return (int) ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getPieceType() == type)
                .count();
    }

    public int pieceCount(Piece.Color color) {
        return (int) ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getColor() == color)
                .count();
    }

    public int pieceCount(Piece.Type type, Piece.Color color) {
        return (int) ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getPieceType() == type && piece.getColor() == color)
                .count();
    }

    /**
     * 기본 보드판으로 초기화한다.
     */
    public void initialize() {
        this.ranks = List.of(
                Rank.createCustomRank("RNBQKBNR"),
                Rank.createCustomRank("PPPPPPPP"),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createCustomRank("pppppppp"),
                Rank.createCustomRank("rnbqkbnr")
        );
    }

    /**
     * 테스트를 위해 사용되는 메소드이다.
     * 빈 보드판으로 초기화한다.
     */
    public void initializeEmpty() {
        this.ranks = List.of(
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank(),
                Rank.createBlankRank()
        );
    }

    /**
     * 테스트를 위해 사용되는 메소드이다. <br/>
     * 보드판을 초기화한다. 특정 보드판으로 초기화된다.
     */
    public void initialize(String pieceAndColorMap) {
        String[] maps = pieceAndColorMap.split(NEW_LINE);
        this.ranks = new ArrayList<>();
        Arrays.stream(maps)
                .forEachOrdered(s -> ranks.add(Rank.createCustomRank(s)));
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getColumn())
                .getPiece(position.getRow());
    }

    public boolean isBlankPiece(Position position) {
        return findPiece(position).isBlank();
    }

    /**
     * 특정 보드판의 위치에 기물을 추가한다. <br />
     * Note: 해당 위치의 기존 기물를 무시하고 추가한다.
     */
    public void assignPiece(Position position, Piece piece) {
        ranks.get(position.getColumn())
                .setPiece(position.getRow(), piece);
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
        double score = ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(value -> value.getPieceType().getDefaultScore())
                .sum();

        return score - penaltyPawn(color);
    }

    /**
     * 패털티 점수: 같은 세로줄의 같은 색의 폰의 경우 점수 0.5점으로 처리한다. <br />
     * 같은 column의 개수가 2 이상인 해당 색깔의 폰을 찾은 후 그 개수만큼 점수에서 0.5점 차감한다.
     */
    private double penaltyPawn(Piece.Color color) {
        int targetPawnNumber = 0;
        for (int row = 0; row < ROW_NUMBER; row++) {
            int count = 0;
            for (int col = 0; col < COLUMN_NUMBER; col++) {
                Piece target = findPiece(new Position(col, row));
                if (target.getColor() != color || target.getPieceType() != PAWN) {
                    continue;
                }
                count++;
            }
            targetPawnNumber += count > 1 ? count : 0;
        }
        return (double) targetPawnNumber * 0.5;
    }

    /**
     * 기물 별로 가진 모든 점수를 합한 점수를 기준으로 정렬한다.
     */
    public void sortPieces(Piece.Color color) {
        Map<Piece.Type, Double> scores = calculatePieceScore(color);

        // 체스말 별로 점수 정렬
        List<Piece> targetSortedPieces = color == BLACK ? sortedBlackPieces : sortedWhitePieces;
        targetSortedPieces.sort(Comparator.comparingDouble(
                piece -> scores.get(piece.getPieceType())));
        Collections.reverse(targetSortedPieces);
    }

    /**
     * 각 기물 별로 점수 계산 <br />
     * 기물 개수 * 기물 기본 점수
     */
    private Map<Piece.Type, Double> calculatePieceScore(Piece.Color color) {
        Map<Piece.Type, Double> scores = new HashMap<>(Map.of(
                PAWN, 0.0,
                KNIGHT, 0.0,
                BISHOP, 0.0,
                ROOK, 0.0,
                KING, 0.0,
                QUEEN, 0.0));

        ranks.stream()
                .flatMap(rank -> rank.rankPieces.stream())
                .map(rankPiece -> rankPiece.piece)
                .filter(piece -> piece.getColor() == color)
                .forEach(piece -> scores.computeIfPresent(
                        piece.getPieceType(), (k, v) -> v + piece.getPieceType().getDefaultScore()));
        return scores;
    }

    public List<Piece> getSortedBlackPieces() {
        return sortedBlackPieces;
    }

    public List<Piece> getSortedWhitePieces() {
        return sortedWhitePieces;
    }

    public boolean isOutOfBoardIndex(Position position) {
        return position.getColumn() < 0 || position.getColumn() >= Board.COLUMN_NUMBER ||
                position.getRow() < 0 || position.getRow() >= Board.ROW_NUMBER;
    }

    public boolean isRemovedAllKing(Piece.Color color) {
        return pieceCount(KING, color) == 0;
    }

    public boolean isMoved(Position position) {
        return ranks.get(position.getColumn())
                .isMoved(position.getRow());
    }


    public void setPieceMoved(Position position) {
        ranks.get(position.getColumn())
                .setMoved(position.getRow());
    }

}
