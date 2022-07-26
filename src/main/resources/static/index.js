let connected = false
var username = ''
var contacts = []
function getContacts(username) {
    url = 'http://localhost:8080/acquireallcontacts/' + username;
    $.ajax({
        url: url,
        method: 'GET',
        success: function (result) {
            $.each(result, function (index, value){
                console.log(value)
            })
        }
    })
}

function getHistoryMessages(username) {
    url = 'http://localhost:8080/acquirehistorymessages/' + username;
    $.ajax({
        url: url,
        method: 'GET',
        success: function (result) {
            $.each(result, function (index, value){
                console.log(value)
            })
        }
    })
}

$(document).ready(function () {
    $("#btnLogin").click(function () {
        $.ajax({
            url: 'http://localhost:8080/login',
            data: JSON.stringify(
                {
                    username: $("#tbUsername").val(),
                    password: $("#tbPassword").val(),

                }),
            method: 'POST',
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                connected = true
                username = $("#tbUsername").val()
                getContacts(username)
                getHistoryMessages(username)
            }
        })
    });
    $("#btnSend").click(function () {
        $.ajax({
            url: 'http://localhost:8080/sendmessage',
            data: JSON.stringify(
                {
                    sender: $("#tbUsername").val(),
                    receiver: $("#tbReceiver").val(),
                    type: 'text',
                    content: $('#tbMsg').val()
                }),
            method: 'POST',
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
            }
        })
    });
    setInterval(function () {
        if(connected){
            url = 'http://localhost:8080/heartbeat/' + username;
            $.ajax({
                url: url,
                method: 'GET',
                success: function (result) {
                    $.each(result, function (index, value) {
                        console.log(value)
                    })
                }
            })
        }
    }, 1000);
})

