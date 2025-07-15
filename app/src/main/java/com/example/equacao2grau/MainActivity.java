package com.example.equacao2grau;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.equacao2grau.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // botão calcular
        binding.btnCalcular.setOnClickListener(view -> {

            // coleta os valores digitados pelo usuário
            String ValorA = binding.valorA.getText().toString();
            String ValorB = binding.valorB.getText().toString();
            String ValorC = binding.valorC.getText().toString();

            // verifica se os valores são vazios
            if (ValorA.isEmpty() || ValorB.isEmpty() || ValorC.isEmpty()) {
                //Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();

                // exibe uma mensagem de alerta usando AlertDialog
                new AlertDialog.Builder(this)
                        .setTitle("Aviso")
                        .setMessage("Foi identificado que há campo(s) vazio(s), esse(s) vai(ão) ser(ão) preenchido(s) por 0. Deseja continuar?")
                        .setPositiveButton("Sim", (dialog, which) -> {
                            if(ValorA.isEmpty()){
                                binding.valorA.setText("0");
                            }
                            if(ValorB.isEmpty()){
                                binding.valorB.setText("0");
                            }
                            if(ValorC.isEmpty()){
                                binding.valorC.setText("0");
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();

            // verifica se o valor de A é 0
            }else if(Double.parseDouble(ValorA) == 0){

                // esconde o teclado
                InputMethodManager esconderTeclado = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                esconderTeclado.hideSoftInputFromWindow(binding.valorA.getWindowToken(), 0);

                // exibe uma mensagem de erro usando snackbar
                Snackbar.make(view, "O valor de A não pode ser 0", Snackbar.LENGTH_SHORT)
                        .setAction("Corrigir", v -> {
                            binding.valorA.setText("");
                            binding.valorA.requestFocus();
                            esconderTeclado.showSoftInput(binding.valorA, InputMethodManager.SHOW_IMPLICIT);
                        }).show();
            }else{

                // coleta os valores digitados pelo usuário e converte para double
                double a = Double.parseDouble(binding.valorA.getText().toString());
                double b = Double.parseDouble(binding.valorB.getText().toString());
                double c = Double.parseDouble(binding.valorC.getText().toString());

                // calcula o delta
                double delta = (b * b) - (4 * a * c);

                // verifica se possui raizes reais
                if(delta < 0){
                    binding.auxiliarResultado.setText("Não possui raizes reais");
                }else if(delta == 0){
                    binding.auxiliarResultado.setText("Possui apenas uma raiz real");
                }else{
                    binding.auxiliarResultado.setText("Possui duas raizes reais");
                }

                // calcula as raizes
                double x1 = ((-b + Math.sqrt(delta)) / (2 * a));
                double x2 = ((-b - Math.sqrt(delta)) / (2 * a));

                // exibe as raizes
                binding.x1.setText(String.format("%.2f",x1));
                binding.x2.setText(String.format("%.2f",x2));
            }
        });

        // botão limpar
        binding.btnLimpar.setOnClickListener(view -> {
            binding.valorA.setText("");
            binding.valorB.setText("");
            binding.valorC.setText("");
            binding.auxiliarResultado.setText("");
            binding.x1.setText("");
            binding.x2.setText("");
            binding.valorA.requestFocus();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}