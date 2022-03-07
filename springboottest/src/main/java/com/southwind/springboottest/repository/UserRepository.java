package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.User;
import org.hibernate.annotations.Subselect;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
//    @Select("select * from user where name=")
//    User Select();
}
