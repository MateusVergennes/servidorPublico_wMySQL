package com.treinamentos.servidorpublicodb.repository;

import com.treinamentos.servidorpublicodb.entity.ServidorPublico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorPublicoRepository extends CrudRepository<ServidorPublico, Long>{

}
