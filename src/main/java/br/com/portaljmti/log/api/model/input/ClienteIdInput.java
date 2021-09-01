package br.com.portaljmti.log.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {
	
	@NotNull
	private long id;
}
