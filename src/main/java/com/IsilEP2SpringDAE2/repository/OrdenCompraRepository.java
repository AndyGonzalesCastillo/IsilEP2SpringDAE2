package com.IsilEP2SpringDAE2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Integer>{
	List<OrdenCompra> findAll();
	List<OrdenCompra> findByFechaRegistroBetween(Date fechaInicio, Date fechaFin);
	OrdenCompra findById(int id);
	void deleteById(int id);
}
