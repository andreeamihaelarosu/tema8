package com.andreearosu.HibernateSpringApp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	@Autowired
	private ItemRepository itemR;
	
	@Autowired 
	private CategorieRepository catR;
	
	@GetMapping("/")
	public void DisplayText() {
		System.out.println("Asta este rootul");
	}
	
	@GetMapping("/items")
	public Page<Item> getAllItems(Pageable pageable) {
		return itemR.findAll(pageable);
	}
	
	@GetMapping("/categorie/{Cat_id}/items")
	public Page<Item> getItemsByCategoryId(@PathVariable(value="Cat_id") Long Cat_id, Pageable pageable){
		return itemR.findByCategoryId(Cat_id, pageable);
	}
	
	@PostMapping("/categorie/{Cat_id}/items")
	public Item createItem(@PathVariable(value="Cat_id") Long Cat_id, @Valid @RequestBody Item item) {
		return catR.findById(Cat_id).map(categorie -> {
			item.setCategorie(categorie);
			return itemR.save(item);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Item" + Cat_id + "not found"));
	}
	
	@PutMapping("/items/{Item_id}")
	public Item updateItem(@PathVariable Long Item_id, @Valid @RequestBody Item itemReq) {
		return itemR.findById(Item_id).map(item -> {
					item.setTitle(itemReq.getTitle());
					item.setDescription(itemReq.getDescription());
					item.setContent(itemReq.getContent());
					return itemR.save(item);
											  }).orElseThrow( () -> new ExceptieLipsaObiect("Item: "+Item_id+" not found!"));
	}
	

	
	@DeleteMapping("/items/{Item_id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long item_Id) {
		return itemR.findById(item_Id).map(item -> {
					itemR.delete(item);
					return ResponseEntity.ok().build();
		}).orElseThrow( () -> new ExceptieLipsaObiect("Item: "+item_Id+" not found!"));		
	}

}
