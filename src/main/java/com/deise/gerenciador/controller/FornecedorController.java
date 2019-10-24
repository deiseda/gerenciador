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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deise.gerenciador.model.Fornecedor;
import com.deise.gerenciador.repository.FornecedorRepository;
import com.deise.gerenciador.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	public FornecedorService fornecedorService;
	 
	@Autowired
	public FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> obterTodos(){
		return fornecedorService.carregarTodos();		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Fornecedor> obterPeloId(@PathVariable Long id) {
		return fornecedorRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> criar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response) {
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(fornecedorSalvo.getIdFornecedor()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(fornecedorSalvo);
	}
	
/*	@DeleteMapping("/{idFornecedor}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idFornecedor) {
		fornecedorService.remover(idFornecedor);
	}*/
	
	@DeleteMapping(path ={"/{idFornecedor}"})
	public ResponseEntity<?> delete(@PathVariable Long idFornecedor) {
	   return fornecedorRepository.findById(idFornecedor)
	           .map(record -> {
	        	   fornecedorRepository.deleteById(idFornecedor);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping(value="/{idFornecedor}")
	public ResponseEntity<Fornecedor> alterar(@PathVariable Long idFornecedor, @Valid @RequestBody Fornecedor fornecedor) {
		
		return fornecedorRepository.findById(idFornecedor)
				.map(record -> {
					record.setCnpj(fornecedor.getCnpj());
					record.setNomeFantasia(fornecedor.getNomeFantasia());
					record.setRazaoSocial(fornecedor.getRazaoSocial());
					record.setTelefone(fornecedor.getTelefone());
					Fornecedor updated = fornecedorRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.noContent().build());
	}

}
