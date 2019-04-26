package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import redis.clients.jedis.Jedis;


public class WikiCrawler {
	// keeps track of where we started
	@SuppressWarnings("unused")
	private final String source;

	// the index where the results go
	private JedisIndex index;

	// queue of URLs to be indexed
	private Queue<String> queue = new LinkedList<String>();

	// fetcher used to get pages from Wikipedia
	final static WikiFetcher wf = new WikiFetcher();

	/**
	 * Constructor.
	 *
	 * @param source
	 * @param index
	 */
	public WikiCrawler(String source, JedisIndex index) {
		this.source = source;
		this.index = index;
		queue.offer(source);
	}

	/**
	 * Returns the number of URLs in the queue.
	 *
	 * @return
	 */
	public int queueSize() {
		return queue.size();
	}

	/**
	 * Gets a URL from the queue and indexes it.
	 * @param testing
	 *
	 * @return URL of page indexed.
	 * @throws IOException
	 */
	public String crawl(boolean testing) throws IOException {
		// TODO: FILL THIS IN!
		// 예외처리 못함
		if (queue.isEmpty()) return null;

		// FIFO 순서로 큐에서 URL을 선택하고 제거(공통)
		String url = queue.poll();

		// 이미 인덱싱 되어 있는 경우 null을 반환 (not testing만)
		if (!testing && index.isIndexed(url)) return null;

		// 메서드를 호출하여 페이지의 내용을 읽음
		Elements paragraphs = wf.fetchWikipedia(url);

		// 원래 코드 - 내부 코드에서 에러 발생(readWikipedia)
//		Elements paragraphs;
//		if (testing) {
//			paragraphs = wf.readWikipedia(url); // 파일에서 읽음
//		}else {
//			paragraphs = wf.fetchWikipedia(url); // 웹에서 읽음
//		}

		// 각 페이지의 인덱싱 여부와 관계없이 페이지를 인덱싱
		index.indexPage(url, paragraphs);

		// 페이지 내 모든 내부 링크를 찾아 순서대로 큐에 저장
		queueInternalLinks(paragraphs);
		return url;
	}

	/**
	 * Parses paragraphs and adds internal links to the queue.
	 * 
	 * @param paragraphs
	 */
	// NOTE: absence of access level modifier means package-level
	void queueInternalLinks(Elements paragraphs) {
        // TODO: FILL THIS IN!
		for (Element paragraph : paragraphs) {
			queueInternalLinks(paragraph);
		}
	}

	// 보고 짬
	private void queueInternalLinks(Element element) {
		Elements relURLs = element.select("a[href]");

		for (Element el : relURLs) {
			String relURL = el.attr("href");

			// link가 내부인지 확인하기 위한 용도 - 특징 '/wiki/'로 시작
			if (relURL.startsWith("/wiki/")) {
				String absURL = el.attr("abs:href");
				queue.offer(absURL);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// make a WikiCrawler
		Jedis jedis = JedisMaker.make();
		JedisIndex index = new JedisIndex(jedis);
		String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		WikiCrawler wc = new WikiCrawler(source, index);
		
		// for testing purposes, load up the queue
		Elements paragraphs = wf.fetchWikipedia(source);
		wc.queueInternalLinks(paragraphs);

		// loop until we index a new page
		String res;
		do {
			res = wc.crawl(false);

            // REMOVE THIS BREAK STATEMENT WHEN crawl() IS WORKING
            break;
		} while (res == null);
		
		Map<String, Integer> map = index.getCounts("the");
		for (Entry<String, Integer> entry: map.entrySet()) {
			System.out.println(entry);
		}
	}
}
