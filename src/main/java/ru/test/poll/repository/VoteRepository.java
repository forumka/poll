package ru.test.poll.repository;

import org.springframework.data.repository.CrudRepository;
import ru.test.poll.entity.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
