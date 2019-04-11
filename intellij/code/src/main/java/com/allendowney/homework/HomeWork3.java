package com.allendowney.homework;

import java.util.*;

public class HomeWork3 {

    static int N, M;
    static Map<Integer, Integer> inputMap = new HashMap();
    static int[] input;
    public static int count = 0;
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

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] input = new int[n];
        for (int i=0; i<n; i++)
            input[i] = sc.nextInt();

        makeCountMap(n, m, input);
        solution(0, new int[M]);
    }

    public static void makeCountMap(int n, int m, int[] input) {
        N = n;
        M = m;

        for (int i=0; i<N; i++) {
            int v =input[i];
            inputMap.put(v, inputMap.get(v) == null ? 1 : inputMap.get(v) + 1); // 중복 제거하고 해당 값이 몇개 있는지 카운팅 함
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
            count++;
            result += str + "\n";
//            System.out.println(str);
            // 끝낸다.
            return;
        }

        // TEST
        int[] temp = new int[inputMap.keySet().size()];
        int k=0;
        for (int key : inputMap.keySet()) {
            temp[k++] = key;
        }

        // 순회중 데이터 조작이 일어나서 ConcurrentModificationException가 자꾸 일어남
        // map의 key가 자리에 들어갈 자격이 되는 아이들이다.
        for (int key : temp) {
            // output에 key값을 셋팅, map count도 줄여줌
            removeKeyFromMap(key);
            output[i] = key;
            // 준비가 끝났으니 재귀함수 호출
            solution(i + 1, output);
            // 다른 케이스를 위해 원상복귀
            recoveryMap(key);
        }
    }

    private static void recoveryMap(int key) {
        inputMap.put(key, inputMap.getOrDefault(key, 0) + 1);
    }

    private static void removeKeyFromMap(int key) {
        // key는 무조건 map에 있다.
        if (inputMap.get(key) == 1) {
            inputMap.remove(key); // 모두 사용했으므로 없애줌
        }else {
            inputMap.put(key, inputMap.get(key) - 1); // count 개수를 1 낮춰준다.
        }
    }
}
