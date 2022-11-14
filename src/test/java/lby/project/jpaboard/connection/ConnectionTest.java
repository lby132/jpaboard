package lby.project.jpaboard.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void connectTest() throws SQLException {
        final Connection con = dataSource.getConnection();

        assertThat(con).isNotNull();
    }
}
