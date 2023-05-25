package com.example.j_project.repo;

import com.example.j_project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    /*@Query(value = "select * from product ORDER BY productName, prize ASC", nativeQuery = true)
    List<Product> sortBy(@Param("keyword") String keyword);*/
    List<Product> findByOrderByPrizeAsc();
    List<Product> findByOrderByPrizeDesc();
    List<Product> findByOrderByProductNameAsc();
    List<Product> findByOrderByProductNameDesc();
    List<Product> findByOrderByDataAsc();
    List<Product> findByOrderByDataDesc();
    List<Product> findByOrderByIdAsc();
    List<Product> findByOrderByIdDesc();


}
