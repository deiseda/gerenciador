package com.deise.gerenciador.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.deise.gerenciador.model.Cliente;
import com.deise.gerenciador.model.Fornecedor;
import com.deise.gerenciador.repository.ClienteRepository;
import com.deise.gerenciador.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteService;
	 
	@Autowired
	public ClienteRepository clienteRepository;

	
	@GetMapping
	public List<Fornecedor> obterTodos(){
		return clienteService.carregarTodos();		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> obterPeloId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(clienteSalvo.getIdCliente()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(clienteSalvo);
	}

	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
	   return clienteRepository.findById(id)
	           .map(record -> {
	        	   clienteRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		
		return clienteRepository.findById(id)
				.map(record -> {
					record.setCpfCnpj(cliente.getCpfCnpj());
					record.setEmail(cliente.getEmail());
					record.setIdEndereco(cliente.getIdEndereco());
					record.setNomeFantasia(cliente.getNomeFantasia());
					record.setNomeRazao(cliente.getNomeRazao());
					record.setRg(cliente.getRg());
					record.setTelefoneComercial(cliente.getTelefoneComercial());
					record.setTelefonePessoal(cliente.getTelefonePessoal());
					record.setTipoDocumento(cliente.getTipoDocumento());					
					Cliente updated = clienteRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.noContent().build());
	}

}
