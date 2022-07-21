package br.com.marketshop.view;

import br.com.marketshop.model.Product;

public class ProgramTest {

	public static void main(String[] args) {
		Product ps4 = new Product("PlayStation 4", 1789.44);
		Product xbox = new Product("Xbox 360", 1699.00);
		Product nswitch = new Product("Nintendo Switch", 2499.99);
		
		System.out.println(ps4);
		System.out.println(xbox);
		System.out.println(nswitch);

	}

}
