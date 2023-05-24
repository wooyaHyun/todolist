<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<input type="hidden" id="userId" name="userName" value="test" />
<div>
    <h3 id="titleMonth"> </h3>
    <button id="insertPage"> 장부 등록 </button>
    <br>
    <br>
    <button> 지난주 </button>   <span id="fromDate"></span> ~ <span id="toDate"></span>   <button> 다음주 </button>
</div>







<%@ include file="/WEB-INF/view/layout/footer.jsp" %>

<script>
//-------init variable-------------
const date = new Date();
const theYear = date.getFullYear();
const theMonth = date.getMonth();
const theDate  = date.getDate();
const theDayOfWeek = date.getDay();
const thisWeek = [];

const str = "Hello_123_World_456_!!!";
const regex = /[^0-9]/g;


//-------event-------------
$("#insertPage").click(function(){
    window.location.href = "/api/v1/ledgers"
});


//-------func-------------
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

    }).fail(function(error){
        alert(JSON.stringify(error));
    });
}

const selectWeekLedgerListCallback = function(res){
    //일주일치 장부 내역 callback

}

const init = function(){

    $("#titleMonth").text((theMonth+1) + "월");

    for(let i=0; i<7; i++) {
      let resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek));
      let yyyy = resultDay.getFullYear();
      let mm = Number(resultDay.getMonth()) + 1;
      let dd = resultDay.getDate();

      mm = String(mm).length === 1 ? '0' + mm : mm;
      dd = String(dd).length === 1 ? '0' + dd : dd;

      thisWeek[i] = yyyy + '-' + mm + '-' + dd;
    }

    $("#fromDate").text(thisWeek[0]);
    $("#toDate").text(thisWeek[6]);

    //일주일치 장부 내역 가져오기
    selectWeekLedgerList();
}

init();

</script>