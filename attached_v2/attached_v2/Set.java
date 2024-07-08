import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Generic sets are mutable, unbounded sets of elements of a given type.
 * Element type can be Object.
 */
public class Set<T> extends AbstractCollection<T> {
    private Vector<T> elements; // use generic syntax

    /**
     * Default constructor: initialise this to be an empty Set
     */
    public Set() {
        elements = new Vector<>();
    }

    /**
     * If x is already in this Set, do nothing.
     * Otherwise, add x to this Set.
     */
    public void insert(T x) {
        if (getIndex(x) < 0)
            elements.add(x); // auto-boxing
    }

    /**
     * If x is not in this Set, do nothing.
     * Otherwise, remove x from this Set.
     */
    public boolean remove(Object x) {
        int i = getIndex(x);
        if (i < 0)
            return false;
        elements.set(i, elements.lastElement());
        elements.remove(elements.size() - 1);
        return true;
    }

    /**
     * @return true if x is in this Set, false otherwise.
     */
    public boolean isIn(T x) {
        return (getIndex(x) >= 0);
    }

    /**
     * @return the number of elements in this Set
     */
    public int size() {
        return elements.size();
    }

    /**
     * @return a new Vector<T> of elements of this Set if this Set is not empty.
     * Return null otherwise.
     */
    public Vector<T> getElements() {
        if (size() == 0)
            return null;
        else {
            Vector<T> els = new Vector<>();
            for (T e : elements)
                els.add(e);
            return els;
        }
    }

    /**
     * @return an arbitrary element of this Set
     * @throws IllegalStateException if this Set is empty
     */
    public T choose() throws IllegalStateException {
        if (size() == 0)
            throw new IllegalStateException("Set.choose: set is empty");
        return elements.lastElement();
    }

    /**
     * @return the index where x appears if x is in this Set.
     * Return -1 otherwise.
     */
    private int getIndex(Object x) {
        for (int i = 0; i < elements.size(); i++) {
            if (x.equals(elements.get(i)))
                return i;
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public String toString() {
        if (size() == 0)
            return "Set { }";

        String s = "Set {" + elements.elementAt(0).toString();
        for (int i = 1; i < size(); i++) {
            s = s + ", " + elements.elementAt(i).toString();
        }

        return s + "}";
    }

    /**
     * @return true if this object' state is valid.
     * Return false otherwise.
     */
    public boolean repOK() {
        if (elements == null)
            return false;

        for (int i = 0; i < elements.size(); i++) {
            T x = elements.get(i);
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(j).equals(x))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Set)) return false;
        Set<?> set = (Set<?>) o;
        return getElements().equals(set.getElements());
    }
}
