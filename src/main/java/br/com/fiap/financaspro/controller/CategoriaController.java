package br.com.fiap.financaspro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET, path = "/categoria", produces = "application/json" ) // Somente o metodo get irá funcinar, somente requisições GET. | produces -> oq vai retornar
    @ResponseBody
    public String index() {
        return """
                [
                    {
                        "id": 1,
                        "nome": "Alimentação",
                        "icone": "fast-food"
                    },
                    {
                        "id": 2,
                        "nome": "Educação",
                        "icone": "book"
                    }
                ]
                    """;
    }

}
