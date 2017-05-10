package br.com.anascoding.anahelena;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class FormacaoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao);

        setToolbar();

        // Trata o título e adiciona o botão de voltar
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_title_formacao));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}