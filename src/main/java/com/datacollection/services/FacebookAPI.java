package com.datacollection.services;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Facebook;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Pagination;
import com.datacollection.utils.Parser;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Parameter;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class FacebookAPI implements Facebook {
	
	private OAuthRequest request;
	private OAuth20Service service ;	
	public FacebookAPI() 
	{
		this.service= (OAuth20Service)Auth.getFacebookInstance().getService();		
	}

	/**
	 * Factory method
	 * @return
	 */
	public static Facebook build()
	{
		return  new FacebookAPI();
	}
	public JSONObject getUser() {
		
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookUserInfo_ENDPOINT(), this.service);
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request); 
		Response response = request.send();
		
		JSONObject result=new JSONObject("{'Error':'insidecode'}");
		try {
			System.out.println(response.getBody().toString());
			result = new JSONObject(response.getBody().toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Set<JSONObject> searchPlaces(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("type", "place");
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getAppAccessToken(), request); 
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}
	

	public Set<JSONObject> searchTopics(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearchTopic_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getAppAccessToken(), request); 
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}

	public Set<JSONObject> searchPages(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("type", "page");
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getAppAccessToken(), request); 
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
		
	}

	public Set<JSONObject> searchUsers(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("type", "user");
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request); 
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
		}

	@Override
	public Set<JSONObject> searchEvents(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("type", "event");
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request); 
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}

	@Override
	public Set<JSONObject> searchGroups(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("type", "group");
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request); 
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}
	public Set<JSONObject> getFeed(String id) {		
		
		return this.getAppEdge(id, "feed");
	}

	public Set<JSONObject> getPosts(String id) {
		// TODO Auto-generated method stub
		return this.getAppEdge(id, "posts");
	}

	

	@Override
	public Set<JSONObject> getAppEdge(String nodeID, String edge,Parameter... params) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebook_BASEURL()+"/"+nodeID+"/"+edge, this.service);
		for(Parameter param:params)
			this.request.addParameter(param.getKey(), param.getValue());
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getAppAccessToken(), request);
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}
	
	@Override
	public Set<JSONObject> getUserEdge(String nodeID, String edge,Parameter... params) {
		this.request = new OAuthRequest(Verb.GET, Config.getFacebook_BASEURL()+"/"+nodeID+"/"+edge, this.service);
		for(Parameter param:params)
			this.request.addParameter(param.getKey(), param.getValue());
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request);
		Pagination.initPaginator("facebook");
		Pagination.buildPaginator(this.service).facebookPaginator(this.request);
		return Pagination.getFacebookData();
	}

	

}
