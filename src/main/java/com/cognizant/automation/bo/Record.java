/**
 * 
 */
package com.cognizant.automation.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author 238209
 *
 */
public class Record {

	public static Logger logger = Logger.getLogger(Record.class);
	
	public static String DEFAULT_DATE_FORMAT = "dd/mm/yyyy";
	
	private Map<String, Object> attributes;
	
	private List<String> recordHeaders;

	public Integer getIntValue (String key) {
		Integer intValue = Integer.MIN_VALUE;
		
		Object value = getValue(key);
		
		try {
			if (value != null) {
				intValue = new Double(value.toString()).intValue();
			}
		} catch (NumberFormatException e) {
			logger.error("Error while parsing " + key, e);
			intValue = Integer.MIN_VALUE;
		}
		
		return intValue;
	}
	
	public String getStringValue (String key) {
		Object value = getValue(key);
		
		if (value != null) {
			return value.toString();
		}
		
		return null;
	}


	public Date getDateValue(String key) {
		Date dateValue = null;
		Object value = getValue(key);

		try {
			if (value != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				dateValue = sdf.parse(value.toString());
			}
		} catch (ParseException e) {
			logger.error("Error while parsing Date of Joining", e);
		}
		
		return dateValue;
	}

	public Object getValue (String key) {
		return attributes.get(key);
	}
	
	public void addAttribute(String key, Object value) {
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
		}
	
		addRecordHeader(key);
		attributes.put(key, value);
	}
	
	public void addRecordHeader(String header) {
		if (recordHeaders == null) {
			recordHeaders = new ArrayList<String>();
		}
		
		if (recordHeaders.contains(header) == false) {
			recordHeaders.add(header);
		}
	}
	
	/**
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the recordHeaders
	 */
	public List<String> getRecordHeaders() {
		return recordHeaders;
	}

	/**
	 * @param recordHeaders the recordHeaders to set
	 */
	public void setRecordHeaders(List<String> recordHeaders) {
		this.recordHeaders = recordHeaders;
	}
}
