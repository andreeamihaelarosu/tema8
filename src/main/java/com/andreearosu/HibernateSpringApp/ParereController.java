package com.andreearosu.HibernateSpringApp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParereController {
	@Autowired
	private ParereRepository pr;
	
	@Autowired 
	private ItemRepository ir;
	
	@GetMapping("/item/{Item_id}/pareri")
	public Page<Parere> getPareriByItemId(@PathVariable(value="Item_id") Long Item_id, Pageable pageable){
		return pr.findByItemId(Item_id, pageable);
	}
	
	@PostMapping("item/{Item_id}/pareri")
	public Parere createParere(@PathVariable(value="Item_id") Long Item_id, @Valid @RequestBody Parere parere) {
		return ir.findById(Item_id).map(item -> {
			parere.setItem(item);
			return pr.save(parere);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Item" + Item_id + "not found"));
	}
	
	@PutMapping("/item/{Item_id}/pareri/{Parere_id}")
	public Parere updateParere(@PathVariable(value="Item_id") Long Item_id, @PathVariable(value="Parere_id") Long Parere_id, @Valid @RequestBody Parere parereReq) {
		if(!ir.existsById(Parere_id)) {
			throw new ExceptieLipsaObiect("Item" + Item_id + "not found");
		}
		
		return pr.findById(Parere_id).map(parere -> {
			parere.setText(parereReq.getText());
			return pr.save(parere);
		}).orElseThrow(() -> new ExceptieLipsaObiect("Parere" + Parere_id + "not found"));
	}
}

