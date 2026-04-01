# MAIL SENDER

## 1. Add dependency

````
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
````

## 2. Configure email (Gmail example, properties)

````
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seu-email@gmail.com
spring.mail.password=sua-app-password

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
````

> ⚠️ MUITO IMPORTANTE (Gmail)
Active two step verification and
Generate App Password (not yout normal password)

-  Path: 
Google Accoubt → Security → App Password

## 3. Implement EmailService (correct version)

````JAVA
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String to, String subject, String message) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(message);

            mailSender.send(mail);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
````

## 4. Example usage

````JAVA
emailService.send(
    "user@email.com",
    "Lembrete de agendamento",
    "Seu agendamento é amanhã às 14:00"
);
````

## 5. (Advanced) HTML email support

- If you want nicer emails:

````JAVA
MimeMessage mimeMessage = mailSender.createMimeMessage();
MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

helper.setTo(to);
helper.setSubject(subject);
helper.setText("<h1>" + message + "</h1>", true);

mailSender.send(mimeMessage);
````
