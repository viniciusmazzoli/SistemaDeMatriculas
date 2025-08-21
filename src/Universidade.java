
package com.universidade.matricula;

import java.util.List;
import java.util.ArrayList;

public class Universidade {
    private String nome;
    private List<Curso> cursos;

    public Universidade(String nome) {
        this.nome = nome;
        this.cursos = new ArrayList<>();
    }

    public void gerarCurriculo(String semestre) {
        // Stub do método
        System.out.println("Currículo gerado para o semestre: " + semestre);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
    }
}


