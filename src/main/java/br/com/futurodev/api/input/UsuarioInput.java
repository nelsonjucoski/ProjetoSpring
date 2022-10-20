package br.com.futurodev.api.input;

import br.com.futurodev.api.dto.TelefoneRepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInput {
    private Long id;
    private String nome;
    private String login;
    private String senha;

    private List<TelefoneRepresentationModel> telefones = new ArrayList<TelefoneRepresentationModel>();

    //Getters e Setters


    public List<TelefoneRepresentationModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneRepresentationModel> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
