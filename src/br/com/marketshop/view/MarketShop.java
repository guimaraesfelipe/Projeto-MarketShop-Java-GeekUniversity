package br.com.marketshop.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import br.com.marketshop.helper.Utils;
import br.com.marketshop.model.Product;

public class MarketShop {

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Product> products;
	private static Map<Product, Integer> cart;

	public static void main(String[] args) {
		products = new ArrayList<Product>();
		cart = new HashMap<Product, Integer>();
		menu();
	}

	private static void menu() {
		System.out.println("====================== Bem-vindo(a) ao MarketShop ======================");
		System.out.println("Selecione uma das opções abaixo: ");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Comprar produto");
		System.out.println("4 - Visualizar carrinho");
		System.out.println("5 - Sair");

		int option = 0;

		try {
			option = Integer.parseInt(sc.nextLine());
		} catch (InputMismatchException e) {
			menu();
		} catch (NumberFormatException e) {
			menu();
		}

		switch (option) {
		case 1:
			register();
			break;
		case 2:
			list();
			break;
		case 3:
			buy();
			break;
		case 4:
			viewCart();
			break;
		case 5:
			System.out.println("Obrigado e volte sempre!");
			Utils.pause(2);
			System.exit(0);
		default:
			System.err.println("Opção invalida.");
			Utils.pause(2);
			menu();
			break;
		}
	}

	private static void register() {
		System.out.println("===================\nCadastro de Produto\n===================");
		System.out.println("Nome do produto: ");
		String name = sc.nextLine();

		System.out.println("Preço do produto: ");
		Double price = sc.nextDouble();

		Product product = new Product(name, price);
		products.add(product);

		System.out.println("O produto " + product.getName() + " foi cadastrado com sucesso.");
		Utils.pause(2);
		menu();
	}

	private static void list() {
		if (products.size() > 0) {
			System.out.println("====================\nListagem de produtos\n====================");

			for (Product product : products) {
				System.out.println(product);
				System.out.println();
			}
		} else {
			System.err.println("Não existe nenhum produto cadastrado.");
		}

		Utils.pause(2);
		menu();

	}

	private static void buy() {
		if (products.size() > 0) {
			System.out.println("====================\nProdutos Disponíveis\n====================");
			System.out.println("Informe o código do produto que deseja comprar: \n");

			for (Product product : products) {
				System.out.println(product);
				System.out.println("--------------------");
			}

			int code = Integer.parseInt(sc.nextLine());
			boolean haveInCart = false;

			for (Product product : products) {
				if (product.getCode() == code) {
					int amount = 0;

					try {
						amount = cart.get(product);
						cart.put(product, amount + 1);
					} catch (NullPointerException e) {
						cart.put(product, 1);
					}

					System.out.println("O produto " + product.getName() + " foi adicionado no carrinho.");
					haveInCart = true;
				}

				if (haveInCart) {
					System.out.println("Deseja adicionar outros produtos no carrinho?\n1 - Sim\n2 - Não");
					int option = Integer.parseInt(sc.nextLine());

					if (option == 1) {
						buy();
					} else {
						System.out.println("Por favor, aguarde enquanto fechamos seu pedido....");
						Utils.pause(2);
						closeOrder();
					}
				}
			}

		} else {
			System.err.println("Não existem produtos cadastrados.");
			Utils.pause(2);
			menu();
		}

	}

	private static void viewCart() {
		if (cart.size() > 0) {
			System.out.println("====================\nProdutos no Carrinho\n====================");

			for (Product product : cart.keySet()) {
				System.out.println("Produto: " + product + "\nQuantidade: " + cart.get(product));

			}
		} else {
			System.err.println("Não existem produtos no carrinho de compras.");
		}

		Utils.pause(2);
		menu();

	}

	private static void closeOrder() {
		Double total = 0.0;
		System.out.println("====================\nProdutos no Carrinho\n====================");

		for (Product product : cart.keySet()) {
			total += product.getPrice() * cart.get(product);

			System.out.println(product);
			System.out.println("Quantidade: " + cart.get(product));
			System.out.println("--------------------");
		}

		System.out.println("Sua fatura é " + Utils.doubleToString(total));
		cart.clear();
		System.out.println("Obrigado por escolher a MarketShop.");
		Utils.pause(5);
		menu();
	}

}
