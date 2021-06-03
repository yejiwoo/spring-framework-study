package org.yejiwoo.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yejiwoo.sample.service.MyBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/myboard")
public class MyBoardController {

	@Setter(onMethod_= {@Autowired})
	private MyBoardService myboardService;
	
	//게시물 목록 조회1
	@GetMapping("/list")
	public void showBoardList(Model model) {
		log.info("컨트롤러 - 게시물 목록 조회....");
		model.addAttribute("boardList",myboardService.getBoardList());
	}
	
	
	
}
