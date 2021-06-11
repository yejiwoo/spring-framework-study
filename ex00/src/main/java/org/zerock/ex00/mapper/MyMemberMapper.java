package org.zerock.ex00.mapper;

import org.zerock.ex00.domain.MyAuthorityVO;
import org.zerock.ex00.domain.MyMemberVO;

public interface MyMemberMapper {

	
	//회원 조회: 회원 권한도 함께 조회 됨(스프링 시큐리티도 사용)
	public MyMemberVO selectMyMember(String  userid);
	
	//회원 등록: 회원 등록 시 회원 권한 추가도 같이 수행
	public Integer insertMyMember(MyMemberVO myMember);
	
	//회원 권한 추가
	public Integer insertMyMemAuthority(MyAuthorityVO myAuthority);
	
	//회원 탈퇴
	
	//휴면계정 전환
}
