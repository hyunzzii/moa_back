package com.example.moa.service.recruit;

import com.example.moa.domain.Recruit;
import com.example.moa.domain.RecruitUser;
import com.example.moa.domain.Role;
import com.example.moa.domain.User;
import com.example.moa.dto.recruit.RecruitModifyDto;
import com.example.moa.dto.recruit.RecruitRequestDto;
import com.example.moa.dto.recruit.RecruitResponseDto;
import com.example.moa.exception.NotFindRecruitException;
import com.example.moa.repository.RecruitRepository;
import com.example.moa.repository.RecruitUserRepository;
import com.example.moa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private final RecruitRepository recruitRepository;

    @Autowired
    private final RecruitUserRepository recruitUserRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Recruit saveRecruit(RecruitRequestDto requestDto) {
        User writer = userRepository.findByEmail(requestDto.getWriterEmail())
                .orElse(null);
        return recruitRepository.save(requestDto.toEntity(writer));
    }

    @Override
    public RecruitUser saveRecruitAdmin(Recruit savedRecruit) {
        return recruitUserRepository.save(
                RecruitUser.builder()
                        .id(savedRecruit.getRecruitId())
                        .recruit(savedRecruit)
                        .user(savedRecruit.getWriter())
                        .role(Role.ADMIN)
                        .build());
    }

    @Override
    public Recruit update(RecruitModifyDto recruitModifyDto){
        Long id = recruitModifyDto.getRecruitId();
        Recruit recruit = recruitRepository.findByRecruitId(id)
                .orElseThrow(()->new NotFindRecruitException(id + " recruit not found"));

        return recruit.update(recruitModifyDto);
    }

    @Override
    public void delete(Long id){
        Recruit recruit = recruitRepository.findByRecruitId(id)
                .orElseThrow(()->new NotFindRecruitException(id + " recruit not found"));
        recruitRepository.delete(recruit);
    }

    @Override
    public List<RecruitResponseDto> findAllDesc() {
        return recruitRepository.findAll()
                .stream()
                .map(RecruitResponseDto::from)
                .collect(Collectors.toList());
    }

}

