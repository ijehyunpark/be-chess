package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.pieces.BlankPiece;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceFactory;
import softeer2nd.chess.pieces.concrete.King;
import softeer2nd.chess.pieces.concrete.Pawn;
import softeer2nd.chess.pieces.concrete.Queen;
import softeer2nd.chess.pieces.concrete.Rook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.Board.BoardView.showBoard;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    Board board;
    String sample1 = appendNewLine(
            ".KR.....",
            "P.PB....",
            ".P..Q...",
            "........",
            ".....nq.",
            ".....p..",
            "......p.",
            "....rk.."
    );

    String sample2 = appendNewLine(
            ".KR.....",
            "P.PB....",
            ".P..Q...",
            "........",
            ".....nq.",
            ".....p.p",
            ".....pp.",
            "....rk.."
    );

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                showBoard(board));
    }

    @Test
    @DisplayName("보드판에서 기물 개수 계산 테스트")
    void pieceCount() {
        board.initialize();

        assertEquals(32, board.pieceCount());
        assertEquals(16, board.pieceCount(BlankPiece.Color.BLACK));
        assertEquals(16, board.pieceCount(BlankPiece.Color.WHITE));

        assertEquals(16, board.pieceCount(BlankPiece.Type.PAWN));
        assertEquals(4, board.pieceCount(BlankPiece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(BlankPiece.Type.KING));


        assertEquals(8, board.pieceCount(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK));
        assertEquals(2, board.pieceCount(BlankPiece.Type.KNIGHT, BlankPiece.Color.WHITE));
        assertEquals(1, board.pieceCount(BlankPiece.Type.KING, BlankPiece.Color.BLACK));

        board.initialize(sample1);
        assertEquals(13, board.pieceCount());
        assertEquals(3, board.pieceCount(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK));
    }

    @Test
    @DisplayName("보드판을 임의로 초기화 테스트")
    void initPieces() {
        String expect = appendNewLine(
                ".KR.....",
                "P.PB....",
                ".P..Q...",
                "........",
                ".....nq.",
                ".....p..",
                "......p.",
                "....rk.."
        );

        board.initialize(sample1);

        assertEquals(expect, showBoard(board));
    }

    @Test
    @DisplayName("기물 검색 테스트")
    void findPiece() {
        board.initialize();

        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.BLACK), board.findPiece(new Position("a8")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.BLACK), board.findPiece(new Position("h8")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.WHITE), board.findPiece(new Position("a1")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.WHITE), board.findPiece(new Position("a1")));
    }

    @Test
    @DisplayName("보드판의 특정 위치에 새로운 기물 추가 테스트")
    void createPiece() {
        board.initialize();
        GameManager chessGame = new GameManager(board);
        chessGame.start();

        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");
        chessGame.move(sourcePosition, targetPosition);
        assertEquals(BlankPiece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Pawn.createWhitePawn(), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("점수 계산 메소드를 테스트한다.")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece(new Position("b6"), Pawn.createBlackPawn());
        addPiece(new Position("e6"), Queen.createBlackQueen());
        addPiece(new Position("b8"), King.createBlackKing());
        addPiece(new Position("c6"), Rook.createBlackRook());

        addPiece(new Position("f2"), Pawn.createWhitePawn());
        addPiece(new Position("g2"), Pawn.createWhitePawn());
        addPiece(new Position("e1"), Rook.createWhiteRook());
        addPiece(new Position("f1"), King.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(BlankPiece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(BlankPiece.Color.WHITE), 0.01);

        System.out.println(showBoard(board));

        board.initialize(sample2);
        assertEquals(20.0, board.calculatePoint(BlankPiece.Color.BLACK), 0.01);
        assertEquals(19.5, board.calculatePoint(BlankPiece.Color.WHITE), 0.01);

    }

    private void addPiece(Position position, Piece piece) {
        board.assignPiece(position, piece);
    }

    @Test
    @DisplayName("점수 별로 정렬이 되는지 테스트한다.")
    void sort() {
        // given
        board.initialize(sample2);

        // when
        board.sortPieces(BlankPiece.Color.BLACK);
        board.sortPieces(BlankPiece.Color.WHITE);

        // then
        assertEquals(6, board.getSortedBlackPieces().size());
        assertEquals(Queen.createBlackQueen(), board.getSortedBlackPieces().get(0));
        assertEquals(Rook.createBlackRook(), board.getSortedBlackPieces().get(1));

        assertEquals(6, board.getSortedWhitePieces().size());
        assertEquals(Queen.createWhiteQueen(), board.getSortedWhitePieces().get(0));
        assertEquals(Rook.createWhiteRook(), board.getSortedWhitePieces().get(1));
    }

}
