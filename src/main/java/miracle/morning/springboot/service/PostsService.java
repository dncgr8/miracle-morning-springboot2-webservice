package miracle.morning.springboot.service;

import lombok.RequiredArgsConstructor;
import miracle.morning.springboot.domain.posts.Posts;
import miracle.morning.springboot.domain.posts.PostsRepository;
import miracle.morning.springboot.web.dto.PostsResponseDto;
import miracle.morning.springboot.web.dto.PostsSaveRequestDto;
import miracle.morning.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById (id).orElseThrow (() -> new
                IllegalArgumentException ("해당 사용자가 없습니다. id ="+ id));

        posts.update (requestDto.getTitle (),requestDto.getContent (),requestDto.getAuthor ());
        return id;

    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById (id).orElseThrow (()-> new
                IllegalArgumentException ("해당 사용자가 없습니다"+ id));
                return new PostsResponseDto (entity);
    }

}
