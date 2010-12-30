// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults

//Actions to perform immediately and on document ready
  $(function() {
    $("#tabs").tabs()
  })

  $(document).ready(function() {
    hideAdvanced()
  });


//functions available to the application



function hashit(placename, times, encodingChars){
  //pull the master password and the place
  masterPass = $("#master").val() 
  if(masterPass == ""){
    alert('Master Password is required')
    return
  }

  toHash = masterPass + placename

  //encode to utf8
  hashed = str2rstr_utf8(toHash)

  //hash the specified number of times
  for (i=0; i<times; i++) {
    hashed = rstr_sha256(hashed)
    //hashed = rstr_md5(hashed)
  }

  //encode to hex
  encoded = rstr2any(hashed, encodingChars)

  //set field on page
  $("#hashedPass").text(encoded)

  //give focus to field and highlight text?
}

function resetHasher(val){
  $("#hashedPass").text(val)
  $("#master").val("") 
}

function showAdvanced(){
  $(".advanced").show()
  $(".expandAdvanced").hide()
}

function hideAdvanced(){
  $(".advanced").hide()
  $(".expandAdvanced").show()
}
