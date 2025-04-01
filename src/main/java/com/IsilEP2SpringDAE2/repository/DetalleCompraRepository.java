package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.DetalleCompra;
import com.IsilEP2SpringDAE2.entity.OrdenCompra;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
	List<DetalleCompra> findByOrdenCompra(OrdenCompra ordenCompra);
}
