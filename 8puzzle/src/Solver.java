public class Solver {

    private MinPQ<Node> pq = new MinPQ<Node>();
    private Stack<Board> solution = new Stack<Board>();
    private boolean isSolvable = false;

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node previous;

        Node(Board board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        @Override
        public int compareTo(Node n) {
            return ((this.board.manhattan() + this.moves) - (n.board
                    .manhattan() + n.moves));
        }
    }

    public Solver(Board initial) {
        Node parent = new Node(initial, 0, null);
        Node twin = new Node(initial.twin(), 0, null);
        Node child;
        Board previousBoard = null;
        pq.insert(parent);
        pq.insert(twin);
        while (true) {
            parent = pq.delMin();
            if (parent.board.isGoal())
                break;
            for (Board neighbour : parent.board.neighbors()) {
                if (parent.previous != null)
                    previousBoard = parent.previous.board;
                child = new Node(neighbour, parent.moves + 1, parent);
                if (!child.board.equals(previousBoard)) {
                    pq.insert(child);
                }
            }
        }

        while (parent != null) {
            solution.push(parent.board);
            parent = parent.previous;
        }

        if (solution.peek() == initial) {
            isSolvable = true;
        }
    } // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return isSolvable;
    } // is the initial board solvable?

    public int moves() {
        if (isSolvable())
            return solution.size() - 1;
        return -1;
    } // min number of moves to solve initial board; -1 if no solution

    public Iterable<Board> solution() {
        if (isSolvable())
            return solution;
        return null;
    } // sequence of boards in a shortest solution; null if no solution

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
