package br.com.improving.carrinho;

import java.util.Objects;

/**
 * Classe que representa um produto que pode ser adicionado como item ao carrinho de compras.
 *
 * Importante: Dois produtos são considerados iguais quando ambos possuem o mesmo código.
 */
public class Produto {

	private Long codigo;
	private String descricao;

	/**
	 * Construtor da classe Produto.
	 *
	 * @param codigo
	 * @param descricao
	 */
	public Produto(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * Retorna o código do produto.
	 *
	 * @return Long
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * Configura o código do produto.
	 *
	 * @param codigo
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna a descrição do produto.
	 *
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Configura a descrição do produto.
	 *
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Equals e hashCode pelo código do produto
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	public boolean isValid() {
		boolean codigoValido = codigo >= 0;
		boolean descricaoValida = !descricao.isEmpty() && descricao.length() > 5;
		
		return codigoValido && descricaoValida;
	}
	
}