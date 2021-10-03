package com.solbeg.task.repository;

import com.solbeg.task.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, UUID> {

    List<Industry> findAllByParentIsNull();
}