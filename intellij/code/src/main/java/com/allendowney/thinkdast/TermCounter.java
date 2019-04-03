package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


/**
 * Encapsulates a map from search term to frequency (count).
 *
 * @author downey
 *
 */
public class TermCounter {

	private Map<String, Integer> map; // 검색어와 등장횟수 맵핑
	private String label; // 검색어의 출처가 되는 문자 식별 = URL

	public TermCounter(String label) {
		this.label = label;
		this.map = new HashMap<String, Integer>();
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Returns the total of all counts.
	 *
	 * @return
	 */
	public int size() {
		// TODO: FILL THIS IN!
		return 0;
	}

	/**
	 * Takes a collection of Elements and counts their words.
	 * 웹 페이지 인덱싱 보조 메서드
	 *
	 * @param paragraphs : josup 라이브러리의 Element 객체 컬렉션
	 */
	public void processElements(Elements paragraphs) {
		for (Node node: paragraphs) { // 컬렉션 반복문 실행
			processTree(node); // Node 객체에 대해 메서드 호출
		}
	}

	/**
	 * Finds TextNodes in a DOM tree and counts their words.
	 * 웹 페이지 인덱싱 보조 메서드
	 *
	 * @param root : DOM 트리의 루트
	 */
	public void processTree(Node root) {
		// NOTE: we could use select to find the TextNodes, but since
		// we already have a tree iterator, let's use it.
		for (Node node: new WikiNodeIterable(root)) {
			if (node instanceof TextNode) { // TextNode만 찾음
				processText(((TextNode) node).text());
			}
		}
	}

	/**
	 * Splits `text` into words and counts them.
	 * 웹 페이지 인덱싱 보조 메서드
	 *
	 * @param text  The text to process.
	 */
	public void processText(String text) {
		// replace punctuation with spaces, convert to lower case, and split on whitespace
		String[] array = text.replaceAll("\\pP", " "). // 구두점 공백 대체
				              toLowerCase(). // 소문자로 변환
				              split("\\s+"); // 텍스트를 단어로 나눔
		
		for (int i=0; i<array.length; i++) {
			String term = array[i];
			incrementTermCount(term); // 단어 하나에 대해 카운트 실행
		}
	}

	/**
	 * Increments the counter associated with `term`.
	 *
	 * @param term
	 */
	public void incrementTermCount(String term) {
		// System.out.println(term);
		put(term, get(term) + 1);
	}

	/**
	 * Adds a term to the map with a given count.
	 *
	 * @param term
	 * @param count
	 */
	public void put(String term, int count) {
		map.put(term, count);
	}

	/**
	 * Returns the count associated with this term, or 0 if it is unseen.
	 * incrementTermCount 구현을 쉽게 하기 위하여 없을 경우 0을 리턴함
	 *
	 * @param term
	 * @return
	 */
	public Integer get(String term) {
		Integer count = map.get(term);
		return count == null ? 0 : count;
	}

	/**
	 * Returns the set of terms that have been counted.
	 *
	 * @return
	 */
	public Set<String> keySet() {
		return map.keySet();
	}

	/**
	 * Print the terms and their counts in arbitrary order.
	 */
	public void printCounts() {
		for (String key: keySet()) {
			Integer count = get(key);
			System.out.println(key + ", " + count);
		}
		System.out.println("Total of all counts = " + size());
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.fetchWikipedia(url);

		TermCounter counter = new TermCounter(url.toString());
		counter.processElements(paragraphs);
		counter.printCounts();
	}
}
