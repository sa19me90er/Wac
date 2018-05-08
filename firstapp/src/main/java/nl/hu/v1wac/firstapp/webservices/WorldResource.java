package nl.hu.v1wac.firstapp.webservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.lang.model.element.VariableElement;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import nl.hu.v1wac.firstapp.persistence.CountryDao;
import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/countries")
public class WorldResource
{
	private CountryDao _countryDao;
	
	public WorldResource()
	{
		_countryDao = new CountryDao();
	}
	
	
	
	@GET
	@Produces("application/json")
//	@RolesAllowed({ "user", "admin" })
	public String getWorldResource()
	{
		ArrayList<Country> countries = _countryDao.findAll();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : countries)
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			job.add("iso3", c.getIso3Code());
			job.add("continent", c.getContinent());
			job.add("region", c.getRegion());
			job.add("population", c.getPopulation());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces()
	{
		JsonArrayBuilder jab = Json.createArrayBuilder();
		ArrayList<Country> countries = _countryDao.findTenLargestSurface();
		for (Country c : countries)
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			job.add("iso3", c.getIso3Code());
			job.add("continent", c.getContinent());
			job.add("region", c.getRegion());
			job.add("population", c.getPopulation());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("largestpopulations")
	@Produces("application/json")
	public String getLargestPopulations()
	{
		
		ArrayList<Country> countries = _countryDao.findTenLargestPopulations();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : countries)
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			job.add("iso3", c.getIso3Code());
			job.add("continent", c.getContinent());
			job.add("region", c.getRegion());
			job.add("population", c.getPopulation());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{country}")
	@Produces("application/json")
	public String getCn(@PathParam("country") String country)
	{
		Country c = _countryDao.findByCode(country);
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("code", c.getCode());
		job.add("name", c.getName());
		job.add("capital", c.getCapital());
		job.add("surface", c.getSurface());
		job.add("goverment", c.getGovernment());
		job.add("lat", c.getLatitude());
		job.add("lng", c.getLongitude());
		job.add("iso3", c.getIso3Code());
		job.add("continent", c.getContinent());
		job.add("region", c.getRegion());
		job.add("population", c.getPopulation());

		return job.build().toString();
	}
	
	@DELETE
	@Path("delete/{code}")
	@Produces("application/json")
	public void delete(@PathParam("code") String code)
	{
		_countryDao.delete(code);
	}
	
	@PUT
	@Path("save")
	public void save(@QueryParam("code") String code,
			@QueryParam("iso3") String iso3,
			@QueryParam("name") String name,
			@QueryParam("capital") String capital,
			@QueryParam("continent") String continent,
			@QueryParam("region") String region,
			@QueryParam("goverment") String goverment,
			@QueryParam("population") int population,
			@QueryParam("population") double lat,
			@QueryParam("longitude") double longitude,
			@QueryParam("surface") double surface)
	{
		Country country = new Country(code,iso3, name, capital, continent, region, surface, population, goverment, lat, longitude);
		
		
		_countryDao.update(country);
	}
	
	@POST
	@Path("create")
	public void create(@QueryParam("code") String code,
			@QueryParam("iso3") String iso3,
			@QueryParam("name") String name,
			@QueryParam("capital") String capital,
			@QueryParam("continent") String continent,
			@QueryParam("region") String region,
			@QueryParam("goverment") String goverment,
			@QueryParam("population") int population,
			@QueryParam("population") double lat,
			@QueryParam("longitude") double longitude,
			@QueryParam("surface") double surface)
	{
		Country country = new Country(code,iso3, name, capital, continent, region, surface, population, goverment, lat, longitude);
		
		
		_countryDao.save(country);
	}
}