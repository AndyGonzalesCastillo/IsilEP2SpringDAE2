package com.IsilEP2SpringDAE2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {
	Optional<Producto> findByNombre(String nombre);
	Producto findById(int codigo);
	List<Producto> findAll();
	void deleteById(int id);
}