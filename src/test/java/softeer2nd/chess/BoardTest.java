package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create(){
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("보드판에서 체스 말 개수 계산 테스트")
    void pieceCount() {
        board.initialize();

        assertEquals(32, board.pieceCount());
        assertEquals(16, board.pieceCount(Piece.Color.BLACK));
        assertEquals(16, board.pieceCount(Piece.Color.WHITE));

        assertEquals(16, board.pieceCount(Piece.Type.PAWN));
        assertEquals(4, board.pieceCount(Piece.Type.KNIGHT));
        assertEquals(2, board.pieceCount(Piece.Type.KING));


        assertEquals(8, board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK));
        assertEquals(2, board.pieceCount(Piece.Type.KNIGHT, Piece.Color.WHITE));
        assertEquals(1, board.pieceCount(Piece.Type.KING, Piece.Color.BLACK));



        board.initialize(sample1);
        assertEquals(13, board.pieceCount());
        assertEquals(3, board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK));
    }

    @Test
    @DisplayName("체스판을 임의로 초기화 테스트")
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

        board.initialize(sample1);

        assertEquals(expect, board.showBoard());
    }

    @Test
    @DisplayName("Piece 검색 테스트")
    void findPiece() {
        board.initialize();

        assertEquals(Piece.createPiece(Piece.Type.ROOK, Piece.Color.BLACK), board.findPiece("a8"));
        assertEquals(Piece.createPiece(Piece.Type.ROOK, Piece.Color.BLACK), board.findPiece("h8"));
        assertEquals(Piece.createPiece(Piece.Type.ROOK, Piece.Color.WHITE), board.findPiece("a1"));
        assertEquals(Piece.createPiece(Piece.Type.ROOK, Piece.Color.WHITE), board.findPiece("a1"));

    }
}