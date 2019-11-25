import { AbstractControl, Validators } from '@angular/forms';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidacoesFormService {

  constructor() { }

  // Validacao de CPF
  isValidCpf() {

    let cpf = null;

    return (control: AbstractControl): Validators => {
      const cpfAntigo = control.value;

      if (cpfAntigo != null) {
        cpf = cpfAntigo.replace(/[^0-9]+/g, '');
      }

      // console.log(cpf);
      if (cpf) {
        let numbers, digits, sum, i, result, equalDigits;
        equalDigits = 1;
        if (cpf.length < 11) {
          return { cpfNotValid: true };
        }

        for (i = 0; i < cpf.length - 1; i++) {
          if (cpf.charAt(i) !== cpf.charAt(i + 1)) {
            equalDigits = 0;
            break;
          }
        }

        if (!equalDigits) {
          numbers = cpf.substring(0, 9);
          digits = cpf.substring(9);
          sum = 0;
          for (i = 10; i > 1; i--) {
            sum += numbers.charAt(10 - i) * i;
          }

          result = sum % 11 < 2 ? 0 : 11 - (sum % 11);

          if (result !== Number(digits.charAt(0))) {
            return { cpfNotValid: true };
          }
          numbers = cpf.substring(0, 10);
          sum = 0;

          for (i = 11; i > 1; i--) {
            sum += numbers.charAt(11 - i) * i;
          }
          result = sum % 11 < 2 ? 0 : 11 - (sum % 11);

          if (result !== Number(digits.charAt(1))) {
            return { cpfNotValid: true };
          }
          return null;
        } else {
          return { cpfNotValid: true };
        }
      }
      return null;
    };

  }

  // Validacao de Telefone
  isValidPhone() {

    // Retornando o Telefone
    return (control: AbstractControl): Validators => {
      const telefone = control.value;

      // Validando
      var regex = new RegExp('^\\([0-9]{2}\\)(9[0-9]{4}-[0-9]{4})$');

      if (regex.test(telefone) === false) {
        return { phoneNotValid: true };
      }
      return null;
    };
  }
}
