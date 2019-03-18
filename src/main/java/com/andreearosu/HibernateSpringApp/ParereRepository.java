package com.andreearosu.HibernateSpringApp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParereRepository extends JpaRepository<Parere,Long>{
	Page<Parere> findByItemId(Long Item_id, Pageable pageable);
	//Optional<Parere> findByIdAndItemId(Long Parere_id, Long Item_id);
}
