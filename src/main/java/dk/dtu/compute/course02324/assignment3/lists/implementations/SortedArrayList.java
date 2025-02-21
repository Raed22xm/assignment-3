package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;
import dk.dtu.compute.course02324.assignment3.lists.types.SortedList;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Comparator;

/**
 * An implementation of the interface {@link SortedList} based on the
 * {@link ArrayList} implementation of the interface {@link List},
 * which dynamically adapts in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> implements SortedList<E> {

    @Override
    public boolean add(@NotNull E e) {
        if (e == null) {
            throw new IllegalArgumentException("Cannot add null element to the sorted list");
        }
        int insertIndex = findIndexToInsert(e);
        super.add(insertIndex, e);
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Cannot insert at an index in a SortedList");
    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Sorting is not supported in SortedArrayList");
    }

    /**
     * Finds the position at which a given element should be inserted
     * to the sorted list. The element must not be <code>null</code>.
     * The implementation finds this position by linearly going through
     * the array, stopping at the first element greater or equal to
     * this element.
     *
     * @param e the given element to be inserted
     * @return the position at which the element should be inserted
     */
    private int findIndexToInsert(@NotNull E e) {
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(e) >= 0) {
                return i;
            }
        }
        return size();
    }
}
