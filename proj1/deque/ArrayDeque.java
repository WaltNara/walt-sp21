package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int _size;
    private T[] items;
    private int first;
    private int last;
    private class ArratDequeIterator implements Iterator<T> {
        private int p;
        public ArratDequeIterator() {
            p = first;
        }
        @Override
        public boolean hasNext() {
            return movePointer(last, 1) != p;
        }
        @Override
        public T next() {
            T item = items[p];
            p = movePointer(p, 1);
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArratDequeIterator();
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        _size = 0;
        first = 1;
        last = 0;
    }

    @Override
    public int size() {
        return _size;
    }

    private int movePointer(int p, int step) {
        return (p + step + items.length) % items.length;
    }

    @Override
    public void addFirst(T item) {
        if (_size == items.length) {
            resize(items.length * 2);
        }
        
        int nextFirst = movePointer(first, -1);
        items[nextFirst] = item;
        first = nextFirst;
        _size++;
    }

    @Override
    public void addLast(T item) {
        if (_size == items.length) {
            resize(items.length * 2);
        }
        int nextLast = movePointer(last, 1);
        items[nextLast] = item;
        last = nextLast;
        _size++;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < _size; i++) {
            a[i] = items[movePointer(first, i)];
        }
        items = a;
        first = 0;
        last = _size - 1;
    }

    @Override
    public T removeFirst() {
        if (_size == 0) {
            return null;
        }
        T item = items[first];
        items[first] = null;
        first = movePointer(first, 1);
        _size--;
        if ((_size-1) < items.length / 4 && items.length > 15) {
            resize(items.length / 4+1);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (_size == 0) {
            return null;
        }
        T item = items[last];
        items[last] = null;
        last = movePointer(last, -1);
        _size--;
        if ((_size-1) < items.length / 4 && items.length > 15) {
            resize(items.length / 4+1);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= _size) {
            return null;
        }
        return items[movePointer(first, index)];
    }

    @Override
    public void printDeque() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _size; i++) {
            sb.append(this.get(i)).append(" ");
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < _size; i++) {
            if (!other.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }


}
