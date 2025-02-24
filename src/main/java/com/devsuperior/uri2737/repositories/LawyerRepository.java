package com.devsuperior.uri2737.repositories;

import com.devsuperior.uri2737.projections.LawyerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2737.entities.Lawyer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {


    @Query(nativeQuery = true, value = "(SELECT name, customers_number AS customersNumber " +
            "FROM lawyers " +
            "ORDER BY customers_number DESC " +
            "LIMIT 1) " +
            "UNION ALL " +
            "(SELECT name, customers_number " +
            "FROM lawyers " +
            "ORDER BY customers_number ASC " +
            "LIMIT 1) " +
            "UNION ALL " +
            "(SELECT 'Average', ROUND(AVG(customers_number),0) " +
            "FROM lawyers)")
    List<LawyerMinProjection> search1();
}
