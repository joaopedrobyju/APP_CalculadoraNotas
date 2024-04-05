package com.example.calculadoranotas;

public class Aluno {
    private String nome;
    private String nomeDisiciplina;
    private double notaAtividade1;
    private double notaAtividade2;
    private double notaProva;
    private int qtdeFaltas;

    public Aluno(String nome, String nomeDisiciplina, double notaAtividade1, double notaAtividade2, double notaProva, int qtdeFaltas) {
        this.nome = nome;
        this.nomeDisiciplina = nomeDisiciplina;
        this.notaAtividade1 = notaAtividade1;
        this.notaAtividade2 = notaAtividade2;
        this.notaProva = notaProva;
        this.qtdeFaltas = qtdeFaltas;
    }

    public double MediaTotal(){

        double pesoAtividade = 0.6;
        double pesoProva = 0.4;

//        double mediaAtividade = (notaAtividade1 + notaAtividade2) * pesoAtividade;
//        double mediaProva = notaProva * pesoProva;
//        double mediaFinal = mediaAtividade + mediaProva;

        double mediaFinal = (notaAtividade1 * pesoAtividade) + (notaAtividade2 * pesoAtividade) + (notaProva * pesoProva);

        return mediaFinal;
    }

    public boolean VerficarAprovado(double mediaFinal){

        if (this.qtdeFaltas > 4) {
            //reprovado
            return false;
        } else if (mediaFinal >= 6.0){
            //aprovado
            return true;
        }else{
            //reprovado por nota
            return false;
        }
    }

}
