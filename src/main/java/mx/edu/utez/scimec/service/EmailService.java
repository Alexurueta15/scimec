package mx.edu.utez.scimec.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import mx.edu.utez.scimec.model.Appointment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void confirmAppointment(String to, Appointment appointment) throws MessagingException {
       MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message);
       helper.setTo(to);
       helper.setSubject("SCIMEC CITA REGISTRADA");
       helper.setText("Tu cita ha sido registrada exitosamentefecha\n" +
               "Fecha: " + appointment.getDateTime().toLocalDate().toString()
               + "\nHora: " + appointment.getDateTime().toLocalTime().toString() +
               "GRACIAS POR USAR SCIMEC");
       new Thread(() -> mailSender.send(message)).start();
    }
}
