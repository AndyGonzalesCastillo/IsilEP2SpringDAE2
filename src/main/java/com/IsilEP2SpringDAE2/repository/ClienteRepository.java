package com.IsilEP2SpringDAE2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
	List<Cliente> findAll();
	//List<Cliente> findByCorreoList(String correo);
	Cliente findById(int codigo);
	void deleteById(int codigo);
	Optional<Cliente> findByCorreo(String correo);
}