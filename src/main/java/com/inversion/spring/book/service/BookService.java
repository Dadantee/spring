package com.inversion.spring.book.service;

import com.inversion.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<BookEntity>();

    public BookService(){
        fillStorage();
    }
    public void fillStorage(){
        Random rand = new Random();
        for(int i = 0; i < 100; i++){
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book " + rand.nextInt(100,999));
            book.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            bookStorage.add(book);
        }
    }

    public List<BookEntity> getBooks(){
        return bookStorage;
    }

    public Optional<BookEntity> byId(Integer id){
        return bookStorage.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public BookEntity create(String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    public Optional<BookEntity> edit(BookEntity book){
       Optional<BookEntity> bookOptional = byId(book.getId());
       if(bookOptional.isEmpty()){
            return Optional.empty();
       }
       BookEntity bookToEdit = bookOptional.get();
       bookToEdit.setTitle(book.getTitle());
       bookToEdit.setDescription(book.getDescription());
       return Optional.of(bookToEdit);
    }

    public Optional<BookEntity>  delete(Integer id){
        Optional<BookEntity> bookToDelete = byId(id);
        if (bookToDelete.isEmpty()){
            return Optional.empty();
        }
        BookEntity oldToDelete = bookToDelete.get();
        bookStorage.remove(bookToDelete.get());
        return Optional.of(oldToDelete);
    }

    public Optional<BookEntity> putRequest(Integer id, Map<String, String> fields){
        Optional<BookEntity> bookToPut = byId(id);
        if(bookToPut.isEmpty()){
            return Optional.empty();
        }
        BookEntity oldToPut = bookToPut.get();
        for (String key: fields.keySet()) {
            switch (key){
                case "title" -> oldToPut.setTitle(fields.get(key));
                case "description" -> oldToPut.setDescription(fields.get(key));
            }
        }
        return Optional.of(oldToPut);
    }
}
