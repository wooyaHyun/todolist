<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<input type="hidden" id="userId" name="userName" value="test" />
<div>
    <h1>장부시스템</h1>

    <button id="insertPage"> 장부 등록 </button>
    <br>
    <br>
    <button id="prev"> 지난주 </button>   <span id="fromDate"></span> ~ <span id="toDate"></span>   <button id="next"> 다음주 </button>
    <br>
    <br>
    <br>
    <h4>일별 지출 수입 합</h3>
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong">
        <tr id="thead">
           <th>장부번호</th>
           <th>사용일자</th>
           <th>장부번호</th>
           <th>장부번호</th>
           <th>장부번호</th>
           <th>장부번호</th>
           <th>장부번호</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>

    <br>
    <br>
    <br>
    <h4>일주일 사용내역</h3>
    <table class="table table-horizontal table-bordered">
        <thead class="thead-strong">
        <tr id="theadList">
           <th>사용일자</th>
           <th>구분</th>
           <th>항목</th>
           <th>금액</th>
        </tr>
        </thead>
        <tbody id="tbodyList">
        </tbody>
    </table>
</div>




<%@ include file="/WEB-INF/view/layout/footer.jsp" %>

<script>
//-------init variable-------------
let date = new Date();

let theYear = date.getFullYear();
let theMonth = date.getMonth();
let theDate  = date.getDate();
let theDayOfWeek = date.getDay();

const week = ['일', '월', '화', '수', '목', '금', '토'];
const thisWeek = [];

const regex = /[^0-9]/g;
const regexDate = /(\d{4})(\d{2})(\d{2})/g;


//-------event-------------
$("#insertPage").click(function(){
    window.location.href = "/ledgers/save";
});

$("#tbodyList").on("click", ".btnDtl", function(){
    console.log($(this).attr("id"));

});

$("#prev").click(function(){
    initWeekPeriod("PREV");
});

$("#next").click(function(){
    initWeekPeriod("NEXT");
});


//-------func-------------
const priceToString = function(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}


const selectWeekLedgerList = function(){
    const data ={
        userId : $("#userId").val(),
        fromDate : $("#fromDate").text().replace(regex, ""),
        toDate : $("#toDate").text().replace(regex, "")
    };

    $.ajax({
        type : 'GET',
        url : '/api/v1/ledgers',
        dataType : 'json',
        contentType : 'application/json; charset= utf-8',
        data : data
    }).done(function(data){

        console.log("=====data check=====");
        console.log(data);
        console.log(data.length);
        selectWeekLedgerListCallback(data.ledgerListResponseDtoList);
        selectWeekLedgerGroupSumCallback(data.ledgerGroupSumResponseDtoList);

    }).fail(function(error){
        alert(JSON.stringify(error));
    });
};

const selectWeekLedgerGroupSumCallback = function(res){

    $.each(res, function(i, o){
        let html = '';
        html += '<span>'+o.ledgerDsc+  ': ' + priceToString(o.amount) + '<span><br>';

        $("#"+o.useDate).append(html);
    });
};

const selectWeekLedgerListCallback = function(res){
    //일주일치 장부 내역 callback
    const cnt = res.length;

    $("#tbodyList").empty();
    let html = '';

    if(cnt > 0){
        $.each(res, function(i,o){
            html += '<tr id="'+o.id+'" class="btnDtl">';
            html += '<td>'+o.useDate.replace(regexDate, '$1-$2-$3') +'</td>';
            html += '<td>'+o.ledgerDsc+'</td>';
            html += '<td>'+o.item+'</td>';
            html += '<td>'+priceToString(o.amount)+'</td>';
            html += '</tr>';
        });
    }else{
        html += '<tr><td colspan="4">일주일간 사용내역이 없습니다.</td></tr>';
    }

    $("#tbodyList").append(html);

}

const initWeekPeriod = function(str){
    if(str == "PREV"){
        date = new Date(theYear, theMonth, theDate - 7);
        theMonth = date.getMonth();
        theDate  = date.getDate();
        theDayOfWeek = date.getDay();
    }else if(str == "NEXT"){
        date = new Date(theYear, theMonth, theDate + 7);
        theMonth = date.getMonth();
        theDate  = date.getDate();
        theDayOfWeek = date.getDay();
    }


    let html1 = '';
    let html2 = '<tr style="min-height:300px;">';
    for(let i=0; i<7; i++) {
      let resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek));
      let yyyy = resultDay.getFullYear();
      let mm = Number(resultDay.getMonth()) + 1;
      let dd = resultDay.getDate();
      let day =  resultDay.getDay();

      mm = String(mm).length === 1 ? '0' + mm : mm;
      dd = String(dd).length === 1 ? '0' + dd : dd;

      thisWeek[i] = yyyy + '-' + mm + '-' + dd;
      const date = yyyy+mm+dd;
      html1 += '<th>'+thisWeek[i]+ ' ('+ week[day] +')'+'</th>';

      html2 += '<td id="'+date+'"></td>';
    }
    html2 += '</tr>';

    $("#fromDate").text(thisWeek[0]);
    $("#toDate").text(thisWeek[6]);

    $("#thead").empty();
    $("#thead").append(html1);
    $("#tbody").empty();
    $("#tbody").append(html2);

    selectWeekLedgerList();
};

const init = function(){
    //일주일 설정 + 데이터 조회
    initWeekPeriod("INIT");

}

init();

</script>