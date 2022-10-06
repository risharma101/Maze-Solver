import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;

public class StrongConnectivity {

    public static void main(String[] args) {
        In in = new In("strongconnectivity.txt");
        Digraph G = new Digraph(in);
        KosarajuSharirSCC sc = new KosarajuSharirSCC(G);
        System.out.println(sc.count());
        
    }
}
