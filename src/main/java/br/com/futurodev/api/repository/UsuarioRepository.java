package br.com.futurodev.api.repository;

import br.com.futurodev.api.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {

}
