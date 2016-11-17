package com.techolution.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techolution.exception.TecholutionAssignmentException;
import com.techolution.service.TecholutionAssignmentService;
import com.techolution.service.TecholutionAssignmentServiceImpl;
import com.techolution.util.JSONUtil;

/**
 * Acts as a Front controller for all services.
 * 
 * @author Netha
 *
 */

@RestController("/")
public class TecholutionAssignmentController {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(TecholutionAssignmentController.class);

	/**
	 * TecholutionAssignmentService reference.
	 */
	TecholutionAssignmentService techolutionAssignmentService;
	/**
	 * Instantiates a new TecholutionAssignmentController
	 * 
	 * @param techolutionAssignmentService
	 *            TecholutionAssignmentServiceImpl
	 * @see com.techolution.service.TecholutionAssignmentService
	 */
	@Autowired
	public TecholutionAssignmentController(TecholutionAssignmentServiceImpl techolutionAssignmentService) {
		super();
		this.techolutionAssignmentService = techolutionAssignmentService;
	}

	/**
	 * Provides Maximum Satisfaction Value 
	 * @return Json String
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMaximunSatisfaction() {
		logger.info("starting the process of getMaximunSatisfaction method");
		ResponseEntity<String> responseEntity = null;
		try {
			List response = techolutionAssignmentService.getMaximunSatisfaction("E:\\data\\data.txt");
			responseEntity = new ResponseEntity<String>(JSONUtil.getJsonString(response), HttpStatus.OK);
		} catch (TecholutionAssignmentException exception) {
			logger.error("Error in getMaximunSatisfaction ", exception);
			responseEntity = new ResponseEntity<String>(JSONUtil.getJsonString(exception.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
