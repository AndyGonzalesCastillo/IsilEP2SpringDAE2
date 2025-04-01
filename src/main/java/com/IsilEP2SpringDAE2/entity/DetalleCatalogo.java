package com.IsilEP2SpringDAE2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DetalleCatalogo")
public class DetalleCatalogo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo")
	private int codigo;
	
	@ManyToOne
	@JoinColumn(name="codigoCatalogo")
	private Catalogo catalogo;
	
	@ManyToOne
	@JoinColumn(name="codigoProductoFijo")
	private ProductoFijo productoFijo;
	
	@Column(name="descripcion")
	private String descripcion;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public ProductoFijo getProductoFijo() {
		return productoFijo;
	}

	public void setProductoFijo(ProductoFijo productoFijo) {
		this.productoFijo = productoFijo;
	}
}
