package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer>{
	List<Material> findAll();
	Material findById(int id);
}
