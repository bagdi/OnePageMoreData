<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<link rel="stylesheet" href="css/input.css"/>
<link rel="stylesheet" href="css/button.css"/>
<link rel="stylesheet" href="css/table.css"/>
<link rel="stylesheet" href="css/alertify/alertify.css"/>

<head>
    <title>Employees</title>
</head>
<body>
<fieldset>
    <legend>Add</legend>
    <label for="firstName">First Name</label>
    <input type="text" id="firstName" class="enjoy-css" placeholder="first name"/></br>
    <label for="firstName">Last Name</label>
    <input type="text" id="lastName" class="enjoy-css" placeholder="last name"/></br>
    <label for="firstName">Position</label>
    <input type="text" id="position" class="enjoy-css" placeholder="position"/></br>
    <label for="firstName">Department</label>
    <input type="text" id="department" class="enjoy-css" placeholder="department"/></br>
    <input type="submit" id="add" value="Add" class="button-css"/></br>
</fieldset>

<fieldset>
    <legend>Search</legend>
    <label for="searchForFirstName">Enter first name</label>
    <input type="text" id="searchForFirstName" class="enjoy-css" placeholder="first name"/>
    <label for="searchForLastName">Enter last name</label>
    <input type="text" id="searchForLastName" class="enjoy-css" placeholder="last name"/>
    <input type="submit" id="search" value="Search" class="button-css"/>
</fieldset>

<fieldset>
    <legend>Data</legend>
    <div id="respBeforeTable"></div>
<table class="table-css">
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>LastName</th>
            <th>Position</th>
            <th>Department</th>
        </tr>
    </thead>
    <tbody id="responseData"></tbody>
</table>
</fieldset>

<script type="text/javascript" src="js/jquery-2.2.2.js"></script>
<script type="text/javascript" src="js/alertify/alertify.js"></script>
<script type="text/javascript" src="js/form.js"></script>
</body>
</html>
