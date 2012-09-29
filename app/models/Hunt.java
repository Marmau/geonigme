package models;

import gngm.Area;
import gngm.Mark;
import gngm.Step;

import java.util.Set;


import tags.*;
import user.User;

public class Hunt implements gngm.Hunt {

	private Integer level;
	private Area area;
	private User createdBy;
	private boolean published;
	private Set<Mark> marks;
	private Set<Step> steps;
	private Set<Tag> tags;

	@Override
	public Area getArea() {
		return area;
	}

	@Override
	public void setArea(Area area) {
		this.area = area;
	}
	
	@Override
	public User getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Boolean getPublished() {
		// TODO Auto-generated method stub
		return published;
	}

	@Override
	public void setPublished(Boolean published) {
		this.published = published;

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
	public Set<gngm.Mark> getMarks() {
		return marks;
	}

	@Override
	public void setMarks(Set<? extends Mark> marks) {
		this.marks = (Set<Mark>) marks;
	}

	@Override
	public Set<Step> getSteps() {
		return steps;
	}

	@Override
	public void setSteps(Set<? extends Step> steps) {
		this.steps = (Set<Step>) steps;
	}

	@Override
	public Set<Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(Set<? extends Tag> tags) {
		this.tags = (Set<Tag>) tags;
	}

}
