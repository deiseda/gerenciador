package com.deise.gerenciador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deise.gerenciador.model.Fornecedor;
import com.deise.gerenciador.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	public FornecedorRepository fornecedorRepository;
	
	public List<Fornecedor> carregarTodos() {
		return fornecedorRepository.findAll();
	}
	
	public void remover(Long idFornecedor) {
		 fornecedorRepository.deleteById(idFornecedor);
	}
	
	public void criar(Fornecedor fornecedor) {
		fornecedorRepository.save(fornecedor);
	}
	

}
