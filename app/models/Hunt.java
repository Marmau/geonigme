package models;

import gngm.Area;
import gngm.Mark;
import gngm.Step;

import java.util.Set;

import org.openrdf.annotations.Iri;

import rdfs.subPropertyOf;

import moat.Tag;

import user.User;

public class Hunt implements gngm.Hunt {

	private Integer level;

	@Override
	public Area getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setArea(Area area) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean getIsPublished() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIsPublished(Boolean isPublished) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getLevel() {
		return level;
	}

	@Override
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public Set<Mark> getMarks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMarks(Set<? extends Mark> marks) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Step> getSteps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSteps(Set<? extends Step> steps) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Tag> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTags(Set<? extends Tag> tags) {
		// TODO Auto-generated method stub

	}

}
