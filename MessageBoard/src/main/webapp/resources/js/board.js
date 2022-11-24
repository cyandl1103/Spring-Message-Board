
// 게시글 등록 버튼 눌렀을 때
function fn_boardRegister(){	
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = $("#content").val();
	var file_name = null;
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!subject){
		alert("제목을 입력해주세요!");
		return;
	}

	// 파일 헤더
	const form = $("#file")[0];
	
	// 파일이 있는 경우
	if(form.files.length != 0){
		var formData = new FormData();
		formData.append("file", form.files[0]);

		$.ajax({
			url : "/board/upload",
			enctype : 'multipart/form-data', 
			type : "POST", 
			processData : false, 
			contentType : false, 
			data : formData,
			async : false, 
			success: function(data){
				file_name = data;
			  },
			  err: function(err){
				console.log("err:", err)
			  }
		  });

	}

	// type : get or post
	// url : 통신할 url
	// data : 서버로 보낼 데이터
	// success : 통신 성공 시 호출해야 하는 함수
	// fail : 통신 실패 시 호출해야 하는 함수
	
	$.ajax({
		type : "POST",
		url : "/board/register",
		async : false, 
		data : {
			"subject" : subject, 
			"name" : name, 
			"content" : content,
			"file" : file_name
		},
	
		success: function(data){
			if(data == "Y"){
				alert("게시글을 등록하였습니다.");
				location.href = "/board/list";		
			}
		},
		
		error: function(data){
			alert("게시글을 등록하는데 실패했습니다.");
			console.log("data : ' " + data + " '");
		}
	});
	
};


// 파일 다운로드
function fn_downloadFile(file){

	$.ajax({
		type : "POST",
		url : "/board/download",
		data : {
			"file" : file
		},
	
		success: function(data){
			console.log("파일 다운로드 완료");		
			console.log(data);
		},
		
		error: function(data){
			console.log("오류	data : ' " + data + " '");
		}
	});
}


// 해당 번호(seq) 게시글 조회
function fn_goView(seq){
	$("#seq").val(seq);

	var f = $("#frm");
	f.attr("action", "/board/view");
	f.attr("method", "GET");
	f.submit();

};

// 해당 번호(seq) 게시글 삭제
function fn_boardDelete(seq){
	$.ajax({
		type : "POST",
		url : "/board/delete",
		data : {"seq" : seq},
	
		success: function(data){
			if(data == "Y"){
				alert("게시글을 삭제하였습니다.");
				location.href = "/board/list";		
			}
		},
		
		error: function(data){
			alert("게시글을 삭제하는데 실패했습니다.");
			console.log("data : ' " + data + " '");
		}
	});
};

// 해당 번호(seq) 게시글 수정 페이지로 이동
function fn_goUpdateView(seq){
	$("#seq").val(seq);

	var f = $("#frm");
	f.attr("action", "/board/goUpdateView");
	f.attr("method", "POST");
	f.submit();

};


// 해당 번호(seq) 게시글 수정
function fn_boardUpdate(){
	var seq = $("#seq").val();
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = $("#content").val();
	
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!subject){
		alert("제목을 입력해주세요!");
		return;
	}

	$.ajax({
		type : "POST",
		url : "/board/update",
		data : { "seq" : seq, "subject" : subject, "name" : name, "content" : content},
	
		success: function(data){
			if(data == "Y"){
				alert("게시글을 수정하였습니다.");
				fn_goView(seq);		
			}
		},
		
		error: function(data){
			alert("게시글을 수정하는데 실패했습니다.");
			console.log("data : ' " + data + " '");
		}
	});

};


// 댓글 등록 버튼 눌렀을 때
function fn_replyRegister(){
	var bseq = $("#bseq").val(); // 게시물 번호
	var name = $("#name").val(); // 댓글 작성자 이름
	var content = $("#content").val(); // 댓글 내용
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!content || !name){
		alert("이름과 내용을 입력해주세요!");
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/board/reply/register",
		data : {"bseq" : bseq, "name" : name, "content" : content},
	
		success: function(data){
			if(data == "Y"){
				alert("댓글을 등록하였습니다.");
				fn_goView(bseq);		
			}
		},
		
		error: function(data){
			alert("댓글을 등록하는데 실패했습니다.");
			console.log("data : ' " + data + " '");
		}
	});
};


