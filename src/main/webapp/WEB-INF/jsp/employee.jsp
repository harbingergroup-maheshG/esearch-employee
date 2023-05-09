<!DOCTYPE html>
<%@page import="com.myco.esearchemployee.model.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.myco.esearchemployee.model.Employee"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript">
	function preventBack() {
		window.history.forward();
	}

	setTimeout("preventBack()", 0);

	window.onunload = function() {
		null
	};
</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Oswald:400);

.navigation {
	width: 100%;
	background-color: black;
}

img {
	width: 25px;
	border-radius: 50px;
	float: left;
}

.logout {
	font-size: .8em;
	font-family: 'Oswald', sans-serif;
	position: relative;
	right: -18px;
	bottom: -4px;
	overflow: hidden;
	letter-spacing: 3px;
	opacity: 0;
	transition: opacity .45s;
	-webkit-transition: opacity .35s;
}

.button {
	text-decoration: none;
	float: right;
	padding: 12px;
	margin: 15px;
	color: white;
	width: 25px;
	background-color: black;
	transition: width .35s;
	-webkit-transition: width .35s;
	overflow: hidden
}

a:hover {
	width: 100px;
}

a:hover .logout {
	opacity: .9;
}

a {
	text-decoration: none;
}

* {
	box-sizing: border-box;
}

.row::after {
	content: "";
	clear: both;
	display: table;
}

[class*="col-"] {
	float: left;
	padding: 15px;
}

html {
	font-family: "Lucida Sans", sans-serif;
}

.header {
	background-color: #9933cc;
	color: #ffffff;
	padding: 15px;
}

.menu ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

.menu li {
	padding: 8px;
	margin-bottom: 7px;
	background-color: #33b5e5;
	color: #ffffff;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.menu li:hover {
	background-color: #0099cc;
}

.aside {
	background-color: #33b5e5;
	padding: 15px;
	color: #ffffff;
	text-align: center;
	font-size: 14px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.footer {
	background-color: #0099cc;
	color: #ffffff;
	text-align: center;
	font-size: 12px;
	padding: 15px;
}

/* For mobile phones: */
[class*="col-"] {
	width: 100%;
}

@media only screen and (min-width: 600px) {
	/* For tablets: */
	.col-s-1 {
		width: 8.33%;
	}
	.col-s-2 {
		width: 16.66%;
	}
	.col-s-3 {
		width: 25%;
	}
	.col-s-4 {
		width: 33.33%;
	}
	.col-s-5 {
		width: 41.66%;
	}
	.col-s-6 {
		width: 50%;
	}
	.col-s-7 {
		width: 58.33%;
	}
	.col-s-8 {
		width: 66.66%;
	}
	.col-s-9 {
		width: 75%;
	}
	.col-s-10 {
		width: 83.33%;
	}
	.col-s-11 {
		width: 91.66%;
	}
	.col-s-12 {
		width: 100%;
	}
}

@media only screen and (min-width: 768px) {
	/* For desktop: */
	.col-1 {
		width: 8.33%;
	}
	.col-2 {
		width: 16.66%;
	}
	.col-3 {
		width: 25%;
	}
	.col-4 {
		width: 33.33%;
	}
	.col-5 {
		width: 41.66%;
	}
	.col-6 {
		width: 50%;
	}
	.col-7 {
		width: 58.33%;
	}
	.col-8 {
		width: 66.66%;
	}
	.col-9 {
		width: 75%;
	}
	.col-10 {
		width: 83.33%;
	}
	.col-11 {
		width: 91.66%;
	}
	.col-12 {
		width: 100%;
	}
}
</style>
</head>
<body>

	<div class="header">
		<h1>Employee Portal</h1>
		<h2>Welcome ${employee.firstName} ${employee.lastName }</h2>

		<div class="input-group">
			<input type="search" class="form-control rounded"
				placeholder="Search..." aria-label="Search"
				aria-describedby="search-addon" />
			<button type="button" class="btn btn-outline-primary">search</button>
		</div>
	</div>

	<div class="row">
		<div class="col-3 col-s-3 menu">
			<ul>
				<li>Update Profile</li>
				<!--  <li>The Food</li> -->
			</ul>
		</div>
		<div class="navigation">

			<a class="button" href="/"> <!-- <img
				src="https://pbs.twimg.com/profile_images/378800000639740507/fc0aaad744734cd1dbc8aeb3d51f8729_400x400.jpeg"> -->
				<div class="logout">LOGOUT</div>

			</a>

		</div>

		<div class="col-6 col-s-9">
			<h3>
				TASK DETAILS
				<Table border="1">
					<tr>
						<th>TASK ID</th>
						<th>TITLE</th>
						<th>DESCRIPTION</th>
						<th>ESTIMATED TIME</th>
					</tr>
					<%
					Employee employee = (Employee) request.getAttribute("employee");
					List<Task> taskList = employee.getTask();
					for (Task task : taskList) {
					%>
					<tr align="center">
						<td><%=task.getTaskId()%></td>
						<td><%=task.getTitle()%></td>
						<td><%=task.getDescription()%></td>
						<td><%=task.getEstimatedTime()%></td>
					</tr>
					<%
					}
					%>
				</Table>
			</h3>

		</div>

		<div class="col-3 col-s-12">
			<div class="aside">
				<h1>Details</h1>
				<p align="left">Employee ID : ${employee.employeeId }</p>
				<p align="left">First Name : ${employee.firstName }</p>
				<p align="left">Last Name : ${employee.lastName }</p>
				<p align="left">email : ${employee.email}</p>

			</div>
		</div>
	</div>

	<div class="footer">
		<p>If you can dream it, you can do it.</p>
	</div>

</body>
</html>

