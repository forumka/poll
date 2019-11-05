package ru.test.poll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.poll.entity.Question;
import ru.test.poll.entity.Vote;
import ru.test.poll.repository.VoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote getVoteById(long voteId) {
        Optional<Vote> optionalVote = voteRepository.findById(voteId);
        if (optionalVote.isPresent())
            return optionalVote.get();
        return null;
    }

    public List<Vote> getAllVotes(){
        List<Vote> list = new ArrayList<>();
        voteRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public void addVote(Vote vote){
        Question question = vote.getQuestion();
        if (question != null)
            question.setVote(vote);
        voteRepository.save(vote);
    }

    public void updateVote(long voteId, Vote vote) {
        Vote obj = getVoteById(voteId);
        if (obj == null)
            throw new NullPointerException("Vote does not found");
        if (vote.getActive() != null)
            obj.setActive(vote.getActive());
        if (vote.getDateFrom() != null)
            obj.setDateFrom(vote.getDateFrom());
        if (vote.getDateTo() != null)
            obj.setDateTo(vote.getDateTo());
        if (vote.getName() != null)
            obj.setName(vote.getName());
        if (vote.getQuestion() != null) {
            Question question = vote.getQuestion();
            obj.setQuestion(question);
            question.setVote(vote);
        }
        voteRepository.save(obj);
    }

    public void deleteVote(long voteId) {
        Vote obj = getVoteById(voteId);
        if (obj == null)
            throw new NullPointerException("Vote does not found");
        voteRepository.delete(obj);
    }
}
