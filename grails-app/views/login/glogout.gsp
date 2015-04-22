<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Google Logout</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<div id="loginForm" class="login-form">
				<h2>Wrong Account</h2>
				<p class="warning">You tried to use email address ${email} to 
				login, but you must use your account from <b>${org}</b>.  
				<p class="warning">Please <g:link class="accent" action="auth" params="[switchAccounts:'true']">try again</g:link> with your institution account.</p>
			</div>
		</div>
	</body>
</html>
