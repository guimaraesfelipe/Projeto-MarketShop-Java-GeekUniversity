package br.com.marketshop.model;

import br.com.marketshop.helper.Utils;

public class Product {
	private static int counter = 1;
	private int code;
	private String name;
	private Double price;
	
	public Product(String name, Double price) {
		this.code = Product.counter;
		this.name = name;
		this.price = price;
		Product.counter++;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "C�digo: " + this.getCode() + "\nNome: " + this.getName() + "\nPre�o: " + Utils.doubleToString(getPrice());
	}
	
}
