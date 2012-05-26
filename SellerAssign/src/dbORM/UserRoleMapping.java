package dbORM;

public class UserRoleMapping {
	int id;
	int user_id;
	int role_id;

	public int getId() {
		return id;
	}

	public int getUser_id() {
		return user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
}
