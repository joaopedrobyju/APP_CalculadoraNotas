package com.example.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText nomeAlunoText;
    private EditText nomeDisciplinaText;
    private EditText notaAtividadeNumber;
    private EditText notaAtividade2Number;
    private EditText notaProvaNumber;
    private EditText qtdeFaltasNumber;
    private Button avaliarAlunoButton;
    private Button limparButton;
    private Button deslogarButton;

    private TextView textViewTitulo;
    private SharedPreferences sp;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.avaliarAlunoButton){
            this.avaliarAluno();
        }else if (view.getId() == R.id.limparButton){
            this.LimparCampo();
        }else if (view.getId() == R.id.deslogarButton){
            deleteSharedPreferences("arquivo_conf");
            this.fecharTela();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewTitulo = findViewById(R.id.textViewTitulo);
        nomeAlunoText = findViewById(R.id.nomeAlunoText);
        nomeDisciplinaText = findViewById(R.id.nomeDisciplinaText);
        notaAtividadeNumber = findViewById(R.id.notaAtividadeNumber);
        notaAtividade2Number = findViewById(R.id.notaAtividade2Number);
        notaProvaNumber = findViewById(R.id.notaProvaNumber);
        qtdeFaltasNumber = findViewById(R.id.qtdeFaltasNumber);

        avaliarAlunoButton = findViewById(R.id.avaliarAlunoButton);
        limparButton = findViewById(R.id.limparButton);
        deslogarButton = findViewById(R.id.deslogarButton);

        avaliarAlunoButton.setOnClickListener(this);
        deslogarButton.setOnClickListener(this);
        limparButton.setOnClickListener(this);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);
        alterarTitulo();
    }

    private void alterarTitulo(){

        // Calculadora de Notas - nome do Uusuario
        String nomeUsuario = sp.getString("usuario", "");
        String titulo = "Calculadora de notas - " + nomeUsuario;
        textViewTitulo.setText(titulo);
    }


    private void avaliarAluno(){
        String nome = nomeAlunoText.getText().toString();
        String nomeDisciplina = nomeDisciplinaText.getText().toString();
        double notaA1 = Double.parseDouble(notaAtividadeNumber.getText().toString());
        double notaA2 = Double.parseDouble(notaAtividade2Number.getText().toString());
        double notaP= Double.parseDouble(notaProvaNumber.getText().toString());
        int qtdeFaltas = Integer.parseInt(qtdeFaltasNumber.getText().toString());

        Aluno aluno = new Aluno(nome, nomeDisciplina, notaA1, notaA2, notaP,qtdeFaltas);
        double media = aluno.MediaTotal();
        boolean aprovado = aluno.VerficarAprovado(media);

        String mensagem = "";
        if (aprovado){
            mensagem = "Parabéns querido aluno você está aprovado :)";
        } else {
            mensagem = "Caro aluno, infelizmente você está reprovado :(";
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private void LimparCampo(){
        nomeAlunoText.setText("");
        nomeDisciplinaText.setText("");
        notaAtividadeNumber.setText("");
        notaAtividade2Number.setText("");
        notaProvaNumber.setText("");
        qtdeFaltasNumber.setText("");
    }

    private void fecharTela() {
        Intent telaMain = new Intent(this,MainActivity.class);
        startActivity(telaMain);

        finish();
    }






}