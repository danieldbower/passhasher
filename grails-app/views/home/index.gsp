<!doctype html>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#app">Get Started</a></li>
			<li><a href="#about">About</a></li>
			<li><a href="#features">Features and Todo</a></li>
		</ul>
		<div id="app">
			<h1>Generate your passwords with a Hash</h1>
		
			<div class="masterPassword">
				<label id="masterLabel" for="master">Master Password</label> 
				<input type="password" id="master" title="Enter your Master Password">
				<label id="password-strength" for="master"></label>
			</div>
		
			<div id="accordion">
				
				<g:if test="${userInstance}">
					<g:render template="/shared/placeList"/>
				</g:if>
			
				<g:else>
					<h2>Places</h2>
					<div>
						<p class="anonymous">Either sign in (at the top right) to access
							your saved list of places,
						<div class="OR">or</div><p> create a place below
<%--						<g:render template="/shared/placeFormFields"/>--%>
					</div>
				</g:else>
				
				<g:render template="/shared/newPlaceForm"/>
			</div>
		
			<div class="buttons">
				<g:if test="${!userInstance}">
					<button onclick="hashit($('#nameInput').val(), $('#hashTimesInput').val(), $('#encodingInput').val(), $('#passLengthInput').val())">hash it</button>
				</g:if>
				<button title="reset form" onclick="resetHasher('blank')"
					class="reset">reset</button>
				<button title="hide advanced" onclick="hideAdvanced()"
					class="makeAdvancedToggle advanced">simple</button>
				<button title="show advanced" onclick="showAdvanced()"
					class="makeAdvancedToggle expandAdvanced">advanced</button>
			</div>
		
			<div id="hashedPassContainer">
				<span id="hashedPassLabel">Hashed Pass:</span> <span id="hashedPass">blank</span>
			</div>
		
		</div>
		<div id="about">
			<g:render template="/shared/about" />
		</div>

		<div id="features">
			<g:render template="/shared/todo" />
		</div>
	</div>
</body>
</html>
