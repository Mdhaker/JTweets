package com.datacollection.interfaces;
import java.util.Set;
import org.json.JSONObject;

import com.datacollection.services.TwitterAPI;
import com.datacollection.utils.SearchFilter;
public interface Twitter extends Reader
{
	/**
	 * get tweets by query string, handle twitter request query
	 * @param Query text
	 * @return list of json objects of tweets
	 */
	public Set<JSONObject> getTweets(String query);
	
	/**
	 * get tweets by a filter object
	 * @param filter filter object to handle the query request
	 * @return list of json objects of tweets
	 */
	
	public Set<JSONObject> getTweets(SearchFilter.TwitterFilter filter);
	/**
	 * Search Place by query
	 * @param query
	 * @return
	 */
	public Set<JSONObject> getPlaces(String query);
	/**
	 * setting Geo location params
	 * @param longitude
	 * @param latitude
	 * @param radius in km
	 */
	
	public TwitterAPI setGeo(float longitude, float latitude, float radius);
	/**
	 *  Setting language ISO 639-1
	 * @param lang tow chars
	 */
	public TwitterAPI setLang(String lang);	
	/**
	 * Specifies what type of search results you would prefer to receive. The current default is “mixed.” Valid values include:

		* mixed : Include both popular and real time results in the response.
		
		* recent : return only the most recent results in the response
		
		* popular : return only the most popular results in the response.
	 */
	public TwitterAPI setResultType(String result_type);
	/**
	 * The number of tweets to return per page, up to a maximum of 100. Defaults to 15. This was formerly the “rpp” parameter in the old Search API.
	 * @param count
	 */
	public TwitterAPI setCount(int count);
	/**
	 * The entities node will not be included when set to false.	
	 * @param include yes or no 
	 */
	public TwitterAPI setEntity(boolean include);
	

}
