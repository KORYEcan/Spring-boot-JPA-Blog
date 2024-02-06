package com.example.blog1.repository;

import com.example.blog1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


//DAO
//자동으로 bean 등록이 된다.
//@Repository //생략 가능함
public interface UserRepository extends JpaRepository<User,Integer> { // JpaRepository 제너릭을 사용해서 해당 Repository는 User객체값만 들어올수 있게끔 하는것

    // JPA Naming 쿼리
    //SELECT * FROM user WHERE username= ?1 AND password= ?2
    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username= ?1 AND password= ?2" , nativeQuery = true)
//    User login(String username, String password);



}
