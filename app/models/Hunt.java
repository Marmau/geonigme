package models;

import gngm.Area;
import gngm.Mark;
import gngm.Step;

import java.util.Set;

import moat.Tag;

import user.User;

public class Hunt implements gngm.Hunt {

<<<<<<< HEAD
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
=======
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
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    @Override
    public User getCreatedBy() {
	// TODO Auto-generated method stub
	return null;
    }

<<<<<<< HEAD
	@Override
	public User getCreatedBy() {
		return user;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.user=user;
=======
    @Override
    public void setCreatedBy(User createdBy) {
	// TODO Auto-generated method stub

    }
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    @Override
    public Boolean getPublished() {
	// TODO Auto-generated method stub
	return null;
    }

<<<<<<< HEAD
	@Override
	public Boolean getIsPublished() {
		return isPublished;
	}

	@Override
	public void setIsPublished(Boolean isPublished) {
		this.isPublished=isPublished;
=======
    public Boolean isPublished() {
	return getPublished();
    }

    @Override
    public void setPublished(Boolean published) {
	// TODO Auto-generated method stub
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    }

    @Override
    public Integer getLevel() {
	return level;
    }

    @Override
    public void setLevel(Integer level) {
	this.level = level;
    }

<<<<<<< HEAD
	@Override
	public Set<gngm.Mark> getMarks() {
		return marks;
	}

	@Override
	public void setMarks(Set<? extends gngm.Mark> marks) {
		this.marks=(Set<gngm.Mark>) marks;
=======
    @Override
    public Set<Mark> getMarks() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setMarks(Set<? extends Mark> marks) {
	// TODO Auto-generated method stub
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    }

<<<<<<< HEAD
	@Override
	public Set<Step> getSteps() {
		return steps;
	}

	@Override
	public void setSteps(Set<? extends Step> steps) {
		this.steps=(Set<gngm.Step>) steps;
=======
    @Override
    public Set<Step> getSteps() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setSteps(Set<? extends Step> steps) {
	// TODO Auto-generated method stub
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    }

<<<<<<< HEAD
	@Override
	public Set<Tag> getTags() {
		return tags;
	}

	@Override
	public void setTags(Set<? extends Tag> tags) {
		this.tags=(Set<moat.Tag>) tags;
=======
    @Override
    public Set<Tag> getTags() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setTags(Set<? extends Tag> tags) {
	// TODO Auto-generated method stub
>>>>>>> a5a0d65e8784c80ab0a5d67e25e8f0526ddac278

    }

}
