1. Implementation of ArrayList<E>
Dynamic Array Structure
•	The ArrayList<E> class is based on Java's native array structure but expands dynamically.
•	A default capacity (DEFAULT_SIZE = 10) is used initially.
•	If the list reaches full capacity, ensureCapacity() doubles the array size.
Core Functionalities
•	Adding Elements:
o	add(E e): Appends an element to the end.
o	add(int pos, E e): Inserts an element at a given position.
•	Removing Elements:
o	remove(int pos): Removes an element at a specific position.
o	remove(E e): Removes the first occurrence of the given element.
•	Retrieval and Modification:
o	get(int pos): Retrieves an element by index.
o	set(int pos, E e): Updates an element at a specific position.
•	Sorting:
o	Implemented Bubble Sort for ordering elements (sort(Comparator<? super E> c)).
o	The sorting method compares elements using a custom comparator.
Helper Methods
•	ensureCapacity(): Ensures the array resizes dynamically.
•	checkIndex(int pos): Validates index boundaries before access.
2. Implementation of SortedArrayList<E>
Extension of ArrayList<E>
•	SortedArrayList<E> extends ArrayList<E>, inheriting its core functionalities.
•	Elements are always inserted in sorted order.
Key Differences from ArrayList<E>
•	Overridden add(E e): Instead of appending elements, it finds the correct position using findIndexToInsert(E e) and inserts accordingly.
•	Sorting is Disabled:
o	The sort(Comparator<? super E> c) method is overridden to throw an exception, ensuring elements remain sorted upon insertion.
3. GUI Implementation (PersonsGUI.java)
Interface Overview
The GUI enables users to interact with a list of persons. It includes:
•	A text field for name input.
•	Buttons to Add, Sort, and Clear the list.
•	A scrollable list displaying persons.
•	A delete button next to each person to remove them from the list.
Functionalities
•	Add Button: Adds a new person to the list with an incrementing weight.
•	Sort Button: Sorts the list using a generic comparator (GenericComparator<>).
•	Clear Button: Removes all elements from the list.
•	Delete Button: Removes an individual person from the list.
Update Mechanism
•	The update() method dynamically refreshes the list to reflect changes in the GUI.
•	Uses personsPane.getChildren().clear() to reset the list before updating.

