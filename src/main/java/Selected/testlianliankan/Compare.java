package Selected.testlianliankan;

public class Compare {
    private game game;
    private final SetUpGame.Tile[][] board;

    public Compare(game game, SetUpGame.Tile[][] board) {
        this.game = game;
        this.board = board;
    }

    public boolean canConnect(int x1, int y1, int x2, int y2) {
        SetUpGame.Tile tile1 = board[x1][y1];
        SetUpGame.Tile tile2 = board[x2][y2];
        System.out.println("A");

        if (tile1 == null || tile2 == null) {
            System.out.println("b");
            return false;
        }

        if ((tile1.imageId != null && tile2.number != null && tile1.imageId.equals(tile2.number))
                || (tile1.number != null && tile2.imageId != null && tile1.number.equals(tile2.imageId))) {

        } else {

            return false;
        }
        if ((x1 == x2 && (x1 == 0 || x1 == 9)) || (y1 == y2 && (y1 == 0 || y1 == 9))) {
            return true;
        }

        // Whether directly connect
        if (isDirectlyConnected(x1, y1, x2, y2)) {
            return true;
        }



        // Whether in the same line or row
        if (y1 == y2) {
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            boolean directlyConnected = true;
            for (int x = minX + 1; x < maxX; x++) {
                if (board[x][y1] != null) {
                    directlyConnected = false;
                    break;
                }
            }
            if (directlyConnected) {
                return true;
            }

            boolean upClear = true, downClear = true;
            for (int x = minX; x <= maxX; x++) {
                if (y1 > 0 && board[x][y1-1] != null) {
                    upClear = false;
                }
                if (y1 < board[0].length - 1 && board[x][y1+1] != null) {
                    downClear = false;
                }
            }
            if (upClear || downClear) {
                return true;
            }
        }






        for (int x = 0; x < board.length; x++) {
            if (isDirectlyConnected(x1, y1, x, y1) && isDirectlyConnected(x, y1, x2, y2) && board[x][y1] == null) {
                return true;
            }
            if (isDirectlyConnected(x1, y1, x, y2) && isDirectlyConnected(x, y2, x2, y2) && board[x][y2] == null) {
                return true;
            }
        }

        for (int y = 0; y < board[0].length; y++) {
            if (isDirectlyConnected(x1, y1, x1, y) && isDirectlyConnected(x1, y, x2, y2) && board[x1][y] == null) {
                return true;
            }
            if (isDirectlyConnected(x1, y1, x2, y) && isDirectlyConnected(x2, y, x2, y2) && board[x2][y] == null) {
                return true;
            }
        }

        //whether connect with two turn.
        for (int x3 = 0; x3 < board.length; x3++) {
            for (int y3 = 0; y3 < board[0].length; y3++) {
                if (board[x3][y3] != null) continue; // ignore non null space

                for (int x4 = 0; x4 < board.length; x4++) {
                    for (int y4 = 0; y4 < board[0].length; y4++) {
                        if (board[x4][y4] != null) continue; // ignore non null space

                        if (isDirectlyConnected(x1, y1, x3, y3) &&
                                isDirectlyConnected(x3, y3, x4, y4) &&
                                isDirectlyConnected(x4, y4, x2, y2)) {
                            return true;
                        }
                    }
                }
            }
        }


        return false;
    }


    private boolean isDirectlyConnected(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            for (int y = Math.min(y1, y2) + 1; y < Math.max(y1, y2); y++) {
                if (board[x1][y] != null) {
                    return false;
                }
            }
            return true;
        }

        if (y1 == y2) {
            for (int x = Math.min(x1, x2) + 1; x < Math.max(x1, x2); x++) {
                if (board[x][y1] != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

}
