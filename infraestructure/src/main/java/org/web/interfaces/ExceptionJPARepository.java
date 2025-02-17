package org.web.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.web.jpa.ExceptionJPA;

@Repository
public interface ExceptionJPARepository extends JpaRepository<ExceptionJPA, Long> {
}