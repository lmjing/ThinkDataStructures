/**
 *
 */
package com.allendowney.thinkdast;

import java.util.List;
import java.util.Map;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {

	// average number of entries per map before we rehash
	// 로트 팩터 : 하위 맵당 최애 엔트리 개수 결정
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);

		//System.out.println("Put " + key + " in " + map + " size now " + map.size());

		// size() : 맵에 저장된 엔트리의 총 개수
		// check if the number of elements per map exceeds the threshold
		// n > k * factor = n/k(하위 맵당 최대 엔트리 개수) > factor
		if (size() > maps.size() * FACTOR) { // 하위 맵당 엔트리 개수 임계치 초과
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 * rehash 메서드 호출 시마다 내장된 맵의 개수 k가 두 배가 되어야 함
	 */
	/**
	 *
	 */
	protected void rehash() {
		// TODO: FILL THIS IN!
		// 테이블에서 엔트리 수집, 크기 산정, 엔트리 다시 넣기

		makeMaps(maps.size()); // 하위 맵 개수 2배로 늘려줌
		for (MyLinearMap map : maps) {
			for (Object entry : map.getEntries()) {

			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
