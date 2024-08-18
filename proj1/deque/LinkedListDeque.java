package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof LinkedListDeque.Node)) {
                return false;
            }
            LinkedListDeque<T>.Node other = (LinkedListDeque<T>.Node) o;
            return this.item.equals(other.item);
        }
    }

    private Node sentinel;
    private int _size;

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p;

        public LinkedListDequeIterator() {
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return p != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        _size = 0;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public void addFirst(T item) {
        Node oldFirst = sentinel.next;
        Node newNode = new Node(item, oldFirst, sentinel);
        sentinel.next = newNode;
        oldFirst.prev = newNode;
        _size += 1;
    }

    @Override
    public void addLast(T item) {
        Node oldLast = sentinel.prev;
        Node newNode = new Node(item, sentinel, oldLast);
        sentinel.prev = newNode;
        oldLast.next = newNode;
        _size += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = sentinel.next;
        Node newFirst = oldFirst.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        _size -= 1;
        return oldFirst.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = sentinel.prev;
        Node newLast = oldLast.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        _size -= 1;
        return oldLast.item;
    }

    @Override
    public T get(int index) {
        if (index<0 || index >= _size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= _size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println();
            return;
        }
        Node p = this.sentinel;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _size-1; i++) {
            p = p.next;
            sb.append(p.item.toString());
            sb.append(" ");
        }
        p = p.next;
        sb.append(p.item.toString());
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

}
