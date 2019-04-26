package com.allendowney.homework;

import java.util.*;

/**
 * 기수정렬
 * TODO : 음수/양수 마지막에 처리하는 방법으로 할 것
 */

public class HomeWork2 {
    static int max;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Map<Integer, Queue<Integer>> map = new HashMap<>();
        max = 0;
        for (int i=0; i<N; i++) {
            int input = sc.nextInt();
            int abs = Math.abs(input);
            max = max < abs ? abs : max;
            insertQueue(map, 1, input);
        }

        sort(map, 10);
    }

    private static void sort(Map<Integer, Queue<Integer>> map, int div) {
        if (max < div) {
            // 결과 출력
            printResult(map);
            return;
        }

        Map<Integer, Queue<Integer>> newMap = new HashMap<>();
        for (int i=0; i<10; i++) {
            Queue<Integer> queue = map.get(i);
            if (queue == null)
                continue;
            while (!queue.isEmpty()) {
                insertQueue(newMap, div, queue.poll());
            }
        }
        sort(newMap, div * 10);
    }

    private static void printResult(Map<Integer, Queue<Integer>> map) {
        Deque<Integer> deque = new LinkedList<>();
        for (int i=0; i<=9; i++) {
            Queue<Integer> queue = map.get(i);
            if (queue == null)
                continue;
            while (!queue.isEmpty()) {
                int su = queue.poll();
                if (su > 0)
                    deque.addLast(su);
                else
                    deque.addFirst(su);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i : deque) {
            result.append(i + "\n");
        }

        System.out.print(result);
    }

    private static void insertQueue(Map<Integer, Queue<Integer>> map, int div, int input) {
        int abs = Math.abs(input);
        int s = (abs/div)%10;
        if (map.containsKey(s))
            map.get(s).add(input);
        else {
            // 이전에 없었다면 생성해서 넣는다.
            Queue<Integer> queue = new LinkedList<>();
            queue.add(input);
            map.put(s, queue);
        }
    }
//
//    public static void function(int div, Queue<Integer>[] queues) {
//        // 나눌 값이 max 보다 크면 그만
//        if (max < div) {
//            // 최종 값 출력
//            StringBuffer str = new StringBuffer();
//            for (Queue<Integer> q : queues) {
//                while (!q.isEmpty()) { // queue안에 있는거 다 꺼냄
//                    str.append(q.poll() + "\n");
//                }
//            }
//            System.out.println(str);
//            return;
//        }
//
//        // 무식하게 일단 10번 리셋
//        Queue<Integer>[] newQueues = new Queue[20];
//        for (int i=0; i<20; i++) {
//            newQueues[i] = new LinkedList<>();
//        }
//
//        //
//
//        // 0 ~ 19에 담겨있던 애들 꺼내서 다시 담기
//        for (Queue<Integer> q : queues) {
//            while (!q.isEmpty()) { // queue안에 있는거 다 꺼냄
//                int input = q.poll(); // 입력 값 꺼내서
//                int abs = Math.abs(input); // 절대값 구함
//                int s = (abs / div) % 10; // 그 다음 queue에 들어갈 위치
//                newQueues[input < 0 ? 9 - s : 10 + s].add(input); // 그 다음 큐에 넣는다.
//            }
//        }
//        function(div * 10, newQueues);
//    }

/*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 몇 개인지 입력 받아서 그 크기만큼 배열 생성
        int N = sc.nextInt();

        // 무식하게 일단 10번 리셋
        Queue<Integer>[] newQueues = new Queue[20];
        for (int i=0; i<20; i++) {
            newQueues[i] = new LinkedList<>();
        }

        // N 만큼 돌면서 입력 받기
        for (int i=0; i<N; i++) {
            // max 찾기
            int input = sc.nextInt();
            int abs = Math.abs(input);
            max = max < abs ? abs : max;

            // 1의 자리 구한 후
            // -9 ~ -0 ~ 0 ~ 9 순서로 넣어줌
            int s = abs % 10;
//            newQueues[input > 0 ? s : 19 - s].add(input);
            newQueues[input < 0 ? 9 - s : 10 + s].add(input);
        }

        function(10, newQueues);
    }
    */
}
