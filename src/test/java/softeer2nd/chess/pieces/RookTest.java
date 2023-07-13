package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.GameManager;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.pieces.concrete.Rook;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class RookTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("룩 기물의 움직임 테스트")
    void moveRook() {
        // given
        String rookSample = appendNewLine(
                "........",
                "........",
                "....R...",
                "........",
                "........",
                "........",
                "........",
                "........"
        );

        String expect = appendNewLine(
                "........",
                "........",
                "........",
                ".....R..",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(rookSample);

        // when
        GameManager.move(board, new Position("e6"), new Position("e8"));
        Piece result1 = board.findPiece(new Position("e8"));
        GameManager.move(board, new Position("e8"), new Position("e5"));
        Piece result2 = board.findPiece(new Position("e5"));

        GameManager.move(board, new Position("e5"), new Position("a5"));
        Piece result3 = board.findPiece(new Position("a5"));
        GameManager.move(board, new Position("a5"), new Position("f5"));
        Piece result4 = board.findPiece(new Position("f5"));

        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("f5"), new Position("a1"))
        );

        // then
        assertEquals(result1, Rook.createBlackRook());
        assertEquals(result2, Rook.createBlackRook());
        assertEquals(result3, Rook.createBlackRook());
        assertEquals(result4, Rook.createBlackRook());
        assertEquals(expect, BoardView.showBoard(board));
    }

    @ParameterizedTest
    @MethodSource("provideAvailable")
    @DisplayName("룩 기물의 움직임 테스트")
    void available(Position source, Position destination) {
        // given
        board.initializeEmpty();
        board.assignPiece(source, Rook.createBlackRook());

        // when
        GameManager.move(board, source, destination);
        Piece origin = board.findPiece(source);
        Piece target = board.findPiece(destination);

        // then
        assertEquals(origin, BlankPiece.createBlank());
        assertEquals(target, Rook.createBlackRook());
    }

    // 특정 위치의 룩의 모든 이동 경로 생성
    private static void provideAvailableStream(Stream.Builder<Arguments> builder, int col, int row) {
        Position source = new Position(col, row);
        IntStream.range(0, Board.COLUMN_NUMBER)
                .filter(i -> i != col)
                .mapToObj(i -> Arguments.of(source, new Position(i, row)))
                .forEach(builder::add);

        IntStream.range(0, Board.ROW_NUMBER)
                .filter(i -> i != row)
                .mapToObj(i -> Arguments.of(source, new Position(col, i)))
                .forEach(builder::add);
    }

    // 룩의 가능한 모든 이동 경로 생성
    private static Stream<Arguments> provideAvailable() {
        Stream.Builder<Arguments> builder = Stream.builder();
        for (int i = 0; i < Board.COLUMN_NUMBER; i++) {
            for (int j = 0; j < Board.ROW_NUMBER; j++) {
                provideAvailableStream(builder, i, j);
            }
        }
        return builder.build();
    }

    @ParameterizedTest
    @MethodSource("provideUnavailable")
    @DisplayName("룩 기물의 움직임 테스트")
    void unavailable(Position source, Position destination) {
        // given
        board.initializeEmpty();
        board.assignPiece(source, Rook.createBlackRook());

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, source, destination)
        );

        // then
        assertEquals(ExceptionMessage.IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
    }

    private static Stream<Arguments> provideUnavailable() {
        int col = 2;
        int row = 4;
        return Stream.of(
                Arguments.of(new Position(col, row), new Position(2, 4)),
                Arguments.of(new Position(col, row), new Position(1, 3)),
                Arguments.of(new Position(col, row), new Position(5, 3)),
                Arguments.of(new Position(col, row), new Position(7, 2)),
                Arguments.of(new Position(col, row), new Position(4, 2))
        );
    }

}
