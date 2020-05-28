public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {

	private int size;
	private Pair<P, T>[] queue;

//here may be problem
	@SuppressWarnings("unchecked")
	public PQKImp(int k) {
		size = 0;
		queue = (Pair<P, T>[]) new Pair[k];
	}

	@Override
	public int length() {
		return size;
	}

	@Override
	public void enqueue(P pr, T e) {
		try {
			if (size == queue.length) {
				for (int i = 0; i < queue.length; i++) {
					if (queue[i].first.compareTo(pr) < 0) {
						for (int j = queue.length - 1; j > i; j--) {
							queue[j] = queue[j - 1];
						}
						queue[i] = new Pair<P, T>(pr, e);
						return;
					}
				}
			} else {
				for (int i = 0; i < size; i++) {
					if (queue[i].first.compareTo(pr) < 0) {
						for (int j = size; j > i; j--) {
							queue[j] = queue[j - 1];
						}
						queue[i] = new Pair<P, T>(pr, e);
						size++;
						return;
					}
				}
				queue[size] = new Pair<P, T>(pr, e);
				size++;
			}
		} catch (Exception ex) {
			return;
		}

	}

	@Override
	public Pair<P, T> serve() {
		try {
			if (size != 0) {
				Pair<P, T> s = queue[0];
				for (int j = 0; j < queue.length - 1; j++) {
					queue[j] = queue[j + 1];
				}
				queue[queue.length - 1] = null;
				size--;
				return s;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}
}
