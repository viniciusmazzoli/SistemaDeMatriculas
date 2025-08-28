
package com.universidade.matricula;

import java.util.Date;

public class PeriodoMatricula {
    private Date inicio;
    private Date fim;

    public PeriodoMatricula(Date inicio, Date fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean estaAtivo() {
        // Stub do método
        Date agora = new Date();
        return agora.after(inicio) && agora.before(fim);
    }

    // Getters e Setters
    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
}


