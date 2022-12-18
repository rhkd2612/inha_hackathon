package com.mumomu.neighborwith.service;

import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.dto.PostitCreateForm;
import com.mumomu.neighborwith.entity.dto.PostitListForm;
import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.dto.PostitDto;
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
public class PostitService {
    private final PostitRepository postitRepository;
    private final UserRepository userRepository;

    public List<PostitDto> getPostitList(PostitListForm postitListForm) {
        List<Postit> allByBuildingAddress = postitRepository.findAllByBuildingAddress(postitListForm.getBuildingAddress());
        return allByBuildingAddress.stream().filter(p -> p.getDtype()
                .equals(postitListForm.getDtype())).map(PostitDto::new).collect(Collectors.toList());
    }

    public void newPostit(PostitCreateForm postitCreateForm) {
        Postit createdPostit = Postit.newPost(postitCreateForm);
        createdPostit.setCreateTime(new Date());

        Optional<User> writeUser = userRepository.findById(postitCreateForm.getUserId());

        if(writeUser.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 유저 아이디입니다.");

        createdPostit.setUser(writeUser.get());
        createdPostit.setBuildingAddress(writeUser.get().getBuildingAddress());

        postitRepository.save(createdPostit);
    }
}
