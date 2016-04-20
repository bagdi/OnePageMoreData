/**
 * Created by Bogdan on 17.04.2016.
 */
window.onload = function () {
    $("#add").disabled;
}

$(document).ready(function () {
    load();
});

$(function () {
    $("#add").click(function () {
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var position = $("#position").val();
        var department = $("#department").val();
        var add = $("#add").val();
        check(firstName);
        check(lastName);
        check(position);
        check(department);
        $.ajax({
            type: 'POST',
            data: {
                firstName: firstName,
                lastName: lastName,
                position: position,
                department: department,
                add: add
            },
            dataType: 'json',
            url: 'mainServlet',
            success: function (data) {
                alertify.success("Successfully added");
                $("#responseData tr").remove();
                $.each(data, function (index) {
                    var idData = data[index].id;
                    $("#responseData").append("<tr><td id='" + idData + "'>" + idData + "</td><td>"
                        + data[index].firstName + "</td><td>" + data[index].lastName + "</td><td>" 
                        + data[index].position + "</td><td>" + data[index].department
                        + "</td><td><input type='button' class='button-css' onclick='deleteData(" + idData + ")' value='delete'/></td></tr>");
                    $("#responseData").slideDown(500);
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alertify.warning(textStatus + " " + jqXHR.status + " " + errorThrown);
            }
        });
    });
});

$(function () {
    $("#search").click(function () {
        var firstName = $("#searchForFirstName").val();
        var lastName = $("#searchForLastName").val();
        var search = $("#search").val();
        $.ajax({
            type: 'POST',
            data: {
                firstName: firstName,
                lastName: lastName,
                search: search
            },
            dataType: 'json',
            url: 'mainServlet',
            success: function (data) {
                $("#responseData tr").remove();
                $("#respBeforeTable input").remove();
                $("#respBeforeTable").append("<input type='submit' value='Full list' class='button-css' onclick='load()'/>");
                $.each(data, function (index) {
                    var idData = data[index][0];
                    $("#responseData").append("<tr><td id='" + idData + "'>" + idData + "</td><td>"
                        + data[index][2] + "</td><td>" + data[index][3] + "</td><td>"
                        + data[index][4] + "</td><td>" + data[index][1]
                        + "</td><td><input type='button' class='button-css' onclick='deleteData(" + idData + ")' value='delete'/></td></tr>");
                    $("#responseData").slideDown(500);
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alertify.warning(textStatus + " " + jqXHR.status + " " + errorThrown);
            }
        });
    });
});

function check(stringData) {
    if(stringData == "") {
        alertify.error('All fields must be filled');
        errorCallback;
    }
}

function deleteData(idData) {
    $.ajax({
        type: 'POST',
        data: {findRow: idData},
        dataType: 'json',
        url: 'mainServlet',
        success: function (data) {
            alertify.success("Successfully deleted");
            $("#responseData tr").remove();
            $.each(data, function (index) {
                var idData = data[index].id;
                $("#responseData").append("<tr><td id='" + idData + "'>" + idData + "</td><td>"
                    + data[index].firstName + "</td><td>" + data[index].lastName + "</td><td>"
                    + data[index].position + "</td><td>" + data[index].department
                    + "</td><td><input type='button' class='button-css' onclick='deleteData(" + idData + ")' value='delete'/></td></tr>");
                $("#responseData").slideDown(500);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alertify.warning(textStatus + " " + jqXHR.status + " " + errorThrown);
        }
    });
}

function load() {
    $("#add").attr("disabled", true);
    $("#search").attr("disabled", true);
    alertify.message('Wait until the data is loaded');
    $("#responseData").append("<p>Load....</p>");
    var flag = 1;
    $.ajax({
        type: 'POST',
        url: 'mainServlet',
        data: {flag: flag},
        dataType: 'json',
        success: function (data) {
            alertify.success("Successfully loaded");
            $("#add").attr("disabled", false);
            $("#search").attr("disabled", false);
            $("#responseData tr").remove();
            $("#responseData p").remove();
            $("#respBeforeTable input").remove();
            $.each(data, function (index) {
                var idData = data[index].id;
                $("#responseData").append("<tr><td id='" + idData + "'>" + idData + "</td><td>"
                    + data[index].firstName + "</td><td>" + data[index].lastName + "</td><td>"
                    + data[index].position + "</td><td>" + data[index].department
                    + "</td><td><input type='button' class='button-css' onclick='deleteData(" + idData + ")' value='delete'/></td></tr>");
                $("#responseData").slideDown(500);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alertify.warning(textStatus + " " + jqXHR.status + " " + errorThrown);
        }
    });
}