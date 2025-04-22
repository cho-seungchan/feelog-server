package com.app.feelog.service;

import com.app.feelog.domain.dto.ChallengeDiaryDTO;
import com.app.feelog.domain.vo.ChallengeDiaryVO;
import com.app.feelog.repository.ChallengeDiaryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeDiaryServiceImpl implements ChallengeDiaryService {

    private final ChallengeDiaryDAO challengeDiaryDAO;


    @Override
    public void addChallengeDiary(ChallengeDiaryDTO challengeDiaryDTO) {
        challengeDiaryDAO.save(challengeDiaryDTO.toVO());
    }

    @Override
    public ChallengeDiaryDTO findById(Long id) {
        return challengeDiaryDAO.findById(id)
                .map(vo -> {
                    ChallengeDiaryDTO dto = new ChallengeDiaryDTO();
                    dto.setId(vo.getId());
                    dto.setChallengeId(vo.getChallengeId());
                    return dto;
                })
                .orElse(null);
    }
}
