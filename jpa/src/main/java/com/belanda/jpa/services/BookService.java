package com.belanda.jpa.services;

import com.belanda.jpa.dto.BookRecordDTO;
import com.belanda.jpa.models.ReviewModel;
import com.belanda.jpa.models.bookModel;
import com.belanda.jpa.repository.AuthorRepository;
import com.belanda.jpa.repository.BookRepository;
import com.belanda.jpa.repository.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<bookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public bookModel saveBook(BookRecordDTO bookRecordDTO) {
        bookModel book = new bookModel();
        book.setTitle(bookRecordDTO.title());
        book.setPublisher(publisherRepository.findById(bookRecordDTO.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDTO.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDTO.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);

    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }
}
