package com.app.feelog.service;

import com.app.feelog.domain.dto.AdminListDTO;
import com.app.feelog.domain.dto.MemberDTO;
import com.app.feelog.domain.dto.NoticeDTO;
import com.app.feelog.domain.vo.MemberVO;
import com.app.feelog.repository.AdminDAO;
import com.app.feelog.repository.NoticeDAO;
import com.app.feelog.service.voToDto.AdminService;
import com.app.feelog.util.pagination.AdminPagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO;
    private final NoticeDAO noticeDAO;

    //    MemberVO를 DTO로 변환
    @Override
    public List<MemberDTO> getAdminList(AdminPagination adminPagination) {
        List<MemberVO> memberVOlist = adminDAO.findAdminAll(adminPagination);

        List<MemberDTO> memberDTOList =
                memberVOlist.stream()
                        .map(this::toMemberDTO)
                        .collect(Collectors.toList());
        return memberDTOList;
    }

    //    관리자 추가
    public void saveAdmin(MemberDTO adminDTO) {
        adminDAO.saveAdmin(adminDTO.toVO());
    }

    //    관리자 목록 페이지네이션
    public AdminListDTO getAdminAll(AdminPagination adminPagination) {
        AdminListDTO adminListDTO = new AdminListDTO();

        adminPagination.create(adminDAO.findAdminCount());

        adminListDTO.setAdminPagination(adminPagination);
        adminListDTO.setAdminList(getAdminList(adminPagination));

        return adminListDTO;
    }

    //    관리자 삭제(소프트 딜리트)
    public void deleteAdmin(Long id) {
        adminDAO.deleteAdmin(id);
    }
}



