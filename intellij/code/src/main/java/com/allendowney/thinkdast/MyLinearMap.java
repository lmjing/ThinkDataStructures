/**
 *
 */
package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of a Map using a List of entries, so most
 * operations are linear time.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyLinearMap<K, V> implements Map<K, V> {

	private List<Entry> entries = new ArrayList<Entry>();

	// Entry 객체는 키-값 쌍을 포함하는 컨테이너
	public class Entry implements Map.Entry<K, V> {
		// K, V 타입을 입력 받아 선언
		private K key;
		private V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public V setValue(V newValue) {
			value = newValue;
			return value;
		}
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public boolean containsKey(Object target) {
		return findEntry(target) != null;
	}

	/**
	 * Returns the entry that contains the target key, or null if there is none.
	 * 헬퍼 메서드 - 여러 메소드에서 사용 가능
	 *
	 * @param target
	 */
	private Entry findEntry(Object target) {
		// TODO: FILL THIS IN!

		// target key로 엔트리 검색
		for (Entry entry : entries) {
			if (equals(entry.getKey(), target)) {
				// key 값이 동일한 경우 -> 찾음
				return entry;
			}
			// 일치하지 않는 경우 -> 다음 엔트리 확인
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
		for (Map.Entry<K, V> entry: entries) {
			if (equals(target, entry.getValue())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object key) {
		// TODO: FILL THIS IN!

		// key값을 활용하여 entry를 찾는다.
		Entry entry = findEntry(key);
		// entry를 찾았다면 value를 담는다.
		if (entry != null)
			return entry.getValue();

		return null;
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for (Entry entry: entries) {
			set.add(entry.getKey());
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		// TODO: FILL THIS IN!
		// 리턴 값은 이전 key에 맵핑된 value이다.

		Entry entry = findEntry(key);

		if (entry == null) {
			entries.add(new Entry(key, value));
			return null; // 이전 노드가 없으면 null을 리턴한다.
		} else {
			V oldValue = entry.getValue();
			entry.setValue(value); // 새로운 value 삽입 - 주소 참조형으로 entries의 값도 변한다.
			return oldValue;
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
		// TODO: FILL THIS IN!
		Entry entry = findEntry(key);
		if (entry == null)
			return null;
		else {
			entries.remove(entry); // 근데 이렇게 삭제하면 불필요한 for문을 한번 더 돌지 않나...?
			return entry.getValue();
		}

//		for (int i=0; i<entries.size(); i++) {
//			Entry entry = entries.get(i);
//			if (equals(key, entry.getKey())) {
//				// 일치하면 삭제
//				entries.remove(i);
//				return entry.getValue();
//			}
////		}
//
//		return null;
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public Collection<V> values() {
		Set<V> set = new HashSet<V>();
		for (Entry entry: entries) {
			set.add(entry.getValue());
		}
		return set;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyLinearMap<String, Integer>();
		map.put("Word1", 1);
		map.put("Word2", 1);
		map.put("Word2", 2);
		Integer value = map.get("Word1");
		System.out.println(value);

		for (String key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}

	/**
	 * Returns a reference to `entries`.
	 *
	 * This is not part of the Map interface; it is here to provide the functionality
	 * of `entrySet` in a way that is substantially simpler than the "right" way.
	 *
	 * @return
	 */
	protected Collection<? extends java.util.Map.Entry<K, V>> getEntries() {
		return entries;
	}
}
