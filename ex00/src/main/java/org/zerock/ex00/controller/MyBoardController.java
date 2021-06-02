package org.zerock.ex00.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex00.common.paging.MyBoardPagingCreatorDTO;
import org.zerock.ex00.common.paging.MyBoardPagingDTO;
import org.zerock.ex00.domain.MyBoardVO;
import org.zerock.ex00.service.MyBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/myboard")
public class MyBoardController {

	@Setter(onMethod_ = @Autowired)
	private MyBoardService myBoardService;
	
	//게시물 목록 조회 1
//	@GetMapping("/list")
//	public void showBoardList(Model model) {
//		log.info("컨트롤러 - 게시물 목록 조회......");
//		model.addAttribute("boardList",myBoardService.getBoardList());
//	}

	//게시물 목록 조회 2 - 페이징 고려
	@GetMapping("/list")
<<<<<<< HEAD
	public void showBoardList(MyBoardPagingDTO myBoardPagingDTO, Model model) {
=======
	public void showBoardList(MyBoardPagingDTO myBoardPagingDTO,Model model) {
>>>>>>> 26dd8fef8f6f29ad2c5becec90b544226e48b820
		log.info("컨트롤러 - 게시물 목록 조회......");
		log.info("컨트롤러에 전달된 사용자의 페이징처리 데이터: "+myBoardPagingDTO);
		model.addAttribute("boardList",myBoardService.getBoardList(myBoardPagingDTO));
		
		Long rowAmountTotal = myBoardService.getRowAmountTotal(myBoardPagingDTO);
		log.info("컨트롤러에 전달된 게시물 총 개수: "+ rowAmountTotal);
		
		MyBoardPagingCreatorDTO myBoardPagingCreatorDTO = new MyBoardPagingCreatorDTO(rowAmountTotal, myBoardPagingDTO);
		
		log.info("컨트롤러에서 생성된 MyBoardPagingCreatorDTO 객체 정보: "+myBoardPagingCreatorDTO.toString());
		model.addAttribute("pagingCreator",myBoardPagingCreatorDTO);
		
		//model.addAttribute("myBoardPagingCreatorDTO", new MyBoardPagingCreatorDTO(rowAmountTotal, myBoardPagingDTO));
		
		log.info("컨트롤러 - 게시물 목록 조회 완료......");
	}
	
	//게시물 등록 - 페이지 호출
	@GetMapping("/register")
	public void showBoardRegisterPage() {
		log.info("컨트롤러 - 게시물 등록 페이지 호출");
	}
	
	//게시물 등록 - 처리
	@PostMapping("/register")
	public String registerNewBoard(MyBoardVO myBoard, RedirectAttributes redirectAttr) {
		
		log.info("컨트롤러 - 게시물 등록:"+ myBoard);
		
		long bno=myBoardService.registerBoard(myBoard);
		
		log.info("등록된 게시물의 bno: "+bno);
		
		//redirectAttr.addFlashAttribute("result", myBoard.getBno());
		redirectAttr.addFlashAttribute("result",bno);
		
		return "redirect:/myboard/list";	// "/myboard/list" controller 호출
	}
	
//	 //게시물 조회-수정 페이지 호출
//    @GetMapping({"/detail","/modify"})
//    public void showBoardDetail(@RequestParam("bno") Long bno, Model model) {
//        log.info("컨트롤러 - 게시물 조회/수정 페이지 호출: "+ bno);
//        model.addAttribute("board", myBoardService.getBoard(bno));
//        log.info("컨트롤러 - 화면으로 전달할 model: "+ model);
//    }
    
    //게시물 조회 페이지 호출
    @GetMapping({"/detail"})
    public void showBoardDetail(@RequestParam("bno") Long bno, 
					    		@ModelAttribute("myBoardPagingDTO") MyBoardPagingDTO myBoardPagingDTO, 
					    		Model model) {
        log.info("컨트롤러 - 게시물 조회 페이지 호출: "+ bno);
        log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+myBoardPagingDTO);
        model.addAttribute("board", myBoardService.getBoard(bno));
        log.info("컨트롤러 - 화면으로 전달할 model: "+ model);
    }
    
    //게시물 수정 페이지 호출
    @GetMapping({"/modify"})
    public void showBoardModify(@RequestParam("bno") Long bno, Model model) {
        log.info("컨트롤러 - 게시물 수정 페이지 호출: "+ bno);
        model.addAttribute("board", myBoardService.getBoard(bno));
        log.info("컨트롤러 - 화면으로 전달할 model: "+ model);
    }
    
    
    //게시물 수정 처리
    @PostMapping("/modify")
    public String modifyBoard(MyBoardVO myBoard, 
    		MyBoardPagingDTO myBoardPagingDTO,//전달된 페이징 값들을 받음
    		RedirectAttributes redirectAttr){ //전달할 페이징 값을 저장하는 객체

        log.info("컨트롤러 - 게시물 수정 전달된 myBoard 값: " + myBoard);
        log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+ myBoardPagingDTO);
        if (myBoardService.modifyBoard(myBoard)) {
        	redirectAttr.addFlashAttribute("result", "success");
        }
        redirectAttr.addAttribute("bno", myBoard.getBno());
        redirectAttr.addAttribute("pageNum", myBoardPagingDTO.getPageNum());
        redirectAttr.addAttribute("rowAmountPerPage", myBoardPagingDTO.getRowAmountPerPage());
        redirectAttr.addAttribute("scope", myBoardPagingDTO.getScope());
        redirectAttr.addAttribute("keyword", myBoardPagingDTO.getKeyword());
        return "redirect:/myboard/detail";
    }
    
  //게시물 삭제 - By 사용자: 실제 삭제는 안됨
