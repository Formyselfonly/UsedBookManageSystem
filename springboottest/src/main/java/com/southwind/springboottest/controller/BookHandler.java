package com.southwind.springboottest.controller;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.repository.BookRepository;
import com.southwind.springboottest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/")
//@CrossOrigin(origins = "*") //注解实现跨域,更方便 不过不是全局跨域,谁跨域谁注解,好处是特定性强
//tips:之前用的Mybatis-plus,现在练习下用Jpa写接口,看看哪个更好用 反正远离都是差不多
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

    //login mapping
    private final String USERNAME="root";
    private final String PASSWORD="524118";
    @GetMapping("/login")
    public User login(User user){
        if(USERNAME.equals(user.getUsername())&&PASSWORD.equals(user.getPassword())){
            user.setToken(JwtUtil.createToken());
            return user;
        }

        return null;
    }








    @GetMapping("/book/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return bookRepository.findAll(request);
    }

    @PostMapping("/book/save")
    //@RequestBody 就是让这个Book类的book来作为请求接收体,也就是说把传过来的Json数据转化为一个Book类的book
    //一般增删改查的增和改需要@RequestBody
    public String save(@RequestBody Book book){
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/book/findById/{id}")
    public Book findById(@PathVariable("id") Integer id){
        return bookRepository.findById(id).get();
    }

    @PutMapping("/book/update")
    public String update(@RequestBody Book book){
        Book result = bookRepository.save(book);
        if(result != null){
            return "success";
        }else{
            return "error";
        }
    }

    @DeleteMapping("/book/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        bookRepository.deleteById(id);
    }
}
