package org.zerock.ex00.common.fileupload;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final Logger logger=LoggerFactory.getLogger(FileUploadController.class);
	
	//다중파일 업로드 방법1: form 방식의 파일업로드
	//파일 업로드 요청 JSP 페이지 호출
	@GetMapping("fileUploadForm")
	public void callFileUploadForm() {
		logger.info("upload Form");
	}
	
	//다중파일 업로드 방법1: form 방식의 파일업로드
	//Model이용, 업로드 파일 저장
	@PostMapping("/fileUploadFormAction")
	public void fileUploadActionPost(MultipartFile[] uploadFiles, Model model) {
		logger.info("==FileUpload With Form ===");
		
		//저장경로 (Windows 환경이므로 경로구분자를 \\로 지정)
		String strUploadFileRepoDir="C:\\upload";
		
		for(MultipartFile multipartUploadFile : uploadFiles) {
			logger.info("=================");
			logger.info("Upload File Name: "+multipartUploadFile.getOriginalFilename());
			logger.info("Upload File Size: "+multipartUploadFile.getSize());
			
//			//업로드 파일의 리소스(저장폴더와 파일이름)가 설정된 File객체 생성
//			File saveUploadFile = new File(strUploadFileRepoDir, multipartUploadFile.getOriginalFilename());
			
			//internet explorer에서도 정상작동되도록, 위의 코드를 아래의 코드로 대체
			String strUploadFileName = multipartUploadFile.getOriginalFilename();
			//IE10 파일이름 추출: multipartUploadFile.getOriginalFilename()에서 업로드 파일이름만 추출
			//파일 이름만 있는 경우, 파일이름만 추출 됨
			strUploadFileName=strUploadFileName.substring(strUploadFileName.lastIndexOf("\\")+1);
			//업로드정보(저장 폴더와 파일이름 문자열)의 파일객체 생성
			File saveUploadFile=new File(strUploadFileRepoDir, strUploadFileName);
			
			try {
				//파일객체(saveUploadFile)를 이용하여 서버에 업로드파일 저장
				multipartUploadFile.transferTo(saveUploadFile);
				
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}//End-for
	}
	
	//다중파일 업로드 방법2: Ajax 방식의 파일 업로드
	//사용자의 업로드 요청 페이지 호출
	@GetMapping("/fileUploadAjax")
	public void callFileUploadAjax() {
		logger.info("upload Ajax Form");
	}
	
	//업로드 요청 파일-저장 및 결과 메시지 전송
	@PostMapping("/fileUploadAjaxAction")
	public void fileUploadActionAjaxPost(MultipartFile[] uploadFiles) { //Ajax 사용시 Model 필요 없음
		logger.info("===Start FileUPload With Ajax====");
		
		String strUploadFileRepoDir ="C:\\upload";
		
		for(MultipartFile multipartUploadFile: uploadFiles) {
			logger.info("======================");
			logger.info("Upload File Name: "+multipartUploadFile.getOriginalFilename());
			logger.info("Upload File size: "+multipartUploadFile.getSize());
			//업로드 파일 이름 원본 문자열
			String strUploadFileName =multipartUploadFile.getOriginalFilename();
			
			//IE10 파일이름추출: multipartUploadFile.getOriginalFilename()에서 업로드 파일이름만 추출
			//파일이름만 있는 경우, 파일 이름만 추출됨
			strUploadFileName=strUploadFileName.substring(strUploadFileName.lastIndexOf("\\")+1);
			
			logger.info("최종 처리된 업로드 파일 이름: "+strUploadFileName);
			
			//업로드 정보(저장폴더와 파일이름 문자열)의 파일객체 생성
			File saveUploadFile=new File(strUploadFileName, strUploadFileName);
			
			try {
				//서버에 파일객체를 이용하여 업로드 파일 저장
				multipartUploadFile.transferTo(saveUploadFile);
				
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}//End-for
	}
}
