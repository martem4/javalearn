import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by artem on 01.08.17.
 */
public class ArrList<E> implements List<E> {
    private E[] m;

    @Override
    public int size() {
        if (m!=null) {
            return m.length;
        }
        else {
            return 0;
        }

    }

    @Override
    public boolean isEmpty() {
        if (m!=null) {
            return this.size() == 0;
        }
        else {
            return true;
        }

    }

    @Override
    public boolean contains(Object o) {
        if (m!=null) {
            for (E i : m) {
                if (i.equals(o)) {
                    return true;
                }
            }
            return false;
        }
        else return false;

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        if (m!=null && this.size()!=0) {
            Object[] ar = new Object[this.size()];

            for (int i=0; i<m.length; i++) {
                ar[i] = (E)m[i];
            }
            return ar;
        }
        return null;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (m!=null && this.size() != 0) {
            E[] ar = (E[]) new Object[this.size()];

            for (int i=0; i < m.length; i++) {
                ar[i] =  (E)m[i];
            }
            return ar;
        }
        return null;
    }

    @Override
    public boolean add(E e) {
        if (e==null) {
            return false;
        }
        else {
                if(m!=null) {
                    E[] tempArr = m;
                    m = (E[]) new Object[this.size() + 1];
                    System.arraycopy(tempArr, 0, m, 0, tempArr.length);
                    m[m.length - 1] = e;
                    return true;
                }
                else {
                    m = (E[]) new Object[1];
                    m[0] = e;
                    return true;
                }
        }
    }


    @Override
    public boolean remove(Object o) {
        if (m!=null) {
            int i = 0;
            for (E e : m) {
                if (e.equals(o)) {
                    i++;
                }
            }
            if (i > 0) {
                E[] tmpAr = (E[]) new Object[m.length - i];
                int k = 0;
                for (int j = 0; j < m.length; j++) {
                    if (m[j].equals(o)) {
                        continue;
                    } else {
                        tmpAr[k++] = m[j];
                    }
                }
                m = tmpAr;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            boolean existFlag = false;
            for (E e : m ) {
                if(e.equals(o)) {
                    existFlag = true;
                }
            }
            if(!existFlag) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if(c.size()!=0) {
            E[] newArr = (E[]) new Object[m.length + c.size()];
            System.arraycopy(m, 0, newArr, 0, m.length);
            System.arraycopy(c, 0, newArr, newArr.length, c.size());
            m=newArr;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if(c.size()>0) {
            E[] newArr = (E[]) new Object[m.length + c.size()];
//            System.arraycopy(m, 0, newArr, 0);
//            System.arraycopy(c, index, newArr, m.length);
            m=newArr;
            return true;
        }
        return false;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldArraySize = this.size();
        for(Object o : c) {
            this.remove(c);
        }
        if(oldArraySize > this.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldArraySize = this.size();
        for(Object o : c) {
            for(E e : m) {
                if (!e.equals(o)) {
                    this.remove(o);
                }
            }
        }
        if(oldArraySize > this.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        for(int i=0; i<m.length; i++) m[i] = null;
    }

    @Override
    public E get(int index) {
        if (index>=0 && index<=this.size()) {
            return m[index];
        }
        else {
            return null;
        }
    }

    @Override
    public E set(int index, E element) {
        if (index>=0 && index<=this.size()) {
            E oldElement = m[index];
            m[index] = element;
            return oldElement;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        E[] newAr = (E[]) new Object[m.length+1];
        System.arraycopy(m, 0, newAr, 0, index+1);
        newAr[index]=element;
        System.arraycopy(m, index+1, newAr, index+1, m.length-index-1);
    }

    @Override
    public E remove(int index) {
        if (index >=0 && index < m.length) {
            E e = m[index];
            E[] newArr = (E[]) new Object[m.length - 1];

            int j = 0;
            for (int i = 0; i < m.length; i++) {
                if (i == index) {
                    continue;
                } else {
                    newArr[j++] = m[i];
                }
            }
            m = newArr;
            return e;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int i=0;
        for (E e : m) {
            if (e.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i=0;
        int k=0;
        boolean exist = false;
        for (E e : m) {
            if (e.equals(o)) {
                k=i;
                exist=true;
            }
            i++;
        }
        if (exist) return k;
        else return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        ArrList<E> newList = new ArrList<>();
        for (int i=fromIndex; i<=toIndex; i++) {
            newList.add(m[i]);
        }
        return newList;
    }

    private class ArrListIterator implements ListIterator {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < m.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return (E) m[index++];
            }
            else{
                return null;
            }

        }

        @Override
        public boolean hasPrevious() {
            return index>0;
        }

        @Override
        public Object previous() {
            if (hasPrevious()) {
                return (E)m[index-1];
            }
            else {
                return null;
            }
        }

        @Override
        public int nextIndex() {
            if (index < m.length) {
                return index+1;
            }
            else {
                return -1;
            }

        }

        @Override
        public int previousIndex() {
            return index-1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
