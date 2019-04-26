package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcher wf = new WikiFetcher();

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     *
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     *
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        testConjecture(destination, source, 10);
    }

    /**
     * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
     *
     * @param destination
     * @param source
     * @throws IOException
     */

    public static void testConjecture(String destination, String source, int limit) throws IOException {
        // TODO: FILL THIS IN!
        String url = source;

        // 일단 제한 만큼만 돈다.
        for (int i=0; i<limit; i++) {

            System.out.println("fetching " + url);
            // 1. wiki url 가져와 다운로드하고 파싱함
            // URL을 받아 페이지 본문 DOM 객체 담은 elements 컬렉션 반환
            Elements paragraphs = wf.fetchWikipedia(url);

            // 2. DOM 탐색 - 유효한 첫번째 링크 찾는다.
            String firstLink = findValidFirstLink(paragraphs);

            // 3. 페이지 링크가 없거나 본 페이지라면 실패 표시 - 종료
            if (firstLink == null || visited.contains(firstLink)) {
                System.out.println("fail");
                return;
            }

            // 4. 철학 페이지와 일치할 경우 성공 - 종료
            if (firstLink.equals(destination)) {
                System.out.println("success");
                return;
            }

            // 5. 그렇지 않으면 1로 돌아감
            visited.add(firstLink);
            url = firstLink;
        }

    }

    // 본문 DOM 탐색 - 유효한 첫번째 링크 찾는다.
    private static String findValidFirstLink(Elements paragraphs) {

        /**
         * 모두 구현해보면 참 좋겠지만,
         * 시간이 부족하기도 하고 실력이 부족한 탓에
         * 제공해준 WikiParser을 이용하도록 한다.
         */

        /**
         * 유효한 링크 정의
         // 1. 사이드바 또는 박스아웃이 아닌 본문이어야 한다. => 이미 wf.fetchWikipedia를 통해 본문만 가져옴
         // 2. 이탤릭체나 괄호 안에 없어야 함
         // 3. 외부 링크, 현재 페이지에 대한 링크, 레드 링크는 건너 뜀
         // 4. 텍스트 대문자로 시작시 건너 뜀
         */

        WikiParser wp = new WikiParser(paragraphs);
        Element element = wp.findFirstLink();

        if (element != null) {
            return element.attr("abs:href");
        }

        return null;
    }
}
