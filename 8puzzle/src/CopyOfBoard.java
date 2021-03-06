public class CopyOfBoard {

    private final int[][] blocks;
    private final int N;
    private int iEmpty = -1;
    private int jEmpty = -1;
    private int hamming;
    private int manhattan;

    public CopyOfBoard(int[][] blocks) {
        N = blocks.length;
        this.blocks = new int[N][N];
        int iDesired;
        int jDesired;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0 || blocks[i][j] == N * N) {
                    this.blocks[i][j] = N * N;
                    iEmpty = i;
                    jEmpty = j;
                } else {
                    this.blocks[i][j] = blocks[i][j];

                    // hamming calculation
                    if (this.blocks[i][j] != i * N + j + 1)
                        hamming++;

                    // manhattan calculation
                    iDesired = (this.blocks[i][j] - 1) / N;
                    jDesired = (this.blocks[i][j] - 1) % N;

                    manhattan += (Math.abs(i - iDesired) + Math.abs(j
                            - jDesired));
                }
            }
        }
    } // construct a board from an N-by-N array of blocks
      // (where blocks[i][j] = block in row i, column j)

    public int dimension() {
        return N;
    } // board dimension N

    public int hamming() {
        return hamming;
    } // number of blocks out of place

    public int manhattan() {
        return manhattan;
    } // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != i * N + j + 1)
                    return false;
            }
        }
        return true;
    } // is this board the goal board?

    public CopyOfBoard twin() {
        CopyOfBoard twin = new CopyOfBoard(blocks);
        int tmp = twin.blocks[0][0];
        twin.blocks[0][0] = twin.blocks[0][1];
        twin.blocks[0][1] = tmp;
        return twin;
    } // a board obtained by exchanging two adjacent blocks in the same row

    public boolean equals(Object y) {
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != CopyOfBoard.class)
            return false;
        if (N != ((CopyOfBoard) y).N)
            return false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != ((CopyOfBoard) y).blocks[i][j])
                    return false;
            }
        }

        return true;
    } // does this board equal y?

    public Iterable<CopyOfBoard> neighbors() {
        Queue<CopyOfBoard> q = new Queue<CopyOfBoard>();
        int newBlocks[][];
        CopyOfBoard board;

        if (iEmpty > 0) {
            newBlocks = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newBlocks[i][j] = blocks[i][j];
                }
            }

            newBlocks[iEmpty][jEmpty] = newBlocks[iEmpty - 1][jEmpty];
            newBlocks[iEmpty - 1][jEmpty] = N * N;

            board = new CopyOfBoard(newBlocks);
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
            newBlocks[iEmpty][jEmpty - 1] = N * N;

            board = new CopyOfBoard(newBlocks);
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
            newBlocks[iEmpty + 1][jEmpty] = N * N;

            board = new CopyOfBoard(newBlocks);
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
            newBlocks[iEmpty][jEmpty + 1] = N * N;

            board = new CopyOfBoard(newBlocks);
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

    // unit tests
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        CopyOfBoard b = new CopyOfBoard(blocks);

        System.out.println("board");
        System.out.println("empty piece: [" + b.iEmpty + "," + b.jEmpty + "]");
        System.out.println(b);
        System.out.println("isGoal() " + b.isGoal());
        System.out.println("hamming() " + b.hamming());
        System.out.println("manhattan() " + b.manhattan());
        System.out.println("neighbors()\n " + b.neighbors());

        System.out.println("====== children of neigbors =======");

        for (CopyOfBoard n : b.neighbors()) {
            System.out.println("parent");
            System.out.println("empty piece: [" + n.iEmpty + "," + n.jEmpty
                    + "]");
            System.out.println(n);
            for (CopyOfBoard nch : n.neighbors()) {
                System.out.println("empty piece: [" + nch.iEmpty + ","
                        + nch.jEmpty + "]");
                System.out.println(nch);
            }
            System.out.println("------- -------");
        }
    }
}
