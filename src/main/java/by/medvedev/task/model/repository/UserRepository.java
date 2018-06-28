package by.medvedev.task.model.repository;

import by.medvedev.task.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);
    User findUserById(Long id);
    List<User> findUsersByOnline(boolean online);

    @Transactional
    @Modifying
    @Query("update User u set u.online = :status where u.username = :username")
    void updateOnlineStatus(@Param("status") boolean status, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("update User u set u.lastTimeSeen = :time where u.username = :username")
    void updateLastVisitedTime(@Param("time")long time, @Param("username")String username);



}
