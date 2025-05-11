package com.rookies3.MyspringbootLab.service;

import com.rookies3.myspringbootlab.dto.BookCreateRequest;
import com.rookies3.myspringbootlab.dto.BookResponse;
import com.rookies3.myspringbootlab.dto.BookUpdateRequest;
import com.rookies3.myspringbootlab.entity.Book;
import com.rookies3.myspringbootlab.exception.BusinessException;
import com.rookies3.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("해당 도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        return BookResponse.from(book);
    }

    public BookResponse getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException("해당 ISBN의 도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));
        return BookResponse.from(book);
    }

    public List<BookResponse> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        Book saved = bookRepository.save(request.toEntity());
        return BookResponse.from(saved);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("수정할 도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());
        book.setPublishDate(request.getPublishDate());

        return BookResponse.from(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BusinessException("삭제할 도서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }
}
