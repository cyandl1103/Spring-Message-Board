function practice() {
	// alert("자바스크립트 연습 알림");
	// console.log("자바스립트 연습 콘솔에 출력");
	
		
/*
// 변수의 종류
	var : 변수, 유연함, 재선언 가능, 재할당 가능
	let : 변수, 엄격함, 재선언 불가능, 재할당 가능, {} 안에서만 살아있음 -> 생명주기가 짧다, 메모리 절약
	const : 상수, 재선언 불가능, 재할당 불가능

	// 유효 범위 : {} 기준이 아닌 함수 기준
	// for문 안에서 var 선언해도 for문 밖에서도 사용 가능!!
	// <-> let
	
	// 자바스크립트는 정적 유효 범위!! lexical scoping
	// 사용될 때가 아닌 '정의될 때' 값이 할당됨.
*/
	
// 숫자형은 number로 통일됨 //
	console.log(typeof 10.1);
	console.log(5 + 10.3);
	console.log(5 / 10.3);
	// 3^2
	console.log(Math.pow(3,2));
	// 반올림
	console.log(Math.round(10.6));
	// 올림
	console.log(Math.ceil(10.2));
	// 내림
	console.log(Math.floor(10.2));
	// 0 이상 1 미만 작은 난수
	console.log(Math.random());
	// 0 ~ 100 난수
	console.log(Math.round(100 * Math.random()));
	
	// 문자열은 string //
	console.log(typeof "some text");
	//문자열의 길이
	console.log("some text".length);
	var sometext = "some\ntext";
	console.log("'" + sometext + "' 의 길이 : " + "some\ntext".length + " (줄바꿈 \\n은 1글자로 취급)");
	
	
// var //
	
// 배열 //
	var strings = ["문자열 1", "문자열 2", "문자열 3"];
	console.log(strings);
	// 자료형이 혼합된 상태도 가능
	
	var elements = [1, 2, "a", "b"];
// 추가 //
	// 요소 추가 내장 함수 : push
	elements.push("c");
	// 여러 요소 추가 : concat
	// elements에 strings 추가
	elements = elements.concat(strings);
	// 맨 앞에 요소 추가 : unshift
	elements.unshift(0);
	// index 중간에 요소 추가 및 삭제: splice
	// index 3부터 0개의 요소 삭제 후 index 3에 33, 44, 55 삽입
	elements.splice(3, 0, 33, 44, 55);
	console.log(elements);
	
// 삭제 //
	// 첫 번째 요소 삭제 : shift
	elements.shift();
	// 끝 요소 삭제 : pop
	elements.pop();
	// 특정 index 요소 삭제 : splice
	// index 2에 있는 33부터 3개 33, 44, 55 삭제
	elements.splice(2,3);
	// 특정 값 제거 : filter
	// c가 아닌 것만 모아서 배열 재생성
	elements = elements.filter(element => element !== 'c');
	console.log(elements);
	
// 찾기 //
	// 특정 조건 만족하는 인덱스의 값 찾기 : findIndex
	// 값이 0보다 큰 요소의 가장 처음 index 찾기
	var result = elements.findIndex((element) => element > 0);
	console.log("findIndex : " + result);
	
	// 특정 조건 만족하는 값 찾기 : find
	// 값이 0보다 큰 요소의 가장 처음 값 찾기
	var result = elements.find((element) => element > 0);
	console.log("find : " + result);
	
	// 특정 값 찾기 : includes
	var result = elements.includes(2);
	console.log("includes : " + result);
	
	// 문자열 인덱스 찾기
	console.log(strings.indexOf("문자열 3"));
	// 문자열의 문자 인덱스 찾기도 가능
	console.log(strings[2].indexOf("자"));
	
// 정렬 //
	// sort() : 숫자 -> 문자 순
	var arrayToSort = ['b', 'e', 'a', 1, 6, 3, '가', '하', '다'];
	arrayToSort.sort();
	console.log("정렬된 배열 arrayToSort : " + arrayToSort);
	// reverse() : 역순 정렬
	arrayToSort.reverse();
	console.log("역순으로 정렬된 배열 arrayToSort : " + arrayToSort);
	
// 동등 연산자 == & === : 가능하면 === 연산자를 쓰자.. //
	// === : 엄격함!! 좌항과 우항이 완전히 (자료형 등) 똑같을 때만 true
	console.log("1 == '1' : " + (1 == '1'));
	console.log("1 === '1' : " + (1 === '1'));
	console.log("1 === 1 : " + (1 === 1));
	
	// undefined = 의도하지 않은 없는 값
	// null = 의도한 없는 값
	console.log("undefined == null : " + (undefined == null));
	console.log("undefined === null : " + (undefined === null));
	// 0과 -0은 동일하므로 true
	console.log("0 === -0 : " + (0 === -0));
	// Nan = Not a Number이므로 비교가 불가능해 false
	console.log("NaN === NaN : " + (NaN === NaN));
	console.log("true === true : " + (true === true));
	console.log("false === false : " + (false === false));
	
	// 부정 연산자는 != 와 !== 를 쓴다
	
	
	// 조건문 //
	if(1 === 1) {
		console.log("if(1 === 1) : true");
	}
	
/*	
// 입력 prompt //
	var age = prompt("당신의 나이를 입력하세요");
	// 기본은 string
	console.log(typeof age);
	// 곱 연산하면 number로 취급하여 연산
	console.log(typeof (age * 10));
	console.log("당신의 나이는 " + age + "세입니다");
*/	
	
// 반복문 ///
	// while문
	var count = 0;
	while(count < 3){
		// 웹 페이지에 출력
		// document.write(count + " ");
		console.log(count);
		count++;
	}
	
	// for문
	for(let i = 0; i < 3; i++){
		console.log(i);
	}
	
	
// 객체 object //
	// key : value
	var grades = {
		'math' : 90,
		'english' : 80,
		'science' : 70,
		'society' : 60
	};
	
	// 추가
	grades['korean'] = 80;
	console.log(grades);
	// value 가져오기
	console.log(grades['scie' + 'nce']);
	console.log(grades.math);
	
	// 객체에서의 반복문
	for(key in grades){
		console.log("key : " + key + ", value : " + grades[key]);
	}
	
	// 다양한 객체
	var grades = {
		'list' : {
			'math' : 90,
			'english' : 80,
			'science' : 70,
			'society' : 60
		},
		'show' : function(){
			console.log("function called for show");
			// 두가지 다 가능
			console.log("this.list.math : " + this.list.math);
			console.log("this['list']['math'] : " + this['list']['math']);
			for(key in this.list){
				console.log("key : " + key + ", value : " + this.list[key]);
			}
		}
	};
	
	console.log(grades['list']['english']);
	// 함수도 저장 가능
	grades['show']();
	
	
	
// 정규표현식 //	
	// 찾으려는 패턴
	// i : 대소문자 구분 안함
	// g : 해당되는 패턴을 모두 찾음
	// cat을 찾는데 대소문자 구분 안함, 해당 패턴 모두 찾음.
	var pattern = /cat/ig;
	// 이렇게 선언도 가능, l 로 시작하며 4글자까지 출력
	var patternAlt = new RegExp('l...'); 
	
	// 정규표현식 실행 : exec - cat 찾음
	console.log(pattern.exec('I LIKE CATS.')); // ["cat"]
	console.log(patternAlt.exec('i like cats')); // ["like"]
	
	// 검색 인자에 패턴이 존재하는가? : test
	console.log(pattern.test('i like cats'));
	
	// exec과 유사 : match - 있으면 값 반환, 없으면 null
	console.log('i like cats. Cats are cute.'.match(pattern)); // ["cat"]
	
	// 패턴 변경해서 반환 : replace
	console.log('i like cats'.replace(pattern, 'dog')); // ["cat"]
	
	// word(only literal) + space + word
	// ex : hello world
	var pattern2 = /(\w+)\s(\w+)/;
	var str = 'hello world';
	// world does not sat hello
	var result = str.replace(pattern2, "$2 does not say $1");
	console.log(result);
	
	
	
// 함수 //
	// js에서 함수도 변수임
	// increase 함수를 cal의 파라미터로 넘길 수 있다..
	
	function cal(func, num) {
		return func(num);
	};
	
		
	function increase(num) {
		return num + 1;
	};
	
	function decrease(num) {
		return num - 1;
	};
		
	console.log("increase : " + cal(increase, 1));
	console.log("decrease : " + cal(decrease, 1));
	 
// callback 함수
	// 비동기 처리 : ajax - 시간 효율적으로 사용 가능 ex) 예약 필요할 때
	// sort()의 파라미터로 함수를 넣을 수 있다..
	var numbers = [20, 10 , 9, 8, 7, 6, 5, 4, 3, 2, 1, 0];
	
	var sortFunc = function(a, b) {
		// a가 b보다 크면 양수, 같으면 0, b보다 작으면 음수 반환
		return a - b;
	}
	// callback 함수 sortFunc - 값을로서 함수 사용, 내부 인자
	numbers.sort(sortFunc);
	console.log(numbers);
		
	
	// let 대신 var 쓰면 전역변수라 arr의 모든 요소가 최대값인 5가 됨	
	// 클로저 더 공부해야할 듯..
	var arr = [];	
	for	(let i = 0; i < 5; i++){
		arr[i] = function() {
			return i;
		}
	}
	
	for (var index in arr){
		console.log(arr[index]());
	}

		
	return 0;
};














// 익명 함수 : 일회성으로 바로 호출 //
// import 되는 순간 호출됨
(function(){
	console.log("익명 함수 호출됨");
})();

