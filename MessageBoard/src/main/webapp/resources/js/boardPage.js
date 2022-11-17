
// 페이지네이션
 function fn_goPage(pageNum){
 
    // ajax를 통한 비동기 페이징
    var pageFrm = $("#pageFrm");
    
    // get방식으로 데이터 보내기 위해 pageNum=□&amount=□ 형태로 저장
    pageFrm.find("input[name='pageNum']").val(pageNum);
    var params = $("#pageFrm").serialize();
    console.log(params);
    
    $.ajax({ 
		url: "/board/listPage", 
		type: "get", 
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data: params

	}).done(function(json){
		// json 문자열을 자바스크립트 객체로 변환
		const obj = JSON.parse(json);
		
		var str = "";
		// 게시물 리스트 생성하기 위한 절차
		$("#listTable > tbody").empty();
		// 한 페이지에 들어갈 게시물의 수만큼 반복(amount)
		for(let i = 0; i < obj.list.length; i++){
			var seq = obj.list[i].seq;
			var subject = obj.list[i].subject;
			var content = obj.list[i].content;
			var name = obj.list[i].name;
			var reg_date = obj.list[i].reg_date;
			var readCount = obj.list[i].readCount;
			
			var dateFormatted = reg_date.substring(0, 4) + "-" + reg_date.substring(4, 6) + "-" + reg_date.substring(6, 8);
			
			// tbody 내에 넣기 위해 태그들 string 형태로 str에 추가
			// 반복 1번 당 행(tr) 하나씩 생성하게 됨
			str += '<tr align="center" class="body-tr">';
			str += '<th scope="row">' + seq + '</th>';
			str += '<td class="body-content"> <a href="#" onclick="fn_goView(' + seq +')">' + subject + '</a> </td>';
			str += '<td class="body-content">' + content + '</td>';
			str += '<td class="body-content">' + name + '</td>';
			str += '<td>' + dateFormatted + '</td>';	
			str += '<td>' + readCount + '</td>';
			str += "</tr>"
		}
		// 테이블에 추가 - str가 밀어버린 tbody 밑에 들어가게 됨
		$('#listTable').append(str);
		
		// pageMake
		var amount = obj.pageMake.amount;
		var endPage = obj.pageMake.endPage;
		var next = obj.pageMake.next;
		var pageNum = obj.pageMake.pageNum;
		var prev = obj.pageMake.prev;
		var startPage = obj.pageMake.startPage;
		var total = obj.pageMake.total;
		
		// 페이지 표시
		$("#pageInfo").empty();
		str = "";
		if(prev){
			str += " <li class='pageInfo_btn previous'><a href='javascript:void(0);' onclick='fn_goPage(" + (startPage - 1) + "); return false;'>◀</a></li>";
		}
		
		for(let num = startPage; num < endPage+1; num ++){
			if(pageNum == num)
				str += "<li class='pageInfo_btn'><a href='javascript:void(0);' onclick='fn_goPage(" + num + "); return false;' class= 'active'>" + num + "</a></li>";
			else 
				str += "<li class='pageInfo_btn'><a href='javascript:void(0);' onclick='fn_goPage(" + num + "); return false;' class>" + num + "</a></li>";
		}

		
		if(next){
			str += " <li class='pageInfo_btn next'><a href='javascript:void(0);' onclick='fn_goPage(" + (endPage + 1) + "); return false;'>▶</a></li>";
		}
		$('#pageInfo').append(str);		
	});
 
}













//window.onload = function(){
	/*	
	// load
    $(".pageInfo a").on("click", function(e){
        e.preventDefault();
        
        var pageNum = $("pageNum").val();
        var amount = $("amount").val();
		console.log("	pageNum : " + pageNum + "	amount : " + amount)
		
        pageFrm.find("input[name='pageNum']").val($(this).attr("href"));
        pageFrm.attr("action", "/board/list");
        pageFrm.submit();
        
    });
    */
//};

