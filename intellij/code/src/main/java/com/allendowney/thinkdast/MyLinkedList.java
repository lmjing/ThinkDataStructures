/**
 * 
 */
package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author downey
 * @param <E>
 *
 */
public class MyLinkedList<E> implements List<E> {

	/**
	 * Node is identical to ListNode from the example, but parameterized with T
	 *
	 * @author downey
	 *
	 */
	private class Node {
		public E data;
		public Node next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
		public String toString() {
			return "Node(" + data.toString() + ")";
		}
	}

	private int size;            // keeps track of the number of elements
	private Node head;           // reference to the first node

	/**
	 *
	 */
	public MyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run a few simple tests
		List<Integer> mll = new MyLinkedList<Integer>();
		mll.add(1);
		mll.add(2);
		mll.add(3);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

		mll.remove(new Integer(2));
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
	}

	@Override
	public boolean add(E element) {
		if (head == null) {
			head = new Node(element);
		} else {
			Node node = head;
			// loop until the last node
			for ( ; node.next != null; node = node.next) {}
			node.next = new Node(element);
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		//TODO: FILL THIS IN!

		// 예외처리 까먹음
		if (index == 0) {
			// O(1) - 맞춤
			// 첫번째 노드일 경우 새로운 노드 생성 후 원래 첫번째 노드 연결한다.
			head = new Node(element, head);
		} else {
			// O(N) - 맞춤
			// 1. 이전 노드 다음을 현재 새로운 노드로 지정
			Node before = getNode(index-1); // 이전 노드 찾아옴

			// 2. 새로운 노드 다음을 이전노드의 다음과 연결
			before.next = new Node(element, before.next);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean flag = true;
		for (E element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj: collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	/** Returns the node at the given index.
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i=0; i<index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		//TODO: FILL THIS IN!

		// O(N) - 맞춤
		Node node = head;
		for (int i=0; i<size; i++) {
			if (equals(target, node.data)) {
				return i;
			}
			node = node.next;
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
	public Iterator<E> iterator() {
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i=0; i<size; i++) {
			if (equals(target, node.data)) {
				index = i;
			}
			node = node.next;
		}
		return index;
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
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
		//TODO: FILL THIS IN!

		// range 처리
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// 만약 첫번째라면 그냥 head를 next로 변경
		if (index == 0) {
			// O(1)
			head = head.next;
		} else { // 첫번째 노드가 아니므로, 이전 노드가 무조건 있다!
			// O(N)
			// 이전 노드와 다음 노드를 연결해주어야 한다.
			Node before = getNode(index-1);
			/*
			의문 : 만약 size가 5여서, index가 0~4인 경우라고 생각해보자.
			remove(5)를 호출한 경우
			1. 일단 index != 0라서 여기로 들어온다.
			2. 이전 노드를 찾아온다 => 4! (4.next = null)
			2. 아래 코드를 곧바로 실시할 경우
			before.next = null이므로 before.next.next = (null).next가 되어 실행이 안되지 않나?
			어쨌든간에 null이더라도 Object 타입을 Node로 선언해뒀기 때문에 next 변수자체에 접근이 되고, 그래서 null을 받아와서 에러가 안뜨는건가?
			TODO : 테스트해보고 여쭤보기
			 */
			before.next = before.next.next;
		}

		size--;

		// 이전 노드를 가져온다. => 자신 노드 제외 앞-뒤 연결해 줄 목적
		// 만약 없다면 getNode 내부에서 index 예외처리 해줄 것
		Node before = getNode(index - 1);

		// 성공적으로 가져왔다면 앞-뒤 연결해준다.
		Node node = before.next; // 없애줄 노드
		if (node == null)

		// 예외처리 까먹음
		if (index == 0) {
			head = null;
		} else {

		}

		return null;
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
	public E set(int index, E element) {
		Node node = getNode(index);
		E old = node.data;
		node.data = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyLinkedList<E> list = new MyLinkedList<E>();
		for (Node node=head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add(node.data);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node=head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.data;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}
