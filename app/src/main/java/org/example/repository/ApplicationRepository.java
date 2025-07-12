package org.example.repository;

import org.example.entity.Application;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByOwnerIdOrderByIdDesc (Long ownerId);
    Application getByApikey(String apiKey);
    @EntityGraph(attributePaths = "owner")
    Optional<Application> findByApikey(String apikey);
}
