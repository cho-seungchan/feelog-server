package com.app.feelog.repository;

import com.app.feelog.domain.dto.AdminDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mapper.AdminMapper;
import com.app.feelog.util.AdminPagination;
import com.app.feelog.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminDAO {
    private final AdminMapper adminMapper;
    private final Pagination pagination;

    public void saveAdmin(MemberVO adminVO) {
        adminMapper.insertAdmin(adminVO);
    }

    public List<AdminDTO> findAdminAll(AdminPagination pagination) {
        return adminMapper.selectAdminAll(pagination);
    }

    public int findAdminCount() {
        return adminMapper.selectAdminCount();
    }
}
