package com.mumomu.neighborwith.service;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.dto.*;
import com.mumomu.neighborwith.repository.LetterRepository;
import com.mumomu.neighborwith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LetterService {
    private final LetterRepository letterRepository;
    private final UserRepository userRepository;

    public LetterDto getLetterInfo(Long letterId) {
        Optional<Letter> optFindLetter = letterRepository.findById(letterId);

        if(optFindLetter.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 쪽지입니다.");

        Letter findLetter = optFindLetter.get();
        return new LetterDto(findLetter, findLetter.getSender(), findLetter.getReceiver());
    }

    public List<LetterDto> getLetterList(Long userId) {
        Optional<User> optFindUser = userRepository.findById(userId);

        if(optFindUser.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");

        User findUser = optFindUser.get();

        List<LetterDto> ret = findUser.getTotalLetters().stream().map(l -> new LetterDto(l, l.getSender(), l.getReceiver())).collect(Collectors.toList());

        Collections.sort(ret, new Comparator<LetterDto>() {
            @Override
            public int compare(LetterDto l1, LetterDto l2) {
                return l2.getCreateTime().compareTo(l1.getCreateTime());
            }
        });

        return ret;
    }

    public Long newLetter(LetterCreateForm letterCreateForm) {
        User sender = userRepository.findById(letterCreateForm.getSenderId()).get();
        User receiver = userRepository.findById(letterCreateForm.getReceiverId()).get();

        Letter createdLetter = Letter.newLetter(letterCreateForm);
        createdLetter.setCreateTime(new Date());
        createdLetter.setSender(sender);
        createdLetter.setReceiver(receiver);

        sender.addSendLetter(createdLetter);
        receiver.addReceiveLetter(createdLetter);

        letterRepository.save(createdLetter);

        return createdLetter.getId();
    }
}
