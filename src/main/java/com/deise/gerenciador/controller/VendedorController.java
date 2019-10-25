package com.deise.gerenciador.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deise.gerenciador.model.Fornecedor;
import com.deise.gerenciador.model.Vendedor;
import com.deise.gerenciador.repository.FornecedorRepository;
import com.deise.gerenciador.repository.VendedorRepository;
import com.deise.gerenciador.service.FornecedorService;
import com.deise.gerenciador.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {
	
	@Autowired
	public VendedorService vendedorService;
	 
	@Autowired
	public VendedorRepository vendedorRepository;
		
	@GetMapping
	public List<Vendedor> obterTodos(){
		return vendedorService.carregarTodos();		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Vendedor> obterPeloId(@PathVariable Long id) {
		return vendedorRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Vendedor> criar(@Valid @RequestBody Vendedor vendedor, HttpServletResponse response) {
		Vendedor vendedorSalvo = vendedorRepository.save(vendedor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(vendedorSalvo.getIdVendedor()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(vendedorSalvo);
	}

	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
	   return vendedorRepository.findById(id)
	           .map(record -> {
	        	   vendedorRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping(value="/{idVendedor}")
	public ResponseEntity<Vendedor> alterar(@PathVariable Long idVendedor, @Valid @RequestBody Vendedor vendedor) {
		
		return vendedorRepository.findById(idVendedor)
				.map(record -> {
					record.setCpf(vendedor.getCpf());
					record.setMatricula(vendedor.getMatricula());
					record.setNome(vendedor.getNome());
					record.setTelefone(vendedor.getTelefone());
					Vendedor updated = vendedorRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.noContent().build());
	}

}
