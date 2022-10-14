package br.com.futurodev.api.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "usuario")
public class UsuarioModel {

    @Id
    @SequenceGenerator(name= "seq_usuario", sequenceName = "seq_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String login;

    private String senha;

    private String nome;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TelefoneModel> telefones = new ArrayList<TelefoneModel>();

    public List<TelefoneModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneModel> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
