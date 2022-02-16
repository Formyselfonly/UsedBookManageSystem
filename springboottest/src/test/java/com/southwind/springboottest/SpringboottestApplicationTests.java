package com.southwind.springboottest;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class SpringboottestApplicationTests {
    @Autowired
    private BookRepository repository;

    @Test
    void contextLoads() {
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Book> page = repository.findAll(pageRequest);
        int i = 0;
    }

    @Test
    void save(){
        Book book = new Book();
        book.setName("Spring Boot");
        book.setOwner("张三");
        Book book1 = repository.save(book);
        System.out.println(book1);
    }

    @Test
    void findById(){
        Book book = repository.findById(1).get();
        System.out.println(book);
    }

    @Test
    void update(){
        Book book = new Book();
        book.setId(117);
        book.setName("测试测试");
        Book book1 = repository.save(book);
        System.out.println(book1);
    }

    @Test
    void delete(){
        repository.deleteById(117);
    }
}
