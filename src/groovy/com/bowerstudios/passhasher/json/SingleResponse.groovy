package com.bowerstudios.passhasher.json

class SingleResponse {
	boolean success
	Object data
	
	public SingleResponse(Object data){
		success = true
		this.data = data
	}

	public static jsonProperties = { SingleResponse resp ->
		['success': resp.success, 'data':resp.data ]
	}
}
