package com.solbeg.sectorsapp.repository;

import com.solbeg.sectorsapp.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    List<Sector> findAllByParentIsNull();
}