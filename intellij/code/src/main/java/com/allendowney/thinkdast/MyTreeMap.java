/**
 *
 */
package com.allendowney.thinkdast;

import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a Map using a binary search tree.
 *
 * @param <K>
 * @param <V>
 *
 */
public class MyTreeMap<K, V> implements Map<K, V> {

	private int size = 0;
	private Node root = null;

	/**
	 * Represents a node in the tree.
	 *
	 */
	protected class Node {
		public K key;
		public V value;
		public Node left = null;
		public Node right = null;

		/**
		 * @param key
		 * @param value
		 * @param left
		 * @param right
		 */
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}

	@Override
	public boolean containsKey(Object target) {
		return findNode(target) != null;
	}

	/**
	 * Returns the entry that contains the target key, or null if there is none.
	 *
	 * @param target
	 */
	private Node findNode(Object target) {
		// some implementations can handle null as a key, but not this one
		if (target == null) {
			throw new IllegalArgumentException();
		}

		// 컴파일러 경고 무시
		// something to make the compiler happy
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) target;

		// TODO: FILL THIS IN!
		Node cursor = root;
		while (cursor != null) {
			int compare = k.compareTo(cursor.key);

			if (compare == 0) {
				return cursor;
			} else if (compare > 0)
				cursor = cursor.right;
			else
				cursor = cursor.left;
		}

		return null;
	}

	/**
	 * Compares two keys or two values, handling null correctly.
	 *
	 * @param target
	 * @param obj
	 * @return
	 */
	private boolean equals(Object target, Object obj) {
		if (target == null) {
			return obj == null;
		}
		return target.equals(obj);
	}

	@Override
	public boolean containsValue(Object target) {
		return containsValueHelper(root, target);
	}

	private boolean containsValueHelper(Node node, Object target) {
		// TODO: FILL THIS IN!

		// 리프 노드의 자식
		// -> 즉, 한쪽 방향의 자식 노드들을 모두 확인하였지만 찾지 못하였으므로 false 리턴
		if (node == null)
			return false;

		// 현재 노드의 값과 찾는 값이 일치할 경우 true!!!!
		if (equals(node.value, target))
			return true;

		// 현재 노드까지 못찾았으므로 자식들을 검사한다.
		// 왼쪽 자식 검사 -> 있다면 true
		if (containsValueHelper(node.left, target))
			return true;

		// 오른쪽 자식 검사 -> 있다면 true
		if (containsValueHelper(node.right, target))
			return true;

		// 자신이 null이 아니고 자식들을 다 검사했지만 모두 false 일때
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object key) {
		Node node = findNode(key);
		if (node == null) {
			return null;
		}
		return node.value;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void keySetHelper(Node node, Set<K> set) {
		if (node == null) // 가르키는 노드가 없을 때 리턴
			return;

		keySetHelper(node.left, set); // 왼쪽 호출
		set.add(node.key); // 자신 추가
		keySetHelper(node.right, set); // 오른쪽 호출
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new LinkedHashSet<K>();
		// TODO: FILL THIS IN!
		keySetHelper(root, set);

		return set;
	}

	@Override
	public V put(K key, V value) {
		if (key == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			root = new Node(key, value);
			size++;
			return null;
		}
		return putHelper(root, key, value);
	}

	private V putHelper(Node node, K key, V value) {
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) key;

		int compare = k.compareTo(node.key);

		if (compare == 0) {
			// 이미 있는 노드라면 대체하고 이전 값을 리턴한다.
			V before = node.value;
			node.value = value;
			return before;
		}else {
			Node next = compare > 0 ? node.right : node.left;
			if (next == null) {
				// 다음 노드가 없다면 해당 노드에 새로 연결해준다.
				if (compare > 0)
					node.right = new Node(key, value);
				else
					node.left = new Node(key, value);
				size++;
				return null;
			}
			return putHelper(next, key, value);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public V remove(Object key) {
		// OPTIONAL TODO: FILL THIS IN!

		// 컴파일러 경고 무시
		// something to make the compiler happy
		@SuppressWarnings("unchecked")
		Comparable<? super K> k = (Comparable<? super K>) key;

		Node cursor = root;
		Node parent = null;
		int direction = 0; // 0 : 왼쪽 / 1 : 오른쪽
		while (cursor != null) {
			int compare = k.compareTo(cursor.key);

			if (compare == 0) { // 일치 할 경우
				if (direction == 0)
					parent.left = cursor.right;
				else
					parent.right = cursor.left;
				return cursor.value;
			} else {
				// 계속 탐색
				if (compare < 0) {
					cursor = cursor.left;
					direction = 0;
				}else {
					cursor = cursor.right;
					direction = 1;
				}
				parent = cursor;
			}
		}

		// 못 찾았다는거니깐 에러 던짐
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		Deque<Node> stack = new LinkedList<Node>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (node == null) continue;
			set.add(node.value);
			stack.push(node.left);
			stack.push(node.right);
		}
		return set;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyTreeMap<String, Integer>();
		map.put("Word3", 3);
		map.put("Word4", 4);
		map.put("Word5", 5);
		map.put("Word1", 1);
		map.put("Word2", 2);
		map.put("Word1", 99);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}

	/**
	 * Makes a node.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public MyTreeMap<K, V>.Node makeNode(K key, V value) {
		return new Node(key, value);
	}

	/**
	 * Sets the instance variables.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @param node
	 * @param size
	 */
	public void setTree(Node node, int size ) {
		this.root = node;
		this.size = size;
	}

	/**
	 * Returns the height of the tree.
	 *
	 * This is only here for testing purposes.  Should not be used otherwise.
	 *
	 * @return
	 */
	public int height() {
		return heightHelper(root);
	}

	private int heightHelper(Node node) {
		if (node == null) {
			return 0;
		}
		int left = heightHelper(node.left);
		int right = heightHelper(node.right);
		return Math.max(left, right) + 1;
	}
}
