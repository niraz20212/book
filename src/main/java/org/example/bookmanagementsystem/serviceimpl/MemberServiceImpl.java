package org.example.bookmanagementsystem.serviceimpl;

import org.example.bookmanagementsystem.entity.Member;
import org.example.bookmanagementsystem.repository.MemberRepository;
import org.example.bookmanagementsystem.service.MemberService;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member addMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            throw new RuntimeException("Member already exists");
        }else {
            return memberRepository.save(member);
        }

    }

    @Override
    public Member updateMember(Member member) {
        Optional<Member> memberOptional = memberRepository.findById(member.getId());
        if (memberOptional.isPresent()) {
            Member memberToUpdate = memberOptional.get();
            memberToUpdate.setName(member.getName());
            memberToUpdate.setEmail(member.getEmail());
            memberToUpdate.setAddress(member.getAddress());
            memberToUpdate.setMobileNumber(member.getMobileNumber());
            return memberRepository.save(memberToUpdate);

        }else {
            throw new RuntimeException("Member does not exist");
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
