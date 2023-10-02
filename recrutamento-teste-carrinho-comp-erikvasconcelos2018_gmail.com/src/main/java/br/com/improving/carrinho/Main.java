package br.com.improving.carrinho;

import java.math.BigDecimal;

public class Main {

	/*
	 * Escrevi esse simples fluxo para mostrar o básico, pois devido às restrições, não sabia se
	 * poderia implementar testes unitário com o JUNIT para uma melhor cobertura e visão do
	 * funcionamento correto.
	 */

	public static void main(String[] args) {

		Produto produto1 = new Produto(231L, "Coca Cola 2L");
		Produto produto2 = new Produto(784L, "Arroz Emoções 1kg");
		Produto produto3 = new Produto(456L, "Red Bull 500ml");
		Produto produto4 = new Produto(247L, "Iphone XR");
		Produto produto5 = new Produto(572L, "Mouse Rapoo");

		CarrinhoComprasFactory fabricaCarrinhos = new CarrinhoComprasFactory();

		CarrinhoCompras carrinho1 = fabricaCarrinhos.criar("123");

		// teste criação de um item inválido
		try {
			carrinho1.adicionarItem(produto1, BigDecimal.valueOf(0), 3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		carrinho1.adicionarItem(produto1, BigDecimal.valueOf(10.3), 5);

		// Adicionando o mesmo produto com argumento diferentes
		carrinho1.adicionarItem(produto1, BigDecimal.valueOf(50.4), 6);

		// adicionando mais items
		carrinho1.adicionarItem(produto2, BigDecimal.valueOf(12.6), 4);
		carrinho1.adicionarItem(produto3, BigDecimal.valueOf(15.45), 1);
		carrinho1.adicionarItem(produto4, BigDecimal.valueOf(4785.48), 2);
		carrinho1.adicionarItem(produto5, BigDecimal.valueOf(125.98), 3);

		System.out.println("Itens no carrinho 1: " + carrinho1.getItens().size());

		// removendo o item pelo produto
		carrinho1.removerItem(produto2);
		System.out.println("Itens no carrinho 1 depois de remover: " + carrinho1.getItens().size());

		System.out.println("Itens no carrinho 1: " + carrinho1.getItens().size());

		// removendo o item pelo index
		carrinho1.removerItem(2);
		System.out.println("Itens no carrinho 1 depois de remover: " + carrinho1.getItens().size());

		// Obtendo o valor total do carrinho 1
		System.out.println("Valor total carrinho 1: " + carrinho1.getValorTotal());

		CarrinhoCompras carrinho2 = fabricaCarrinhos.criar("456");
		carrinho2.adicionarItem(produto5, BigDecimal.valueOf(125.98), 3);
		carrinho2.adicionarItem(produto2, BigDecimal.valueOf(12.6), 4);

		System.out.println("Valor total carrinho 2: " + carrinho2.getValorTotal());

		System.out.println("\n ******* Ticket médio: " + fabricaCarrinhos.getValorTicketMedio() + " ******* ");
	}

}
