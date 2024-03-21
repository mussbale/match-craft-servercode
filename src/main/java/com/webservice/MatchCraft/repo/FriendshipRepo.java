package com.webservice.MatchCraft.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservice.MatchCraft.model.Friendship;
import com.webservice.MatchCraft.model.User;

public interface FriendshipRepo extends JpaRepository<Friendship, Long> {
    boolean existsByUserAndFriend(User user, User friend);
    List<Friendship> findByUser(User user);
    List<Friendship> findByFriend(User friend);
    List<Friendship> findByFriendAndFriendshipStatus(User friend, String friendshipStatus);
}
