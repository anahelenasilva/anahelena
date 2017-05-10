package br.com.anascoding.anahelena;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private int menuSelecionado;
    private ActionBarDrawerToggle mDrawerToggle;

    public static final String MENU_SELECIONADO = "menuSelecionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = setToolbar();

        // Configura o menu de navegação
        setDrawerLayout();
    }

    // Salva o estado da activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MENU_SELECIONADO, menuSelecionado);
    }

    // Este evento é necessário por da navegação. Ex: se não for definido este evento, então
    // quando uma tela aberta pelo menu for fechada e a activity principal receber o foco novamente
    // ela será recarregada perdendo o seu estado atual
    // Ex: Se a tela que estiver sendo fechada foi aberta pelo segundo item do menu, ao voltar para
    // a activity principal, a tela que será exibida será o do primeiro menu, pq o estado não foi salvo
    // Este método então mantém o estado da activity sincronizado com o menu
    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    // Este método é o tratamento dos menus definidos na toolbar. Ou seja, este evento será disparado
    // se o botão para abrir o menu receber o click. Não confundir com os itens de menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setDrawerLayout(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setItemIconTintList(null); //Faz com que o menu respeite as cores setadas dos ícones na View
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // Este é o método chamado quando um item de menu recebe um click
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navegar(item);
                return true;
            }
        });
    }

    private void navegar(MenuItem menuItem){
        menuSelecionado = menuItem.getItemId();
        menuItem.setChecked(false); //Remove a marcação que fica sobre o item do menu selecionado
        mDrawerLayout.closeDrawers();

        switch (menuItem.getItemId()) {
            case R.id.menu_projetos:
                Intent projetos = new Intent(this, ProjetosActivity.class);
                getSupportActionBar().setTitle(getResources().getString(R.string.activity_title_projetos));
                startActivity(projetos);
                break;
            case R.id.menu_formacao:
                Intent formacao = new Intent(this, FormacaoActivity.class);
                getSupportActionBar().setTitle(getResources().getString(R.string.activity_title_formacao));
                startActivity(formacao);
                break;
            case R.id.menu_contatos:
                Intent contatos = new Intent(this, ContatosActivity.class);
                getSupportActionBar().setTitle(getResources().getString(R.string.activity_title_contatos));
                startActivity(contatos);
                break;
        }
    }
}