package br.com.fiap.financaspro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// imutavel
@Data
@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) //  gerado automaticamente pelo banco de dados
	private Long id;
    private String nome;
    private String icone;

} 
