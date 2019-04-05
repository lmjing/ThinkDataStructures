package com.allendowney.homework;

import java.util.Scanner;

/**
 * 2주차
 * 힙 소트
 * TODO : 삽입을 다 받은 후 정렬 일괄적으로 처리하기
 */
public class HomeWork2_2 {

    static class HeapSort {
        int[] array;
        int N;
        int idx = 1;

        public HeapSort(int N) {
            this.N = N;
            this.array = new int[N + 1];
        }

        public void buildHeap(Scanner sc) {
            for (int i=0; i<N; i++)
                array[idx++] = sc.nextInt();

            // 마지막 노드의 부모(리프노드가 아닌 것들 제외)부터 루트까지 순회하며 heapify
            for (int i=N/2; i>0; i--) {
                heapify(i, N);
            }
        }

        /**
         * i 기준으로 자식 값과의 비교를 통해 힙 조건 맞춤
         * 힙 조건 : 부모 < 자식 && 왼쪽 자식 < 오른쪽 자식
         * 참고 : https://ratsgo.github.io/data%20structure&algorithm/2017/09/27/heapsort/
         * @param i : 부모 기준값
         */
        private void heapify(int i, int size) {
            int lidx = i * 2;
            int ridx = i * 2 + 1;

            // 자식들 간의 비교 -> 더 작은 값과 변경
            // 더 작은 값과 변경할 경우 왼쪽(더 작음)도 만족함
            int ci = i;
            if (lidx <= size && array[lidx] < array[ci])
                ci = lidx;
            if (ridx <= size && array[ridx] < array[ci])
                ci = ridx;
            if (ci == i) return;
            swap(ci, i);
            heapify(ci, size);
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public void printResult() {
            StringBuilder str = new StringBuilder();
            for (int i=1; i<N; i++) {
                str.append(array[i] + "\n");
                // 맨마지막 값을 앞으로 가져온다.
                swap(--idx, 1);
                heapify(1, idx);
            }
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 몇 개인지 입력 받아서 HeapSort 객체 생성
        int N = sc.nextInt();
        HeapSort hs = new HeapSort(N);
        hs.buildHeap(sc); // 힙을 구성한다.

        hs.printResult();
    }
}
