package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author downey
 * @param <T>
 *
 */
public class MyArrayList<T> implements List<T> {
	int size;                    // keeps track of the number of elements
	private T[] array;           // stores the elements

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// You can't instantiate an array of T[], but you can instantiate an
		// array of Object and then typecast it.  Details at
		// http://www.ibm.com/developerworks/java/library/j-jtp01255/index.html
		array = (T[]) new Object[10];
		size = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run a few simple tests
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		mal.add(1);
		mal.add(2);
		mal.add(3);
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);

		mal.remove(new Integer(2));
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);
	}

	@Override
	public boolean add(T element) {
		// TODO: FILL THIS IN!
		/*
		시간 복잡도 : O(1)
		TODO : 이유 옮겨 적어
		 */

		// 1. size 넘었을 경우 리사이징 시켜준다.
		if (size >= array.length) {
			T[] newArray = (T[]) new Object[size * 2];
			System.arraycopy(array, 0, newArray, 0, size * 20);
			array = newArray;
		}

		// 2. 마지막 위치에 엘리먼트 추가
		array[size] = element;
		size++;

		return true;
	}

	@Override
	public void add(int index, T element) {
		// idx 범위 벗어나면 예외처리 실시
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// add the element to get the resizing
		add(element);

		// shift the elements
		for (int i=size-1; i>index; i--) {
			array[i] = array[i-1];
		}
		// put the new one in the right place
		array[index] = element;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean flag = true;
		for (T element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// note: this version does not actually null out the references
		// in the array, so it might delay garbage collection.
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object element: collection) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public int indexOf(Object target) {
		// TODO: FILL THIS IN!

		for (int i = 0; i < size; i++) {
			if(equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		// see notes on indexOf
		for (int i = size-1; i>=0; i--) {
			if (equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// make a copy of the array
		T[] copy = Arrays.copyOf(array, size);
		// make a list and return an iterator
		return Arrays.asList(copy).listIterator(index);
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public T remove(int index) {
		// TODO: FILL THIS IN!
		// 여기 index range 처리해줘야하나?
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		T removeV = array[index];

		for (int i = index; i < size-1; i++) {
			array[i] = array[i+1];
		}

		// 마지막 값 null 처리
		array[size-1] = null;

		// 하나 없어졌으니깐 사이즈 줄여준다.
		size--;

		return removeV;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj: collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		// TODO: FILL THIS IN!

		// 우선 마지막에 추가해줌 (배열 리사이징 목적)
		add(element);

		// 뒤에서 부터 접근해서 하나씩 땡겨줌
		// 뒤에 추가해놨어도 이 과정에서 추가한 값 사라짐
		for (int i = size-1; i > index; i--) {
			array[i] = array[i-1];
		}
		// 해당 index에 추가한다.
		array[index] = element;

		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
		return Arrays.asList(copy);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@Override
	public <U> U[] toArray(U[] array) {
		throw new UnsupportedOperationException();
	}

	public int[] indexsOf(Object target) {
		int ri = 0;
		int[] equalIdexs = new int[size]; // 일단 전체 담을 수 있는 배열 생성

		for (int i=0; i<size; i++) {
			if (equals(array[i], target)) {
				equalIdexs[ri++] = i; // 해당 인덱스를 넣어준다.
			}
		}

		int[] result = ri == 0 ? null : Arrays.copyOf(equalIdexs, ri);
		return result;
	}
}
