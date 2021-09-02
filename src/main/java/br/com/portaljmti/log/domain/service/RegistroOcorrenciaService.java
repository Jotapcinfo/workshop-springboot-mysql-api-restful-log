package br.com.portaljmti.log.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.portaljmti.log.domain.model.Entrega;
import br.com.portaljmti.log.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService BuscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		
		Entrega entrega = BuscaEntregaService.buscar(entregaId);	
		
		return entrega.adicionarOcorrencia(descricao);
	}
}
