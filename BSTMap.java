public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	public BSTNode<K, T> root; // Do not change this
	public int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean update(K k, T e) {
		try {
			BSTNode<K, T> tmp = find(k);
			if (tmp == null) {
				return false;
			} else {
				findUp(root, k, e);
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	private void findUp(BSTNode<K, T> ch, K key, T e) {
		if (ch.key.compareTo(key) == 0) {
			ch.data = e;
			return;
		}
		if (ch.key.compareTo(key) < 0) {
			if (ch.right == null) {
				return;
			} else
				findUp(ch.right, key, e);
		} else {
			if (ch.left == null) {
				return;
			} else
				findUp(ch.left, key, e);
		}
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		try {
			BSTNode<K, T> tmp = find(k);
			if (tmp == null) {
				Pair<Boolean, T> x = new Pair<Boolean, T>(false, null);
				return x;
			} else {
				Pair<Boolean, T> x = new Pair<Boolean, T>(true, tmp.data);
				return x;
			}
		} catch (Exception e) {
			Pair<Boolean, T> x = new Pair<Boolean, T>(false, null);
			return x;
		}
	}

	@Override
	public boolean insert(K k, T e) {
		try {
			if (root == null) {
				BSTNode<K, T> n = new BSTNode<K, T>(k, e);
				root = n;
				size++;
				return true;
			}
			BSTNode<K, T> tmp = find(k);
			if (tmp == null) {
				BSTNode<K, T> n = new BSTNode<K, T>(k, e);
				insert(root, n, k);
				size++;
				return true;
			} else
				return false;
		} catch (Exception ex) {
			return false;
		}
	}

	private void insert(BSTNode<K, T> ch, BSTNode<K, T> n, K k) {
		if (ch.key.compareTo(k) < 0) {
			if (ch.right == null) {
				ch.right = n;
				return;
			} else {
				insert(ch.right, n, k);
			}
		} else {
			if (ch.left == null) {
				ch.left = n;
				return;
			} else {
				insert(ch.left, n, k);
			}
		}
	}

	public boolean remove(K k) {
		try {
			BSTNode<K, T> tmp = find(k);
			if (tmp == null) {
				return false;
			} else {
				BSTNode<K, T> n = root, pa = null;

				while (n != null) {
					if (k.compareTo(n.key) > 0) {
						pa = n;
						n = n.right;
					} else if (k.compareTo(n.key) < 0) {
						pa = n;
						n = n.left;
					} else {
						BSTNode<K, T> n2 = n;
						if (n2.right != null && n2.left != null) {
							n2 = n2.right;
							pa = n;
							while (n2.left != null) {
								pa = n2;
								n2 = n2.left;
							}
							n.key = n2.key;
							n.data = n2.data;
							k = n2.key;
							n = n2;
						}
						if (n.left != null)
							n = n.left;
						else {
							n = n.right;
						}
						if (pa == null) {
							root = n;
						} else {
							if (k.compareTo(pa.key) < 0) {
								pa.left = n;
							} else {
								pa.right = n;
							}
						}
						n = null;
					}
				}
				size--;
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<K> getKeys() {
		try {
			List<K> l = new LinkedList<K>();
			if (root == null) {
				return l;
			}
			return getKeys(root, l);
		} catch (Exception e) {
			List<K> l = new LinkedList<K>();
			return l;
		}
	}

	private List<K> getKeys(BSTNode<K, T> ch, List<K> l) {
		if (ch.left != null) {
			l = getKeys(ch.left, l);
		}
		l.insert(ch.key);
		if (ch.right != null) {
			l = getKeys(ch.right, l);
		}
		return l;
	}

	private BSTNode<K, T> find(K key) {
		if (root == null) {
			return null;
		}
		return find(root, key);
	}

	private BSTNode<K, T> find(BSTNode<K, T> ch, K key) {
		if (ch.key.compareTo(key) == 0) {
			return ch;
		}
		if (ch.key.compareTo(key) < 0) {
			if (ch.right == null) {
				return null;
			} else
				return find(ch.right, key);
		} else {
			if (ch.left == null) {
				return null;
			} else
				return find(ch.left, key);
		}
	}
}

//public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
//public BSTNode<K, T> root; // Do not change this
//public int num_of_elements;
//public BSTMap() {
//root=null;
//num_of_elements=0;
//}
//@Override
//public int size() {
//return num_of_elements;
//}
//@Override
//public boolean full() {
//return false;
//}
//@Override
//public void clear() {
//root=null;
//num_of_elements=0;
//}
//// Update the data of the key k if it exists and return true.
////If k does not exist,
////the method returns false.
//	@Override
//	public boolean update(K k, T e) {
//		if (root == null)
//			return false;
//		BSTNode<K, T> ptr = root;
//		while (ptr != null) {
//			if (ptr.key.compareTo(k) == 0) {
//				ptr.data = e;
//				return true;
//			} else if (k.compareTo(ptr.key) > 0)
//				ptr = ptr.right;
//			else
//				ptr = ptr.left;
//		}
//		return true;
//	}
//
//	@Override
//// Search for element with key k and returns a pair containing true
////and its data if it exists.
////If k does not exist, the method returns false and null.
//	public Pair<Boolean, T> retrieve(K k) {
//		Pair<Boolean, T> res = new Pair<Boolean, T>(false, null);
//		if (root == null)
//			return res;
//		BSTNode<K, T> ptr = root;
//		while (ptr != null) {
//			if (ptr.key.compareTo(k) == 0) {
//				res.first = true;
//				res.second = ptr.data;
//				return res;
//			} else if (k.compareTo(ptr.key) > 0)
//				ptr = ptr.right;
//			else
//				ptr = ptr.left;
//		}
//		return res;
//	}
//
//// Insert a new element if does not exist and return true.
////If k already exists, return false.
//	@Override
//	public boolean insert(K k, T e) {
//		BSTNode<K, T> newNode = new BSTNode<K, T>(k, e);
//		if (root == null) {
//			root = newNode;
//			size++;
//			return true;
//		}
//		BSTNode<K, T> q = null;
//		BSTNode<K, T> ptr = root;
//		while (ptr != null) {
//			q = ptr;
//			if (ptr.key.compareTo(k) == 0) {
//				return false;
//			} else if (k.compareTo(ptr.key) > 0)
//				ptr = ptr.right;
//			else
//				ptr = ptr.left;
//		}
//		if (k.compareTo(q.key) > 0)
//			q.right = newNode;
//		else
//			q.left = newNode;
//		size++;
//		return true;
//	}
//}
//// Remove the element with key k if it exists and return true.
////If the element does not exist return false.
//	@Override
//	public boolean remove(K k) {
//// Search for k
//		K k1 = k;
//		BSTNode<K, T> p = root;
//		BSTNode<K, T> q = null; // Parent of p
//		while (p != null) {
//			int res = k1.compareTo(p.key);
//			if (res < 0) {
//				q = p;
//				p = p.left;
//			} else if (res > 0) {
//				q = p;
//				p = p.right;
//			} else { // Found the key
//// Check the three cases
//				if ((p.left != null) && (p.right != null)) { // Case 3: two
//// children
//// Search for the min in the right subtree
//					BSTNode<K, T> min = p.right;
//					q = p;
//					while (min.left != null) {
//						q = min;
//						min = min.left;
//					}
//					p.key = min.key;
//					p.data = min.data;
//					k1 = min.key;
//					p = min;
//// Now fall back to either case 1 or 2
//				}
//// The subtree rooted at p will change here
//				if (p.left != null) { // One child
//					p = p.left;
//				} else { // One or no children
//					p = p.right;
//				}
//				if (q == null) { // No parent for p, root must change
//					root = p;
//				} else {
//					if (k1.compareTo(q.key) < 0) {
//						q.left = p;
//					} else {
//						q.right = p;
//					}
//				}
//				size--;
//				return true;
//			}
//		}
//		return false; // Not found
//	}

//	@Override
// Return the list of keys in increasing order.
//	public List<K> getKeys() {
//		List<K> res = new LinkedList<K>();
//		getKeys(res, root);
//		return res;
//	}
//
//	private void getKeys(List<K> res, BSTNode<K, T> ptr) {
//		if (ptr == null)
//			return;
//		getKeys(res, ptr.left);
//		res.insert(ptr.key);
//		getKeys(res, ptr.right);
//	}
//}
//////// helping methods to use in testing not required in this pa
//public void display_data_and_keys_inOrder(BSTNode<K,T>ptr)
//{
//if(ptr==null) return;
//display_data_and_keys_inOrder(ptr.left);
//System.out.println("key= "+ptr.key+" data= "+ptr.data);
//display_data_and_keys_inOrder(ptr.right);
//}
//}