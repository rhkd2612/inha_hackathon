package com.mumomu.neighborwith.service;

import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.dto.*;
import com.mumomu.neighborwith.repository.PostitRepository;
import com.mumomu.neighborwith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUserInfo(Long userId) {
        Optional<User> optFindUser = userRepository.findById(userId);

        if(optFindUser.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");

        User findUser = optFindUser.get();
        return new UserDto(findUser, findUser.getPostits(), findUser.getSendLetters(), findUser.getReceiveLetters());
    }

    public Long newUser(UserCreateForm userCreateForm) {
        User createdUser = User.newUser(userCreateForm);
        userRepository.save(createdUser);
        return createdUser.getId();
    }
}
