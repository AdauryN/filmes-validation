package com.CineMeetServer.repo;

import com.CineMeetServer.entities.Friend;
import com.CineMeetServer.entities.User;
import com.CineMeetServer.enums.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    boolean existsByUserAndFriend(User user, User friend);

    List<Friend> findByFriendIdAndStatus(Long friendId, FriendStatus status);

//    List<Friend> findByFriendIdOrUserIdAndStatusIn(Long friendId, Long userId, List<FriendStatus> statuses);

    @Query("SELECT f FROM Friend f WHERE (f.user.id = :userId OR f.friend.id = :userId) AND f.status IN :statuses")
    List<Friend> findFriendsByUserIdAndStatus(@Param("userId") Long userId, @Param("statuses") List<FriendStatus> statuses);



    List<Friend> findByUserIdAndStatusOrFriendIdAndStatus(Long userId, FriendStatus status1,
                                                          Long friendId, FriendStatus status2);
}
