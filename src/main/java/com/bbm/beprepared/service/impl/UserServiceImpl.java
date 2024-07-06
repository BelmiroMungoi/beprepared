package com.bbm.beprepared.service.impl;

import com.bbm.beprepared.dto.response.StatsResponse;
import com.bbm.beprepared.exception.BadRequestException;
import com.bbm.beprepared.exception.EntityNotFoundException;
import com.bbm.beprepared.model.User;
import com.bbm.beprepared.repository.AlertRepository;
import com.bbm.beprepared.repository.CitizenRepository;
import com.bbm.beprepared.repository.UserRepository;
import com.bbm.beprepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Já existe um usuário com esse e-mail!");
        }
        userRepository.save(user);
        return "Usuário criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuário não encontrado!"));
    }

    @Override
    @Transactional(readOnly = true)
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
