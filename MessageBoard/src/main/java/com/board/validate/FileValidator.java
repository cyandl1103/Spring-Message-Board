package com.board.validate;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.board.domain.UploadFile;

@Service
public class FileValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UploadFile file = (UploadFile) target;
		
		// 파일이 없을 때
		if(file.getMpfile().getSize() == 0) {
			errors.rejectValue("mpfile", "fileNPE", "파일을 선택해 주세요.");
			
		}
		
	}
}
