package com.allendowney.checklist;

import java.lang.reflect.Array;
import java.util.EmptyStackException;
import java.util.Stack;

public class Week3 {
    static class Stack {
        private int size; // stack elements 사이즈
        private Object[] stack;

        public Stack() {
            stack = new Object[2]; // 처음은 두개만
            size = 0;
        }

        void push(Object obj) {
            // 1. stack.length 넘었을 경우 리사이징 시켜준다.
            if (size >= stack.length) {
                Object[] newStack = (Object[]) new Object[stack.length * 2];
                System.arraycopy(stack, 0, newStack, 0, size);
                stack = newStack;
            }

            // 2. 마지막 오브젝트 다음 빈 위치에 엘리먼트 추가
            stack[size] = obj;
            size++;
        }

        Object pop() {
            Object object = peek();
            if (object != null) {
                size--;
                stack[size] = null;
            }
            return object;
        }

        boolean isEmpty() {
            return size == 0 ? true : false;
        }

        Object peek() {
            if (size == 0)
                throw new EmptyStackException();
            else {
                return stack[size - 1];
            }
        }

        int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        // 예외처리 확인용
//        java.util.Stack<Integer>  stack = new java.util.Stack<>();

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.size()); // 3
        System.out.println(stack.peek()); // 3
        System.out.println(stack.pop()); // 3
        System.out.println(stack.peek()); // 2
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 1
//        System.out.println(stack.peek()); // error
//        System.out.println(stack.pop()); // error
    }
}
