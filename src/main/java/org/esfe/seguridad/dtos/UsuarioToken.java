package org.esfe.seguridad.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class UsuarioToken {

    private String token;

    private UsuarioToken(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public UsuarioToken build() {
            return new UsuarioToken(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}