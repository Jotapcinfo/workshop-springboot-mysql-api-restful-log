package br.com.portaljmti.log.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.portaljmti.log.domain.model.Entrega;
import br.com.portaljmti.log.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}

}
