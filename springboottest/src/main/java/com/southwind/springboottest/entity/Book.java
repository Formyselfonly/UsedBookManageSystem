package com.southwind.springboottest.entity;

import lombok.Data;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String owner;

/*
    @Version  //如果多线程,那么可以用这个实现乐观锁
    private String version;
*/

}
