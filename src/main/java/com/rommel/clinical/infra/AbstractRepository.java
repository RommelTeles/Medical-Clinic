package com.rommel.clinical.infra;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface AbstractRepository<T extends Serializable>
        extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    long countById(Long id);
}
