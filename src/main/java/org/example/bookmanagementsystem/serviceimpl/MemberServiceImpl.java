package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Member;
import org.example.bookmanagementsystem.repository.BookRepository;
import org.example.bookmanagementsystem.repository.MemberRepository;
import org.example.bookmanagementsystem.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;


    public MemberServiceImpl(MemberRepository memberRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;

    }

    @Override
    public Member addMember(Member member) {
        Member memberOptional = memberRepository.findByEmail(member.getEmail());
        if (memberOptional!=null) {
            throw new RuntimeException("Member already exists");
        } else {
            return memberRepository.save(member);
        }


    }

    @Override
    public void deleteMember(int id) {
        memberRepository.deleteById(id);

    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


}
