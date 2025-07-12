package org.example.repository;

import lombok.NonNull;
import org.example.entity.DataEntry;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<DataEntry, Long> {
    @EntityGraph(attributePaths = "application")
    List<DataEntry> findAll(@NonNull Sort sort);

}
