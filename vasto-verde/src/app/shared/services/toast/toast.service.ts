import { Injectable } from '@angular/core';

import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private toast: ToastrService,
  ) { }

  public toastSuccess(titulo: string, texto: string) {
    this.toast.success(texto, titulo);
  }

  public toastError(titulo: string, texto: string) {
    this.toast.error(texto, titulo);
  }

  public toastInfo(titulo: string, texto: string) {
    this.toast.info(texto, titulo);
  }

  public toastWarning(titulo: string, texto: string) {
    this.toast.warning(texto, titulo);
  }



}
