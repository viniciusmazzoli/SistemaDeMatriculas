package com.universidade.matricula;

public class Usuario {
    protected String login;
    protected String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public boolean validarLogin(String login, String senha) {
        // Stub de validação simples
        return this.login != null && this.login.equals(login)
                && this.senha != null && this.senha.equals(senha);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


