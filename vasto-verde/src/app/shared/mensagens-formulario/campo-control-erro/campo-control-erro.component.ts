import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-campo-control-erro',
  templateUrl: './campo-control-erro.component.html',
  styleUrls: ['./campo-control-erro.component.scss']
})
export class CampocontrolerroComponent implements OnInit {

  @Input() mostraErro;
  @Input() msgErro;

  constructor() { }

  ngOnInit() {
  }

}
