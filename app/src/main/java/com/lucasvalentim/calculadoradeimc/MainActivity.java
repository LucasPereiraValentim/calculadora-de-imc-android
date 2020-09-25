package com.lucasvalentim.calculadoradeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText etAltura;
    private EditText etPeso;
    private Button btnCalcular;
    private Button btnLimpar;
    private TextView tvResultado;
    private TextView tvClassificao;
    private boolean camposValidados = false;
    private double altura;
    private double peso;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAltura        = findViewById(R.id.etAltura);
        etPeso          = findViewById(R.id.etPeso);
        btnCalcular     = findViewById(R.id.btnCalcular);
        btnLimpar       = findViewById(R.id.btnLimpar);
        tvResultado     = findViewById(R.id.tvResultado);
        tvClassificao   = findViewById(R.id.tvClasssificao);



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    esconderTeclado(v);
                    camposValidados = validarCampos();

                    if (camposValidados){

                        DecimalFormat df = new DecimalFormat("#.00");

                        altura = Double.parseDouble(etAltura.getText().toString());

                        peso = Double.parseDouble(etPeso.getText().toString());

                        total = peso / Math.pow(altura, altura);



                        if (total < 18.5) {
                            tvResultado.setText("Seu IMC: " + df.format(total));
                            tvClassificao.setText("Magresa");
                        } else if (total >= 18.5 && total <= 24.9) {
                            tvResultado.setText("Seu IMC: " + df.format(total));
                            tvClassificao.setText("Normal");
                        } else if (total >= 25.0 && total <= 29.9) {
                            tvResultado.setText("Seu IMC: " + df.format(total));
                            tvClassificao.setText("Sobrepeso");
                        } else if (total >= 30.0 && total <= 39.9) {
                            tvResultado.setText("Seu IMC: " + df.format(total));
                            tvClassificao.setText("Obesidade");
                        } else if (total > 40) {
                            tvResultado.setText("Seu IMC: " + df.format(total));
                            tvClassificao.setText("Obesidade grave");
                        }
                    } else {
                        Toast.makeText(getApplication(), "Preecha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(getApplication(), "Preecha os campos em vermelho", Toast.LENGTH_LONG).show();

                }

            }
        });
        //Limpar campos
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAltura.setText("");
                etPeso.setText("");
                tvClassificao.setText("");
                tvResultado.setText("");

            }
        });


    }
    //Validar campos
    private boolean validarCampos() {
        boolean retorno = false;

        if (!TextUtils.isEmpty(etAltura.getText().toString())){
            retorno = true;
        } else {
            etAltura.setError("Preecha este campo");
            retorno = false;
        }

        if (!TextUtils.isEmpty(etPeso.getText().toString())){
            retorno = true;
        } else {
            etPeso.setError("Preecha este campo");
            retorno = false;
        }
        return retorno;
    }


    public void esconderTeclado(View view){
        if(view!= null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}