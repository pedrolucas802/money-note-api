package br.pb.exception;

import br.pb.dto.ReturnMsg;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class APIExceptionMapper implements ExceptionMapper<APIException> {

    @Override
    public Response toResponse(APIException e) {

        return Response.status(e.getStatus()).entity(new ReturnMsg(e.getMessage(), e.getError_message(),false)).build();
    }
}

