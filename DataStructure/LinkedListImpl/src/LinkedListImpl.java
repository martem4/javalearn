import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedListImpl<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        Boolean contain = false;
        for (T t  : this) {
            if (t.equals(o)) {
                contain = true;
            }
        }
        return contain;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        Object[] m = new Object[this.size()];
        int i=0;
        for (Item<T> item = first; item != null; item=item.next) {
            m[i++] = item.getElement();
        }
        return m;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a.length < size)
            a = (T1[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Item<T> x = first; x != null; x = x.next)
            result[i++] = x.getElement();

        if (a.length > size)
            a[size] = null;

        return a;
        // END
    }

    @Override
    public boolean add(final T t) {
        /*TODO
            if no any elemetns add new one with null last and first
            find last element, add new node and set pointers
         */
        Item<T> p = null;
        Item<T> l = null;

        if ((size == 0) && (first == null)) {
            first = new Item<T>(t, null, null);
            last = first;
         }
         else {
            Item<T> item = new Item<T>(t, last,null);
            item.prev =last;
            last.next = item;
            last = item;
            size++;
        }
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        /** TODO
         *  remove first occurence
         */
        for(Item<T> i = first; i != null; i=i.next) {
            if (o == null) {
                return false;
            }
            if (i.equals(o)) {
                if(i == first) {
                    first = i.next;
                    first.prev = null;
                    i.next = null;
                }else if (i == last) {
                    last = i.prev;
                    last.next = null;
                    i.prev = null;
                }else {
                    i.prev.next = i.next;
                    i.next.prev = i.prev;
                    i.next = null;
                    i.prev = null;
                }
                size--;
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
// BEGIN (write your solution here)
        for (Item<T> item=first; item != null;) {
            Item<T> next = item.next;
            item.next=null;
            item.prev=null;
            item=next;
        }
        last=first=null;
        size = 0;
// END
    }

    @Override
    public T remove(final int index) {
// BEGIN (write your solution here)
    if (index > size) {
        return null;
    }

    int i=-1;
    for(Item<T> item=first; item != null; item=item.next) {
        if (++i == index) {
            T removedItem = item.element;
            remove(item);
            return removedItem;
        }
    }
// END
    }

// BEGIN (write your solution here)

    // END
    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        // BEGIN (write your solution here)
        int i=0;
        for (Item<T> item = first; item != null; item=item.next) {
            if (item.equals(target)) {
                return i;
            }
            i++;
        }
        return -1;
        // END
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
// BEGIN (write your solution here)
        int i=0;
        T oldElement = null;
        for (Item<T> item = first; item != null; item=item.next) {
            if (index == i++) {
                oldElement=item.getElement();
                item.element=element;
            }
        }
        return oldElement;
// END
    }

    @Override
    public T get(final int index) {
// BEGIN (write your solution here)
        int i=0;
        for(Item<T> item = first;  item != null; item=item.next) {
            if (i++ == index) {
                return item.getElement();
            }
        }
        return null;
// END
    }

// BEGIN (write your solution here)

// END

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> last;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
// BEGIN (write your solution here)

// END
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
// BEGIN (write your solution here)

// END
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
// BEGIN (write your solution here)

// END
        }

        @Override
        public int previousIndex() {
// BEGIN (write your solution here)

// END
        }

        @Override
        public int nextIndex() {
// BEGIN (write your solution here)

// END
        }

        @Override
        public boolean hasPrevious() {
// BEGIN (write your solution here)

// END
        }

        @Override
        public T previous() {
// BEGIN (write your solution here)

// END
        }

        @Override
        public void remove() {
// BEGIN (write your solution here)

// END
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }

    }
}