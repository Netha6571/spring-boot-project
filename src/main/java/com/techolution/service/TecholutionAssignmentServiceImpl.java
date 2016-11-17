package com.techolution.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.techolution.exception.TecholutionAssignmentException;

/**
 * @author Netha
 */
@Service
public class TecholutionAssignmentServiceImpl implements TecholutionAssignmentService {

	private Integer minutes=0; 
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see TecholutionAssignmentService#getMaximunSatisfaction(String)
	 */
	@Override
	public List getMaximunSatisfaction(String fileLocation) {

		List<Integer> maxSatisfactionList = null;

		try {
			Map<Integer, Integer> menuMap = prepareMenuMap(getFileBuffer(fileLocation));

			Set<Entry<Integer, Integer>> entrySet = menuMap.entrySet();

			List<Entry<Integer, Integer>> entryList = new ArrayList<>(entrySet);

			List<List<Integer>> combinationList = new ArrayList<>();

			int temp = 0;
			for (int i = 0; i < entryList.size(); i++) {

				temp = (int) entryList.get(i).getValue();

				List<Integer> possibleList = new ArrayList<>();

				possibleList.add(entryList.get(i).getKey());

				for (int j = 0; j < entryList.size(); j++) {

					temp = temp + (int) entryList.get(j).getValue();

					if (temp > minutes) {

						temp = temp - (int) entryList.get(j).getValue();

						continue;
					} else {

						possibleList.add(entryList.get(j).getKey());
					}
				}
				if (temp == minutes)
					combinationList.add(possibleList);
			}

			int sum = 0;
			int maxSum = 0;

			for (List<Integer> tempList : combinationList) {

				for (Integer integer : tempList) {
					sum = sum + integer;
				}
				if (sum > maxSum) {
					maxSum = sum;
					maxSatisfactionList = tempList;
				}
			}

		} catch (Exception e) {
			throw new TecholutionAssignmentException(e.getMessage(), e);
		}
		return maxSatisfactionList;
	}

	private Map<Integer, Integer> prepareMenuMap(BufferedReader bufferedReader) throws Exception {
		Map<Integer, Integer> menuMap = new HashMap<>();
		try {
			String line = bufferedReader.readLine();
			minutes=Integer.valueOf(line.split(" ")[0]);
			while (line != null) {
				line = bufferedReader.readLine();
				if (line != null)
					menuMap.put(Integer.valueOf(line.split(" ")[0].trim()), Integer.valueOf(line.split(" ")[1].trim()));

			}
		} finally {
			bufferedReader.close();
		}
		return menuMap;
	}

	private BufferedReader getFileBuffer(String location) throws Exception {

		return new BufferedReader(new FileReader(location));
	}
}
