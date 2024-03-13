package br.com.fiap.financaspro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.financaspro.model.Categoria;
import br.com.fiap.financaspro.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/categoria")
@Slf4j
public class CategoriaController {


    @Autowired  // Gere e entregue o objeto para ser usado
    CategoriaRepository repository;

    @GetMapping
    //somente o método get ira funcionar e o path para ser /categoria, e a saida vai ser um json
    public List<Categoria> index() {
        // 1L = um Long
        // retornando uma Categoria
        return repository.findAll() ; //lista de categoria
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){
        log.info("Cadastrando categoria {}", categoria);
        repository.save(categoria);
        return categoria;
    }

    @GetMapping("{id}")  
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscando categoria por id {}", id);

        return repository
            .findById(id)
            .map(ResponseEntity::ok) // refernece method
            .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria");

        verificarSeExisteCategoria(id);
        
        repository.deleteById(id);
        
        return ResponseEntity.noContent().build();                
    }


    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("Atualizando categoria com id {} para {}", id, categoria);
        // Buscar a categoria

        verificarSeExisteCategoria(id);

       categoria.setId(id);                               
       repository.save(categoria);                                 
       return ResponseEntity.ok(categoria);

    }


    private void verificarSeExisteCategoria(Long id) {
        repository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, 
                                        "Não existe categoria com o id informado. Consulte lista em /categoria"));
    }

    
}