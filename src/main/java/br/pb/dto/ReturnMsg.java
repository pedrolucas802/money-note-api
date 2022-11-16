package br.pb.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RegisterForReflection
public class ReturnMsg {

    private String msg;
    private Boolean success;
    private Object data;
    private String cause;

    public ReturnMsg(String msg, Boolean success, Object data) {
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public ReturnMsg(String msg, Boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public ReturnMsg(String msg, String cause, Boolean success) {
        this.msg = msg;
        this.cause = cause;
        this.success = success;
    }
}
