package repository;

import java.util.ArrayList;
import java.util.List;

import models.Right;
import models.Role;

public class RoleRepository {
	private static ArrayList<Role> instances = new ArrayList<>();

	public static final Role MEMBER = create("member");
	public static final Role MODERATOR = create("moderator");
	public static final Role ADMINISTRATOR = create("administrator", Right.allRights());
	public static final Role DEVELOPER = create("developer", Right.allRights());
	
	static {
		MODERATOR.grantRight(Right.USER_LIST.AND(Right.HUNT_LIST));
	}
	
	public static Role create(String name, int rights) {
		Role r = new Role();
		r.setName(name);
		r.setRights(rights);
		instances.add(r);
		
		return r;
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
}
