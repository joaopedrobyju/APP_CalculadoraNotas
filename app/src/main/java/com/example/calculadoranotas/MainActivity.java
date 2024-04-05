package com.example.calculadoranotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usuarioText;
    private CheckBox logadoCheckBox;
    private Button entrarButton;

    private SharedPreferences sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("arquivo_conf", Context.MODE_PRIVATE);

        usuarioText = findViewById(R.id.usuarioText);
        logadoCheckBox = findViewById(R.id.logadoCheckBox);
        entrarButton = findViewById(R.id.entrarButton);

        entrarButton.setOnClickListener(this);

        verificarUsuarioLogado();
    }

    private void salvarUsuarioLogado(){
        String nomeUsuario = usuarioText.getText().toString();

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("usuario", nomeUsuario);
            editor.putBoolean("logado", true);
            editor.apply();
        }

    private void verificarUsuarioLogado(){
        boolean logado = sp.getBoolean("logado", false);

        if (logado){
            abrirTela();
        }
    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.entrarButton){
            if (usuarioText.getText().toString().isEmpty()){
                //mensagem para usuario preencher o nome
            }else{
                if (this.logadoCheckBox.isChecked()){
                    Toast.makeText(this,"Logado",Toast.LENGTH_LONG).show();
                    salvarUsuarioLogado();
                }
            }
            abrirTela();
        }
    }

    private void abrirTela() {
        Intent telaHome = new Intent(this,HomeActivity.class);
        startActivity(telaHome);

        finish();


    }


}