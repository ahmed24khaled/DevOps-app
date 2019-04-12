package tn.devops.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.devops.demo.entities.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {
	public Book findByTitle(String title);
}

