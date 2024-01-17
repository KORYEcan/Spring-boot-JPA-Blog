package com.example.blog1.repository;

import com.example.blog1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//DAO
//자동으로 bean 등록이 된다.
//@Repository //생략 가능함
public interface UserRepository extends JpaRepository<User,Integer> { // JpaRepository 제너릭을 사용해서 해당 Repository는 User객체값만 들어올수 있게끔 하는것


}
