package org.zerock.ex00.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class MyMemberVO {

	//book_ex.tbl_member 테이블의 컬럼명과 동일하게 필드이름 설정
	private String userid;
	private String userpw;
	private String userName;
	private Timestamp mregDate;
	private Timestamp mmodDate; //암호 등 계정 정보 수정 날짜시간
	private String mdropFlag; //'0'유지 '1'탈퇴요청
	private boolean enabled; // fasle: 비활성화상태, true: 활성화상태 => 휴면계정, 정지상태로 활용
	
	private List<MyAuthorityVO> authorityList;
}
