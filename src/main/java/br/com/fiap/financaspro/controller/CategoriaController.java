package br.com.fiap.financaspro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.fiap.financaspro.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> repository = new ArrayList<>(); 

    @GetMapping
    //somente o método get ira funcionar e o path para ser /categoria, e a saida vai ser um json
    public List<Categoria> index() {
        // 1L = um Long
        // retornando uma Categoria
        return repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){
        log.info("Cadastrando categoria {}", categoria);
        repository.add(categoria);
        return categoria;
    }

    @GetMapping("{id}")  
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscando categoria por id {}", id);

        for(Categoria categoria : repository){
            if (categoria.id().equals(id)) 
                return ResponseEntity.ok(categoria);
        }
        
        // TODO Refactoring with stream
        var categoriaEncontrada = repository
            .stream()
            .filter( c -> c.id().equals(id) )
            .findFirst();

        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoriaEncontrada.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria");

        var categoriaEncontrada = getCategoriaByid(id);

        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        repository.remove(categoriaEncontrada.get());
        
        return ResponseEntity.noContent().build();                
    }


    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("Atualizando categoria com id {} para {}", id, categoria);
        // Buscar a catehoria

        var categoriaEncontrada = getCategoriaByid(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        // Criar uma nova categoria com os novos dados
        
        var categoriaAntiga = categoriaEncontrada.get();
        var categoriaNova = new Categoria(id, categoria.nome(), categoria.icone());

        // apagar a categoria antiga
        repository.remove(categoriaAntiga);
     
       // add a categoria nova
       repository.add(categoriaNova);

       return ResponseEntity.ok(categoriaNova);
    }



    private Optional<Categoria> getCategoriaByid(Long id) {
        var categoriaEncontrada = repository
            .stream()
            .filter( c -> c.id().equals(id) )
            .findFirst();
        return categoriaEncontrada;
    }

    
}