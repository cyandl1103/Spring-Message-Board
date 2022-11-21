package com.board.service;

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
		
		// ������ ���� ��
		if(file.getMpfile().getSize() == 0) {
			errors.rejectValue("mpfile", "fileNPE", "������ ������ �ּ���.");
			
		}
		
	}
}