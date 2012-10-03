package repository;

import java.util.ArrayList;

import models.Hunt;

public class HuntRepository {
	// TODO everything ...
	public int insert()
	{
		return 0;
	}
	
	public int update(Hunt hunt)
	{
		return 0;
	}
	
	public void delete(Hunt hunt)
	{
	}
	
	public ArrayList<Hunt> getHuntsSortByCreationDate(int number, int offset)
	{
		ArrayList<Hunt> res = new ArrayList<Hunt>();
		return res;
	}
	
	public ArrayList<Hunt> getHuntsSortByParams(String order, String search, int number, int offset, boolean published)
	{
		ArrayList<Hunt> res = new ArrayList<Hunt>();
		return res;
	}
	
	public ArrayList<Hunt> getHuntsByAuthor(int authorId)
	{
		ArrayList<Hunt> res = new ArrayList<Hunt>();
		return res;
	}
	
	public Hunt getHuntById(int Id)
	{
		return new Hunt();
	}
	
}
