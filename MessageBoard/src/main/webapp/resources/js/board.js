
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

// 해당 번호(seq) 게시글 수정
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

};


// 댓글 등록
function fn_replyRegister(){
	var bseq = $("#bseq").val();
	var name = $("#name").val();
	var content = $("#content").val();
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!content || !name){
		alert("이름과 내용을 입력해주세요!");
		return;
	}

	
	// type : get or post
	// url : 통신할 url
	// data : 서버로 보낼 데이터
	// success : 통신 성공 시 호출해야 하는 함수
	// fail : 통신 실패 시 호출해야 하는 함수
	
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


function fn_replyReplyRegister(rep, re_level, re_step){

	var bseq = $("#bseq").val();
	var name = $("#repName_" + rep + "_" + re_level + "_" + re_step).val();
	var content = $("#repContent_" + rep + "_" + re_level+ "_" + re_step).val();
	console.log("bseq : " + bseq + "	name : " + name + "	  content : " + content);
	
	// 제목이 없을 경우 알림 띄우고 게시글 등록하지 않음
	if(!content || !name){
		alert("이름과 내용을 입력해주세요!");
		return;
	}

	
	$.ajax({
		type : "POST",
		url : "/board/reply/register",
		data : {
			"bseq" : bseq,
			"name" : name, 
			"content" : content,
			"rep" : rep,
			"re_level" : re_level,
			"re_step" : re_step
		},
	
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


function fn_replyReplyView(rep, re_level, re_step){

	var new_re_step = re_step
	// for(let i = 1; i<20 ; i++){
	// 	if($("#addReply_"+ rep + "_" + re_level+1 + "_" + (i+1)).length > 0){
	// 		continue;
	// 	}
	// 	else{
	// 		new_re_step = i;
	// 		break;
	// 	}
	// }

	var str = "";
	// 대댓글 작성
	$("#addReply_"+ rep + "_" + re_level + "_" + re_step).empty();

	re_level++;
	re_step++;

	// rep 값과 다음 re_level, re_step 설정해줌
	str += '<tr><td colspan="3"><input class="form-control mr-sm-2" type="text" placeholder="이름" id="repName_'+ rep + "_" + re_level + "_" + re_step +'" name="repName" maxlength="20" ></td></tr>';
	str += '<tr><td colspan="3" style="border: none; padding-top:0.1rem"><textarea class="form-control mr-sm-2" type="text" placeholder="내용을 입력해주세요." id="repContent_' + rep + "_" + re_level + "_" + re_step + '" name="repContent" maxlength="100" rows="3"></textarea></td></tr>';
	str += '<tr><td colspan="3" style="border: none;"><button class="btn btn-primary" type="button" onclick="fn_replyReplyRegister(' + rep + "," + re_level + ','+ re_step +');" style="float: right;">등록</button></td></tr>';

	re_level--;
	re_step--;

	$("#addReply_"+ rep + "_" + re_level + "_" + re_step).append(str);

};





