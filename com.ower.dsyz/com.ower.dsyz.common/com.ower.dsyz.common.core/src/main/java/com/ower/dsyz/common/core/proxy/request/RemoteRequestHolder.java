package com.ower.dsyz.common.core.proxy.request;

public class RemoteRequestHolder {

	private final static AbstractRemoteRequest remoteRequest = new HttpRemoteRequest() ;
	
	public static AbstractRemoteRequest get(){
		return remoteRequest;
	}
	
	
}
