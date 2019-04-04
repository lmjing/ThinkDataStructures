/**
 * 
 */
package com.allendowney.thinkdast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.jsoup.nodes.Node;


/**
 * Performs a depth-first traversal of a jsoup Node.
 * 크롤링을 하기 위해 DOM 트리 구조를 기반으로 다음 노드를 찾아 반환해주도록 구현
 * 이를 통해 [노드를 반복적으로 탐색하는 로직] / [노드를 처리하는 로직]으로 분리 가능
 *
 * @author downey
 *
 */
public class WikiNodeIterable implements Iterable<Node> {

	private Node root;

	/**
	 * Creates an iterable starting with the given Node.
	 *
	 * @param root : 탐색하려는 트리의 루트
	 */
	// 탐색하려는 트리의 루트
	public WikiNodeIterable(Node root) {
	    this.root = root;
	}

	// Iterable 인터페이스를 구현할 때 iterator 구현은 필수다.
	@Override
	public Iterator<Node> iterator() {
		return new WikiNodeIterator(root);
	}

	/**
	 * Inner class that implements the Iterator.
	 *
	 * @author downey
	 *
	 */
	private class WikiNodeIterator implements Iterator<Node> {

		// this stack keeps track of the Nodes waiting to be visited
		Deque<Node> stack;

		/**
		 * Initializes the Iterator with the root Node on the stack.
		 * 생성자는 스택을 초기화하고 그 안에 루트 노드를 Push 함
		 *
		 * @param node
		 */
		public WikiNodeIterator(Node node) {
			stack = new ArrayDeque<Node>();
		    stack.push(root);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Node next() {
			// 스택이 비었는지 확인 없으면 에러 throw
			if (stack.isEmpty()) {
				throw new NoSuchElementException();
			}

			//다음 노드가 있다면 스택에서 꺼낸다.
			Node node = stack.pop();
			//System.out.println(node);

			/*
			reverse 하는 이유
			만약에 root 밑에 자식 노드가 1, 2, 3 순서로 존재한다고 할 때,
			stack의 경우 FILO이므로 해당 순서대로 스택에 넣게 되면
			pop의 순서는 반대가 되어 3, 2, 1 순서로 나오게 되므로
			 */
			List<Node> nodes = new ArrayList<Node>(node.childNodes());
			Collections.reverse(nodes);
			for (Node child: nodes) {
				stack.push(child);
			}
			return node;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
