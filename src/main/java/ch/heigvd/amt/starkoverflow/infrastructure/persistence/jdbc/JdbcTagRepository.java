package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.AllArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcTagRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcTagRepository extends JdbcRepository implements ITagRepository {

    public JdbcTagRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Collection<Tag> find(TagQuery query) {
        return null;
    }

    @Override
    public Tag save(Tag entity) {
        String query = String.format("INSERT INTO tags(tag_id, name, color) VALUES(?,?,?)");

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getColor());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void remove(TagId id) {
        super.remove("tags", "tag_id", id);
    }

    @Override
    public Optional<Tag> findById(TagId id) {
        Optional<IEntity> tag = super.find("tags", "tag_id", id.asString());

        return tag.map(entity -> Optional.of((Tag) entity)).orElse(null);
    }

    @Override
    public Collection<Tag> findAll() {
        return (Collection) super.findAll("tags");
    }

    @Override
    public Tag resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Tag(
                new TagId(resultSet.getString("tag_id")),
                resultSet.getString("name"),
                resultSet.getString("color")
        );
    }
}
