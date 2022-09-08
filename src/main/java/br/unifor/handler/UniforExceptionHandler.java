package br.unifor.handler;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.unifor.model.dto.ErrorDto;

@Provider
public class UniforExceptionHandler implements ExceptionMapper<EntityNotFoundException>{

	@Override
	public Response toResponse(EntityNotFoundException ex) {		
		var erro = new ErrorDto(ex.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(erro).build();  
	}
	
}
