package productCrudApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productCrudApp.dao.productDAO;
import productCrudApp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private productDAO prodDAO;
	
	@RequestMapping("/")
	public String home(Model m) {
		System.out.println("Home");
		List<Product> prodList = prodDAO.getAllProduct();
		System.out.println(prodList);
		m.addAttribute("products", prodList);
		return "index";
	}
	
	@RequestMapping("/addProduct")
	public String addProduct(Model m) {
		m.addAttribute("title", "AddProduct"); 
		return "add_product_form";
	}
	
	@RequestMapping(path = "/handleForm", method = RequestMethod.POST)
	public RedirectView handelForm(@ModelAttribute Product product, HttpServletRequest req) {
		System.out.println(product);
		System.out.println(this.prodDAO.insert(product));
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteData(@PathVariable("productId") Integer id, HttpServletRequest req) {
		System.out.println("Delete");
		this.prodDAO.deleteProduct(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath()+"/");
		return redirectView;
		
	}
	@RequestMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") int id, Model m) {
		Product product = this.prodDAO.getProductById(id);
		m.addAttribute("product", product);
		return "update_prod_form";
	}

	@RequestMapping(path = "/handleUpdate", method = RequestMethod.POST)
	public RedirectView handleUpdate(@ModelAttribute Product product, HttpServletRequest req) {
		System.out.println("Upday");
		System.out.println(product);
		this.prodDAO.updateProduct(product);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(req.getContextPath()+"/");
		return redirectView;
		
	}
}
