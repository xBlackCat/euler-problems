package org.xblackcat.euler.util;

import gnu.trove.TIntCollection;
import gnu.trove.TLongCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.iterator.TLongIntIterator;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.list.TLongList;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.procedure.*;
import gnu.trove.set.TLongSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * 17.11.2017 13:21
 *
 * @author xBlackCat
 */
public class TUtils {
    public static long max(TLongSet set) {
        if (set.isEmpty()) {
            throw new IllegalArgumentException("Empty set");
        }

        final TLongIterator it = set.iterator();
        long max = it.next();
        while (it.hasNext()) {
            final long v = it.next();
            if (max < v) {
                max = v;
            }
        }

        return max;
    }

    public static TLongIntMap nullLongIntMap() {
        return new TLongIntMap() {
            @Override
            public long getNoEntryKey() {
                return 0;
            }

            @Override
            public int getNoEntryValue() {
                return 0;
            }

            @Override
            public int put(long key, int value) {
                return 0;
            }

            @Override
            public int putIfAbsent(long key, int value) {
                return 0;
            }

            @Override
            public void putAll(Map<? extends Long, ? extends Integer> map) {

            }

            @Override
            public void putAll(TLongIntMap map) {

            }

            @Override
            public int get(long key) {
                return 0;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public int remove(long key) {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public TLongSet keySet() {
                return nullLongSet();
            }

            @Override
            public long[] keys() {
                return new long[0];
            }

            @Override
            public long[] keys(long[] array) {
                return new long[0];
            }

            @Override
            public TIntCollection valueCollection() {
                return nullIntCollection();
            }

            @Override
            public int[] values() {
                return new int[0];
            }

            @Override
            public int[] values(int[] array) {
                return new int[0];
            }

            @Override
            public boolean containsValue(int val) {
                return false;
            }

            @Override
            public boolean containsKey(long key) {
                return false;
            }

            @Override
            public TLongIntIterator iterator() {
                return nullLongIntIterator();
            }

            @Override
            public boolean forEachKey(TLongProcedure procedure) {
                return true;
            }

            @Override
            public boolean forEachValue(TIntProcedure procedure) {
                return true;
            }

            @Override
            public boolean forEachEntry(TLongIntProcedure procedure) {
                return true;
            }

            @Override
            public void transformValues(TIntFunction function) {

            }

            @Override
            public boolean retainEntries(TLongIntProcedure procedure) {
                return false;
            }

            @Override
            public boolean increment(long key) {
                return false;
            }

            @Override
            public boolean adjustValue(long key, int amount) {
                return false;
            }

            @Override
            public int adjustOrPutValue(long key, int adjust_amount, int put_amount) {
                return 0;
            }
        };
    }

    public static TIntCollection nullIntCollection() {
        return new TIntCollection() {
            @Override
            public int getNoEntryValue() {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public boolean contains(int entry) {
                return false;
            }

            @Override
            public TIntIterator iterator() {
                return nullIntIterator();
            }

            @Override
            public int[] toArray() {
                return new int[0];
            }

            @Override
            public int[] toArray(int[] dest) {
                return new int[0];
            }

            @Override
            public boolean add(int entry) {
                return false;
            }

            @Override
            public boolean remove(int entry) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean containsAll(TIntCollection collection) {
                return false;
            }

            @Override
            public boolean containsAll(int[] array) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> collection) {
                return false;
            }

            @Override
            public boolean addAll(TIntCollection collection) {
                return false;
            }

            @Override
            public boolean addAll(int[] array) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(TIntCollection collection) {
                return false;
            }

            @Override
            public boolean retainAll(int[] array) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(TIntCollection collection) {
                return false;
            }

            @Override
            public boolean removeAll(int[] array) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean forEach(TIntProcedure procedure) {
                return false;
            }
        };
    }

    private static TIntIterator nullIntIterator() {
        return new TIntIterator() {
            @Override
            public int next() {
                return 0;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public void remove() {

            }
        };
    }

    private static TLongIntIterator nullLongIntIterator() {
        return new TLongIntIterator() {
            @Override
            public long key() {
                return 0;
            }

            @Override
            public int value() {
                return 0;
            }

            @Override
            public int setValue(int val) {
                return 0;
            }

            @Override
            public void advance() {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public void remove() {

            }
        };
    }

    public static TLongSet nullLongSet() {
        return new TLongSet() {
            @Override
            public long getNoEntryValue() {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public boolean contains(long entry) {
                return false;
            }

            @Override
            public TLongIterator iterator() {
                return null;
            }

            @Override
            public long[] toArray() {
                return new long[0];
            }

            @Override
            public long[] toArray(long[] dest) {
                return new long[0];
            }

            @Override
            public boolean add(long entry) {
                return false;
            }

            @Override
            public boolean remove(long entry) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean containsAll(TLongCollection collection) {
                return false;
            }

            @Override
            public boolean containsAll(long[] array) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Long> collection) {
                return false;
            }

            @Override
            public boolean addAll(TLongCollection collection) {
                return false;
            }

            @Override
            public boolean addAll(long[] array) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(TLongCollection collection) {
                return false;
            }

            @Override
            public boolean retainAll(long[] array) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean removeAll(TLongCollection collection) {
                return false;
            }

            @Override
            public boolean removeAll(long[] array) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean forEach(TLongProcedure procedure) {
                return true;
            }
        };
    }

    public static <T> TLongObjectMap<T> nullLongObjectMap() {
        return new TLongObjectMap<T>() {
            @Override
            public long getNoEntryKey() {
                return 0;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public boolean containsKey(long key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public T get(long key) {
                return null;
            }

            @Override
            public T put(long key, T value) {
                return null;
            }

            @Override
            public T putIfAbsent(long key, T value) {
                return null;
            }

            @Override
            public T remove(long key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends Long, ? extends T> m) {

            }

            @Override
            public void putAll(TLongObjectMap<? extends T> map) {

            }

            @Override
            public void clear() {

            }

            @Override
            public TLongSet keySet() {
                return nullLongSet();
            }

            @Override
            public long[] keys() {
                return new long[0];
            }

            @Override
            public long[] keys(long[] array) {
                return new long[0];
            }

            @Override
            public Collection<T> valueCollection() {
                return Collections.emptySet();
            }

            @Override
            public Object[] values() {
                return new Object[0];
            }

            @Override
            public T[] values(T[] array) {
                return null;
            }

            @Override
            public TLongObjectIterator<T> iterator() {
                return nullLongObjectIterator();
            }

            @Override
            public boolean forEachKey(TLongProcedure procedure) {
                return true;
            }

            @Override
            public boolean forEachValue(TObjectProcedure<? super T> procedure) {
                return true;
            }

            @Override
            public boolean forEachEntry(TLongObjectProcedure<? super T> procedure) {
                return true;
            }

            @Override
            public void transformValues(TObjectFunction<T, T> function) {

            }

            @Override
            public boolean retainEntries(TLongObjectProcedure<? super T> procedure) {
                return false;
            }
        };
    }

    private static <T> TLongObjectIterator<T> nullLongObjectIterator() {
        return new TLongObjectIterator<T>() {
            @Override
            public long key() {
                return 0;
            }

            @Override
            public T value() {
                return null;
            }

            @Override
            public T setValue(T val) {
                return null;
            }

            @Override
            public void advance() {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public void remove() {

            }
        };
    }
}
