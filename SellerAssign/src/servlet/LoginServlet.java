package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import dbORM.User;
import dbORM.UserRoleMapping;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User where loginId =:loginId");
		query.setParameter("loginId", request.getParameter("userName"));
		System.out.println("******** " + query.getQueryString());
		List userList = query.list();
		if (userList.size() == 0) {
			try {
				throw new Exception("User not found");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createUser(request, session);
		} else {
			handleLogin(userList, request, response);
		}

		session.clear();
		session.close();
	}

	private void handleLogin(List userList, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Iterator it = userList.iterator();
		while (it.hasNext()) {
			User usr = (User) it.next();
			System.out.print("loginId..." + usr.getLoginId() + "passwordis .."
					+ usr.getPassword());
			if (usr.getPassword().equals(request.getParameter("password"))) {
				setAuthCookie(request, response);
				List userRoleList = getUserRole(usr.getId());
				List roles = getRoles();
				request.setAttribute("loggedInUser", usr);
				request.setAttribute("userRoleList", userRoleList);
				request.setAttribute("roles", roles);
				request.getRequestDispatcher("/updateUser/UpdateUser.jsp")
						.forward(request, response);

			} else {
				request.getRequestDispatcher("/login/Login.jsp").forward(
						request, response);
			}
		}
	}

	private void setAuthCookie(HttpServletRequest request,
			HttpServletResponse response) {
		String cookieId = UUID.randomUUID().toString();
		Cookie authCookie = new Cookie("authCookie", cookieId);
		request.getSession().setAttribute("authCookie", authCookie);
		response.addCookie(authCookie);
	}

	private List getRoles() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session.createQuery("from Role").list();
	}

	private List getUserRole(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from UserRoleMapping where user_id =:id ");
		// .createQuery("from User as u right outer join UserRoleMapping as ur on u.id = ur.user_id right outer join Role rl on rl.id=ur.role_id where u.id =:id ");
		query.setParameter("id", id);
		List lst = query.list();

		Iterator it = lst.iterator();
		while (it.hasNext()) {
			UserRoleMapping urm = (UserRoleMapping) it.next();
			System.out.println(".." + urm.getId() + ".." + urm.getRole_id()
					+ "userid.." + urm.getUser_id());
			// System.out.print(it.next());

		}
		return lst;
	}

	private void createUser(HttpServletRequest request, Session session) {
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setLoginId(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		session.save(user);
		tx.commit();
	}

}
