package softeer2nd.chess;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.pieces.BlankPiece;
import softeer2nd.chess.pieces.MovablePiece;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.concrete.King;

import static softeer2nd.chess.exception.ExceptionMessage.OUT_OF_BOUND_INPUT;
import static softeer2nd.chess.exception.ExceptionMessage.WRONG_TURN;

public class GameManager {

    private final Board board;
    private boolean isStart = false;
    private boolean ignoreTurn = false;
    private Piece.Color gameTurn = Piece.Color.BLACK;

    public GameManager() {
        this.board = new Board();
        this.board.initialize();
    }

    /**
     * 테스트를 위한 생성자입니다.
     */
    public GameManager(Board board) {
        this.board = board;
    }

    /**
     * 보드판에서 한 기물을 이동시킨다. <br/>
     * 구체적으로 가능한 이동일 경우 보드판에서 sourcePosition와 targetPosition의 위치를 교환한다.
     *
     * @throws IllegalArgumentException 범위를 벗어나는 이동, 같은 색깔 기물로 이동, 빈 칸 이동의 경우 발생한다.
     * @Return 만약 상대방의 모든 {@link King} 기물이 제거되었다면 {@code true}를 반환한다. 아닌 경우 {@code false}를 반환한다.
     */
    public boolean move(Position sourcePosition, Position destinationPosition) {
        verifyStartGame();
        verifyOutOfBoardIndex(destinationPosition);

        MovablePiece sourcePiece = ifNotBlankCastMovable(board.findPiece(sourcePosition));
        verifyIsCorrectTurn(sourcePiece);
        Piece destinationPiece = board.findPiece(destinationPosition);

        sourcePiece.verifyMove(board, sourcePosition, destinationPosition);

        // 다른 색 기물일 경우 제거한다.
        if (!Piece.isSameColorAndHasPiece(sourcePiece, destinationPiece)) {
            destinationPiece = BlankPiece.createBlank();
        }

        board.assignPiece(sourcePosition, destinationPiece);
        board.assignPiece(destinationPosition, sourcePiece);

        changeGameTurn();
        return board.isRemovedAllKing(gameTurn);
    }

    private void changeGameTurn() {
        gameTurn = gameTurn == Piece.Color.BLACK ?
                Piece.Color.WHITE : Piece.Color.BLACK;
    }

    private void verifyIsCorrectTurn(MovablePiece sourcePiece) {
        if (!ignoreTurn && gameTurn != sourcePiece.getColor()) {
            throw new RuntimeException(WRONG_TURN);
        }
    }

    private void verifyStartGame() {
        if (!isStart) {
            throw new RuntimeException("게임을 시작해 주세요.");
        }
    }

    private MovablePiece ifNotBlankCastMovable(Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.MOVE_NOT_MOVABLE_PIECE);
        }
        return (MovablePiece) sourcePiece;
    }

    private void verifyOutOfBoardIndex(Position destinationPosition) {
        if (destinationPosition.getYPos() < 0 || destinationPosition.getYPos() >= Board.COLUMN_NUMBER ||
                destinationPosition.getXPos() < 0 || destinationPosition.getXPos() >= Board.ROW_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_BOUND_INPUT);
        }
    }

    public void start() {
        isStart = true;
    }

    public String showBoard() {
        return BoardView.showBoard(board);
    }

    public void setIgnoreTurn() {
        this.ignoreTurn = true;
    }

}
