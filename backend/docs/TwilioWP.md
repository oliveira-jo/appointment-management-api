# 1. Criar template aprovado (Meta via Twilio)

Twilio Console → Messaging → WhatsApp → Templates

* Template ideal (aprovável)

Use algo simples e neutro:

````
Nome: appointment_reminder

Conteúdo:
Olá {{1}}, lembramos que você possui um agendamento em {{2}} às {{3}}.

Responda para reagendar se necessário.
````

## Regras pra aprovação

- Sem marketing
- Sem emoji exagerado
- Linguagem neutra
- Relacionado a serviço

* Isso aprova rápido

# 2. TWILIO CONFIG
````
@Configuration
public class TwilioConfig {

  @Value("${env.TWILIO_ACCOUNT_SID:}")
  private String accountSid;

  @Value("${env.TWILIO_AUTH_TOKEN:}")
  private String authToken;

  @PostConstruct
  public void init() {
    Twilio.init(accountSid, authToken);
  }
}
````
in 

# 3. TEMPLATE CONFIG  
````java
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppService {

  @Value("${TWILIO_WHATSAPP_NUMBER}")
  private String from;

  public Message sendTemplate(String to, String templateSid, String variablesJson) {

    return Message.creator(
        new PhoneNumber("whatsapp:" + to),
        new PhoneNumber(from),
        null
    )
    .setContentSid(templateSid)
    .setContentVariables(variablesJson)
    .create();
  }
}
````

## Exemplo
````
String variables = """
{
  "1": "João",
  "2": "01/04",
  "3": "16:00"
}
""";
whatsAppService.sendTemplate(
    "+5554999999999",
    "HXxxxxxxxxxxxxxxxxxxxxx", // SID do template
    variables
);
````

# 3. Fluxo completo
## Scheduler
- Class: AppointmentScheduler
  - sendReminderOneDayBefore();
## Orquestrador
- NotificationService

## Arquitetura Final
````
Cliente agenda
      ↓
Salva telefone + opt-in
      ↓
Scheduler (1 dia antes)
      ↓
NotificationService
      ↓
WhatsApp (template)
      ↓
Fallback SMS
      ↓
Fallback Email
````
