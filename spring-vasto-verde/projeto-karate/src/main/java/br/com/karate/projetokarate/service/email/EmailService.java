package br.com.karate.projetokarate.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/enviarEmail", method = RequestMethod.POST)
    public ResponseEntity<String> enviarEmail(@RequestBody EmailModelo email) {

        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setFrom(email.getRemetente());
        mensagem.setTo(email.getDestinatario());
        mensagem.setSubject(email.getAssunto());
        mensagem.setText(email.getMensagem());

        try {
            

            mailSender.send(mensagem);
            return new ResponseEntity<String>("E-mail enviado com sucesso!", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Debug erro -> "  + e.getStackTrace());
            System.out.println("Debug erro -> "  + e.getMessage());
            return new ResponseEntity<String>("Falha ao enviar", HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo interno para enviar email caso dê erros

}