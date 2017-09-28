package com.gochinatv.cdn.api.third.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;

public class LogParseRequestObservableCommand extends HystrixObservableCommand<String>{

	private final String name;
	
	protected LogParseRequestObservableCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("LogParseRequestObservableCommand"));
        this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		return new LogParseRequestObservableCommand("test").observe();
	}

}
