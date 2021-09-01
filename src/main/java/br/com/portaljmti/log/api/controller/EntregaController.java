package br.com.portaljmti.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.portaljmti.log.api.assembler.EntregaAssembler;
import br.com.portaljmti.log.api.model.EntregaModel;
import br.com.portaljmti.log.api.model.input.EntregaInput;
import br.com.portaljmti.log.domain.model.Entrega;
import br.com.portaljmti.log.domain.repository.EntregaRepository;
import br.com.portaljmti.log.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable long entregaId) {
		return entregaRepository.findById(entregaId)
				
				.map(entrega -> ResponseEntity.ok(
				 entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}