<<<<<<< HEAD
//    @Override
//    @PostMapping("/delete")
//    public String setBoardDeleted(@RequestParam("bno") Long bno,
//	    MyBoardPagingDTO myBoardPagingDTO,//전달된 페이징 값들을 받음
//	    RedirectAttributes redirectAttr){ //전달할 페이징 값을 저장하는 객체
//    	
//	    log.info("컨트롤러 - 게시물 삭제(bdelFlag값변경 글번호): " + bno);
//	    log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+ myBoardPagingDTO);
//	
//	    if (myBoardService.setBoardDeleted(bno)) {
//	    	redirectAttr.addFlashAttribute("result", "success");
//	    }
//	    redirectAttr.addAttribute("pageNum", myBoardPagingDTO.getPageNum());
//	    redirectAttr.addAttribute("rowAmountPerPage", myBoardPagingDTO.getRowAmountPerPage());
//	    redirectAttr.addAttribute("scope", myBoardPagingDTO.getScope());
//	    redirectAttr.addAttribute("keyword", myBoardPagingDTO.getKeyword());
//	    return "redirect:/myboard/list";
//    } 
=======
    @PostMapping("/delete")
    public String setBoardDeleted(@RequestParam("bno") Long bno,
    		MyBoardPagingDTO myBoardPagingDTO,//전달된 페이징 값들을 받음
    		RedirectAttributes redirectAttr){ //전달할 페이징 값을 저장하는 객체
    	
	    log.info("컨트롤러 - 게시물 삭제(bdelFlag값변경 글번호): " + bno);
	    log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+ myBoardPagingDTO);
	
	    if (myBoardService.setBoardDeleted(bno)) {
	    	redirectAttr.addFlashAttribute("result", "success");
	    }
	    redirectAttr.addAttribute("pageNum", myBoardPagingDTO.getPageNum());
	    redirectAttr.addAttribute("rowAmountPerPage", myBoardPagingDTO.getRowAmountPerPage());
	    redirectAttr.addAttribute("scope", myBoardPagingDTO.getScope());
	    redirectAttr.addAttribute("keyword", myBoardPagingDTO.getKeyword());
	    return "redirect:/myboard/list";
    } 
>>>>>>> 26dd8fef8f6f29ad2c5becec90b544226e48b820

    
    
  //게시물 삭제 - 특정 게시물 삭제 By 관리자: 실제 삭제 발생
    @PostMapping("/remove")
    public String removeBoard(@RequestParam("bno") Long bno,
						    MyBoardPagingDTO myBoardPagingDTO,//전달된 페이징 값들을 받음
						    RedirectAttributes redirectAttr){ //전달할 페이징 값을 저장하는 객체
	    log.info("컨트롤러 - 게시물 삭제: 삭제되는 글번호: " + bno);
	    log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+ myBoardPagingDTO);
	
	    if (myBoardService.removeBoard(bno)) {
	    	redirectAttr.addFlashAttribute("result", "success");
	    }
	
	    redirectAttr.addAttribute("pageNum", myBoardPagingDTO.getPageNum());
	    redirectAttr.addAttribute("rowAmountPerPage", myBoardPagingDTO.getRowAmountPerPage());
	    redirectAttr.addAttribute("scope", myBoardPagingDTO.getScope());
	    redirectAttr.addAttribute("keyword", myBoardPagingDTO.getKeyword());
	
	    return "redirect:/myboard/list";
    }
    
    //게시물 삭제 – 삭제 설정된 모든 게시물 삭제 - By 관리자: 실제 삭제 발생
    @PostMapping("/removeAll")
    public String removeAllDeletedBoard(MyBoardPagingDTO myBoardPagingDTO,//전달된 페이징 값들을 받음
    			RedirectAttributes redirectAttr) {//전달할 페이징 값을 저장하는 객체

	    log.info("컨트롤러 - 전달된 MyBoardPagingDTO: "+ myBoardPagingDTO);
	    //삭제된 행수를 removedRowCnt 이름으로 반환
	    redirectAttr.addFlashAttribute("removedRowCnt", myBoardService.removeAllDeletedBoard());
	
	    log.info("관리자에 의해 삭제된 총 행수: " + redirectAttr.getAttribute("removedRowCnt"));
	
	    redirectAttr.addAttribute("pageNum", myBoardPagingDTO.getPageNum());
	    redirectAttr.addAttribute("rowAmountPerPage", myBoardPagingDTO.getRowAmountPerPage());
	    redirectAttr.addAttribute("scope", myBoardPagingDTO.getScope());
	    redirectAttr.addAttribute("keyword", myBoardPagingDTO.getKeyword());
	    return "redirect:/myboard/list";
    } 

    
}
