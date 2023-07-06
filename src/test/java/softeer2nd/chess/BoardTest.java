package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.ChessGame;
import softeer2nd.chess.pieces.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.Board.BoardView.showBoard;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    Board board;
    String sample1 =
            ".KR....." +
            "P.PB...." +
            ".P..Q..." +
            "........" +
            ".....nq." +
            ".....p.." +
            "......p." +
            "....rk..";

    String sample2 =
            ".KR....." +
            "P.PB...." +
            ".P..Q..." +
            "........" +
            ".....nq." +
            ".....p.p" +
            ".....pp." +
            "....rk..";

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create(){
        ChessGame.initialize(board);
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
        ChessGame.initialize(board);

        assertEquals(32, board.pieceCount());
        assertEquals(16, board.pieceCount(BlankPiece.Color.BLACK));
        assertEquals(16, board.pieceCount(BlankPiece.Color.WHITE));

        assertEquals(16, board.pieceCount(BlankPiece.Type.PAWN));
        assertEquals(4, board.pieceCount(BlankPiece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(BlankPiece.Type.KING));


        assertEquals(8, board.pieceCount(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK));
        assertEquals(2, board.pieceCount(BlankPiece.Type.KNIGHT, BlankPiece.Color.WHITE));
        assertEquals(1, board.pieceCount(BlankPiece.Type.KING, BlankPiece.Color.BLACK));

        ChessGame.initialize(board, sample1);
        assertEquals(13, board.pieceCount());
        assertEquals(3, board.pieceCount(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK));
    }

    @Test
    @DisplayName("보드판을 임의로 초기화 테스트")
    void initPieces() {
        String expect =
                ".KR.....\n" +
                "P.PB....\n" +
                ".P..Q...\n" +
                "........\n" +
                ".....nq.\n" +
                ".....p..\n" +
                "......p.\n" +
                "....rk..\n";

        ChessGame.initialize(board, sample1);

        assertEquals(expect, showBoard(board));
    }

    @Test
    @DisplayName("기물 검색 테스트")
    void findPiece() {
        ChessGame.initialize(board);

        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.BLACK), board.findPiece(new Board.Position("a8")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.BLACK), board.findPiece(new Board.Position("h8")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.WHITE), board.findPiece(new Board.Position("a1")));
        assertEquals(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.WHITE), board.findPiece(new Board.Position("a1")));
    }

    @Test
    @DisplayName("보드판의 특정 위치에 새로운 기물 추가 테스트")
    void createPiece() {
        ChessGame.initialize(board);

        Board.Position sourcePosition = new Board.Position("b2");
        Board.Position targetPosition = new Board.Position("b3");
        ChessGame.move(board, sourcePosition, targetPosition);
        assertEquals(BlankPiece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Pawn.createWhitePawn(), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("점수 계산 메소드를 테스트한다.")
    void calculatePoint() {
        ChessGame.initializeEmpty(board);

        addPiece(new Board.Position("b6"), Pawn.createBlackPawn());
        addPiece(new Board.Position("e6"), Queen.createBlackQueen());
        addPiece(new Board.Position("b8"), King.createBlackKing());
        addPiece(new Board.Position("c6"), Rook.createBlackRook());

        addPiece(new Board.Position("f2"), Pawn.createWhitePawn());
        addPiece(new Board.Position("g2"), Pawn.createWhitePawn());
        addPiece(new Board.Position("e1"), Rook.createWhiteRook());
        addPiece(new Board.Position("f1"), King.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(BlankPiece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(BlankPiece.Color.WHITE), 0.01);

        System.out.println(showBoard(board));

        ChessGame.initialize(board, sample2);
        assertEquals(20.0, board.calculatePoint(BlankPiece.Color.BLACK), 0.01);
        assertEquals(19.5, board.calculatePoint(BlankPiece.Color.WHITE), 0.01);

    }

    private void addPiece(Board.Position position, BlankPiece piece) {
        board.assignPiece(position, piece);
    }

    @Test
    @DisplayName("점수 별로 정렬이 되는지 테스트한다.")
    void sort() {
        // given
        ChessGame.initialize(board, sample2);

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

    @Test
    @DisplayName("단순 이동 기물 이동 테스트")
    void moveNonRecursive() {
        // given
        String sample =
                ".K......" +
                "........" +
                "K....KN." +
                "........" +
                "........" +
                "........" +
                "........" +
                ".......K";

        String expect =
                "........\n" +
                "..K.....\n" +
                ".....KN.\n" +
                "........\n" +
                "K.......\n" +
                "........\n" +
                "........\n" +
                ".......K\n";

        ChessGame.initialize(board, sample);

        // when
        ChessGame.move(board, new Board.Position("b8"), new Board.Position("b7"));
        ChessGame.move(board, new Board.Position("b7"), new Board.Position("c7"));

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("c7"), new Board.Position("a8"))
        );

        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a5"));
        ChessGame.move(board, new Board.Position("a5"), new Board.Position("a4"));
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("h0"))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("i1"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("g6"))
        );

        // then
        assertEquals(expect, showBoard(board));
    }

    @Test
    @DisplayName("재귀적 이동 기물 이동 테스트")
    void moveRecursive() {
        // given
        String sample =
                "........" +
                "........" +
                "Q....QN." +
                "........" +
                "........" +
                "........" +
                "........" +
                ".......Q";

        String expect =
                "........\n" +
                "........\n" +
                "......N.\n" +
                "........\n" +
                "....Q...\n" +
                "Q.......\n" +
                "........\n" +
                ".....Q..\n";

        ChessGame.initialize(board, sample);

        // when
        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a8"));
        ChessGame.move(board, new Board.Position("a8"), new Board.Position("a6"));
        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a5"));
        ChessGame.move(board, new Board.Position("a5"), new Board.Position("a3"));


        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("g6"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("d4"))
        );
        ChessGame.move(board, new Board.Position("h1"), new Board.Position("e4"));
        ChessGame.move(board, new Board.Position("f6"), new Board.Position("f1"));


        // then
        assertEquals(expect, showBoard(board));
    }
}