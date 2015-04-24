<html>
<head>
	<meta name='layout' content='main' />
	<title>${grailsApplication.metadata['app.name']}</title>
</head>
<body>
	<div>
		<h2>
			<g:if test='${flash.message}'>
				<span>${flash.message}</span>
			</g:if>
			<g:else>
				Thank you for using ${grailsApplication.metadata['app.name']}.
			</g:else>
		</h2>
		<p class="warning">Log <a href="${grailsApplication.config.grails.serverURL}" class="accent">back in</a>?</p>
		
		<g:if test="${grailsApplication.config.gplus.active}">
			<p class="warning">Logout of <a href="${gPlusLogoutUrl}" class="accent">Google, too</a>?</p>
		</g:if>
	</div>
</body>
</html>
