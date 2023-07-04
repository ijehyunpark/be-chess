package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * 체스 보드판을 나타낸다.
 */
public class Board {
    private final List<Pawn> tokens = new ArrayList<>();

    /**
     * 체스 보드판에 새로운 게임 말을 추가한다.
     * @param pawn 추가할 새로운 게임 말(폰만 허용)
     */
    public void add(Pawn pawn) {
        this.tokens.add(pawn);
    }

    /**
     * 현재 체스 보드판에 존재하는 게임 말의 개수를 찾는다.
     * @return 체스 보드판 내의 게임 말 개수
     */
    public int size() {
        return tokens.size();
    }

    /**
     * 체스 보드판에 할당되어 있는 특정 게임 말을 찾는다.
     * @param i 게임 말이 할당되어 있는 인덱스
     * @return 만약, 게임 말이 존재할 경우 해당 게임말을 반환한다. 없을 경우 null을 반환하다.
     */
    public Pawn findPawn(int i) {
        if(this.tokens.size() <= i || i < 0)
            throw new IllegalArgumentException("잘못된 인덱스 접근입니다.");
        return this.tokens.get(i);
    }
}
