package br.com.futurodev.api.controllers;

import br.com.futurodev.api.model.ProdutoModel;
import br.com.futurodev.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/index/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String digiteNome(@PathVariable String nome) {
        return "Fala " + nome + "...blz";
    }

    /**
     * Teste de Registro
     */
    @RequestMapping(value = "/produto/{descricao}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String salvar(@PathVariable String descricao) {
        ProdutoModel produto = new ProdutoModel();
        produto.setDescricao(descricao);
        produtoRepository.save(produto);
        return "Produto " + descricao + " Salvo!";
    }
    @GetMapping("/produtos")
    @ResponseBody
    public ResponseEntity<List<ProdutoModel>> listarProdutos(){
       List<ProdutoModel> produtos =  produtoRepository.findAll();
       return new ResponseEntity<List<ProdutoModel>>(produtos,  HttpStatus.OK);
    }
    @PostMapping(value = "produto/salvar")
    @ResponseBody
    public ResponseEntity<ProdutoModel> salver(@RequestBody ProdutoModel produtoModel){
        ProdutoModel prod = produtoRepository.save(produtoModel);
        return new ResponseEntity<ProdutoModel>(prod, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/produto/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto){
        produtoRepository.deleteById(idProduto);
        return new ResponseEntity<String>("Produto deletado com sucesso!", HttpStatus.OK);
    }
}