package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private Set<CarrinhoCompras> conjuntoCarrinhosCompra = new LinkedHashSet<>();

	public CarrinhoComprasFactory() {
	}

	/**
	 * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
	 *
	 * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho
	 * deverá ser retornado.
	 *
	 * @param identificacaoCliente
	 * @return CarrinhoCompras
	 */
	public CarrinhoCompras criar(String identificacaoCliente) {
		Optional<CarrinhoCompras> optionalCarrinho = clientePossuiCarinho(identificacaoCliente);

		if (optionalCarrinho.isPresent()) {
			return optionalCarrinho.get();
		} else {
			CarrinhoCompras novoCarrinho = new CarrinhoCompras(identificacaoCliente);
			conjuntoCarrinhosCompra.add(novoCarrinho);

			return novoCarrinho;
		}
	}

	/**
	 * Retorna o valor do ticket médio no momento da chamada ao método. O valor do ticket médio é a
	 * soma do valor total de todos os carrinhos de compra dividido pela quantidade de carrinhos de
	 * compra. O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
	 * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTicketMedio() {
		if(conjuntoCarrinhosCompra.size() > 0) {
			BigDecimal somaTotalCarrinhos = conjuntoCarrinhosCompra.stream()
					.map(cp -> cp.getValorTotal()).reduce((acm, v) -> acm.add(v)).get();
			long quantidadeCarrinhos = conjuntoCarrinhosCompra.size();
			
			BigDecimal ticketMedio = somaTotalCarrinhos.divide(BigDecimal.valueOf(quantidadeCarrinhos));
			
			ticketMedio = ticketMedio.setScale(1, RoundingMode.HALF_EVEN);
			return ticketMedio;
		}
		
		return BigDecimal.ZERO;
	}

	/**
	 * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar. Deve
	 * ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos
	 * de compras.
	 *
	 * @param identificacaoCliente
	 * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um
	 *         carrinho de compras e e false caso o cliente não possua um carrinho.
	 */
	public boolean invalidar(String identificacaoCliente) {
		Optional<CarrinhoCompras> optionalCarrinho = clientePossuiCarinho(identificacaoCliente);
		
		if(optionalCarrinho.isPresent()) {
			CarrinhoCompras carrinhoRemover = optionalCarrinho.get();
			return conjuntoCarrinhosCompra.remove(carrinhoRemover);
		}
		
		return false;
	}

	private Optional<CarrinhoCompras> clientePossuiCarinho(String identificacaoCliente) {
		Optional<CarrinhoCompras> optional = conjuntoCarrinhosCompra.stream()
				.filter(cp -> cp.getIdentificadorCliente().equals(identificacaoCliente))
				.findFirst();
		return optional;
	}
}
