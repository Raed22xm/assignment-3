package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.Arrays;

/**
 * An implementation of the interface {@link List} based on basic Java
 * arrays, which dynamically are adapted in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class ArrayList<E> implements List<E> {

    /**
     * Constant defining the default size of the array when the
     * list is created.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Current size of the list.
     */
    private int size = 0;

    /**
     * The array for storing the elements.
     */
    private E[] list;

    /**
     * Constructor initializes the list with default size.
     */
    public ArrayList() {
        this.list = createEmptyArray(DEFAULT_SIZE);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull E get(int pos) throws IndexOutOfBoundsException {
        checkIndex(pos);
        return list[pos];
    }

    @Override
    public E set(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        checkIndex(pos);
        E old = list[pos];
        list[pos] = e;
        return old;
    }

    @Override
    public boolean add(@NotNull E e) {
        ensureCapacity();
        list[size++] = e;
        return true;
    }

    @Override
    public boolean add(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + pos);
        }
        ensureCapacity();
        System.arraycopy(list, pos, list, pos + 1, size - pos);
        list[pos] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        checkIndex(pos);
        E removedElement = list[pos];
        System.arraycopy(list, pos + 1, list, pos, size - pos - 1);
        size--;
        return removedElement;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if ((e == null && list[i] == null) || (e != null && e.equals(list[i]))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) {
        if (c == null) {
            throw new NullPointerException("Comparator cannot be null");
        }

        boolean swapped;
        int j = size;
        do {
            swapped = false;
            for (int i = 0; i + 1 < j; i++) {
                if (c.compare(list[i], list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            j--;
        } while (swapped);
    }

    /**
     * Creates a new array of type <code>E</code> with the given size.
     *
     * @param length the size of the array
     * @return a new array of type <code>E</code> and the given length
     */
    @SuppressWarnings("unchecked")
    private E[] createEmptyArray(int length) {
        return (E[]) new Object[length];
    }

    /**
     * Ensures there is enough space in the array, doubling the size if necessary.
     */
    private void ensureCapacity() {
        if (size == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
    }

    /**
     * Checks if an index is within valid bounds.
     */
    private void checkIndex(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + pos);
        }
    }
}
