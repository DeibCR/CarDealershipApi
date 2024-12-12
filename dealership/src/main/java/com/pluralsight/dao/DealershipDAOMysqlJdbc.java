package dao;

import model.Dealership;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DealershipDAOMysqlJdbc implements DealershipDAO {

    private final DataSource dataSource;

    public DealershipDAOMysqlJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Dealership findDealershipById(int delearshipID){
        return null;

    }

    @Override
    public List<Dealership> findAllDealerships() {
        return null;
    }
}
