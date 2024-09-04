package com.example.jparemind1.domain1;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_store_id")
	private BookStore bookStore;

	@Builder
	public Book(final Long id, final String title, final String author, final BookStore bookStore) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookStore = bookStore;
	}

}
