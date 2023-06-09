package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView lblCEP, lblLogradouro, lblComplemento, lblBairro, lblCidade, lblEstado;

    EditText CidadeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        lblCEP = findViewById(R.id.lblCEP);
        lblLogradouro = findViewById(R.id.lblLogradouro);
        lblComplemento = findViewById(R.id.lblComplemento);
        lblBairro = findViewById(R.id.lblBairro);
        lblCidade = findViewById(R.id.lblCidade);
        lblEstado = findViewById(R.id.lblEstado);
        btnBuscarCep = findViewById(R.id.btnBuscaCep);

        FloatingActionButton cadastrar = findViewById(R.id.cadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cadastrado com sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCEP.setText(retorno.getCep().toString());
                    lblLogradouro.setText(retorno.getLogradouro().toString());
                    lblComplemento.setText(retorno.getComplemento().toString());
                    lblBairro.setText(retorno.getBairro().toString());
                    CidadeEditText.setText(retorno.getLocalidade().toString());
                    lblEstado.setText(retorno.getUf().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}