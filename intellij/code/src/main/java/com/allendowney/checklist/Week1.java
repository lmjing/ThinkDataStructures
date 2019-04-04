package com.allendowney.checklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Q, 1장 : ArrayList로 초기화 했을때, LinkedList 에 있는 메소드 사용하려면 어떻게 해야하는가
 * - 내가 이해해서 적었던 문제 : 인터페이스 객체를 리턴하는 함수가 있을 때, 그 인터페이스를 구현한 특정 구현체의 함수를 사용하고 싶을 때 어떻게 할까?
 */
public class Week1 {
    public static void main (String[] args) {
        List<Integer> test = testFunction();
        test.add(2); // add 메소드는 list꺼다. arraylist인지 모름.
    }

    // List Interface를 리턴하는 함수
    static List<Integer> testFunction() {
        List<Integer> test = new ArrayList<>();
        return test;
    }
}
