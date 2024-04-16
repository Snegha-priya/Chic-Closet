package com.chiccloset.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.chiccloset.config.MultitenantConfiguration;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @Autowired private ErrorLogDao errorLogDao;
	 * 
	 * @Autowired private MailService mailService;
	 */

	@Autowired
	private MultitenantConfiguration multitenantConfiguration;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> Exception(Exception exception, WebRequest webRequest, HandlerMethod handler) {
		ResponseEntity<String> entity = new ResponseEntity<String>(exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);

		String errorMessage = "General Exception :: On Controller :: " + handler.getMethod().getDeclaringClass()
				+ " :: At Method :: " + handler.getMethod().getName() + " :: " + exception.getMessage();
		logger.error(errorMessage);
		try {

			/*
			 * multitenantConfiguration.setDefaultTenant(); ErrorLogModel errorLogModel =
			 * ErrorLogModel.builder().createdtime(LocalDateTime.now())
			 * .modifiedtime(LocalDateTime.now()).status(0).tenantid("" +
			 * TenantContext.getCurrentTenant())
			 * .module("General Exception").message(exception.getMessage()).build();
			 * errorLogDao.save(errorLogModel);
			 */
			// mailService.exceptionMail(1000, errorMessage);
		} catch (Exception e) {
			logger.error("General Exception ::  Mail :: 1000 :: " + e.getMessage());
		}
		logger.info("hello");
		return entity;
	}

	@ExceptionHandler(ChicClosetException.class)
	public ResponseEntity<String> VaruvaaiException(ChicClosetException exception, WebRequest webRequest,
			HandlerMethod handler) {
		ResponseEntity<String> entity = new ResponseEntity<String>(exception.getMessage(),
				HttpStatus.BAD_REQUEST);

		String errorMessage = "Varuvaai Exception :: On Controller :: " + handler.getMethod().getDeclaringClass()
				+ " :: At Method :: " + handler.getMethod().getName() + " :: " + exception.getMessage();
		logger.error(errorMessage);

		try {
			/*
			 * multiTenantJpaConfiguration.setCurrentTenant(UtilConstants.PUBLIC);
			 * ErrorLogModel errorLogModel =
			 * ErrorLogModel.builder().createdtime(LocalDateTime.now())
			 * .modifiedtime(LocalDateTime.now()).status(0).module("Varuvaai Exception")
			 * .message(exception.getMessage()).build(); if
			 * (!TenantContext.getCurrentTenant().equalsIgnoreCase("public")) {
			 * errorLogModel.setTenantid("" + TenantContext.getCurrentTenant()); }
			 * errorLogDao.save(errorLogModel);
			 */

			// mailService.exceptionMail(exception.getErrorCode(), errorMessage);
		} catch (Exception e) {
			logger.error("Varuvaai Exception ::  Mail :: 1000 :: " + e.getMessage());
		}
		return entity;
	}
}
