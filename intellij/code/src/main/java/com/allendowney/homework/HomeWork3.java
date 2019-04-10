package com.allendowney.homework;

import java.util.Scanner;

public class HomeWork3 {

    static int N;
    static int M;
    static int[] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        input = new int[N];
        for (int i=0; i<N; i++)
            input[i] = sc.nextInt();

        // 조합 (중복 허용 X)
//        solution1(new int[N], 0, 0);
    }

    // 조합 (재귀함수로 - 1,0,1)
    static void solution1(int[] check, int idx, int saveCount) {
        // 모든 경우는 들어가고 or 안들어가고 둘 중 하나임
        if (saveCount == M) {
            // M개가 되었으므로 프린트 해준다.
            StringBuilder str = new StringBuilder();
            for (int i=0; i<idx; i++) {
                if (check[i] == 1)
                    str.append(input[i]);
            }
            System.out.println(str); // 조합을 출력
            return;
        }

        if (idx >= N) // 다돌았으면 끝
            return;

        // 아직 M개가 되지 않은 경우

        // 현재 idx의 값을 포함하는 경우
        check[idx] = 1;
        solution1(check, idx + 1, saveCount + 1);

        // 현재 idx의 값을 포함하지 않는 경우
        check[idx] = 0;
        solution1(check, idx + 1, saveCount);
    }

    // 조합 (재귀함수로 - 1,0,1) -> 원 순열 사용
    static void solution2(int[] check, int idx, int saveCount) {
        // 모든 경우는 들어가고 or 안들어가고 둘 중 하나임
        if (saveCount == M) {
            // M개가 되었으므로 프린트 해준다.
            StringBuilder str = new StringBuilder();
            for (int i=0; i<idx; i++) {
                if (check[i] == 1)
                    str.append(input[i]);
            }
            System.out.println(str); // 조합을 출력
            return;
        }

        if (idx >= N) // 다돌았으면 끝
            return;

        // 아직 M개가 되지 않은 경우

        // 현재 idx의 값을 포함하는 경우
        check[idx] = 1;
        solution1(check, idx + 1, saveCount + 1);

        // 현재 idx의 값을 포함하지 않는 경우
        check[idx] = 0;
        solution1(check, idx + 1, saveCount);
    }

    // 순열 - array는 M개
//    static void solution3(int[] array, int idx, int count, StringBuilder result, int s) {
//        if (idx >= M)
//            idx = 0; // 끝났으면 처음을 다시 가르킨다.
//
//        // 모든 경우는 들어가고 or 안들어가고 둘 중 하나임
//        if (count== M) {
//            // 모두 담았으므로 결과 프린트
//            System.out.println(result);
//            return;
//        }
//
//        // 현재 idx의 값을 담는 경우
//        solution3(array, idx + 1, count + 1, new StringBuilder(result.append(array[idx]) + " "));
//
//        // 현재 idx의 값을 포함하지 않는 경우
//        solution3(array, idx + 1, count, result);
//    }
}
