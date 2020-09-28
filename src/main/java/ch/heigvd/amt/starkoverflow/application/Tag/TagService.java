package ch.heigvd.amt.starkoverflow.application.Tag;

import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TagService {

    private ITagRepository tagRepository;

    public TagService(ITagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

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
