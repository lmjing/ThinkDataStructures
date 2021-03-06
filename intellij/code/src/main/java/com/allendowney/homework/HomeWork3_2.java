package com.allendowney.homework;

import java.util.*;

public class HomeWork3_2 {

    static int N, M;
    static Map<Integer, Integer> inputMap = new HashMap();
    static int[] input;
    public static long count = 0;
    public static String result = "";

    public static void main(String[] args) {
        // 기존 코드 -> 테스트 위해서 다 모듈화함
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//
//        for (int i=0; i<N; i++) {
//            int v = sc.nextInt();
//            inputMap.put(v, inputMap.get(v) == null ? 1 : inputMap.get(v) + 1); // 중복 제거하고 해당 값이 몇개 있는지 카운팅 함
//        }

        // 예제로 주신 샘플코드 모듈화 테스트 버전
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int[] input = new int[n];
//        for (int i=0; i<n; i++)
//            input[i] = sc.nextInt();
//
//        makeCountMap(n, m, input);
//        setAllCount();
//
//        solution(0, new int[M]);

        int n = 20;
        int m = 10;
        int[] input = new int[n];
        Random random = new Random();
        StringBuilder inputStr = new StringBuilder();
        for (int i=0; i<n; i++) {
            input[i] = random.nextInt(10);
            inputStr.append(input[i] + " ");
        }
        System.out.println("inputs : " + inputStr);

        makeCountMap(n, m, input);
        solution(0, new int[m]);
//        System.out.println(count);
//        System.out.println(result);
    }

    public static void setAllCount() {
        // 중복 허용
        count = 1;
        for (int i=0; i<M; i++)
            count *= inputMap.size();
        System.out.println(count);
    }

    public static void makeCountMap(int n, int m, int[] input) {
        N = n;
        M = m;

        for (int i=0; i<N; i++) {
            int v =input[i];
            inputMap.put(v, inputMap.getOrDefault(v, 0) + 1); // 중복 제거하고 해당 값이 몇개 있는지 카운팅 함
        }
    }

    /**
     *
     * @param i : 총 M자리수 중에서 i번째 자리를 채울 차례이다.
     * @param output
     */
    static void solution(int i, int[] output) {
        if (i >= M) {
            StringBuilder str = new StringBuilder();
            for (int su : output) {
                str.append(su + " ");
            }
            result += str + "\n";
            // 큰 수들 돌렸을 때 잘 구하고 있는지 확인하기 위함
            System.out.println(str);
            count++;
            // 끝낸다.
            return;
        }

        // map의 key가 자리에 들어갈 자격이 되는 아이들이다.
        for (int key : inputMap.keySet()) {
            output[i] = key;
            solution(i+1, output);
        }
    }
}
