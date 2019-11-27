import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-listar-pessoas',
  templateUrl: './listar-pessoas.component.html',
  styleUrls: ['./listar-pessoas.component.scss']
})
export class ListarPessoasComponent implements OnInit {

  constructor(
    private route: Route
  ) { }

  ngOnInit() {

    console.log('NgOnInit');
    console.log(this.route);

  }

}
