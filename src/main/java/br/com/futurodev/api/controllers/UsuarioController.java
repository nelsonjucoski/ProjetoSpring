package br.com.futurodev.api.controllers;

import br.com.futurodev.api.model.UsuarioModel;
import br.com.futurodev.api.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

   @PostMapping(value = "/" , produces = "application/json")
   public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario){
       UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
       return new ResponseEntity<UsuarioModel>(usu, HttpStatus.CREATED);
   }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> atualizar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
        cadastroUsuarioService.delete(idUsuario);
        return new ResponseEntity<String>("Usuário Deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        UsuarioModel usu = cadastroUsuarioService.getUserById(idUsuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }


    @GetMapping(value = "/buscaPorNome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioModel>> getUserByName(@RequestParam (name = "nome") String nome){
       List<UsuarioModel> usuarios = cadastroUsuarioService.getUserByName(nome);
       return  new ResponseEntity<List<UsuarioModel>>(usuarios, HttpStatus.OK);
    }


}
