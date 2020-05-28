
public class LinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		if (empty())
			return;
		current = head;
	}

	@Override
	public void findNext() {
		if (empty() || current.next == null)
			return;
		current = current.next;
	}

	@Override
	public boolean last() {
		if (!empty()) {
			return current.next == null;
		}
		return true;
	}

	@Override
	public T retrieve() {
		if (!empty())
			return current.data;
		return null;
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public void insert(T e) {
		if (empty()) {
			head = new Node<T>(e);
			current = head;
		} else {
			Node<T> tmp = current.next;
			current.next = new Node<T>(e);
			findNext();
			current.next = tmp;
		}
	}

	@Override
	public void remove() {
		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}
		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

	@Override
	public int size() {
		if (!empty()) {
			Node<T> tmp = head;
			int counter = 1;
			while (tmp.next != null) {
				counter++;
				tmp = tmp.next;
			}
			return counter;
		}
		return 0;
	}

	@Override
	public boolean exists(T e) {
		if (!empty()) {
			Node<T> tmp = head;
			while (tmp.next != null) {
				if (tmp.data.equals(e))
					return true;
				tmp = tmp.next;
			}
			if (tmp.data.equals(e))
				return true;
		}
		return false;
	}

}

class Node<T> {
	public Node<T> next;
	public T data;

	public Node(T data) {
		this.data = data;
		this.next = null;
	}

}