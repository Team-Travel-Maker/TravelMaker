package com.app.travelmaker.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class ChangePwSendMailService {

    private final JavaMailSender emailsender; // Bean 등록해둔 MailConfig 를 emailsender
    private String  address = "http://localhost:10000/accounts/ok/reset"; // 비밀번호 재설정 주소
    // 메일 내용 작성
    public MimeMessage createMessage(String to, Long id) throws MessagingException, UnsupportedEncodingException {
//		System.out.println("보내는 대상 : " + to);

        MimeMessage message = emailsender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);// 보내는 대상
        message.setSubject("Wegether 비밀번호 재설정");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> TravelMaker 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>항상 당신의 꿈을 응원합니다. 감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>비밀번호 재설정 주소입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "비밀번호 재설정 URL : <strong>";
        msgg += "<a href="+ address + "?id=" + id +">" + "이 곳을 클릭하세요." + "</a></strong><div><br/> "; // 메일에 주소넣기
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("boy9801@naver.com", "TravelMaker_Admin"));// 보내는 사람

        return message;
    }
    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
    public void sendSimpleMessage(String to,Long id) throws Exception {

        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to,id); // 메일 발송
        try {// 예외처리
            emailsender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

    }
}