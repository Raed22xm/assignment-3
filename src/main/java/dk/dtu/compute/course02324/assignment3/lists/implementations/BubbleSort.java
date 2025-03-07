package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;

import javax.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * This is implementing a simplistic sorting algorithm based on the
 * Bubble Sort algorithm in a generic way. The actual sorting algorithm
 * is implemented as a generic static method.
 */
public class BubbleSort {

    /**
     * Generic method for sorting a list of type T according to a comparator.
     * The implementation is based on the BubbleSort algorithm, shown
     * in the lectures of the course Advanced Programming (02324) and
     * Project in Software Development (02362), adjusted from arrays to
     * lists and using the comparator instead of direct comparison of
     * integer values.
     *
     * @param comp the comparator
     * @param list the list
     * @param <T>  the type of the list
     */
    public static <T> void sort(@NotNull Comparator<? super T> comp, @NotNull List<T> list) {
        if (comp == null || list == null) {
            throw new IllegalArgumentException("Arguments of the sort function must not be null.");
        }
        boolean swapped;
        int j = list.size();
        do {
            swapped = false;
            for (int i = 1; i < j; i++) {
                if (comp.compare(list.get(i - 1), list.get(i)) > 0) {
                    T temp = list.get(i - 1);
                    list.set(i - 1, list.get(i));
                    list.set(i, temp);
                    swapped = true;
                }
            }
            j--; // Optimization to reduce unnecessary comparisons
        } while (swapped);
    }
}
