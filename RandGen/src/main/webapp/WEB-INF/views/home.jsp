<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Random Number Generator</title>
</head>
<body><center>
<h3>Generate A Random Number</h3>
<form name="randrangeform" action="rangegeneration" onsubmit="return validateForm()" method="post" >
<br><br>
Using a range:<br />
<br>
<table><tr>
<td>Range Min:</td><td><input type="number" name="min" /></td></tr>
<tr><td>Range Max:</td><td><input type="number" name="max" /></td></tr>
<tr><td>Seed (optional):</td><td><input type="number" name="seed" /></td></tr>
</table><br>
<input type="submit" value="Generate" /></form>


Using a profile:
<form name="profileform" action="profilegeneration" onsubmit="return validateForm()" method="post" >

<table><tr>
<td>ProfileID:</td><td><input type="number" name="profileid" /></td></tr>
</table>
<br>

<input type="submit" value="Generate" />
</form>
<br>



<h3>Create A Profile</h3>
<form name="createform" action="newprofile" method="post" >
<br>
Using a range:<br />
<br>
<table><tr>
<td>Range Min:</td><td><input type="number" name="min" /></td></tr>
<tr><td>Range Max:</td><td><input type="number" name="max" /></td></tr>
<tr><td>Seed (optional):</td><td><input type="number" name="seed" /></td></tr>
</table><br>
<input type="submit" value="Make New Profile" /></form>
<br>

<h3>Delete a profile:</h3>
<form name="delform" type="number" action="delprofile" method="post" >

<table><tr>
<td>ProfileID:</td><td><input type="number" name="profileid" /></td></tr>
</table>
<br>

<input type="submit" value="Delete Profile" />
</form>
<br>

</center></body>
</html>
