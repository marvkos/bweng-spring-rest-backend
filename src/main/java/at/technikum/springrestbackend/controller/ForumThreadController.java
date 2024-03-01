package at.technikum.springrestbackend.controller;


import at.technikum.springrestbackend.dto.ForumPostDTO;
import at.technikum.springrestbackend.mapper.ForumPostMapper;
import at.technikum.springrestbackend.model.ForumPostModel;
import at.technikum.springrestbackend.repository.ForumPostRepository;
import at.technikum.springrestbackend.services.ForumPostServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

//FORUM THREAD, NOT POST! NAMING IS BAD!
@RestController
@RequestMapping("/forumposts")
@CrossOrigin
public class ForumThreadController {

    private final ForumPostMapper postMapper;
    private final ForumPostServices postServices;
    private final ForumPostRepository postRepository;

    @Autowired
    public ForumThreadController(
            ForumPostMapper postMapper,
            ForumPostServices postServices,
            ForumPostRepository postRepository) {
        this.postMapper = postMapper;
        this.postServices = postServices;
        this.postRepository = postRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ForumPostDTO> readAll() {
        return postServices.findAll().stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @GetMapping("/{eventid}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ForumPostDTO> readAllofEvent(@PathVariable String eventid){
//        return postServices.findAllByEvent(eventid).stream()
//                .map(postMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ForumPostDTO read(@PathVariable String id) {
        ForumPostModel forumPost = postServices.find(id);
        return postMapper.toDTO(forumPost);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ForumPostDTO create(@RequestBody @Valid ForumPostDTO forumPostDTO){
        ForumPostModel forumPost = postMapper.toEntity(forumPostDTO);
        postServices.save(forumPost);
        return postMapper.toDTO(forumPost);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ForumPostDTO update(@PathVariable String id, @RequestBody ForumPostDTO updatedForumPostDTO){

        //for update logic CTRL+LMB on 'update' - method call
        return postMapper.toDTO(postServices.update(id, updatedForumPostDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete(@PathVariable String id){
//        postServices.find(id);
        postRepository.deleteById(id);
        System.out.println("Forum Thread with id: " + id + " deleted successfully!");

    }
}