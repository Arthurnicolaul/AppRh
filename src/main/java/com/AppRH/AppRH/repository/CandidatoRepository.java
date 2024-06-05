package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.AppRH.AppRH.model.Candidato;
import com.AppRH.AppRH.model.Vaga;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, Long> {
    // No need to declare findById, it's already provided by JpaRepository
    public Candidato findById(long id);
    public Iterable<Candidato>findByVaga(Vaga vaga);
    public Candidato findByRg(String rg);
    public List<Candidato>findByNomeCandidato(String nome);
    

}