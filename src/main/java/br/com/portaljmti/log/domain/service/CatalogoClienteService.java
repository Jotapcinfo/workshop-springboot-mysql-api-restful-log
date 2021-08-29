package br.com.portaljmti.log.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.portaljmti.log.domain.exception.NegocioException;
import br.com.portaljmti.log.domain.model.Cliente;
import br.com.portaljmti.log.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
