package com.example.equacao2grau;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.equacao2grau.databinding.ActivityMainBinding;

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
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            // verifica se o valor de A é 0
            }else if(Double.parseDouble(ValorA) == 0){
                Toast.makeText(this, "O valor de A não pode ser 0", Toast.LENGTH_SHORT).show();
                binding.auxiliarResultado.setText("O valor de A não pode ser 0");
            }else{
                double a = Double.parseDouble(binding.valorA.getText().toString());
                double b = Double.parseDouble(binding.valorB.getText().toString());
                double c = Double.parseDouble(binding.valorC.getText().toString());

                double delta = (b * b) - (4 * a * c);

                // verifica se possui raizes reais
                if(delta < 0){
                    binding.auxiliarResultado.setText("Não possui raizes reais");
                }else if(delta == 0){
                    binding.auxiliarResultado.setText("Possui apenas uma raiz real");
                }else{
                    binding.auxiliarResultado.setText("Possui duas raizes reais");
                }

                double x1 = ((-b + Math.sqrt(delta)) / (2 * a));
                double x2 = ((-b - Math.sqrt(delta)) / (2 * a));

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