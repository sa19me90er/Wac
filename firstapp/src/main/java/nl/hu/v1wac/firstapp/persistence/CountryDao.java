package nl.hu.v1wac.firstapp.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.lang.model.element.VariableElement;
import javax.validation.constraints.Null;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDao extends BaseDao
{
	public Country save(Country country)
	{
		nonQuery("INSERT INTO public.country("+
				"code, name, continent, region, surfacearea, indepyear, population,"+ 
				"lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate,"+ 
				"code2, latitude, longitude, capital)"+
			"VALUES (?, ?, ?, ?, ?, ?, ?,"+ 
            		"?, ?, ?, ?, ?, ?,"+ 
					"?, ?, ?, ?);",
					country.getCode(),
					country.getName(),
					country.getContinent(),
					country.getRegion(),
					country.getSurface(),
					1995,
					country.getPopulation(),
					10,
					70,
					70,
					country.getName(),
					country.getGovernment(),
					"",
					country.getIso3Code(),
					country.getLatitude(),
					country.getLongitude(),
					country.getCapital());
		
		return country;
	}
	
	public Country update(Country country)
	{
		 nonQuery("UPDATE public.country"+ 
   " SET name=?, continent=?, region=?, surfacearea=?, indepyear=?,"+  
       " population=?, lifeexpectancy=?, gnp=?, gnpold=?, localname=?, "+ 
       " governmentform=?, headofstate=?, code2=?, latitude=?, longitude=?,"+  
       " capital=?"+ 
 " WHERE code = ?;",
			country.getName(),
			country.getContinent(),
			country.getRegion(),
			country.getSurface(),
			1995,
			country.getPopulation(),
			10,
			70,
			70,
			country.getName(),
			country.getGovernment(),
			"",
			country.getIso3Code(),
			country.getLatitude(),
			country.getLongitude(),
			country.getCapital(),
			country.getCode());
				
		return country;
	}
	
	public boolean delete(String country)
	{
		nonQuery("DELETE FROM countrylanguage WHERE countrycode = ?",country);
		nonQuery("DELETE FROM City WHERE countrycode = ?",country);
		return nonQuery("DELETE FROM Country WHERE Code = ?",country);
	}
	
	public boolean delete(Country country)
	{
		return nonQuery("DELETE FROM Country WHERE Code = ?",country.getCode());
	}
	
	public ArrayList<Country> findAll()
	{
		ArrayList<Map<String, Object>> result = query("SELECT Code,code2,name,capital,continent,region,surfacearea,population,governmentform,latitude,longitude FROM Country");
		ArrayList<Country> returnValue = new ArrayList<Country>();
		for(Map<String, Object> country : result)
		{
			returnValue.add(getByDictonary(country));
		}
		
		return returnValue;
	}
	
	public Country findByCode(String code)
	{
		ArrayList<Map<String, Object>> result = query("SELECT Code,code2,name,capital,continent,region,surfacearea,population,governmentform,latitude,longitude FROM Country where Code = ?", code);
		
		if(result == null || result.size() == 0)
			return null;
		
		Map<String, Object> country = result.get(0);
		return getByDictonary(country);
		
	}
	
	public ArrayList<Country> findTenLargestPopulations()
	{
		ArrayList<Map<String, Object>> result = query("SELECT Code,code2,name,capital,continent,region,surfacearea,population,governmentform,latitude,longitude FROM Country order by population desc limit 10");
		ArrayList<Country> returnValue = new ArrayList<Country>();
		for(Map<String, Object> country : result)
		{
			returnValue.add(getByDictonary(country));
		}
		
		return returnValue;
	} 
	
	public ArrayList<Country> findTenLargestSurface()
	{
		ArrayList<Map<String, Object>> result = query("SELECT Code,code2,name,capital,continent::varchar,region,surfacearea,population,governmentform,latitude,longitude FROM Country order by surfacearea desc limit 10");
		ArrayList<Country> returnValue = new ArrayList<Country>();
		for(Map<String, Object> country : result)
		{
			returnValue.add(getByDictonary(country));
		}
		
		return returnValue;
	} 
	
	private Country getByDictonary(Map<String, Object> country)
	{
		String code = (String)country.get("code");
		String Code3 = (String)country.get("code2");
		String name = (String)country.get("name");
		String capital = (String)country.get("capital");
		String continent = country.get("continent").toString();
		String region = (String)country.get("region");
		double surface = ((BigDecimal)country.get("surfacearea")).doubleValue();
		int population = (int)country.get("population");
		String goverment = (String)country.get("governmentform");
		
		double lat = country.get("latitude") == null ? 0 : ((BigDecimal)country.get("latitude")).doubleValue();
		double lng = country.get("latitude") == null ? 0 : ((BigDecimal)country.get("longitude")).doubleValue();
		
		return new Country(code, Code3, name, capital, continent, region, surface, population, goverment, lat, lng);
	}
	
}
