
package com.universidade.matricula;

import java.util.List;
import java.util.ArrayList;

public class SistemaDeMatriculas {
    private List<Usuario> usuarios;
    private List<PeriodoMatricula> periodosMatricula;

    public SistemaDeMatriculas() {
        this.usuarios = new ArrayList<>();
        this.periodosMatricula = new ArrayList<>();
    }

    public void notificarSistemaDeCobrancas(Aluno aluno, List<Disciplina> disciplinas) {
        // Stub do método
        System.out.println("Sistema de cobranças notificado para o aluno " + aluno.getNome() + " sobre as disciplinas: ");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }

    // Getters e Setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<PeriodoMatricula> getPeriodosMatricula() {
        return periodosMatricula;
    }

    public void setPeriodosMatricula(List<PeriodoMatricula> periodosMatricula) {
        this.periodosMatricula = periodosMatricula;
    }

    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void adicionarPeriodoMatricula(PeriodoMatricula periodo) {
        this.periodosMatricula.add(periodo);
    }
}


