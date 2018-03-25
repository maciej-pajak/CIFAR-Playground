package pl.maciejpajak;


/**
 * Simple data structure that keeps fixed number of biggest elements according to their natural ordering.
 * @param <T>
 */
public class FixedSortedStash<T extends Comparable<T>> {

    private int size = 0;
    private T[] items;

    @SuppressWarnings("unchecked")
    public FixedSortedStash(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException();
        items = (T[]) new Object[capacity];
    }

    public void add(T elem) {
        if (size == 0) {
            items[size++] = elem;
        } else {
            int pos = findInsertPosition(elem);
            if (pos < items.length) {
                shift(pos);
                items[pos] = elem;
            }
            if (size != items.length) size++;
        }
    }

    private int findInsertPosition(T elem) {
        for (int i = 0 ; i < size ; i++) {
            if (elem.compareTo(items[i]) > 0) return i;
        }
        return size;
    }

    public T get(int index) {
        return items[index];
    }

    public int size() {
        return size;
    }

    public T getLastElem() {
        return (size == 0) ? null : items[size - 1];
    }

    public boolean isFull() {
        return (size == items.length);
    }

    private void shift(int pos) {
        for (int i = items.length - 2 ; i >= pos ; i--) {
            items[i + 1] = items[i];
        }
    }

    public void printAll() {
        System.out.print("[");
        for (T item : items) {
            System.out.print(item + ", ");
        }
        System.out.println("]");
    }


}
