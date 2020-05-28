import java.io.File;
import java.util.Scanner;

public class Recommender {

	// Return the top k recommended friends for user i using the popular nodes
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		try {
			if (g.isNode(i)) {
				PQK<Double, K> pqk = new PQKImp<Double, K>(k);
				List<K> nodes = g.getNodes();
				nodes.findFirst();
				double pr;
				K key;
				while (!nodes.last()) {
					pr = (double) g.neighb(nodes.retrieve()).size();
					key = nodes.retrieve();
					if (!g.neighb(i).exists(key) && i.compareTo(key) != 0)
						pqk.enqueue(pr, key);
					nodes.findNext();
					if (nodes.last()) {
						if (!g.neighb(i).exists(key) && i.compareTo(key) != 0)
							pqk.enqueue((double) g.neighb(nodes.retrieve()).size(), nodes.retrieve());
					}
				}
				return pqk;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	// Return the top k recommended friends for user i using common neighbors
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		try {
			if (g.isNode(i)) {
				PQK<Double, K> pqk = new PQKImp<Double, K>(k);
				List<K> nodes = g.getNodes(), n = g.neighb(i);
				nodes.findFirst();
				double pr;
				K key;
				while (!nodes.last()) {
					n.findFirst();
					pr = 0.0;
					key = nodes.retrieve();
					while (!n.last()) {
						if (g.neighb(key).exists((n.retrieve())) && !g.neighb(i).exists(key) && i.compareTo(key) != 0) {
							pr += 1.0;
						}
						n.findNext();
					}
					if (n.last() && !n.empty()) {
						if (g.neighb(key).exists((n.retrieve())) && !g.neighb(i).exists(key) && i.compareTo(key) != 0) {
							pr += 1.0;
						}
					}
					if (!g.neighb(i).exists(key) && i.compareTo(key) != 0)
						pqk.enqueue(pr, key);
					nodes.findNext();
				}
				if (nodes.last() && !nodes.empty()) {
					n.findFirst();
					pr = 0.0;
					key = nodes.retrieve();
					while (!n.last()) {
						if (g.neighb(key).exists((n.retrieve())) && !g.neighb(i).exists(key) && i.compareTo(key) != 0) {
							pr += 1.0;
						}
						n.findNext();
					}
					if (n.last() && !n.empty()) {
						if (g.neighb(key).exists((n.retrieve())) && !g.neighb(i).exists(key) && i.compareTo(key) != 0) {
							pr += 1.0;
						}
					}
					if (!g.neighb(i).exists(key) && i.compareTo(key) != 0)
						pqk.enqueue(pr, key);
				}
				return pqk;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	// Read graph from file. The file is a text file where each line contains an
	// edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
