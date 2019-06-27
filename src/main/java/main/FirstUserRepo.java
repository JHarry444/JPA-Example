package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FirstUserRepo implements UserRepo {
	
	private EntityManagerFactory  managerFactory = Persistence.createEntityManagerFactory("primary");
	private EntityManager manager = managerFactory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();

	@Override
	public void create(List<User> toInsert) {
		this.transaction.begin();
		toInsert.forEach(this.manager::persist);
		this.transaction.commit();
	}

	@Override
	public List<User> read() {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM users u", User.class);
		return query.getResultList();
	}

	@Override
	public void update(List<User> toUpdate) {
		this.transaction.begin();
		for (User user : toUpdate) {
			User willUpdate = this.manager.find(User.class, user.getId());
			willUpdate.setAge(user.getAge());
			willUpdate.setGender(user.getGender());
			willUpdate.setOccupation_id(user.getOccupation_id());
			willUpdate.setZip_code(user.getZip_code());
		}
		this.transaction.commit();
	}

	@Override
	public void delete(List<User> toDelete) {
		this.transaction.begin();
		toDelete.stream().map(u -> this.manager.find(u.getClass(), u.getId())).forEach(this.manager::remove);
		this.transaction.commit();
	}

}
