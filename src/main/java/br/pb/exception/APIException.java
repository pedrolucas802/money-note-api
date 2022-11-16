package br.pb.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.core.Response;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class APIException  extends RuntimeException {

    private Response.Status status;
    private String error_message;

    public APIException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }

    public APIException(String message, String error_message, Response.Status status) {
        super(message);
        this.status = status;
        this.error_message = error_message;
    }

    public static APIException throwException(Exception e) {

        if(e instanceof APIException)
            return (APIException) e;

        return new APIException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
