package com.allendowney.thinkdast;

import org.jfree.data.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

import com.allendowney.thinkdast.Profiler.Timeable;

public class ProfileListAdd {

    /**
     * @param args
     */
    public static void main(String[] args) {
        profileArrayListAddEnd();
        //profileArrayListAddBeginning();
        //profileLinkedListAddBeginning();
        //profileLinkedListAddEnd();
    }

    /**
     * Characterize the run time of adding to the end of an ArrayList
     */
    /**
     * 결과
     * add 메소드는 O(1)임
     * 여기서 timeMe는 n개를 추가하니깐 선형으로 O(n) = a + bn
    */
    public static void profileArrayListAddEnd() {
        Timeable timeable = new Timeable() {
            List<String> list;

            // 초기 셋팅 - ArrayList 생성
            public void setup(int n) {
                list = new ArrayList<String>();
            }

            // n만큼 string add 작업 실시
            public void timeMe(int n) {
                for (int i=0; i<n; i++) {
                    list.add("a string");
                }
            }
        };
        int startN = 4000; // n은 4000 부터 테스트 시작
        int endMillis = 1000; // 임계치 설정 -> 강제 종료
        runProfiler("ArrayList add end", timeable, startN, endMillis); // 프로파일링 실행
    }

    /**
     * Characterize the run time of adding to the beginning of an ArrayList
     */
    public static void profileArrayListAddBeginning() {
        // TODO: FILL THIS IN!
    }

    /**
     * Characterize the run time of adding to the beginning of a LinkedList
     */
    public static void profileLinkedListAddBeginning() {
        // TODO: FILL THIS IN!
    }

    /**
     * Characterize the run time of adding to the end of a LinkedList
     */
    public static void profileLinkedListAddEnd() {
        // TODO: FILL THIS IN!
    }

    /**
     * Runs the profiles and displays results.
     *
     * @param timeable
     * @param startN
     * @param endMillis
     */
    private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
        Profiler profiler = new Profiler(title, timeable); // 프로파일 정보 셋팅
        XYSeries series = profiler.timingLoop(startN, endMillis); // 시간 별로 실행하며 데이터 셋팅
        profiler.plotResults(series); // 셋팅된 데이터를 기반으로 log-log 스케일로 증가차수에 따른 그래프 그림
    }
}