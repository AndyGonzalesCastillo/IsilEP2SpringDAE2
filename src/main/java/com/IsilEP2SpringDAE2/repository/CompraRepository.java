package com.IsilEP2SpringDAE2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Compra;
import com.IsilEP2SpringDAE2.entity.Proveedor;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Integer> {
	List<Compra> findAll();
	List<Compra> findByProveedor(Proveedor proveedor);
	void deleteById(int id);
	Compra findByFecha(Date Fecha);
	Compra findByDetalle(String detalle);
	Compra findById(int id);
	
}