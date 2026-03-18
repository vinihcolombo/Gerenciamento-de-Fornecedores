package com.vc.fornecedor.services;

import com.vc.fornecedor.models.FornecedorModel;
import com.vc.fornecedor.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorModel> findAll(){
        return fornecedorRepository.findAll();
    }

    public FornecedorModel criarFornecedor(FornecedorModel fornecedorModel){
        return fornecedorRepository.save(fornecedorModel);
    }

    public void deletarFornecedor(Long id){
        fornecedorRepository.deleteById(id);
    }

    public Optional<FornecedorModel> buscarPorId(Long id){
        return fornecedorRepository.findById(id);
    }

    public FornecedorModel atualizarFornecedor(Long id, FornecedorModel fornecedorModel){
        FornecedorModel newFornecedorModel = fornecedorRepository.findById(id).get();
        newFornecedorModel.setContato(fornecedorModel.getContato());
        newFornecedorModel.setNomeFantasia(fornecedorModel.getNomeFantasia());
        newFornecedorModel.setCnpj(fornecedorModel.getCnpj());

        return fornecedorRepository.save(newFornecedorModel);
    }
}