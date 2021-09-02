package br.com.portaljmti.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.portaljmti.log.api.assembler.OcorrenciaAssembler;
import br.com.portaljmti.log.api.model.OcorrenciaModel;
import br.com.portaljmti.log.api.model.input.OcorrenciaInput;
import br.com.portaljmti.log.domain.model.Entrega;
import br.com.portaljmti.log.domain.model.Ocorrencia;
import br.com.portaljmti.log.domain.service.BuscaEntregaService;
import br.com.portaljmti.log.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrat(@PathVariable long entregaId,
			
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		
		    Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
				.registrar(entregaId, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);	
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}
}
