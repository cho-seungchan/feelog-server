package com.app.feelog.mapper;

import com.app.feelog.domain.dto.AdminDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.util.AdminPagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
//    관리자 추가
    public void insertAdmin(MemberVO adminVO);

    public List<AdminDTO> selectAdminAll(AdminPagination adminPagination);

    public int selectAdminCount();
}
