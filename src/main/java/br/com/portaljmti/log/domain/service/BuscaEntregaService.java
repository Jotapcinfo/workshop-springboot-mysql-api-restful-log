package br.com.portaljmti.log.domain.service;

import org.springframework.stereotype.Service;

import br.com.portaljmti.log.domain.exception.EntidadeNaoEncontradaException;
import br.com.portaljmti.log.domain.model.Entrega;
import br.com.portaljmti.log.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		
    return entregaRepository.findById(entregaId)
				
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));	
	}
}
