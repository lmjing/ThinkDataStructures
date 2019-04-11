package com.allendowney.checklist;

import com.allendowney.thinkdast.Profiler;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import org.jfree.data.xy.XYSeries;

import java.util.*;

public class Week3 {

    static class CustomStack {
        private Object[] stack;
        private int size;

        public CustomStack() {
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
//        question1();
//        question2();
//        question3();
        question4();
    }

    public static void profileTokenizerEnd() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            String str;

            // 초기 셋팅 - ArrayList 생성
            public void setup(int n) {
                StringBuilder temp = new StringBuilder();
                for (int i=0; i<n; i++)
                    temp.append("test,");
                str = temp.toString();
            }

            // tokenizer 실시
            public void timeMe(int n) {
                StringTokenizer tokenizer = new StringTokenizer(str, ",");
                while (tokenizer.hasMoreTokens())
                    tokenizer.nextToken();
            }
        };
        int startN = 100; // n은 4000 부터 테스트 시작
        int endMillis = 1000; // 임계치 설정 -> 강제 종료
        runProfiler("Tokenizer end", timeable, startN, endMillis); // 프로파일링 실행
    }

    public static void profileSplitEnd() {
        Profiler.Timeable timeable = new Profiler.Timeable() {
            String str;

            // 초기 셋팅 - ArrayList 생성
            public void setup(int n) {
                StringBuilder temp = new StringBuilder();
                for (int i=0; i<n; i++)
                    temp.append("test,");
                str = temp.toString();
            }

            // split 작업 실시
            public void timeMe(int n) {
                String split[] = str.split(",");
            }
        };
        int startN = 100; // n은 4000 부터 테스트 시작
        int endMillis = 1000; // 임계치 설정 -> 강제 종료
        runProfiler("Split end", timeable, startN, endMillis); // 프로파일링 실행
    }

    private static void runProfiler(String title, Profiler.Timeable timeable, int startN, int endMillis) {
        Profiler profiler = new Profiler(title, timeable); // 프로파일 정보 셋팅
        XYSeries series = profiler.timingLoop(startN, endMillis); // 시간 별로 실행하며 데이터 셋팅
        profiler.plotResults(series); // 셋팅된 데이터를 기반으로 log-log 스케일로 증가차수에 따른 그래프 그림
    }

    private static void question4() {
//        String str = "test,tokenizer,split,,java,";
//        StringTokenizer tokenizer = new StringTokenizer(str, ",");
//        for (int i=0; tokenizer.hasMoreTokens(); i++) {
//            System.out.println(i + "번째 : " + tokenizer.nextToken());
//        }
//
//        String split[] = str.split(",");
//        for (int i=0; i<split.length; i++)
//            System.out.println(i + "번째 : " + split[i]);

        profileSplitEnd();
//        profileTokenizerEnd();
    }

    private static void question3() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator<Integer> itr = list.iterator();
        Iterable<Integer> iterable = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }
        };
    }

    private static void question2() {
        Stack<Integer> stack = new Stack<Integer>();
        Queue<Integer> q = new LinkedList<>();

        LinkedList<Integer> t = new LinkedList<>();
        t.pop();
    }

    private static void question1() {
        CustomStack customStack = new CustomStack();
        customStack.push(1);
        customStack.push("2");
        customStack.push("ㄱ");
        System.out.println(customStack.size()); // 3
        System.out.println(customStack.peek()); // "ㄱ"
        System.out.println(customStack.pop()); // "ㄱ"
        System.out.println(customStack.pop()); // "2"
        System.out.println(customStack.pop()); // 1
//        customStack.pop(); // error
    }
}
