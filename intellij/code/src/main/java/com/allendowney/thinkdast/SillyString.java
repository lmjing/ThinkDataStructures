/**
 *
 */
package com.allendowney.thinkdast;

import java.util.Map;

/**
 * @author downey
 *
 */
public class SillyString {
	private final String innerString;

	public SillyString(String innerString) {
		this.innerString = innerString;
	}

	public String toString() {
		return innerString;
	}

	@Override
	public boolean equals(Object other) {
		return this.toString().equals(other.toString());
	}

	/**
	 * 좋은 해시함수는 아니다. 서로 다른 문자열에 대해 같은 해시코드를 리턴하기 때문
	 * ex) ab == ba
	 * -> 많은 객체가 동일한 해시코드를 갖는다면 결국 같은 하위 맵으로 몰리게 됨
	 * -> 특정 하위맵에 다른 맵보다 많은 엔트리가 있으면 k개의 하위 맵으로 인한 성능 향상은 k보다 줄어들게 됨
	 * ==> 해시험수는 균등해야 한다. = 일정 범위에 있는 어떤 값으로 고루 퍼지도록 해야 한다.
	 * @return
	 */
	@Override
	public int hashCode() {
		int total = 0;
		// 각 문자의 유니코드들을 합산한다.
		for (int i=0; i<innerString.length(); i++) {
			total += innerString.charAt(i);
		}
		System.out.println(total);
		return total;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<SillyString, Integer> map = new MyBetterMap<SillyString, Integer>();

		map.put(new SillyString("Word1"), 1);
		map.put(new SillyString("Word2"), 2);
		Integer value = map.get(new SillyString("Word1"));
		System.out.println(value);

		for (SillyString key: map.keySet()) {
			System.out.println(key + ", " + map.get(key));
		}
	}
}
