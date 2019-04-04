package com.allendowney.homework;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 기수정렬
 */

public class HomeWork2 {

    static int max = 0; // 자리 수 만큼 돌기 위함

    public static void function(int div, Queue<Integer>[] queues) {
        // 나눌 값이 max 보다 크면 그만
        if (max < div) {
            // 최종 값 출력
            StringBuffer str = new StringBuffer();
            for (Queue<Integer> q : queues) {
                while (!q.isEmpty()) { // queue안에 있는거 다 꺼냄
                    str.append(q.poll() + "\n");
                }
            }
            System.out.println(str);
            return;
        }

        // 무식하게 일단 10번 리셋
        Queue<Integer>[] newQueues = new Queue[20];
        for (int i=0; i<20; i++) {
            newQueues[i] = new LinkedList<>();
        }

        //

        // 0 ~ 19에 담겨있던 애들 꺼내서 다시 담기
        for (Queue<Integer> q : queues) {
            while (!q.isEmpty()) { // queue안에 있는거 다 꺼냄
                int input = q.poll(); // 입력 값 꺼내서
                int abs = Math.abs(input); // 절대값 구함
                int s = (abs / div) % 10; // 그 다음 queue에 들어갈 위치
                newQueues[input < 0 ? 9 - s : 10 + s].add(input); // 그 다음 큐에 넣는다.
            }
        }
        function(div * 10, newQueues);
    }


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
}
