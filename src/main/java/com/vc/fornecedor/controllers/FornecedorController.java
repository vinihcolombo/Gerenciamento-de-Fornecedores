package com.vc.fornecedor.controllers;

import com.vc.fornecedor.models.FornecedorModel;
import com.vc.fornecedor.services.FornecedorService;
import jakarta.persistence.GeneratedValue;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorModel> criarFornecedor(@RequestBody FornecedorModel fornecedorModel){
        fornecedorService.criarFornecedor(fornecedorModel);
        FornecedorModel request = fornecedorService.criarFornecedor(fornecedorModel);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(fornecedorModel.getId()).toUri();
        return ResponseEntity.created(uri).body(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFornecedor(@PathVariable Long id){
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FornecedorModel>> findAll(){
        List<FornecedorModel> request = fornecedorService.findAll();
        return ResponseEntity.ok().body(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorModel> buscarPorId(@PathVariable Long id){
        Optional<FornecedorModel> fornecedor = fornecedorService.buscarPorId(id);

        if(fornecedor.isPresent()){
            return ResponseEntity.ok(fornecedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorModel> atualizar(@PathVariable Long id, @RequestBody FornecedorModel fornecedorModel){
        FornecedorModel request = fornecedorService.atualizarFornecedor(id, fornecedorModel);
        return ResponseEntity.ok().body(request);
    }
}
