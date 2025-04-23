package com.app.feelog.mapper;

import com.app.feelog.domain.vo.MemberTaskPoolVO;
import com.app.feelog.util.Pagination;
import com.app.feelog.util.pagination.MemberPagination;
import org.apache.ibatis.annotations.Mapper;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberTaskPoolMapper {
    public void insertMemberTaskPool(MemberTaskPoolVO memberTaskPoolVO);

    public List<MemberTaskPoolVO> selectMemberTaskPoolAll(MemberPagination memberPagination);

    public int selectCountAll();

    public void deleteMemberTaskPoolById(Long id);

    public void updateMemberTaskPool(MemberTaskPoolVO memberTaskPoolVO);

    public Optional<MemberTaskPoolVO> selectMemberTaskPoolById(Long id);
}
