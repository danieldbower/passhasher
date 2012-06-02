package com.bowerstudios.passhasher.json

class PagedResponse {
	boolean success
	Collection data
	int total
	
	public PagedResponse(Collection data, int total){
		success = true
		this.data = data
		this.total = total
	}

	public static jsonProperties = { PagedResponse resp ->
		['success': resp.success, 'data':resp.data, 'total': resp.total]
	}
}
