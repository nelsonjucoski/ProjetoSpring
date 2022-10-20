package br.com.futurodev.api.controllers;

import br.com.futurodev.api.dto.TelefoneRepresentationModel;
import br.com.futurodev.api.dto.UsuarioRepresentationModel;
import br.com.futurodev.api.input.UsuarioInput;
import br.com.futurodev.api.model.TelefoneModel;
import br.com.futurodev.api.model.UsuarioModel;
import br.com.futurodev.api.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

   @PostMapping(value = "/" , produces = "application/json")
   public ResponseEntity<UsuarioRepresentationModel> cadastrar(@RequestBody UsuarioInput usuarioInput){
       UsuarioModel usu = toDomainObject(usuarioInput);
       cadastroUsuarioService.salvar(usu);
       return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.CREATED);
   }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> atualizar(@RequestBody UsuarioInput usuarioInput){
        UsuarioModel usu = toDomainObject(usuarioInput);
        cadastroUsuarioService.salvar(usu);
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
        cadastroUsuarioService.delete(idUsuario);
        return new ResponseEntity<String>("Usuário Deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        UsuarioModel usu = cadastroUsuarioService.getUserById(idUsuario);
        UsuarioRepresentationModel usuarioRepresentationModel = toModel(usu);
        return new ResponseEntity<UsuarioRepresentationModel>(usuarioRepresentationModel, HttpStatus.OK);
    }

    @GetMapping(value = "/buscaPorNome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioRepresentationModel>> getUserByName(@RequestParam (name = "nome") String nome){
       List<UsuarioModel> usuarios = cadastroUsuarioService.getUserByName(nome);
       List<UsuarioRepresentationModel> usuariosRepresentationModel = toCollectionModel(usuarios);
       return  new ResponseEntity<List<UsuarioRepresentationModel>>(usuariosRepresentationModel, HttpStatus.OK);
    }

    /*Metodo de geração de UsuaraioRepresentatioModel ou  simplismente DTO*/
    private UsuarioRepresentationModel toModel(UsuarioModel usu) {
        UsuarioRepresentationModel usuarioRepresentationModel = new UsuarioRepresentationModel();
        usuarioRepresentationModel.setId(usu.getId());
        usuarioRepresentationModel.setNome(usu.getNome());
        usuarioRepresentationModel.setLogin(usu.getLogin());

        for (int i = 0; i < usu.getTelefones().size(); i++) {
            TelefoneRepresentationModel telefoneRepresentationModel = new TelefoneRepresentationModel();
            telefoneRepresentationModel.setId(usu.getTelefones().get(i).getId());
            telefoneRepresentationModel.setNumero(usu.getTelefones().get(i).getNumero());
            telefoneRepresentationModel.setTipo(usu.getTelefones().get(i).getTipo());

            usuarioRepresentationModel.getTelefones().add(telefoneRepresentationModel);
        }
        return usuarioRepresentationModel;
    }

    /*Metodo de geração de LIST UsuaraioRepresentatioModel ou  simplismente DTO*/
    private List<UsuarioRepresentationModel> toCollectionModel(List<UsuarioModel> usuariosModel){
        return usuariosModel.stream()
                .map(usuarioModel -> toModel(usuarioModel) )
                .collect(Collectors.toList());
    }

    /*METODO CONVERTE UM OBJ USUARIOINPUT EM USUARIOMODEL */
    private UsuarioModel toDomainObject(UsuarioInput usuarioInput){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuarioInput.getId());
        usuarioModel.setNome(usuarioInput.getNome());
        usuarioModel.setLogin(usuarioInput.getLogin());
        usuarioModel.setSenha(usuarioInput.getSenha());

        for (int i = 0; i < usuarioInput.getTelefones().size(); i++) {
            TelefoneModel telefoneModel = new TelefoneModel();
            telefoneModel.setTipo(usuarioInput.getTelefones().get(i).getTipo());
            telefoneModel.setNumero(usuarioInput.getTelefones().get(i).getNumero());
            telefoneModel.setId(usuarioInput.getTelefones().get(i).getId());

            usuarioModel.getTelefones().add(telefoneModel);
        }
        return usuarioModel;
    }
}
