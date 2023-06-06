<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>

<input type="hidden" id="userId" name="userName" value="test" />
<div>
    <h3 id="titleMonth">장부시스템_등록</h3> <span><button id="back">이전</button><button id="save">등록</button></span>

    </br></br></br>
    <input id="useDate" class="datepicker" readonly />
</br>
</br>
    지출수입 구분
    <select id="dsc" >
    </select>
</br>
</br>
    항목선택
    <select id="item" >
    </select>
</br>
</br>
    금액 입력
    <input type="text" id="amount" placeholder="금액을 입력하세요" />
</br>
</br>
    사용내용
    <textarea  id="cntn" > </textarea>
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

let expenditureList = [];
let incomeList = [];

//const str = "Hello_123_World_456_!!!";
const regex = /[^0-9]/g;


//-------event-------------
$("#save").click(function(){
    let useDate = $("#useDate").val();
    useDate = useDate.replace(regex, "");
    const data = {
        cntn : $("#cntn").val(),
        amount : $('#amount').val(),
        ledgerDsc : $('#dsc').val(),
        item : $('#item').val(),
        useDate : useDate,
        userId : "test"
    };

    $.ajax({
        type: 'post',
        url: '/api/v1/ledgers',
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function(){
        alert("장부가 등록되었습니다. ");
        window.location.href = '/ledgers';
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
});

$("#back").click(function(){
    window.location.href = "/ledgers";
});

$("#dsc").change(function(){
    itemListCallback($("#dsc").val());

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
        selectLedgerDscListCallback(data);
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
}

const selectLedgerDscListCallback = function(res){
    //장부항목 리스트 callBack
    const data = res.ledgerDsc;
    expenditureList = res.expenditureItem;
    incomeList = res.incomeItem;

    let html = '';

    $.each(data, function(i, o){
        html += '<option value="'+o.code+'">' + o.title + '</option>' ;
    });

    $("#dsc").append(html);
    itemListCallback($("#dsc").val());

}

const itemListCallback = function(str){
    $("#item").empty();

    let html = '';

    if(str == 'EXPENDITURE' ){
        $.each(expenditureList, function(i, o){
            html += '<option value="'+o.code+'">' + o.title + '</option>' ;
        });
    }else{
        $.each(incomeList, function(i, o){
            html += '<option value="'+o.code+'">' + o.title + '</option>' ;
        });
    }

    $("#item").append(html);
}


const init = function(){


    selectLedgerDscList();

    setTimeout(() => $('.datepicker').datepicker("setDate", date ), 100);
}




init();
</script>