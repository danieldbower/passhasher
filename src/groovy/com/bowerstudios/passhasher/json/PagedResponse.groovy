package com.bowerstudios.passhasher.json

class PagedResponse {
	
	boolean success
	
	/**
	 * Payload - Paged Data
	 */
	Collection data
	
	int total
	
	/**
	 * Descriptive message to the client about the action that occurred
	 */
	String message
	
	public PagedResponse(Collection data, int total, String message){
		success = true
		this.data = data
		this.total = total
		this.message = message
	}

	public static jsonProperties = { PagedResponse resp ->
		['success': resp.success, 'data':resp.data, 'total': resp.total,
			'message':resp.message]
	}
}
