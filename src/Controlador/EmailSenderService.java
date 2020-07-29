
package Controlador;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author kike6
 */
public class EmailSenderService {
    private final Properties properties = new Properties();
    private String password="equipos7";
    private Session session;
    private String remitente ="distribuidora.disah@gmail.com";
    
    
    private void init(){
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", remitente);
        properties.put("mail.smtp.clave", password);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.mail.sender","");
        
        
        session = Session.getDefaultInstance(properties);
        
    }
    
    public void inseremail(String destinatario,String nom,String ap,String am){
        String asunto = "Notificasion";
        String cuerpo = "Buen dia"+" "+nom+" "+ap+" "+am+" "+"El departamento de sistemas le notifica que cambia la contraseña cada 7 o 15 dia para evitar el  robos de dicha contraseña";
        
        
        Properties props = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", remitente);
        properties.put("mail.smtp.clave", password);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port",587);
        
        
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        
        try{
            message.setFrom( new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com",remitente,password);
            trans.sendMessage(message, message.getAllRecipients());
            trans.close();
        }catch(MessagingException me){
            me.printStackTrace();
        }
        
        
    }
    
    public void updemail(String destinatario,String nom,String ap,String am){
        String asunto = "Notificasion";
        String cuerpo = "Buen dia"+" "+nom+" "+ap+" "+am+" "+" Usted  a actualizado su contraseña, En caso de que usted no haya realizado el cambio notificar al area de Tic ";
        
        Properties props = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", remitente);
        properties.put("mail.smtp.clave", password);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port",587);
        
        
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        
        try{
            message.setFrom( new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com",remitente,password);
            trans.sendMessage(message, message.getAllRecipients());
            trans.close();
        }catch(MessagingException me){
            me.printStackTrace();
        }
        
        
    }
    
    public void sendEmail(String correo,String nom,String ap,String am){
        init();
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Registro");
            message.setText("Buen dia"+nom+" "+ap+" "+am+" "+"El departamento de sistemas le notifica que cambia la contraseña cada 7 o 15 dia para evitar el  robos de dicha contraseña");
            Transport t = session.getTransport("smtp");
            t.connect((String)properties.get("mail.smtp.user"), "equipos7");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            return;
        }
        
    }
    
    
    
}
