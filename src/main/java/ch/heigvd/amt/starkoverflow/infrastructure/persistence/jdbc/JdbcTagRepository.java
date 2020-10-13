package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.AllArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcTagRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcTagRepository extends JdbcRepository implements ITagRepository {

    @Override
    public Collection<Tag> find(TagQuery query) {
        return null;
    }

    @Override
    public Tag save(Tag entity) {
        super.insert("tags", Arrays.asList(
                "tag_id",
                "name",
                "color"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getName(),
                entity.getColor()
        ));

        return entity;
    }

    @Override
    public void remove(TagId id) {
        super.remove("tags", "tag_id", id);
    }

    @Override
    public Optional<Tag> findById(TagId id) {
        Optional<IEntity> tag = super.find("tags", "tag_id", id.asString());

        return tag.map(entity -> (Tag) entity);
    }

    public Optional<Tag> findByName(String name) {
        Optional<IEntity> tag = super.find("tags", "name", name);

        return tag.map(entity -> (Tag) entity);
    }

    @Override
    public Collection<Tag> findAll() {
        return (Collection) super.findAll("tags");
    }

    public Tag resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Tag(
                new TagId(resultSet.getString("tag_id")),
                resultSet.getString("name"),
                resultSet.getString("color")
        );
    }
}
