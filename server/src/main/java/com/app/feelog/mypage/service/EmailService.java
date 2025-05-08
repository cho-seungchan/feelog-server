package com.app.feelog.mypage.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final HttpSession session;

    // 2025.04.18 조승찬 :: 이메일 회원가입을 위한 이메일 발송
    public void sendCertifyingEmail(String email, HttpServletResponse response) throws MessagingException {
        String code = createCode(); // 인증 코드 생성

        //  쿠키에 인증코드 저장
        Cookie cookie = new Cookie("token", code);
        cookie.setMaxAge(60 * 10);   // 10분동안 유효
        cookie.setPath("/");    // 모든 경로에서 접근 가능
        response.addCookie(cookie);

        //  세션에 사용자 이메일 저장
        session.setAttribute("email", email);

        //  입력받은 주소로 이메일 발송
        String title = "[feelog]회원 가입에 감사드립니다.";

        StringBuilder body = new StringBuilder();

        body.append("<body>");
        body.append("<div style=\"width: 600px; margin: 0px auto\">");
        body.append("<div style=\"background-color: #fff; width: 70%; margin: 30px auto; padding: 60px 30px; text-align: center; border-radius: 8px;\">");
        body.append("<img src='cid:logoImage' alt=\"로고 이미지\" />");
        body.append("<h2 style=\"margin: 80px 0 20px; font-size: 40px; font-weight: 100; letter-spacing: -4px; color: #585858;\">");
        body.append("회원 가입 확인");
        body.append("</h2>");
        body.append("<p style=\"font-size: 14px; line-height: 22px; color: #6f6f6f\">");
        body.append("본 메일은 회원 가입 확인을 위해<br />발송되는 메일입니다.");
        body.append("회원 가입을 완료하시려면<br />‘회원 가입‘ 버튼을 클릭해주세요.");
        body.append("</p>");
        body.append("<a href=\"http://52.78.155.144:10000/join/certified?code=" + code + "\" style=\"text-decoration-line: none; display: block; color: #fff; background: #383838; cursor: pointer; padding: 10px 60px; margin: 80px auto; text-align: center; font-size: 18px;\" rel=\"noreferrer noopener\">");
        body.append("회원 가입");
        body.append("</a>");
        body.append("<div>");
        body.append("<p style=\"font-size: 11px; line-height: 14px; color: #6f6f6f\">");
        body.append("본 이메일은 발송된 지 10분이 지나면 만료됩니다.<br />");
        body.append("문의사항은 contact@feelog.com으로 보내주세요.");
        body.append("</p>");
        body.append("</div>");
        body.append("</div>");
        body.append("</div>");
        body.append("</body>");

        String emailBody = body.toString();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom("feelog <contact@feelog.com>"); // 이메일 발송자
        mimeMessageHelper.setTo(email); // 이메일 수신자
        mimeMessageHelper.setSubject(title);    // 이메일 제목
        mimeMessageHelper.setText(emailBody, true);   // emailBody는 이메일 내용, true는 html 형식인지 묻는거

        // 로고 이미지 첨부
//        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\gb_0900_jfk\\teamproject2\\feelog-server\\server\\src\\main\\resources\\static\\images\\kakao.webp"));
//        mimeMessageHelper.addInline("logoImage", fileSystemResource);

        javaMailSender.send(mimeMessage);
    }


    // 2025.04.19 조승찬 :: 비밀번호 재발급을 위한 이메일 발송
    public void sendPasswordIssue(String email, HttpServletResponse response) throws MessagingException {
        String code = createCode(); // 인증 코드 생성

        //  쿠키에 인증코드 저장
        Cookie cookie = new Cookie("token", code);
        cookie.setMaxAge(60 * 10);   // 10분동안 유효
        cookie.setPath("/");    // 모든 경로에서 접근 가능
        response.addCookie(cookie);

        //  세션에 사용자 이메일 저장
        session.setAttribute("email", email);

        //  입력받은 주소로 이메일 발송
        String title = "[feelog]비밀번호 변경 요청.";

        StringBuilder body = new StringBuilder();

        body.append("<body>");
        body.append("<div style=\"width: 600px; margin: 0px auto\">");
        body.append("<div style=\"background-color: #fff; width: 70%; margin: 30px auto; padding: 60px 30px; text-align: center; border-radius: 8px;\">");
        body.append("<img src='cid:logoImage' alt=\"로고 이미지\" />");
        body.append("<h2 style=\"margin: 80px 0 20px; font-size: 40px; font-weight: 100; letter-spacing: -4px; color: #585858;\">");
        body.append("비밀번호 변경 확인");
        body.append("</h2>");
        body.append("<p style=\"font-size: 14px; line-height: 22px; color: #6f6f6f\">");
        body.append("본 메일은 비밀번호 변경을 위해<br />발송되는 메일입니다.");
        body.append("비밀번호를 변경하려면<br />‘비밀번호 변경‘ 버튼을 클릭해주세요.");
        body.append("</p>");
        body.append("<a href=\"http://52.78.155.144:10000/login/confirm?code=" + code + "\" style=\"text-decoration-line: none; display: block; color: #fff; background: #383838; cursor: pointer; padding: 10px 60px; margin: 80px auto; text-align: center; font-size: 18px;\" rel=\"noreferrer noopener\">");
        body.append("비밀번호 변경");
        body.append("</a>");
        body.append("<div>");
        body.append("<p style=\"font-size: 11px; line-height: 14px; color: #6f6f6f\">");
        body.append("본 이메일은 발송된 지 10분이 지나면 만료됩니다.<br />");
        body.append("문의사항은 contact@feelog.com으로 보내주세요.");
        body.append("</p>");
        body.append("</div>");
        body.append("</div>");
        body.append("</div>");
        body.append("</body>");

        String emailBody = body.toString();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom("feelog <contact@feelog.com>"); // 이메일 발송자
        mimeMessageHelper.setTo(email); // 이메일 수신자
        mimeMessageHelper.setSubject(title);    // 이메일 제목
        mimeMessageHelper.setText(emailBody, true);   // emailBody는 이메일 내용, true는 html 형식인지 묻는거

        // 로고 이미지 첨부
//        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\gb_0900_jfk\\teamproject2\\feelog-server\\server\\src\\main\\resources\\static\\images\\kakao.webp"));
//        mimeMessageHelper.addInline("logoImage", fileSystemResource);

        javaMailSender.send(mimeMessage);
    }

    // 코드 생성
    private String createCode() {
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            code += codes.charAt(random.nextInt(codes.length()));
        }
        return code;
    }
}
