package com.treinamentos.servidorpublicodb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treinamentos.servidorpublicodb.entity.ServidorPublico;
import com.treinamentos.servidorpublicodb.repository.ServidorPublicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServidorPublicoServiceImpl implements ServidorPublicoService {
	@Autowired
	private ServidorPublicoRepository servidorRepository; // quem tem acesso aos dados eh a camada de servico que quem tem acesso a ela eh a de aplciacao(repository)

	@Override
	public List<ServidorPublico> listAll() {
		List<ServidorPublico> servidorespublicos = new ArrayList<>();
		servidorRepository.findAll().forEach(servidorespublicos::add);//cada servidor publico encontrado, ele adiciona
		return servidorespublicos;
	}

	@Override
	public Optional<ServidorPublico> listByMatricula(long matricula) {
		return servidorRepository.findById(matricula);
	}

	@Override
	public void save(ServidorPublico servidor) {
		servidorRepository.save(servidor);
	}

	@Override
	public void update(ServidorPublico servidor) {
		//aqui com Optional checamos se ele existe no banco de dados para poder dar o update
		Optional<ServidorPublico> servidorEcontrado =
				servidorRepository.findById(servidor.getMatricula());

		servidorEcontrado.ifPresent(
				p -> {
					servidorRepository.save(servidor);
				}
		);
	}

	@Override
	public void delete(long matricula) {
		Optional<ServidorPublico> servidorEcontrado =
				servidorRepository.findById(matricula);

		servidorEcontrado.ifPresent(
				p -> {
					servidorRepository.delete(servidorEcontrado.get());
				}
		);
	}

}
