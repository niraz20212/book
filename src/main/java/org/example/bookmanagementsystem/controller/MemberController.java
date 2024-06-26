package org.example.bookmanagementsystem.controller;

import org.example.bookmanagementsystem.entity.Member;
import org.example.bookmanagementsystem.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/author")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/save")
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);

    }
    @PutMapping("/update")
    public Member updateMember(@RequestBody Member member){
        return memberService.updateMember(member);
    }
    @DeleteMapping
    public void deleteMember(int id){
        memberService.deleteMember(id);
    }
    @GetMapping("/getAll")
    List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }
}
