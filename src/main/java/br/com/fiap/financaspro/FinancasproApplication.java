package br.com.fiap.financaspro;

// starter
// spring boot extension pack
// crtl + shift + p = add stryle || Devtools

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller // controlador da aplicação web
public class FinancasproApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancasproApplication.class, args);
	}

	@RequestMapping // Requisição vai ser mapeada para o metodo
	@ResponseBody 
	public String home(){
		return "Finanças Pro";
	}

}
