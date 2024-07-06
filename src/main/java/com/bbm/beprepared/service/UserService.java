package com.bbm.beprepared.service;

import com.bbm.beprepared.dto.response.StatsResponse;
import com.bbm.beprepared.model.User;

public interface UserService {

    String createUser(User user);

    User getUserById(Long id);

    StatsResponse getAllStats();
}
