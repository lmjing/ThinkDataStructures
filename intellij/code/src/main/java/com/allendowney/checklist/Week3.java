package com.allendowney.checklist;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Week3 {

    static class Stack {
        private Object[] stack;
        private int size;

        public Stack() {
            stack = new Object[2];
            size = 0;
        }

        public void push(Object obj) {
            // 1. size 넘었을 경우 리사이징 시켜준다.
            if (size >= stack.length) {
                Object[] newStack = (Object[]) new Object[size * 2];
                System.arraycopy(stack, 0, newStack, 0, size);
                stack = newStack;
            }

            // 2. 마지막 위치에 엘리먼트 추가
            stack[size] = obj;
            size++;
        }

        public Object peek() {
            if (size == 0)
                throw new EmptyStackException();
            else {
                return stack[size - 1];
            }
        }

        public Object pop() {
            Object obj = peek();
            size--;
            stack[size] = null;
            return obj;
        }

        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        stackTest();
    }

    private static void stackTest() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push("2");
        stack.push("ㄱ");
        System.out.println(stack.size()); // 3
        System.out.println(stack.peek()); // "ㄱ"
        System.out.println(stack.pop()); // "ㄱ"
        System.out.println(stack.pop()); // "2"
        System.out.println(stack.pop()); // 1
//        stack.pop(); // error
    }
}
