<html>
<head>
	<meta name='layout' content='main'/>
	<title><g:message code="springSecurity.login.title"/></title>
	<script src="https://apis.google.com/js/client:platform.js" async defer></script>
	<script type='text/javascript'>
		function signinCallback(authResult) {
			console.log(authResult);
		
			if (authResult['status']['signed_in']) {
				document.getElementById('signinButton').setAttribute('style', 'display: none');
		
				document.getElementById('codeInput').value = authResult['code'];
				document.getElementById("loginForm").submit();
			} else if(authResult['error'] == 'access_denied'){
				console.log('Access Denied, we should logout and come back');
				window.location.replace('${gPlusLogoutUrl}');
			}else {
				console.log('SigninCallback returned false signed_in status: ' + authResult['error']);
			}
		}
	</script>
</head>

<body>
<div id='login'>
	<div class='inner'>
		<div class='fheader'><g:message code="springSecurity.login.header"/></div>

		<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
		</g:if>

		<g:form action="gplus" method="POST" id='loginForm' name="loginForm" class="login-form form-horizontal" autocomplete='off' useToken="true">
			<g:field type="hidden" name="codeInput"/>
			<span id="signinButton" class="gPlusSignInButton">
				<span class="g-signin"
					data-theme='light'
					data-callback="signinCallback"
					data-clientid="${clientId}"
					data-redirecturi="postmessage"
					data-approvalprompt="${approvalPrompt}"
					data-cookiepolicy="single_host_origin"
					data-scope="email">
				</span>
			</span>
		</g:form>
	</div>
</div>

</body>
</html>
