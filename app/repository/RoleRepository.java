package repository;

import java.util.ArrayList;
import java.util.List;

import models.Right;
import models.Role;

public class RoleRepository {
	private static ArrayList<Role> instances = new ArrayList<>();

	public static final Role MEMBER = create("member", "Membre");
	public static final Role MODERATOR = create("moderator", "Modérateur");
	public static final Role ADMINISTRATOR = create("administrator", "Administrateur", Right.allRights());
	public static final Role DEVELOPER = create("developer", "Développeur", Right.allRights());
	
	static {
		MODERATOR.grantRight(Right.USER_LIST.AND(Right.HUNT_LIST));
	}
	
	public static Role create(String name, String label, int rights) {
		Role r = new Role();
		r.setName(name);
		r.setLabel(label);
		r.setRights(rights);
		instances.add(r);
		
		return r;
	}

	public static Role create(String name, String label) {
		return create(name, label, 0);
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
