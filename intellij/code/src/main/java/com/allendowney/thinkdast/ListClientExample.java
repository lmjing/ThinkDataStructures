package com.allendowney.thinkdast;

import java.util.LinkedList;
import java.util.List;

public class ListClientExample {
	@SuppressWarnings("rawtypes")
	private List list;

	@SuppressWarnings("rawtypes")
	public ListClientExample() {
		list = new LinkedList();
		// List 인터페이스로는 객체를 생성할 수 없다.
		// why? -> 클래스 객체가 아니라 인터페이스니깐....
		// 인터페이스는 정의만 함
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		System.out.println(list);
	}
}
