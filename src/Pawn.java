import java.util.HashSet;

public class Pawn extends Piece {
	private int movement, originalX, originalY;

	public Pawn(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
		originalX = x;
		originalY = y;
		if (isWhite) {
			setImage("Icons/WP.png");
			movement = -1;
		} else {
			setImage("Icons/BP.png");
			movement = 1;
		}
	}

	@Override
	public HashSet<Square> calcMoves(ChessBoard board) {
		moves.clear();
		if (checkMove(board.getSquare(x, y + movement)) && 
				(x == originalX && y == originalY))
			checkMove(board.getSquare(x, y + (2*movement)));
		checkAttackMove(board.getSquare(x - 1, y + movement));
		checkAttackMove(board.getSquare(x + 1, y + movement));
		return moves;
	}

	@Override
	public HashSet<Square> getMoves() {
		return moves;
	}

	@Override
	protected boolean checkMove(Square square) {
		if (square == null) return false;
		if (square.isBlocked()) {
			return false;
		}
		moves.add(square);
		return true;
	}

	private boolean checkAttackMove(Square square) {
		if (square == null) return false;
		if (square.isBlocked()) {
			if (isWhite != square.getPiece().isWhite) {
				moves.add(square);
				return false;
			}
			return false;
		}
		return false;
	}
}
