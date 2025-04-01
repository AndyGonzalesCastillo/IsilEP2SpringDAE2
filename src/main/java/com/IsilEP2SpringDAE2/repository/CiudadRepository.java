package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad,Integer> {
	List<Ciudad> findByEstado(String Estado);
	Ciudad findById(int id);
}
