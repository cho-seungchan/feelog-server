package com.app.feelog.mapper;

import com.app.feelog.domain.dto.DiarySearchDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryDetailDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.util.pagination.PostPagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface DiaryMapper {

    // 다이어리 등록
    public void insert(DiaryVO diaryVO);

    // 단일 조회 (수정용)
    Optional<DiaryVO> selectById(Long id);

    // 수정
    void update(DiaryVO diaryVO);

    List<DiarySearchDTO> searchDiaries(String keyword);

    List<DiarySearchDTO> getRecentDiaries();

    List<DiarySearchDTO> searchMoreDiaries(@Param("keyword") String keyword,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);


    List<DiaryVO> findVisibleDiaries(@Param("channelOwnerId") Long channelOwnerId,
                                     @Param("viewerId") Long viewerId);

    //    박정근 :: 다이어리 페이지네이션
    public List<DiaryJoinDTO> selectDiaryListPagination(PostPagination postPagination);

    public List<DiaryJoinDTO> selectDiaryListPaginationAllAndSubscribe(@Param("postPagination") PostPagination postPagination, @Param("memberId") Long memberId);

    public int selectDiaryCount();

    public int selectDiaryCountAllAndSubscribe(Long memberId);

    public List<DiaryJoinDTO> selectDiaryListPaginationAll(PostPagination postPagination);

    public int selectDiaryCountAll();

    //    박정근 :: 다이어리 상세보기
    public DiaryDetailDTO selectDiaryDetailByDiaryId(Long diaryId);

    public List<DiaryJoinDTO> selectRandomDiary();

    public int selectDiaryLikeCount(Long diaryId);

    public int selectDiaryReplyCount(Long diaryId);

    public void addReadCount(Long diaryId);
}
