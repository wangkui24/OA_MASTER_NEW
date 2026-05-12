package com.kwang43.boot.model.response;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SecurityResponse implements Serializable {

    private static final long serialVersionUID = -8028241397871894702L;

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Long expires_in;
    private String nick_name;
    private List<String> permissions;
}
