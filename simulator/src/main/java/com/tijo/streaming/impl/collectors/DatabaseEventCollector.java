package com.tijo.streaming.impl.collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.domain.generic.DBTableConfig;
import com.tijo.streaming.impl.messages.DumpStats;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseEventCollector extends AbstractEventCollector {

    private String jdbcurl;
    private String username;
    private String password;
    private DBTableConfig[] tables;
    private String driver;
    private  int maxLoggingRows;
    private long startTime =System.currentTimeMillis();

    private Connection con;
    private PreparedStatement[] statements ;

    public DatabaseEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            jdbcurl = config.getConfig("sim.database.connectionUrl");
            username = config.getConfig("sim.database.username");
            password = config.getConfig("sim.database.password");
            String tableNames = config.getConfig(
                "sim.database.tables");
            ObjectMapper mapper=new ObjectMapper();
            tables = mapper.readValue(tableNames, DBTableConfig[].class);
            driver =config.getConfig("sim.database.driver");


            Class.forName(driver);
            con = DriverManager.getConnection(jdbcurl, username, password);

            statements = new PreparedStatement[tables.length];
            for (int i = 0; i < tables.length; i++) {
               String sql = createInsertSQL(tables[i]);
               statements[i] =con.prepareStatement(sql) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    private String createInsertSQL(DBTableConfig tableConfig)
    {
        StringBuilder  sql  = new StringBuilder();
        sql.append("insert into ").append(tableConfig.getTableName()).append(" (" );
        String[] cols = tableConfig.getColName();
        sql.append (Stream.of(cols).collect(Collectors.joining(", "))) .append(") Values ( ");
        for (int i = 0; i < cols.length; i++) {
            sql = sql.append( "?");
            if(i!= cols.length-1){
                sql.append(",");
            }
        }
        sql.append(")");
        return sql.toString();
    }

    public DatabaseEventCollector(int maxEvents) {
        super(maxEvents);
    }

    @Override
    public void onReceive(Object message)  {
//        logger.debug(message);
        if (message instanceof DumpStats) {
            logger.info("Processed " + numberOfEventsProcessed + " events");
            return;
        }else if( message instanceof Event) {
            Event event = ((Event) message);
            String[] input = new String[0];
            try {
                input = writer.writeValueAsString(event).trim().split(",");
                for (int k = 0; k <tables.length ; k++) {
                    int[] indexes = tables[k].getIndexes();
                    for (int i = 0; i < indexes.length; i++) {
                        statements[k].setString(i + 1, input[indexes[i]-1].replaceAll("\"", ""));
                    }
                    statements[k].executeUpdate();
                }
            }
            catch (JsonProcessingException | SQLException e) {
                e.printStackTrace();
                System.out.println("Exception "+ e.getMessage());
                System.exit(0);
            }
        }
    }
}
