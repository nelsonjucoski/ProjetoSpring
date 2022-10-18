package br.com.futurodev.api.repository;

import br.com.futurodev.api.model.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> getUserByName(String nome);
}
