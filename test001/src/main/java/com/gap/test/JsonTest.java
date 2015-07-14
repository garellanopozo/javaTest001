package com.gap.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonTest {

	void readJsonFromFile() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("D:\\referers.json"));
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMatchedElementOfReferrerEntity(
			String hostAndPathWanted) {
		LinkedHashMap<String, Object> elementMatched = null;
		List<String> domains;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> setEntry2;
		Map<String, Object> setEntry3;
		Set<Map.Entry<String, Object>> entries;
		Set<Map.Entry<String, Object>> entries2;
		elementMatched = new LinkedHashMap<String, Object>();
		try {
			Map<String, Object> jsonMap = mapper.readValue(new FileReader(
					"D:\\referers.json"), Map.class);
			Set<Map.Entry<String, Object>> setEntry = jsonMap.entrySet();
			for (Map.Entry<String, Object> mapEntry : setEntry) {
				setEntry2 = ((Map<String, Object>) mapEntry.getValue());
				entries = setEntry2.entrySet();
				for (Map.Entry<String, Object> mapEntries : entries) {
					setEntry3 = ((Map<String, Object>) mapEntries.getValue());
					entries2 = setEntry3.entrySet();
					for (Map.Entry<String, Object> mapEntries2 : entries2) {
						domains = (List<String>) mapEntries2.getValue();
						if (domains.contains(hostAndPathWanted)) {
							elementMatched.put("medium", mapEntry.getKey());
							elementMatched.put("source", mapEntries.getKey());
							elementMatched.put("elementFound", mapEntries);
							System.out.println(elementMatched);
							return elementMatched;
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return elementMatched;
	}

	public static void main(String[] args) {
		JsonTest obj = new JsonTest();
		//obj.readJsonFromFile();
		obj.getMatchedElementOfReferrerEntity("www.talktalk.co.uk");
	}

}
