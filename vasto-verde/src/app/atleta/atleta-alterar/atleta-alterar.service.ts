import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastService } from './../../shared/services/toast/toast.service';
import { Injectable } from '@angular/core';

import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { AtletaService } from './../atleta.service';
import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';

import { AtletaAlterarComponent } from './atleta-alterar.component';
import { VwAtletaPessoa } from './../../shared/model/vwAtletaPessoa';


@Injectable({
  providedIn: 'root'
})
export class AtletaAlterarService {

  vwAtletaPessoa: VwAtletaPessoa;

  constructor(
    private modalService: BsModalService,
    private atletaService: AtletaService,
    private toast: ToastService,
    private formBuilder: FormBuilder,
    protected validacaoForm: ValidacoesFormService,
  ) { }

  showModal(idAtleta: number) {

    // Abrindo o Modal
    const bsModalRef: BsModalRef = this.modalService.show(AtletaAlterarComponent);

    // Tentando carregar o atleta para abrir o modal.
    this.atletaService.getAtletaPessoaById(idAtleta).subscribe(
      // Caso Sucesso
      (success) => {

        this.vwAtletaPessoa = success;


        // Passando as informacoes
        bsModalRef.content.atleta = this.vwAtletaPessoa;
        bsModalRef.content.formulario = this.criarFormulario(this.vwAtletaPessoa);

        return (bsModalRef.content as AtletaAlterarComponent);

      },
      // Tratamento de erros
      (error) => {
        switch (error['status']) {
          case 404: {
            this.toast.toastWarning('Não encontrado.',
              'O atleta não foi encontrado na base de dados. Atualize a página e tente novamente.');
            break;
          }
          case 500: {
            this.toast.toastErroBanco();
            break;
          }
          default: {
            this.toast.toastErroBanco();
            break;
          }
        }

        console.log('Debug Atleta Alterar Service Error: ', error);
      });
  }

  private criarFormulario(vwAtletaPessoa: VwAtletaPessoa): FormGroup {
    let formulario: FormGroup;

    formulario = this.formBuilder.group({
      // Campos referentes ao Atleta
      idAtleta: [vwAtletaPessoa.idAtleta],
      federacao: [vwAtletaPessoa.federacao],
      confederacao: [vwAtletaPessoa.confederacao],
      dataInicio: [vwAtletaPessoa.dataInicio],
      idGrau: [vwAtletaPessoa.idGrau, Validators.required],
      // Campos referentes ao Responsavel
      nomeResponsavel: [vwAtletaPessoa.nomeResponsavel, Validators.required],
      telefoneResponsavel: [vwAtletaPessoa.telefoneResponsavel, [Validators.required, this.validacaoForm.isValidPhone()]],
      cpfResponsavel: [vwAtletaPessoa.cpfResponsavel, [Validators.required, this.validacaoForm.isValidCpf()]]
    });

    return formulario;
  }


}
