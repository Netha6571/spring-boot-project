package com.techolution.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techolution.exception.TecholutionAssignmentException;

/**
 * Utility class for handling Json string
 * 
 * @author Netha
 *
 */

public class JSONUtil {
	
	/**
	 * Logger Refference.
	 */
	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

	/**
	 * Object mapper refference
	 */
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Translates any object to well formed json string.
	 * 
	 * @param obj
	 *            object
	 * @return well formed json 
	 * 						string
	 */
	public static String getJsonString(Object obj) {
		String jsonString = "";
		try {
			jsonString =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException ex) {
			logger.info("Exception occured in converting object to json string "+ex);
			throw new TecholutionAssignmentException(ex.getMessage());
		}
		return jsonString;
	}
}

