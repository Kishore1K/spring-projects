package productCrudApp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import productCrudApp.model.Product;

@Component
public class productDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int insert(Product product) {
		return (Integer)this.hibernateTemplate.save(product);
	}
	
	
	public List<Product> getAllProduct(){
		return this.hibernateTemplate.loadAll(Product.class); 
	}

	public Product getProductById(Long id) {
		return this.hibernateTemplate.get(Product.class, id)
	}
	
	@Transactional
	public void deleteProduct(Long id) {
		Product product = this.hibernateTemplate.load(Product.class, id);
		this.hibernateTemplate.delete(product);
	}
}
