package com.app.feelog.repository;

import com.app.feelog.domain.dto.SubscribeDTO;
import com.app.feelog.domain.enumeration.SubscribeStatus;
import com.app.feelog.domain.vo.SubscribeVO;
import com.app.feelog.mapper.SubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubscribeDAO {

    private final SubscribeMapper subscribeMapper;

    // 1. 구독 여부 확인
    public boolean selectIsSubscribed(Long memberId, Long channelId) {
        return subscribeMapper.isSubscribed(memberId, channelId);
    }

    // 2. 구독
    public Long insertSubscribe(Long memberId, Long channelId) {
        SubscribeDTO subscribeDTO = new SubscribeDTO();
        subscribeDTO.setMemberId(memberId);
        subscribeDTO.setChannelId(channelId);

        subscribeMapper.subscribe(subscribeDTO);

        return subscribeDTO.getId(); // 여기서 id를 받아서 리턴!!
    }

    // 3. 구독 취소
    public void deleteSubscribe(Long memberId, Long channelId) {
        subscribeMapper.unsubscribe(memberId, channelId);
    }

    public Optional<SubscribeVO> findSubscribeOne(@Param("memberId") Long memberId, @Param("channelId") Long channelId){
        return subscribeMapper.selectSubscribeOne(memberId, channelId);
    };

    public int findSubscribeCount(Long channelId){
        return subscribeMapper.selectSubscribeCount(channelId);
    };

    public SubscribeDTO findByMemberAndChannel(Long memberId, Long channelId) {
        return subscribeMapper.findByMemberAndChannel(memberId, channelId);
    }

//    public void deleteSubscribe(@Param("memberId") Long memberId, @Param("channelId") Long channelId){
//        subscribeMapper.deleteSubscribe(memberId, channelId);
//    };

    //    박정근 :: 회원ID로 구독중인 채널 ID 조회
    public List<Long> findSubscribeIdsByMemberId(Long memberId){
        return subscribeMapper.selectSubscribeIdsByMemberId(memberId);
    };


}
