package com.app.feelog.repository;

import com.app.feelog.domain.vo.MemberTaskPoolVO;
import com.app.feelog.mapper.MemberTaskPoolMapper;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.MemberPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberTaskPoolDAO {
    private final MemberTaskPoolMapper memberTaskPoolMapper;

    public void saveMemberTaskPool(MemberTaskPoolVO memberTaskPoolVO) {
        memberTaskPoolMapper.insertMemberTaskPool(memberTaskPoolVO);
    }

    public List<MemberTaskPoolVO> findMemberTaskPoolAll(MemberPagination memberPagination){
        return memberTaskPoolMapper.selectMemberTaskPoolAll(memberPagination);
    };

    public int findCountAll(){
        return memberTaskPoolMapper.selectCountAll();
    };

    public void setMemberTaskPool(MemberTaskPoolVO memberTaskPoolVO){
        memberTaskPoolMapper.updateMemberTaskPool(memberTaskPoolVO);
    };


    public void deleteMemberTaskPoolById(Long id){
        memberTaskPoolMapper.deleteMemberTaskPoolById(id);
    };
}
