package pl.edu.agh.mwo.invoice;

import java.awt.List;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	public void addProduct(Product product) {
		int count = this.products.containsKey(this.products)? this.products.get(product) + 1: 1;
    	this.products.put(product, count);
	}

	public void addProduct(Product product, Integer quantity) {		
		int count = this.products.containsKey(this.products)? this.products.get(product) + quantity: quantity;
    	this.products.put(product, count);
	}

	public BigDecimal getTotalNetPrice() {
		BigDecimal sum = BigDecimal.ZERO;
		products.entrySet().stream().forEach(product ->{
			sum.add(product.getKey().getPrice().multiply(new BigDecimal(product.getValue())));
		});
		return sum;
	}

	public BigDecimal getTax() {
		BigDecimal sum = BigDecimal.ZERO;
		System.out.println("<<<>>>>");
		products.entrySet().stream().forEach(product -> System.out.println(product.getKey().getName() + " : " + product.getValue()));
		System.out.println("<<<>>>>");
		products.entrySet().stream().forEach(product ->{
			BigDecimal tax = product.getKey().getTaxPercent();
			BigDecimal value = product.getKey().getPrice();
			sum.add(tax.multiply(value.multiply(new BigDecimal(product.getValue()))));
		});
		return sum;
	}

	public BigDecimal getTotalGrossPrice() {
		return getTotalNetPrice().add(getTax());
	}
}
