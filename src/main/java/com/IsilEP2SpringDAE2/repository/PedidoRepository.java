package com.IsilEP2SpringDAE2.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Cliente;
import com.IsilEP2SpringDAE2.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	List<Pedido> findAll();
	List<Pedido> findByCliente(Cliente cliente);
	void deleteById(int codigo);
	Pedido findById(int codigo);
	Pedido findByNroPedido(int NroPedido);
	Pedido findByFechaPedido(Date FechaPedido);
}
