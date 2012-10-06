package models.old;

import java.util.Set;

public class Tag implements tags.Tag {
	
	private String name;
	private Set<tags.Tag> equivalentTags;
	private Set<Object> isTagOf;
	private Set<tags.Tag> relatedTags;
	private Set<Object> tagNames;

	@Override
	public Set<tags.Tag> getEquivalentTags() {
		return equivalentTags;
	}

	@Override
	public void setEquivalentTags(Set<? extends tags.Tag> equivalentTags) {
		this.equivalentTags=(Set<tags.Tag>) equivalentTags;
		
	}

	@Override
	public Set<Object> getIsTagOf() {
		return isTagOf;
	}

	@Override
	public void setIsTagOf(Set<?> isTagOf) {
		this.isTagOf=(Set<Object>)isTagOf;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
		
	}

	@Override
	public Set<tags.Tag> getRelatedTags() {
		return relatedTags;
	}

	@Override
	public void setRelatedTags(Set<? extends tags.Tag> relatedTags) {
		this.relatedTags=(Set<tags.Tag>)relatedTags;
		
	}

	@Override
	public Set<Object> getTagNames() {
		return tagNames;
	}

	@Override
	public void setTagNames(Set<?> tagNames) {
		this.tagNames=(Set<Object>) tagNames;
		
	}

}
