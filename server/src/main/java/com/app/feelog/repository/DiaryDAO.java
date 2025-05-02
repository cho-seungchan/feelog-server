package com.app.feelog.repository;

import com.app.feelog.domain.dto.DiarySearchDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryDetailDTO;
import com.app.feelog.domain.dto.joinDTO.DiaryJoinDTO;
import com.app.feelog.domain.vo.DiaryVO;
import com.app.feelog.mapper.DiaryMapper;
import com.app.feelog.util.pagination.PostPagination;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiaryDAO {
    private final DiaryMapper diaryMapper;

    public void save(DiaryVO diaryVO){
        diaryMapper.insert(diaryVO);
    }

    public Optional<DiaryVO> findById(Long id) {
        return diaryMapper.selectById(id);
    }

    public void update(DiaryVO diaryVO) {
        diaryMapper.update(diaryVO);
    }

    public List<DiarySearchDTO> searchDiaries(String keyword) {
        return diaryMapper.searchDiaries(keyword);
    }

    public List<DiarySearchDTO> getRecentDiaries() {
        return diaryMapper.getRecentDiaries();
    }

    public List<DiarySearchDTO> searchMoreDiaries(String keyword, int limit, int offset){
        return diaryMapper.searchMoreDiaries(keyword,limit,offset);
    }


    public List<DiaryVO> findVisibleDiaries(Long channelOwnerId, Long viewerId) {
        return diaryMapper.findVisibleDiaries(channelOwnerId, viewerId);
    }


    //    박정근 :: 다이어리 페이지네이션
    public List<DiaryJoinDTO> findDiaryListPagination(PostPagination postPagination){
        return diaryMapper.selectDiaryListPagination(postPagination);
    };

    public int selectDiaryCount(){
        return diaryMapper.selectDiaryCount();
    };

    public int selectDiaryCountAllAndSubscribe(Long memberId) {
        return diaryMapper.selectDiaryCountAllAndSubscribe(memberId);
    };

    public List<DiaryJoinDTO> findDiaryListPaginationAllAndSubscribe(@Param("postPagination") PostPagination postPagination, @Param("memberId")Long memberId){
        return diaryMapper.selectDiaryListPaginationAllAndSubscribe(postPagination, memberId);
    };

    public List<DiaryJoinDTO> findDiaryListPaginationAll(PostPagination postPagination){
        return diaryMapper.selectDiaryListPaginationAll(postPagination);
    };

    public int findDiaryCountAll(){
        return diaryMapper.selectDiaryCountAll();
    };

    //    박정근 :: 다이어리 상세보기
    public DiaryDetailDTO findDiaryDetailByDiaryId(Long diaryId){
        return diaryMapper.selectDiaryDetailByDiaryId(diaryId);
    };

    public List<DiaryJoinDTO> findRandomDiary(){
        return diaryMapper.selectRandomDiary();
    };

    public int findDiaryLikeCount(Long diaryId){
        return diaryMapper.selectDiaryLikeCount(diaryId);
    };

    public int findDiaryReplyCount(Long diaryId){
        return diaryMapper.selectDiaryReplyCount(diaryId);
    };

    public void addReadCount(Long diaryId){
        diaryMapper.addReadCount(diaryId);
    };

}
