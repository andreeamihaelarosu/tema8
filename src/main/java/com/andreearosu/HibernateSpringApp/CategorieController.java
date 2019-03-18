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
public class CategorieController {
	@Autowired
	private CategorieRepository catR;
	
	@GetMapping("/categorii")
	public Page<Categorie> getAllItems(Pageable pageable) {
		return catR.findAll(pageable);
	}
	
	@PostMapping("/categorii")
	public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
		return catR.save(categorie);
	}
	
	@PutMapping("/categorii/{Cat_id}")
	public Categorie updateItem(@PathVariable Long Cat_id, @Valid @RequestBody Categorie catReq) {
		return catR.findById(Cat_id).map(categorie -> {
				categorie.setName(catReq.getName());
				categorie.setDescription(catReq.getDescription());
					return catR.save(categorie);
											  }).orElseThrow( () -> new ExceptieLipsaObiect("Categorie: "+Cat_id+" not found!"));
	}
	
	@DeleteMapping("/categorii/{Cat_id}")
	public ResponseEntity<?> deleteItem(@PathVariable Long Cat_Id) {
		return catR.findById(Cat_Id).map(categorie -> {
					catR.delete(categorie);
					return ResponseEntity.ok().build();
		}).orElseThrow( () -> new ExceptieLipsaObiect("Item: "+Cat_Id+" not found!"));		
	}

}
