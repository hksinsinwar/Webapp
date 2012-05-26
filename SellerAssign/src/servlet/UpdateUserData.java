package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import dbORM.Role;
import dbORM.User;
import dbORM.UserRoleMapping;

/**
 * Servlet implementation class UpdateUserData
 */
@WebServlet("/UpdateUserData")
public class UpdateUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// UserRoleMapping usrRollMapping = session.get(UserRoleMapping, )
		String[] userroles = request.getParameterValues("roles");
		String loginId = request.getParameter("userName");
		String password = request.getParameter("password");
		String userId = request.getParameter("userid");
		List roles = getRoles();
		Iterator it = roles.iterator();
		while (it.hasNext()) {
			Role role = (Role) it.next();
			if (isUserRole(role, userroles)) {
				saveUserRole(role, userId);
			} else {
				deleteRole(role, userId);
			}
		}
		for (int i = 0; i < userroles.length; i++) {

		}

		updateOrSaveUser(loginId, password);
	}

	private void deleteRole(Role role, String userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query qury = session
				.createQuery("delete from UserRoleMapping where  role_id=:id and user_id=:user_id");
		qury.setParameter("id", role.getId());
		qury.setParameter("user_id", userId);
		
		int count = qury.executeUpdate();
		tx.commit();
		session.flush();
		session.close();

	}

	private boolean isUserRole(Role role, String[] userroles) {
		// TODO Auto-generated method stub
		for (int i = 0; i < userroles.length; i++) {
			if (Integer.valueOf(userroles[i]) == role.getId()) {
				return true;
			}
		}
		return false;
	}

	private List getRoles() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Role");
		List lst = query.list();
		session.close();
		return lst;
	}

	private void updateOrSaveUser(String loginId, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query qury = session.createQuery("from User where loginId =:id");
		qury.setParameter("id", loginId);
		User usr = null;
		if (qury.list().size() > 0)
			usr = (User) qury.list().get(0);
		else
			usr = new User();
		usr.setLoginId(loginId);
		usr.setPassword(password);
		session.saveOrUpdate(usr);
		tx.commit();
		session.flush();
		session.close();
	}

	private void saveUserRole(Role role, String userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		UserRoleMapping userRol = new UserRoleMapping();
		userRol.setRole_id(role.getId());
		userRol.setUser_id(Integer.valueOf(userId));
		session.saveOrUpdate(userRol);
		tx.commit();
		session.flush();
		session.close();
	}

}
