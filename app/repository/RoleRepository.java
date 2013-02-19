package repository;

import java.util.ArrayList;
import java.util.List;

import models.Right;
import models.Role;

public class RoleRepository {
	private static ArrayList<Role> instances = new ArrayList<>();

	public static final Role MEMBER = create("member", Right.MEMBER_AREA);
	public static final Role MODERATOR = create("moderator", Right.MEMBER_AREA);
	public static final Role ADMINISTRATOR = create("administrator", Right.allRights());
	public static final Role DEVELOPER = create("developer", Right.allRights());
	
	static {
		MODERATOR.grantRight(Right.USER_LIST.AND(Right.HUNT_LIST));
	}
	
	public static Role create(String name, int rights) {
		//System.out.println("Creating role "+name+" with rights "+rights);
		Role r = new Role();
		r.setName(name);
		r.setRights(rights);
		instances.add(r);
		return r;
	}
	public static Role create(String name, Right rights) {
		return create(name, rights.v());
	}
	public static Role create(String name) {
		return create(name, 0);
	}

	public static List<Role> getAll() {
		return instances;
	}
	
	public static Role get(String name) {
		for (Role r : instances) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
	
	public static void init() {
		// Do nothing, static values automatically setted.
	}
}
