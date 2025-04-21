package com.app.feelog.repository;

import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mapper.AdminMapper;
import com.app.feelog.util.pagination.AdminPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminDAO {
    private final AdminMapper adminMapper;

    public void saveAdmin(MemberVO adminVO) {
        adminMapper.insertAdmin(adminVO);
    }

    public List<MemberVO> findAdminAll(AdminPagination pagination) {
        return adminMapper.selectAdminAll(pagination);
    }

    public int findAdminCount() {
        return adminMapper.selectAdminCount();
    }

    public void deleteAdmin(Long id) {adminMapper.deleteAdmin(id);}
}