// 대댓글 등록
// parent_rseq = 대댓글의 부모가 될 rseq
function fn_replyReplyRegister(parent_rseq){
	var bseq = $("#bseq").val(); // 게시물 번호
	var name = $("#repName_" + parent_rseq).val(); // 댓글 작성자 이름
	var content = $("#repContent_" + parent_rseq).val(); // 댓글 내용
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!content || !name){
		alert("이름과 내용을 입력해주세요!");
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/board/reply/register",
		data : {"bseq" : bseq, "name" : name, "content" : content, "parent_rseq" : parent_rseq},
	
		success: function(data){
			if(data == "Y"){
				alert("댓글을 등록하였습니다.");
				fn_goView(bseq);		
			}
		},
		
		error: function(data){
			alert("댓글을 등록하는데 실패했습니다.");
			console.log("data : ' " + data + " '");
		}
	});
};


// 대댓글 작성 폼 생성
function fn_replyReplyView(parent_rseq, userName){

	var str = "";

	// 대댓글 작성 부위(tbody)
	$("#addReply_"+ parent_rseq).empty();

	if(userName == ""){
		// 여기서 parent_rseq = 등록 기준이 될 부모 rseq가 된다는 의미
		str += '<tr><td colspan="3"><input class="form-control mr-sm-2" type="text" placeholder="이름" id="repName_'+ parent_rseq +'" name="repName" maxlength="20" ></td></tr>';
		str += '<tr><td colspan="3" style="border: none; padding-top:0.1rem"><textarea class="form-control mr-sm-2" type="text" placeholder="내용을 입력해주세요." id="repContent_' + parent_rseq + '" name="repContent" maxlength="100" rows="3"></textarea></td></tr>';
		str += '<tr><td colspan="3" style="border: none;"><button class="btn btn-primary" type="button" onclick="fn_replyReplyRegister('+ parent_rseq + ');" style="float: right;">등록</button></td></tr>';
	}
	else{
		// 여기서 parent_rseq = 등록 기준이 될 부모 rseq가 된다는 의미
		str += '<tr><td colspan="3"><input class="form-control mr-sm-2" type="text" placeholder="'+ userName +'" id="repName_'+ parent_rseq +'" name="repName" maxlength="20" value="'+ userName +'" readonly></td></tr>';
		str += '<tr><td colspan="3" style="border: none; padding-top:0.1rem"><textarea class="form-control mr-sm-2" type="text" placeholder="내용을 입력해주세요." id="repContent_' + parent_rseq + '" name="repContent" maxlength="100" rows="3"></textarea></td></tr>';
		str += '<tr><td colspan="3" style="border: none;"><button class="btn btn-primary" type="button" onclick="fn_replyReplyRegister('+ parent_rseq + ');" style="float: right;">등록</button></td></tr>';
	}

	$("#addReply_"+ parent_rseq).append(str);

};

// 대댓글 삭제
function fn_replyDelete(rseq){
	var bseq = $("#bseq").val();
	$.ajax({
		type : "POST",
		url : "/board/reply/delete",
		data : {"rseq" : rseq},
	
		success: function(data){
			if(data == "Y"){
				alert("댓글을 삭제하였습니다.");
				fn_goView(bseq);		
			}

			else if (data == "N"){
				alert("대댓글이 있는 댓글은 삭제할 수 없습니다.");
				//fn_goView(bseq);	
			}
		},
		
		error: function(data){
			alert("댓글을 삭제할 수 없습니다.");
			console.log("data : ' " + data + " '");
		}
	});

}

// 내비게이션 바에 있는 유저 이름 누르면 로그아웃 할지 물음
function fn_userMenu(){
	var logout = confirm("로그아웃 하시겠습니까?");
	if(logout){
		location.href = "/user/logout";
	}
}

// 회원 가입 요청
function fn_userRegister(){
	var userId = $("#userId").val();
	var userPw = $("#userPw").val();
	var userName = $("#userName").val();

	// 아이디, 비밀번호, 이름 공백일 시 회원가입 진행 안함
	if(!userId){
		alert("아이디를 입력해주세요!");
		return;
	}
	else if(!userPw){
		alert("비밀번호를 입력해주세요!");
		return;
	}
	else if(!userName){
		alert("이름을 입력해주세요!");
		return;
	}

	$.ajax({
		type : "POST",
		url : "/user/register",
		data : {
			"id" : userId,
			"pw" : userPw,
			"name" : userName
		},
	
		success: function(data){
			if(data == "Y"){
				alert("회원가입이 완료되었습니다.");
				location.href = "/";		
			}

			else if (data == "N"){
				alert("중복된 아이디입니다.");
			}
		},
		
		error: function(data){
			alert("회원가입을 할 수 없습니다.");
			console.log("data : ' " + data + " '");
		}
	});
}