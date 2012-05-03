<div class="notices">
<g:if test="${flash.notice}">
  <div class="ui-widget">
    <div class="ui-state-highlight ui-corner-all">
        <p><span class="ui-icon ui-icon-info" ></span>
      <strong>Info:</strong>  ${flash.notice} </p>
    </div>
  </div>
</g:if>

<g:if test="${flash.alert}">
  <div class="ui-widget">
      <div class="ui-state-error ui-corner-all" >
          <p><span class="ui-icon ui-icon-alert" ></span>
          <strong>Alert:</strong> ${flash.alert}</p>
      </div>
  </div>
</g:if>
</div>