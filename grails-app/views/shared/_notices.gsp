<div class="notices">
<g:if test="${flash.error}">
  <div class="ui-widget">
      <div class="ui-state-error ui-corner-all" >
          <p><span class="ui-icon ui-icon-alert" ></span>
          <strong>Alert:</strong> ${flash.error}</p>
      </div>
  </div>
</g:if>
</div>