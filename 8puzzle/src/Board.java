public class Board {
    private final int[][] blocks;
    private final int N;

    public Board(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    } // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)

    public int dimension() {
        return N;
    } // board dimension N

    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0)
                    continue;
                if (blocks[i][j] != i * N + j + 1)
                    hamming++;
            }
        }

        return hamming;
    } // number of blocks out of place

    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                manhattan += (Math.abs(i - ((blocks[i][j] - 1) / N)) + Math
                        .abs(j - ((blocks[i][j] - 1) % N)));
            }
        }

        return manhattan;
    } // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1 && blocks[i][j] == 0)
                    return true;
                if (blocks[i][j] != i * N + j + 1)
                    return false;
            }
        }
        return true;
    } // is this board the goal board?

    public Board twin() {
        int[][] twinBlocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twinBlocks[i][j] = blocks[i][j];
            }
        }
        if (twinBlocks[0][0] != 0 && twinBlocks[0][1] != 0) {
            twinBlocks[0][0] = blocks[0][1];
            twinBlocks[0][1] = blocks[0][0];
        } else {
            twinBlocks[1][0] = blocks[1][1];
            twinBlocks[1][1] = blocks[1][0];
        }
        return new Board(twinBlocks);
    } // a board obtained by exchanging two adjacent blocks in the same row

    public boolean equals(Object y) {
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != Board.class)
            return false;
        if (N != ((Board) y).N)
            return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != ((Board) y).blocks[i][j])
                    return false;
            }
        }

        return true;
    } // does this board equal y?

    public Iterable<Board> neighbors() {
        Queue<Board> q = new Queue<Board>();
        int[][] newBlocks;
        Board board;
        int iEmpty = -1;
        int jEmpty = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    iEmpty = i;
                    jEmpty = j;
                }
            }
        }

        if (iEmpty > 0) {
            newBlocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBlocks[i][j] = blocks[i][j];
                }
            }

            newBlocks[iEmpty][jEmpty] = newBlocks[iEmpty - 1][jEmpty];
            newBlocks[iEmpty - 1][jEmpty] = 0;

            board = new Board(newBlocks);
            q.enqueue(board);
        }

        if (jEmpty > 0) {
            newBlocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBlocks[i][j] = blocks[i][j];
                }
            }

            newBlocks[iEmpty][jEmpty] = newBlocks[iEmpty][jEmpty - 1];
            newBlocks[iEmpty][jEmpty - 1] = 0;

            board = new Board(newBlocks);
            q.enqueue(board);
        }

        if (iEmpty < N - 1) {
            newBlocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBlocks[i][j] = blocks[i][j];
                }
            }

            newBlocks[iEmpty][jEmpty] = newBlocks[iEmpty + 1][jEmpty];
            newBlocks[iEmpty + 1][jEmpty] = 0;

            board = new Board(newBlocks);
            q.enqueue(board);
        }

        if (jEmpty < N - 1) {
            newBlocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBlocks[i][j] = blocks[i][j];
                }
            }

            newBlocks[iEmpty][jEmpty] = newBlocks[iEmpty][jEmpty + 1];
            newBlocks[iEmpty][jEmpty + 1] = 0;

            board = new Board(newBlocks);
            q.enqueue(board);
        }

        return q;
    } // all neighboring boards

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == N * N)
                    s.append(String.format("%2d ", 0));
                else
                    s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    } // string representation of the board (in the output format specified
      // below)
}
