package com.deise.gerenciador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deise.gerenciador.model.Vendedor;
import com.deise.gerenciador.repository.VendedorRepository;

@Service
public class VendedorService {

	@Autowired
	public VendedorRepository vendedorRepository;
	
	public List<Vendedor> carregarTodos() {
		return vendedorRepository.findAll();
	}
	
	public void remover(Long idVendedor) {
		vendedorRepository.deleteById(idVendedor);
	}
	
	public void criar(Vendedor vendedor) {
		vendedorRepository.save(vendedor);
	}
	

}
