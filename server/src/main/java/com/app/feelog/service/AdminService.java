package com.app.feelog.service;

import com.app.feelog.domain.dto.AdminDTO;
import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.mapper.AdminMapper;
import com.app.feelog.repository.AdminDAO;
import com.app.feelog.util.AdminPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminService {
    private final AdminDAO adminDAO;

    public void saveAdmin(MemberDTO adminVO) {
        adminDAO.saveAdmin(adminVO.toVO());
    }

    public AdminListDTO getAdminAll(AdminPagination adminPagination) {
        AdminListDTO adminListDTO = new AdminListDTO();

        adminPagination.create(adminDAO.findAdminCount());

        adminListDTO.setAdminPagination(adminPagination);
        adminListDTO.setAdminList(adminDAO.findAdminAll(adminPagination));

        return adminListDTO;
    }
}
