/*
SAMPLE TESTS:

Input: maze1.txt, Output:
####################
#.................##
#..................#
S@@@@@@@@@@@@@@@@@@#
##################@#
G@@@#.............@#
###@#.............@#
###@@@@@@@@@@@@@@@@#
###...............##
##................##
##.#################
##..################
###.################
###.#..............#
###.########.#######
#.................##
#.................##
##...............###
##................##
####################

Input: maze2.txt, Output:
####################
#.................##
#..................#
S@@@@@@@@@@@@@@@@@@#
##################@#
#...#.............@#
###.#.............@#
###..............@@#
###..............@##
##@@@@@@@@@@@@@@@@##
##@#################
##@@################
###@################
##@@#..............#
##@#########.#######
#.@...............##
#.@...............##
##@..............###
##@@@@@@@@@@@@@@@@##
#################G##

Input: maze3.txt, Output:
####################
#.................##
#..................#
#..................#
##################.#
#...#..............#
###.#..............#
###................#
###...............##
##................##
##.#################
##..################
###.################
##..#..............#
##.#########.#######
#.................##
#.................##
##...............###
##@@@@@@@@@@@@@@@@##
##S##############G##

Input: maze4NoSolution.txt, Output: Maze is not solvable


 */


import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class MazeSolver {

    private Graph g;
    private int startrow;
    private int startcol;
    private int goalrow;
    private int goalcol;
    private int[][] maze;

    public MazeSolver(String input) {
        createMaze(input);
        g = new Graph(maze.length * maze[0].length);
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                int[] x = {r - 1, r, r, r + 1};
                int[] y = {c, c - 1, c + 1, c};
                for (int i = 0; i < 4; i++) {
                    if (maze[r][c] == 0 && x[i] >= 0 && x[i] < maze.length && y[i] >= 0 && y[i] < maze[0].length && maze[x[i]][y[i]] == 0) {
                        g.addEdge(graphInd(r, c), graphInd(x[i], y[i]));
                    }
                }
            }
        }
        BreadthFirstPaths b = new BreadthFirstPaths(g, graphInd(startrow, startcol));
        if (b.hasPathTo(graphInd(goalrow, goalcol))) printPath(b);
        else System.out.println("Maze is not solvable");
    }

    public void printPath(BreadthFirstPaths b) {
        for (int x : b.pathTo(graphInd(goalrow, goalcol))) {
            int c = x % maze.length;
            int r = x / maze.length;
            maze[r][c] = 2;
        }

        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                int n = maze[r][c];
                if (r == startrow && c == startcol) {
                    System.out.print("S");
                } else if (r == goalrow && c == goalcol) {
                    System.out.print("G");
                } else if (n == 0) {
                    System.out.print(".");
                } else if (n == 1) {
                    System.out.print("#");
                } else if (n == 2) {
                    String ANSI_RESET = "\u001B[0m";
                    String ANSI_PURPLE = "\u001B[35m";
                    System.out.print(ANSI_PURPLE + "@" + ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    public void createMaze(String input) {
        In in = new In("mazeData\\" + input);
        int len = 0;
        int wid = 0;

        while (in.hasNextLine()) {
            len++;
            String s = in.readLine();
            wid = s.length();
        }
        this.maze = new int[len][wid];

        In in2 = new In("mazeData\\" + input);
        int rind = 0;
        while (in2.hasNextLine()) {
            String s = in2.readLine();
            int cind = 0;
            for (String c : s.split("")) {
                if (c.equals("#")) {
                    maze[rind][cind] = 1;
                } else if (c.equals(".")) {
                    maze[rind][cind] = 0;
                } else if (c.equals("S")) {
                    maze[rind][cind] = 0;
                    startrow = rind;
                    startcol = cind;
                } else if (c.equals("G")) {
                    maze[rind][cind] = 0;
                    goalrow = rind;
                    goalcol = cind;
                }
                cind++;
            }
            rind++;
        }
//        for (int r = 0; r < maze.length; r++) {
//            for (int c = 0; c < maze[0].length; c++) {
//                System.out.print(maze[r][c] + " ");
//            }
//            System.out.println();
//        }
    }
    
    public int graphInd(int row, int col) {
        return row * maze[0].length + col;
    }


    public static void main(String[] args) {
        MazeSolver m = new MazeSolver(args[0]);
    }

}
