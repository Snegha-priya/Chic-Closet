package com.chiccloset.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.chiccloset.Application;

public class StreamLambdaHandler implements RequestStreamHandler {

	private final static Logger logger = LoggerFactory.getLogger("StreamLambdaHandler");

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	
	public StreamLambdaHandler() throws ContainerInitializationException {
        handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
                .defaultProxy()
                .asyncInit()
                .springBootApplication(Application.class)
                .buildAndInitialize();
    }
	
	/*static {
		try {
			// handler =
			// SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
			long startTime = Instant.now().toEpochMilli();
			handler = ((SpringBootProxyHandlerBuilder<AwsProxyRequest>) new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
					.defaultProxy().asyncInit(startTime)).springBootApplication(Application.class).buildAndInitialize();
			
			handler = new 
			// we use the onStartup method of the handler to register our custom filter
			/*
			 * handler.onStartup(servletContext -> { FilterRegistration.Dynamic registration
			 * = servletContext.addFilter("CognitoIdentityFilter",
			 * CognitoIdentityFilter.class);
			 * registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
			 * true, "/*"); });
			 */
		/*} catch (ContainerInitializationException e) {
			logger.error("Exception in SpringBootLambdaContainerHandler :: " + e.getMessage());
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}*/

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		logger.debug("handleRequest");
		handler.proxyStream(inputStream, outputStream, context);
	}

}
