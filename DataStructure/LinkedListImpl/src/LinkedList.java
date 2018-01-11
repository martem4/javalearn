import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedList<T> implements List<T> {

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
        for (Item<T> item=first; item != null; item=item.next) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        if (this.size > 0) {
            Object[] m = new Object[this.size()];
            int i = 0;
            for (Item<T> item = first; item != null; item = item.next) {
                m[i++] = item.getElement();
            }
            return m;
        }
        return null;
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
            if no any elements add new one with null last and first
            find last element, add new node and set pointers
         */
        if ((size == 0) && (first == null)) {
            first = new Item<T>(t, null, null);
            last = first;
        }
        else {
            Item<T> item = new Item<T>(t, last,null);
            item.prev = last;
            last.next = item;
            last = item;
        }

        size++;
        return true;
    }

    private   void unlink(Item<T> item) {
        if (item == first) {
            first = item.getNext();
            first.prev = null;
            item.next = null;
        }
        else if (item == last) {
            last = last.prev;
            last.next = null;
            item.prev = null;
        }
        else {
            item.prev.next = item.next;
            item.next.prev = item.prev;
            item.next = null;
            item.prev = null;
        }
        item = null;
    }

    @Override
    public boolean remove(final Object o) {
        /** TODO
         *  remove first occurence
         */
        for(Item<T> i = first; i != null; i=i.next) {
            if (i.element == null) {
                unlink(i);
                size--;
                return true;
            }

            if (i.element.equals(o)) {
                unlink(i);
                size--;
                return true;
            }
        }
        return false;
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
    public T remove(final int index) throws IndexOutOfBoundsException{
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
        return null;
        // END
    }

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

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> last;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            if (index <= size) {
                int i = 0;
                for (Item item=first; item != null; item=item.next) {
                    if (i++ == index)
                        current = item;
                }
            }
            else {
                throw new NoSuchElementException();
            }
            // END
        }

        @Override
        public boolean hasNext() {
            if( current==null) {
                throw new NoSuchElementException();
            }
            else if (current.next == null) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
        // BEGIN (write your solution here)
                if (hasNext()) {
                    current = current.next;
                    return current.getElement();
                }
                else
                    throw new NoSuchElementException();
        // END
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
        // BEGIN (write your solution here)
            if (current != null) {
                current.element = element;
            }
            else
                throw new NoSuchElementException();
        // END
        }

        @Override
        public int previousIndex() {
// BEGIN (write your solution here)
            if (hasPrevious()) {
                return indexOf(current.prev);
            }
// END
            return -1;
        }

        @Override
        public int nextIndex() {
// BEGIN (write your solution here)
            if (hasNext()) {
                return indexOf(current.next);
            }
            return -1;
// END
        }

        @Override
        public boolean hasPrevious() {
// BEGIN (write your solution here)
            if (current != null)
                return current.prev != null;
            else
                    throw new NoSuchElementException("Iterato " +
                            "does't have any elements!");
// END
        }

        @Override
        public T previous() {
// BEGIN (write your solution here)
            if (hasPrevious()) {
                return current.getPrev().getElement();
            }
            return null;
// END
        }

        @Override
        public void remove() {
// BEGIN (write your solution here)
            LinkedList.this.remove(current);
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
