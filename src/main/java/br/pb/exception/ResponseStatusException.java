package br.pb.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.vertx.codegen.annotations.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.util.Asserts;
import javax.ws.rs.core.Response;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ResponseStatusException extends RuntimeException {
    private Response.Status status = null;

    @Nullable
    private String reason = "";

    public ResponseStatusException(Response.Status status){
        this(status, (String)null, (Throwable)null);
    }

    public ResponseStatusException(Response.Status status, String reason, @Nullable Throwable cause) {
        super((String)null, cause);
        Asserts.notNull(status, "HttpStatus is required");
        this.status = status;
        this.reason = reason;
    }

    public ResponseStatusException(Response.Status status, @Nullable String reason) {
        this(status, reason, (Throwable)null);
    }

    @Nullable
    public String getReason() {
        return this.reason;
    }

    public String getMessage() {
        String msg = this.status + (this.reason != null ? " \"" + this.reason + "\"" : "");
        return NestedExceptionUtils.buildMessage(msg, this.getCause());
    }
}
