public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; // Do not change this

	public MGraph() {
		adj = new BSTMap<K, List<K>>();
	}

	@Override
	public boolean addNode(K i) {
		try {
			if (isNode(i)) {
				return false;
			} else {
				List<K> l = new LinkedList<K>();
				adj.insert(i, l);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isNode(K i) {
		try {
			Pair<Boolean, List<K>> p = adj.retrieve(i);
			return p.first;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addEdge(K i, K j) {
		try {
			if (isEdge(i, j)) {
				return false;
			} else {
				Pair<Boolean, List<K>> p = adj.retrieve(i);
				p.second.insert(j);
				p = adj.retrieve(j);
				p.second.insert(i);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isEdge(K i, K j) {
		try {
			if (isNode(i) && isNode(j)) {
				Pair<Boolean, List<K>> p = adj.retrieve(i), q = adj.retrieve(j);
				return p.second.exists(j) && q.second.exists(i);
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<K> neighb(K i) {
		try {
			if (isNode(i)) {
				Pair<Boolean, List<K>> p = adj.retrieve(i);
				return p.second;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deg(K i) {
		try {
			if (isNode(i)) {
				Pair<Boolean, List<K>> p = adj.retrieve(i);
				return p.second.size();
			} else
				return -1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<K> getNodes() {
		try {
			return adj.getKeys();
		} catch (Exception e) {
			List<K> l = new LinkedList<K>();
			return l;
		}
	}
}
