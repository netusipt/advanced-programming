# Assignment 3
This solution implements a generic list system with three main parts:

1. `ArrayList<E>`  
   Uses a dynamic array that grows by doubling capacity when full.  
   It supports `get`, `set`, `add`, indexed `add`, `remove`, `indexOf`, and `sort` with bounds/null checks.

2. `BubbleSort`  
   Implements generic bubble sort using a provided `Comparator`.  
   Adjacent elements are swapped until no swaps are needed, so the list becomes sorted according to the comparator.

3. `SortedArrayList<E extends Comparable<E>>`  
   Extends `ArrayList` and keeps elements sorted at insertion time.  
   `add(E e)` finds the correct insertion index using linear search and inserts there.  
   `sort(...)` is disabled (`UnsupportedOperationException`) because the list is always maintained in sorted order.

Overall, the design separates responsibilities clearly:
- `ArrayList` handles storage and basic list operations.
- `BubbleSort` handles comparator-based sorting.
- `SortedArrayList` enforces sorted behavior automatically.

## GUI App (PersonsApp)

The JavaFX app in `PersonsApp` is a small interface for testing the list implementations with `Person` objects.

In `PersonsGUI`, initialization is intentionally split into two private methods:
`initGUI()` creates and arranges all visual components, while `setButtonActions()` attaches event handlers.
This separation keeps layout code and interaction logic independent, making the class easier to read, maintain, and extend.

- From the menu, you can switch between:
  - `(unsorted) List` -> uses `ArrayList<Person>`
  - `sorted List` -> uses `SortedArrayList<Person>`
  - `No Implementation` -> removes the active list UI
- In `PersonsGUI`, you can:
  - add a person (name + weight) at the end
  - add a person at a specific index
  - sort the current list
  - clear the list
  - delete individual entries
- The GUI also shows:
  - average weight of people in the list
  - most frequent name
  - exception/error messages (e.g., invalid number input, invalid index, unsupported sort on sorted list)

This makes it easy to manually compare behavior of normal and automatically sorted list implementations.
