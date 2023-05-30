<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<input type="hidden" id="userId" name="userName" value="test" />
<div>
    <h3 id="titleMonth">장부시스템_등록</h3> <span><button>이전</button><button>등록</button></span>

    </br></br></br>
    <input class="datepicker" readonly />


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

//const str = "Hello_123_World_456_!!!";
const regex = /[^0-9]/g;


//-------event-------------
$("#insertPage").click(function(){
    window.location.href = "/ledgers/save"
});


//-------func-------------
const selectLedgerDscList = function(){

    $.ajax({
        type : 'GET',
        url : '/api/v1/ledger-dsc',
        dataType : 'json',
        contentType : 'application/json; charset= utf-8',
    }).done(function(data){

        console.log("=====data check=====");
        console.log(data);
        console.log(data.length);

    }).fail(function(error){
        alert(JSON.stringify(error));
    });
}

const selectLedgerDscListCallback = function(res){
    //장부항목 리스트 callBack

}


const init = function(){


    selectLedgerDscList();

}




init();
</script>