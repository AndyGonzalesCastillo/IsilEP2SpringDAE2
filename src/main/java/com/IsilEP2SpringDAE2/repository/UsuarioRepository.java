package com.IsilEP2SpringDAE2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByCorreoAndPassword(String correo, String password);
	List<Usuario> findAll();
	Usuario findById(int codigo);
	void deleteById(int codigo);
	Optional<Usuario> findByCorreo(String correo);
}