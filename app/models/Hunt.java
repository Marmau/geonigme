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
	private Area area;
	private User user;
	private boolean isPublished;
	private Set<gngm.Mark> marks;
	private Set<gngm.Step> steps;
	private Set<moat.Tag> tags;

	@Override
	public Area getArea() {
		return area;
	}

	@Override
	public void setArea(Area area) {
		this.area=area;

	}

	@Override
	public User getCreatedBy() {
		return user;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.user=user;

	}

	@Override
	public Boolean getIsPublished() {
		return isPublished;
	}

	@Override
	public void setIsPublished(Boolean isPublished) {
		this.isPublished=isPublished;

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
	public void setMarks(Set<? extends gngm.Mark> marks) {
		this.marks=(Set<gngm.Mark>) marks;

	}

	@Override
	public Set<Step> getSteps() {
		return steps;
	}

	@Override
	public void setSteps(Set<? extends Step> steps) {
		this.steps=(Set<gngm.Step>) steps;

	}

	@Override
	public Set<Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(Set<? extends Tag> tags) {
		this.tags=(Set<moat.Tag>) tags;

	}

}
