package com.sixteen.school.repository;

import com.sixteen.school.model.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("Glass")
public interface GlassRepository extends JpaRepository<Glass, Long> {
}
