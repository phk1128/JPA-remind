package com.example.jparemind1.fixture;

import java.util.List;

import com.example.jparemind1.domain1.Book;
import com.example.jparemind1.domain1.BookStore;

public class BookFixture {

	public static List<Book> createBook(BookStore bookStore) {
		return List.of(
			Book.builder()
				.title("테스트북1")
				.author("테스트저자1")
				.bookStore(bookStore)
				.build()
			,Book.builder()
				.title("테스트북2")
				.author("테스트저자2")
				.bookStore(bookStore)
				.build()
			,Book.builder()
				.title("테스트북3")
				.author("테스트저자3")
				.bookStore(bookStore)
				.build()
			,Book.builder()
				.title("테스트북4")
				.author("테스트저자4")
				.bookStore(bookStore)
				.build()
		);
	}
}
