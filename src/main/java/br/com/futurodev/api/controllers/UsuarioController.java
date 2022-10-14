package br.com.futurodev.api.controllers;

import br.com.futurodev.api.model.UsuarioModel;
import br.com.futurodev.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository ;


    @PostMapping(value = "/" , produces = "application/json")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = usuarioRepository.save(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> atualizar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = usuarioRepository.save(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
      usuarioRepository.deleteById(idUsuario);
      return new ResponseEntity<String>("Usuário Deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        UsuarioModel usu = usuarioRepository.findById(idUsuario).get();
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }


}
