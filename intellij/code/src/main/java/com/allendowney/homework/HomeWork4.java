package com.allendowney.homework;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Scanner;

public class HomeWork4 {

    /**
     * 최대 개수 10000
     * 쉬운 계산을 위해 0번째 인덱스는 비워둔다.
     *
     * TODO : 배열로 풀었으나 런타임 에러 뜸!
     * 그 이유는 내가 만든 tree 배열의 개수가 잘못됨
     * 바보같이 input 개수 만큼 선언해놨으나,
     * 실제로는 한 노드당 두개의 자식을 가질 수 있으므로 2^1000 - 1이 되어야 함
     * 이는 너무 큰 숫자로 애초에 선언할 수 없는 사이즈임
     * ==> 따라서 구조체로 만들기!
     * 참고 : https://www.acmicpc.net/board/view/26171
     */
    static int[] tree = new int[10001];
    static StringBuilder result = new StringBuilder();

    static class Tree {
        Node root;

        public void addNode(int v) {
            // root일 때
            if (root == null) {
                root = new Node(v);
                return;
            }

            // root부터 차례대로 비교하며 위치에 삽입
            Node cur = root;
            Node parent = null;
            int d = -1;
            // 찾는 위치가 비었을 때 멈춤
            while (cur != null) {
                parent = cur;
                // 삽입될 값이 비교노드 값 보다 작거나 같다 -> 오른쪽으로 이동
                if (cur.value <= v) {
                    cur = cur.rightChild;
                    d = 1;
                }else { // 크다 -> 왼쪽으로 이동
                    cur = cur.leftChild;
                    d = 0;
                }
            }

            if (d > 0)
                parent.rightChild = new Node(v);
            else
                parent.leftChild = new Node(v);
        }

        class Node {
            int value;
            Node leftChild;
            Node rightChild;

            public Node(int v) {
                this.value = v;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        Tree tree = new Tree();
        /**
         * 백준 Test코드
         */
//        while(sc.hasNextLine()){
//            tree.addNode(Integer.parseInt(sc.nextLine()));
////            addTree(Integer.parseInt(sc.nextLine()));
//        }

        /**
         * 개인 Test코드
         */
        // 들어오는 순서대로 처리한다.
        while (true) {
            String input = br.readLine();
            // TODO : 입력 받는걸 못하겠어서... 걍 엔터 한번 더 누르면 그만 입력 받게 함
            if (input == null || input.equals(""))
                break;

            // 트리를 만들기 위해 데이터를 넣어준다.
            tree.addNode(Integer.parseInt(input));
//            addTree(Integer.parseInt(input));
        }

        // 후위 순회로 결과 출력
//        makeResult();
        postOrder(tree.root);
        // 마지막 '\n' 삭제
        System.out.println(result.delete(result.length()-1, result.length()));
    }

//    private static void makeResult() {
//        postOrder(1);
//    }

//    private static void postOrder(int index) {
//        // 가르키는 노드가 없을 때 리턴
//        if (tree[index] == 0)
//            return;
//
//        // 왼쪽부터 호출 => 자신의 * 2
//        postOrder(index * 2);
//        // 오른쪽 호출 => 자신 * 2 + 1
//        postOrder(index * 2 + 1);
//        // 자신 저장
//        result.append(tree[index] + "\n");
//    }

    private static void postOrder(Tree.Node node) {
        // 가르키는 노드가 없을 때 리턴
        if (node == null)
            return;

        // 왼쪽부터 호출 => 자신의 * 2
        postOrder(node.leftChild);
        // 오른쪽 호출 => 자신 * 2 + 1
        postOrder(node.rightChild);
        // 자신 저장
        result.append(node.value + "\n");
    }

    private static void addTree(int value) {
        // root부터 차례대로 비교하며 위치에 삽입
        int cur = 1;
        // 찾는 위치가 비었을 때 멈춤
        while (tree[cur] > 0) {
            // 삽입될 값이 비교노드 값 보다 작거나 같다 -> 오른쪽으로 이동
            if (tree[cur] <= value) {
                cur = cur * 2 + 1;
            }else { // 크다 -> 왼쪽으로 이동
                cur = cur * 2;
            }
        }
        tree[cur] = value;
    }

}
