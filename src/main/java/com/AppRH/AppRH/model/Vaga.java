package com.AppRH.AppRH.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_vaga")
    private String nome;
    @Column(name = "descricao_vaga")
    private String description;
    @Column(name = "data_vaga")
    private String data;
    @Column(name = "salario_vaga")
    private String salario;


    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> canditatos;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getSalario() {
        return salario;
    }


    public void setSalario(String salario) {
        this.salario = salario;
    }


    public List<Candidato> getCanditatos() {
        return canditatos;
    }


    public void setCanditatos(List<Candidato> canditatos) {
        this.canditatos = canditatos;
    }


    public Vaga orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }



}


    

