package com.AppRH.AppRH.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

    public Vaga findById(long id);
    public List<Vaga> findByNome(String nome);
}
