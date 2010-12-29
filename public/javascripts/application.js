// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults

function hashit(placename, times, encodingChars){
  //pull the master password and the place
  toHash = $('master').value + placename

  //encode to utf8
  hashed = str2rstr_utf8(toHash)

  //hash the specified number of times
  for (i=0; i<times; i++) {
    hashed = rstr_md5(hashed)
  }

  //encode to hex
  encoded = rstr2any(hashed, encodingChars)

  //set field on page
  $('hashedPass').firstChild.nodeValue = encoded

  //give focus to field and highlight text?
}

