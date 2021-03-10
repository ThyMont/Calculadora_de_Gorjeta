package com.thymont.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekPorcentagem;
    private TextView txtGorjeta;
    private TextView txtTotal;
    private TextInputEditText txtValor;
    private TextView txtPorcentagem;
    private Double valor = 0.0;
    private Double gorjeta = 0.0;
    private Integer porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekPorcentagem = findViewById(R.id.seekBarPorcentagem);
        txtGorjeta = findViewById(R.id.txtGorjeta);
        txtTotal = findViewById(R.id.txtTotal);
        txtValor = findViewById(R.id.txtValor);
        txtPorcentagem = findViewById(R.id.txtPorcentagem);
        setListeners();
        render();
    }

    public void setListeners(){
        txtPorcentagem.setText(seekPorcentagem.getProgress() + "%");
        seekPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                render();
            }
        });

        txtValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0){
                    valor = 0.0;
                } else {
                    valor = Double.parseDouble(s.toString());
                }
               render();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    valor = 0.0;
                } else {
                    valor = Double.parseDouble(s.toString());
                }
                render();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void render(){
        txtPorcentagem.setText(porcentagem + "%");
        gorjeta = valor*porcentagem/100;
        txtGorjeta.setText(String.format("R$ %.2f", gorjeta));
        txtTotal.setText(String.format("R$ %.2f", valor + gorjeta));
    }

}