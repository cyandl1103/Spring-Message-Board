
// 게시글 등록
function fn_boardRegister(){	
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = $("#content").val();
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!subject){
		alert("제목을 입력해주세요!");
		return;
	}
	
	// console.log(subject + " " + name + " " + content);
	
	// type : get or post
	// url : 통신할 url
	// data : 서버로 보낼 데이터
	// success : 통신 성공 시 호출해야 하는 함수
	// fail : 통신 실패 시 호출해야 하는 함수
	
	$.ajax({
		type : "POST",
		url : "/board/register",
		data : {"subject" : subject, "name" : name, "content" : content},
	
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

// 해당 번호(seq) 게시글 조회
function fn_goView(seq){
	$("#seq").val(seq);

	var f = $("#frm");
	f.attr("action", "/board/view");
	f.attr("method", "POST");
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
}

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
	
	// console.log(subject + " " + name + " " + content);

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

}

// 해당 번호(seq) 게시글 수정
function fn_goUpdateView(seq){
	$("#seq").val(seq);

	var f = $("#frm");
	f.attr("action", "/board/goUpdateView");
	f.attr("method", "POST");
	f.submit();

};