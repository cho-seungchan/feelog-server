package com.app.feelog.converter;

import com.app.feelog.domain.enumeration.MemberNotificationCommunityPost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// 화면에서 전달받은 일반 컨트롤러에 enum 타입을 받을 경우 아래와 같이 구현한다.
@Component // Spring이 알아서 자동으로 convert를 사용하여 해당 객체의 enum필드에 주입해준다.
public class StringToMemberNotificationCommunityPostConverter implements Converter<String, MemberNotificationCommunityPost> {
    @Override
    public MemberNotificationCommunityPost convert(String source) {
        for(MemberNotificationCommunityPost memberNotificationCommunityPost : MemberNotificationCommunityPost.values()) {
            if(memberNotificationCommunityPost.getCode().equals(source)) {
                return memberNotificationCommunityPost;
            }
        }
        throw new IllegalArgumentException("알 수 없는 값: " + source);
    }
}
