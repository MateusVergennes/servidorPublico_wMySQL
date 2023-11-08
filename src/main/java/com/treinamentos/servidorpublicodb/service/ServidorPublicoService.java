package com.treinamentos.servidorpublicodb.service;

import com.treinamentos.servidorpublicodb.entity.ServidorPublico;

import java.util.List;
import java.util.Optional;

public interface ServidorPublicoService {
	List<ServidorPublico> listAll();
	Optional<ServidorPublico> listByMatricula(long matricula);//Optional bom para fazer a busca se o elemento realmente existe
																//ele tem um metodo (if_present)

	void save(ServidorPublico servidor);
	void update(ServidorPublico servidor);
	void delete(long matricula);
}
