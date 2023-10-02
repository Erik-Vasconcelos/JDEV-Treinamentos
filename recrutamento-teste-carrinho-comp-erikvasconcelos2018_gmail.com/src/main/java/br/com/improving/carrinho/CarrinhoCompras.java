package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private String identificadorCliente;
	//Usei o set porque em um carrinho de compras não pode haver itens repetidos
	private Set<Item> itens = new LinkedHashSet<>();

	public CarrinhoCompras(String identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 *
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser
	 * seguidas: - A quantidade do item deverá ser a soma da quantidade atual com a quantidade
	 * passada como parâmetro. - Se o valor unitário informado for diferente do valor unitário atual
	 * do item, o novo valor unitário do item deverá ser o passado como parâmetro.
	 *
	 * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao
	 * carrinho de compras.
	 *
	 * @param produto
	 * @param valorUnitario
	 * @param quantidade
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		boolean argumentosValidos = argumentosNovoItemValidos(produto, valorUnitario, quantidade);

		if (argumentosValidos) {
			Optional<Item> optionaItem = produtoJaExiste(produto);

			if (optionaItem.isPresent()) {
				adicionaProdutoAoItemExistente(optionaItem.get(), produto, valorUnitario,
						quantidade);

			} else {
				Item item = new Item(produto, valorUnitario, quantidade);
				itens.add(item);
			}
		} else {
			throw new IllegalArgumentException(
					"Não foi possível adicionar o item ao carrinho, pois os argumentos passados são inválidos");
		}
	}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de compras.
	 *
	 * @param produto
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e
	 *         false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(Produto produto) {
		Optional<Item> optionalItem = produtoJaExiste(produto);
		if (optionalItem.isPresent()) {
			Item item = optionalItem.get();
			return itens.remove(item);
		}

		return false;
	}

	/**
	 * Permite a remoção do item de acordo com a posição. Essa posição deve ser determinada pela
	 * ordem de inclusão do produto na coleção, em que zero representa o primeiro item.
	 *
	 * @param posicaoItem
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e
	 *         false caso o produto não exista no carrinho.
	 */
	public boolean removerItem(int posicaoItem) {
		if (posicaoItem > itens.size()) {
			return false;
		} else {
			Iterator<Item> iterator = itens.iterator();

			int index = 0;
			Item itemRemover = null;
			while (iterator.hasNext()) {
				if (index == posicaoItem) {
					itemRemover = iterator.next();
					break;
				}
				index ++;
			}
			return itens.remove(itemRemover);
		}
	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais de todos
	 * os itens que compõem o carrinho.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		if(itens.size() > 0) {
			BigDecimal valorTotalCarrinho =
					itens.stream().map(it -> it.getValorTotal()).reduce((acm, v) -> acm.add(v)).get();
			return valorTotalCarrinho;
		}
		
		return BigDecimal.ZERO;
		
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public Collection<Item> getItens() {
		return itens;
	}

	public String getIdentificadorCliente() {
		return identificadorCliente;
	}

	public void setIdentificadorCliente(String identificadorCliente) {
		this.identificadorCliente = identificadorCliente;
	}

	/*-------------------------Métodos auxiliares------------------------------*/

	private Optional<Item> produtoJaExiste(Produto produto) {
		Optional<Item> optionalItem =
				itens.stream().filter(it -> it.getProduto().equals(produto)).findFirst();
		return optionalItem;
	}

	private void adicionaProdutoAoItemExistente(Item item, Produto produto,
			BigDecimal valorUnitario, int quantidade) {

		int novaQuantidadeItem = item.getQuantidade() + quantidade;
		item.setQuantidade(novaQuantidadeItem);

		boolean valorUnitarioDiferente = item.getValorUnitario().compareTo(valorUnitario) != 0;
		if (valorUnitarioDiferente) {
			item.setValorUnitario(valorUnitario);
		}
	}

	private boolean argumentosNovoItemValidos(Produto produto, BigDecimal valorUnitario,
			int quantidade) {
		boolean produtoValido = produto.isValid();
		boolean valorUnitarioValido = valorUnitario.compareTo(BigDecimal.valueOf(0)) == 1;
		boolean quantidadeValida = quantidade > 0;

		return produtoValido && valorUnitarioValido && quantidadeValida;
	}
}