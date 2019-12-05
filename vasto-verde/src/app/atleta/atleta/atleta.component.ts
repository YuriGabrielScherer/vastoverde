import { PessoaService } from './../../pessoa/pessoa.service';
import { AtletaService } from './../atleta.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-atleta',
  templateUrl: './atleta.component.html',
  styleUrls: ['./atleta.component.scss'],
  preserveWhitespaces: true
})
export class AtletaComponent implements OnInit {

  // Mascara para o CPF
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  public maskData = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];

  constructor(
    private atletaService: AtletaService,
    private pessoa: PessoaService
  ) { }

  teste: any[];

  ngOnInit() {



  }

  funcao() {
    this.pessoa.list().subscribe((dados) => {
      this.teste = dados;
    })

  }

}
