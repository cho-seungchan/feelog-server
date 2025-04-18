// 2025.04.17 조승찬

package com.app.feelog.service;

import com.app.feelog.domain.dto.MemberDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Service
public class KakaoService {

    // 2025.04.17 조승찬 :: Authorization Code로 Access Token 요청
    public String getKakaoAccessToken(String code){
        String accessToken = null;
        String requestURI = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = null;

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=fae1d8d22225221e075546a8f1f4ac4d");
            stringBuilder.append("&redirect_uri=http://localhost:10000/kakao/login");
            stringBuilder.append("&code=" + code);

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();

            if(connection.getResponseCode() == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                JsonElement jsonElement = JsonParser.parseString(result);
                accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();

                bufferedReader.close();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return accessToken;
    }

    // 2025.04.17 조승찬 :: Access Token으로 사용자 정보 요청
    public Optional<MemberDTO> getKakaoInfo(String token){
        log.info("getKakaoInfo: {}", token);
        String accessToken = null;
        String requestURI = "https://kapi.kakao.com/v2/user/me";
        MemberDTO memberDTO = null;

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + token);

            if(connection.getResponseCode() == 200){
                log.info("getKakaoInfo response code: {}", connection.getResponseCode());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                JsonElement jsonElement = JsonParser.parseString(result);
                JsonElement kakaoAccount = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject();
                JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");

                memberDTO = new MemberDTO();
                memberDTO.setMemberEmail(kakaoAccount.getAsJsonObject().get("email").getAsString());
                memberDTO.setMemberNickname(profile.getAsJsonObject().get("nickname").getAsString());

                bufferedReader.close();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(memberDTO);
    }

}
