package com.example.snoopsht.repository;


import com.example.snoopsht.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface laptopRepository extends JpaRepository<Laptop, Long> {
}
