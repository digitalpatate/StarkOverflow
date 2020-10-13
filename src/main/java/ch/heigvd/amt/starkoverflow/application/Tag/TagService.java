package ch.heigvd.amt.starkoverflow.application.Tag;

import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Named("TagService")
@ApplicationScoped
public class TagService {

    @Inject
    @Named("JdbcTagRepository")
    private ITagRepository tagRepository;

    public void createTag(CreateTagCommand command){
        Tag tag = command.createEntity();
        tagRepository.save(tag);
    }

    public TagsDTO getTags() {
        Collection<Tag> tags = tagRepository.findAll();

        List<TagDTO> tagDTOS = tags
                .stream()
                .map(tag -> TagDTO.builder()
                        .name(tag.getName())
                        .color(tag.getColor())
                        .build())
                .collect(Collectors.toList());

        return TagsDTO.builder().tags(tagDTOS).build();
    }

    public TagsDTO getTag(TagQuery query){
        Collection<Tag> tags = tagRepository.find(query);

        List<TagDTO> tagsDTO = tags.stream().map(question -> TagDTO.builder().build()).collect(Collectors.toList());

        return TagsDTO.builder().tags(tagsDTO).build();
    }

    public Tag findOrCreateTag(CreateTagCommand createTagCommand) {
        String tagName = createTagCommand.getName();
        Optional<Tag> tagIfExists = tagRepository.findByName(tagName);

        Tag tag = null;

        if(tagIfExists.isPresent()) {
            tag = tagIfExists.get();
        } else {
            tag = createTagCommand.createEntity();
            tag = tagRepository.save(tag);
        }

        return tag;
    }
}
