package com.bowerstudios.passhasher.json

class SingleResponse {
	
	boolean success
	
	/**
	 * Payload - data affected by an action
	 */
	Object data
	
	/**
	 * Descriptive message to the client about the action that occurred
	 */
	String message
	
	public SingleResponse(Object data, String message){
		success = true
		this.data = data
		this.message = message
	}

	public static jsonProperties = { SingleResponse resp ->
		['success': resp.success, 'data':resp.data, 'message':resp.message]
	}
}
