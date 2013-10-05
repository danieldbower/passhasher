<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Pass Hasher" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<g:layoutHead />
<r:require modules="jquery, jquery-ui, hash, passwordStrengthMeter" />
<r:layoutResources />
</head>
<body>
	<div class="header">
		<g:render template="/shared/notices" />
		<p align="right">
			<sec:ifLoggedIn>
				Welcome Back <sec:loggedInUserInfo field="username"/> !
				<g:link controller='logout'>Logout</g:link>
			</sec:ifLoggedIn>
			<sec:ifNotLoggedIn>
				<g:link controller='login' action='auth'>Login</g:link>
			</sec:ifNotLoggedIn>
		</p>
	</div>
	<div class="content">
		<div id="tabs">
			<ul>
				<li><a href="#app">Get Started</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#features">Features and Todo</a></li>
			</ul>
			<div id="app">
				<g:layoutBody />
			</div>
			<div id="about">
				<g:render template="/shared/about" />
			</div>

			<div id="features">
				<g:render template="/shared/todo" />
			</div>
		</div>
	</div>
	<!-- div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div -->
	<div class="footer" role="contentinfo">
		Brought to you by <a href="http://bowerstudios.com">BowerStudios.com</a>
	</div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>