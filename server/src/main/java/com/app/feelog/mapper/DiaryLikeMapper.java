package com.app.feelog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryLikeMapper {
    public int selectLikeCount(Long diaryId);

    public int selectReplyCount(Long diaryId);
}
