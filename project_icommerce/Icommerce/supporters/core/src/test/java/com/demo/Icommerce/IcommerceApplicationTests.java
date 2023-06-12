package com.demo.Icommerce;

import com.demo.Icommerce.domain.product.entity.Category;
import com.demo.Icommerce.domain.product.entity.Product;
import com.demo.Icommerce.domain.product.repository.CategoryRepository;
import com.demo.Icommerce.domain.product.repository.ProductRepository;
import com.demo.Icommerce.infrastructure.util.DateTimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class IcommerceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void findAll() {
		List<Product> products = productRepository.findAll();
		System.out.println(products);
	}

	@Test
	public void find(){
		System.out.println(productRepository.findAll());
	}

	@Test
	public void createOne() {
		Category category = new Category();
		category.setName("Clothing");
		category.setCreatedAt(DateTimeUtils.nowAsMillis());
		category.setCreatedBy(-1L);
		category.setProduct(IntStream.rangeClosed(1,10)
		.mapToObj(el -> {
			Product product = new Product();
			product.setName("Hoddies");
			product.setPrice(new BigDecimal(el * 25));
			product.setBranch("branch-"+el);
			product.setDescription("Description - " + el);
			product.setCreatedAt(DateTimeUtils.nowAsMillis());
			product.setCreatedBy(-1L);
			product.setCategory(category);
			return product;
		})
				.collect(Collectors.toSet())
		);
		categoryRepository.save(category);
	}

}
