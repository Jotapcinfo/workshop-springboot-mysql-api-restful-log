package br.com.portaljmti.log.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portaljmti.log.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List <Cliente> listar() {
		
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("11 99999-8888");
		cliente1.setEmail("joaosilva@pjmti.com");	
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("José");
		cliente2.setTelefone("11 99999-7777");
		cliente2.setEmail("josesilva@pjmti.com");	
        
		return Arrays.asList(cliente1, cliente2);
	}
}
