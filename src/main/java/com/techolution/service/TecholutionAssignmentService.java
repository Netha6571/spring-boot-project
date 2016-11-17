package com.techolution.service;

import java.util.List;

/**
 * @author Netha
 */
public interface TecholutionAssignmentService {

	/**
	 * This method will give the maximum satisfaction value from the given meu.
	 * 
	 * @param fileLocation
	 *            String
	 * @return List
	 */
	List getMaximunSatisfaction(String fileLocation);
}
