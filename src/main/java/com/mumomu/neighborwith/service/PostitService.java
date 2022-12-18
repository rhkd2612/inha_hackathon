package com.mumomu.neighborwith.service;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.dto.*;
import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.postittype.CourierPostit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import com.mumomu.neighborwith.entity.postittype.SharePostit;
import com.mumomu.neighborwith.repository.PostitRepository;
import com.mumomu.neighborwith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostitService {
    private final PostitRepository postitRepository;
    private final UserRepository userRepository;
    private final LetterService letterService;

    public PostitDto getPostit(Long postitId) {
        Optional<Postit> optFindPostit = postitRepository.findById(postitId);

        if(optFindPostit.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");

        Postit findPostit = optFindPostit.get();
        return new PostitDto(findPostit);
    }

    public List<PostitDto> getPostitList(PostitListForm postitListForm) {
        String buildingAddress = userRepository.findById(postitListForm.getUserId()).get().getBuildingAddress();

        List<Postit> allByBuildingAddress = postitRepository.findAllByBuildingAddress(buildingAddress);
        List<PostitDto> ret = allByBuildingAddress.stream().map(PostitDto::new).collect(Collectors.toList());

        if(!postitListForm.getDtype().equals("recent")){
            ret = allByBuildingAddress.stream().filter(p -> p.getDtype()
                    .equals(postitListForm.getDtype())).map(PostitDto::new).collect(Collectors.toList());
        }

        Collections.sort(ret, new Comparator<PostitDto>() {
            @Override
            public int compare(PostitDto p1, PostitDto p2) {
                return p2.getCreateTime().compareTo(p1.getCreateTime());
            }
        });

        return ret;
    }

    public Long newPostit(PostitCreateForm postitCreateForm) {
        Postit createdPostit = Postit.newPost(postitCreateForm);
        createdPostit.setCreateTime(new Date());

        Optional<User> writeUser = userRepository.findById(postitCreateForm.getUserId());

        if(writeUser.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 유저 아이디입니다.");

        createdPostit.setUser(writeUser.get());
        createdPostit.setBuildingAddress(writeUser.get().getBuildingAddress());

        writeUser.get().addPostit(createdPostit);

        postitRepository.save(createdPostit);

        return createdPostit.getId();
    }

    public void participatePostit(PostitParticipateForm postitParticipateForm) {
        Optional<User> optParticipateUser = userRepository.findById(postitParticipateForm.getUserId());
        Optional<Postit> optPostit = postitRepository.findById(postitParticipateForm.getId());

        if(optParticipateUser.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 유저 아이디입니다.");
        if(optPostit.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 게시글 아이디입니다.");

        User participateUser = optParticipateUser.get();
        Postit postit = optPostit.get();

        boolean success = true;

        switch(postit.getDtype()){
            case "SharePostit":
                success = ((SharePostit)postit).participant();
                break;
            case "DeliveryPostit":
                success = ((DeliveryPostit)postit).participant();
                break;
            case "CourierPostit":
                success = ((CourierPostit)postit).participant();
                break;
        }

        if(success) {
            letterService.newLetter(
                    new LetterCreateForm(participateUser.getId(), postit.getUser().getId(), postit.getTitle(), "활동에 참여하였습니다.", false)
            );
        }
    }
}
