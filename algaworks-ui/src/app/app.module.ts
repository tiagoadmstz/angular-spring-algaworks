import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PessoaCadastroComponent } from './pessoas/pessoa-cadastro/pessoa-cadastro.component';
import { PessoasPesquisaComponent } from './pessoas/pessoas-pesquisa/pessoas-pesquisa.component';
import { LancamentoCadastroComponent } from './lancamentos/lancamento-cadastro/lancamento-cadastro.component';
import { LancamentosPesquisaComponent } from './lancamentos/lancamentos-pesquisa/lancamentos-pesquisa.component';
import { CategoriaCadastroComponent } from './categorias/categoria-cadastro/categoria-cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    PessoaCadastroComponent,
    PessoasPesquisaComponent,
    LancamentoCadastroComponent,
    LancamentosPesquisaComponent,
    CategoriaCadastroComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
