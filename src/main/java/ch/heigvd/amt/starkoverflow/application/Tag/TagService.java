package ch.heigvd.amt.starkoverflow.application.Tag;

import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class TagService {

    @Inject
    private ITagRepository tagRepository;


    public void createTag(CreateTagCommand command){
        Tag tag = command.createEntity();
        tagRepository.save(tag);
    }

    public TagsDTO getTag(TagQuery query){
        Collection<Tag> tags = tagRepository.find(query);

        List<TagDTO> tagsDTO = tags.stream().map(question -> TagDTO.builder().build()).collect(Collectors.toList());

        return TagsDTO.builder().tags(tagsDTO).build();
    }
}
