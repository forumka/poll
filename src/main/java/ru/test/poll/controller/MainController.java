package ru.test.poll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.poll.entity.Vote;
import ru.test.poll.service.VoteService;

import java.util.List;

@RestController
@Api(value="Poll System")
public class MainController {

    @Autowired
    private VoteService voteService;

    @ApiOperation(value = "View a list of available votes",response = List.class)
    @GetMapping(value = "/votes")
    @ResponseBody
    public Page<Vote> getVotes(@PageableDefault
    @SortDefault.SortDefaults({
            @SortDefault(sort = "name", direction = Sort.Direction.DESC),
            @SortDefault(sort = "dateFrom", direction = Sort.Direction.ASC)
    }) Pageable pageable){
        List<Vote> list = voteService.getAllVotes();
        Page<Vote> page = new PageImpl<>(list, pageable, list.size() % 10);
        return page;
    }

    @ApiOperation(value = "Search a vote with an ID",response = Vote.class)
    @GetMapping(value = "/votes/{voteId}")
    @ResponseBody
    public Vote getVote(@PathVariable("voteId") long voteId) {
        return voteService.getVoteById(voteId);
    }

    @ApiOperation(value = "Add a vote")
    @PostMapping(value = "/vote")
    @ResponseBody
    public ResponseEntity addVote(@RequestBody Vote vote) {
        voteService.addVote(vote);
        return new ResponseEntity("Vote saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a vote")
    @PutMapping(value = "/votes/{voteId}")
    @ResponseBody
    public ResponseEntity updateVote(@PathVariable long voteId, @RequestBody Vote vote) {
         voteService.updateVote(voteId, vote);
         return new ResponseEntity("Vote updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a vote")
    @DeleteMapping(value = "/votes/{voteId}")
    @ResponseBody
    public ResponseEntity deleteVote(@PathVariable("voteId") long voteId) throws NullPointerException{
        voteService.deleteVote(voteId);
        return new ResponseEntity("Vote deleted successfully", HttpStatus.OK);
    }

}
