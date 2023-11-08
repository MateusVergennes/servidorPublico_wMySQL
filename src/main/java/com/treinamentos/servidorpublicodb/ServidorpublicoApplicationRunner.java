package com.treinamentos.servidorpublicodb;


import com.treinamentos.servidorpublicodb.entity.ServidorPublico;
import com.treinamentos.servidorpublicodb.service.ServidorPublicoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Configuration//para o springBoot rodar a partir do momento que a aplicacao for rodada
public class ServidorpublicoApplicationRunner implements CommandLineRunner {
	
	private ServidorPublicoService servidorService; // para acessar o service que tem acesso aos dados
	
	@Autowired
	public void setServidorPublicoService(ServidorPublicoService servidorService) {
		this.servidorService = servidorService;//metodo fracamente acoplado
	}//assim ele ja vai carregar o objeto servidorService com os metodos criados
	
	@PostConstruct// p/ o metodo ser chamado
	public void listAll() {
		List<ServidorPublico> servidorespublicos = servidorService.listAll();
		
		if (servidorespublicos.size() != 0) {
			System.out.println("##################################################");
			servidorespublicos.forEach(
				servidor -> {
					System.out.println(servidor.getMatricula());
					System.out.println(servidor.getNome());
					System.out.println(servidor.getFoto());
					System.out.println(servidor.getOrgao());
				}
			);
		}else {
			System.out.println("Arquivo JSON Vazio");
		}
	}
	
	@PostConstruct
	public void listByMatricula() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matricula procurada"));
		
		Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);
		
		if (servidorEncontrado.isPresent()) {
			System.out.println("##################################################");
			System.out.println(servidorEncontrado.get().getMatricula());//aqui usa o get, para pegar o objeto retornado, ai sim a chave do obj
			System.out.println(servidorEncontrado.get().getNome());		//isso por estar dentro de um Optional
			System.out.println(servidorEncontrado.get().getFoto());
			System.out.println(servidorEncontrado.get().getOrgao());
		}else {
			System.out.println("Nao existe o servidro com a matricula informada");
		}
	}

	public void save() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matricula do novo Servidor"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

		if (!servidorEncontrado.isPresent()){
			ServidorPublico novoservidor = new ServidorPublico();
			novoservidor.setMatricula(matricula);

			String nome = JOptionPane.showInputDialog("Digite o nome do servidor");
			novoservidor.setNome(nome);
			String foto = JOptionPane.showInputDialog("Digite o nome da foto do servidor");
			novoservidor.setFoto(foto);
			String orgao = JOptionPane.showInputDialog("Digite o orgao do servidor");
			novoservidor.setOrgao(orgao);
			String vinculo = JOptionPane.showInputDialog("Digite o vinculo do servidor");
			novoservidor.setVinculo(vinculo);
			String cargo = JOptionPane.showInputDialog("Digite o cargo do servidor");
			novoservidor.setCargo(cargo);
			String lotacao = JOptionPane.showInputDialog("Digite a lotacao do servidor");
			novoservidor.setLotacao(lotacao);
			String exercicio = JOptionPane.showInputDialog("Digite o exercicio do servidor");
			novoservidor.setExercicio(exercicio);
			String email = JOptionPane.showInputDialog("Digite o email do servidor");
			novoservidor.setEmail(email);
			String telefone = JOptionPane.showInputDialog("Digite o telefone do servidor");
			novoservidor.setTelefone(telefone);
			String celular = JOptionPane.showInputDialog("Digite o celular do servidor");
			novoservidor.setCelular(celular);
			String cpf = JOptionPane.showInputDialog("Digite o CPF do servidor");
			novoservidor.setCpf(cpf);
			String naturalidade = JOptionPane.showInputDialog("Digite a naturalidade do servidor");
			novoservidor.setNaturalidade(naturalidade);

			servidorService.save(novoservidor);
		}else{
			System.out.println("Servidor Publico ja existe");
		}
	}

	public void update(){
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matricula do Servidor a ser alterada"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

		if (servidorEncontrado.isPresent()){

			String nome = JOptionPane.showInputDialog("Digite o nome do servidor");
			servidorEncontrado.get().setNome(nome);

			servidorService.update(servidorEncontrado.get());
		}else{
			System.out.println("Servidor nao encontrado");
		}
	}
	@PostConstruct
	public void delete() {
		long matricula = Long.parseLong(JOptionPane.showInputDialog("Digite a matricula do Servidor a ser excluido"));
		Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);

		if (servidorEncontrado.isPresent()){
			servidorService.delete(matricula);
		}else{
			System.out.println("Servidor nao Encontrado");
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
