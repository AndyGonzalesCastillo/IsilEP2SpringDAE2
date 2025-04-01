package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.ProductoFijo;

@Repository
public interface ProductoFijoRepository extends JpaRepository<ProductoFijo, Integer> {
	List<ProductoFijo> findAll();
	ProductoFijo findByCodigo(int codigo);
}
