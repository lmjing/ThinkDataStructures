package com.allendowney.homework;

import java.util.Scanner;

/*
합병정렬 정리해놓았던 거 : https://www.evernote.com/l/AV_K1ozCVlZCRJud_VeGfpvjMEeqpKMgAgA/
 */
public class HomeWork1 {

    static int[] inputs;

    private static int[] function(int f, int e) {
        // 1. 예외처리 - 더이상 나누지 않아도 됨
        if (f == e) {// front가 end 보다 같거나 클 때 호출
            return new int[] {inputs[f]}; // 하나만 들어있는 배열을 리턴해줌
        }
        // 1. 일단 나눔
        // 1) 중간 지점 찾기
        int half = (e + f) / 2;

        // 2) 앞 ~ 중간 지점 / 중간지점 + 1 ~ 맨 끝 으로 각각 함수 호출
        int[] leftArray = function(f, half);
        int[] rightArray = function(half + 1, e);

        // 나누었으면 합쳐주자!
        int N = e-f+1;
        int[] newArray = new int[N];
        int i = 0; // 새로운 배열의 포인터

        // 1) 왼쪽 / 오른쪽 앞부터 하나씩 비교해서 저장해주자! -> 포인터 생성
        int p1 = 0, p2 = 0;
        while (i < N) {
            if (p1 == leftArray.length) { // 왼쪽 다 끝난 경우
                /* NO - 시간초과
                // 오른쪽에 남은거 다 옮겨 준다.
                System.arraycopy(rightArray, p2, newArray, i, N-i);
                return newArray;
                */
                newArray[i++] = rightArray[p2++];
            } else if (p2 == rightArray.length) { // 오른쪽 다 끝난 경우
                /* NO - 시간초과
                // 왼쪽에 남은거 다 옮겨 준다.
                System.arraycopy(leftArray, p1, newArray, i, N-i);
                return newArray;
                */
                newArray[i++] = leftArray[p1++];
            } else {
                // 둘다 아직 안 끝난 경우
                newArray[i++] = leftArray[p1] < rightArray[p2] ? leftArray[p1++] : rightArray[p2++];
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 몇 개인지 입력 받아서 그 크기만큼 배열 생성
        int N = sc.nextInt();
        inputs = new int[N];

        // 전체 입력 값 배열에 저장
        for (int i=0; i<N; i++) {
            inputs[i] = sc.nextInt();
        }

        // 시작과 끝 지정해서 함수 호출 -> for문
        for (int su : function(0, N-1)) {
            System.out.println(su);
        }
//        function(0, N-1);
    }
}
