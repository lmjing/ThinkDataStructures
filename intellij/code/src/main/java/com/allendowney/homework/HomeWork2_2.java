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

            // 마지막 노드의 부모부터 루트까지 순회하며 heapify
            for (int i=N/2; i>0; i--) {
                heapify(i);
            }
        }

        /**
         * i 기준으로 자식 값과의 비교를 통해 힙 조건 맞춤
         * 힙 조건 : 부모 < 자식 && 왼쪽 자식 < 오른쪽 자식
         * 참고 : https://ratsgo.github.io/data%20structure&algorithm/2017/09/27/heapsort/
         * @param i : 부모 기준값
         */
        private void heapify(int i) {
            int lidx = i * 2;
            int ridx = i * 2 + 1;

            // 2. 바뀐 후, 부모-오른쪽 비교
            if (ridx <= N && array[ridx] < array[i]) {
                swap(ridx, i);
                heapify(lidx); // 오른쪽 자식 바뀌었으므로 heapify 실행
            }
            // 1. 왼쪽 노드와 비교
            if (lidx <= N && array[lidx] < array[i]) {
                swap(lidx, i);
                heapify(lidx); // 왼쪽 자식 바뀌었으므로 heapify 실행
            }
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public void printResult() {
            StringBuilder str = new StringBuilder();
            for (int i : array) {
                str.append(i + "\n");
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
//
//    static class HeapSort {
//        int[] array;
//        int N;
//        int size = 0;
//
//        public HeapSort(int N) {
//            this.N = N;
//            this.array = new int[N];
//        }
//
//        public void insert(int su) {
//            // 일단 마지막에 넣는다.
//            // size == 새로운 노드의 위치
//            array[size] = su;
//            // 최소 힙에 맞게 정렬한다.
//            insertSort(size);
//            size++;
//        }
//
//        private void insertSort(int i) {
//            // 부모 노드와 비교한다.
//            while (i > 0) {
//                int rootIdx = getRootIdx(i);
//                // 자식이 부모보다 크면 멈춤
//                if (array[i] > array[rootIdx])
//                    break;
//
//                // 자식이 더 작으면 변경
//                swap(rootIdx, i);
//                // i = 부모노드로 셋팅 -> 반복
//                i = rootIdx;
//            }
//        }
//
//        private void swap(int i, int j) {
//            int temp = array[i];
//            array[i] = array[j];
//            array[j] = temp;
//        }
//
//        private int getRootIdx(int i) {
//            if (i == 0) return -1; // 부모 노드면 -1을 리턴한다.
//            return (i - 1) / 2;
//        }
//
//        public void printResult() {
//            StringBuilder result = new StringBuilder();
//
//            // 힙이 빌 때 까지 결과 값을 출력한다.
//            while (size > 0) {
//                result.append(String.valueOf(delete()) + "\n");
//            }
//            System.out.println(result);
//        }
//
//        private int delete() {
//            int min = array[0]; // 최소값은 루트 값
//            size--; // 하나 빠져나갔으므로 사이즈 개수 줄임
//            swap(size, 0); // 마지막 값을 루트로 옮김
//            array[size] = 0; // 삭제 해준다.
//            deleteSort(); // 재정렬
//
//            return min;
//        }
//
//        private void deleteSort() {
//            // 부모 노드 = 삽입된 노드 -> 자식들과 비교
//            // 더 작은 값이 있을 경우 자신과 위치 변경
//
//            int i = 0; // 루트부터 시작
//
//            while (i < size) { // 인덱스가 배열 안의 값을 가르킬 때 까지
//                 int left = i * 2 + 1;
//                 int right = i * 2 + 2;
//
//                int minIdx = -1;
//                 if (left >= size) // 왼쪽 자식이 범위를 벗어나면
//                     break;
//                 else if (right >= size) // 왼쪽 자식은 있는데 오른쪽 자식이 없을 경우
//                     minIdx = left;
//                 else // 둘다 있으면 왼쪽과 오른쪽 자식 중 더 작은 값의 주소를 저장
//                     minIdx = array[left] < array[right] ? left : right;
//
//                // 부모가 자식보다 작으면 멈춤
//                if (array[i] < array[minIdx])
//                    break;
//
//                // 부모가 더 크면 변경
//                swap(minIdx, i);
//                // i = 자식노드로 셋팅 -> 반복
//                i = minIdx;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        // 몇 개인지 입력 받아서 HeapSort 객체 생성
//        int N = sc.nextInt();
//        HeapSort hs = new HeapSort(N);
//
//        // 전체 입력 값 배열에 저장
//        for (int i=0; i<N; i++) {
//            hs.insert(sc.nextInt());
//        }
//
//        hs.printResult();
//    }

}
