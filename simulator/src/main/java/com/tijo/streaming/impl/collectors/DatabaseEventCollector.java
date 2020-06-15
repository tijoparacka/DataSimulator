package com.tijo.streaming.impl.collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tijo.DataSimulator;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.messages.DumpStats;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseEventCollector extends AbstractEventCollector {

    private String jdbcurl;
    private String username;
    private String password;
    private String table;
    private String driver;
    private  int maxLoggingRows;
    private long startTime =System.currentTimeMillis();
    private String cols;
    private Connection con;
    private PreparedStatement st ;

    public DatabaseEventCollector() {
        super();
        try {
            ConfigUtil config = ConfigUtil.getInstance();
            jdbcurl = config.getConfig("sim.database.connectionUrl");
            username = config.getConfig("sim.database.username");
            password = config.getConfig("sim.database.password");
            table = config.getConfig(
                "sim.database.table");
            driver =config.getConfig("sim.database.driver");
            cols =config.getConfig( "sim.database.columns");

            Class.forName(driver);
            con = DriverManager.getConnection(jdbcurl, username, password);

            st =con.prepareStatement(createInsertSQL(cols,table));


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }

    private String createInsertSQL(String cols,String table)
    {
        StringBuilder  sql  = new StringBuilder();
        sql.append("insert into ").append(table).append(" ("+ cols+") values(");
        String[] col = cols.split(",");
        for (int i = 0; i < col.length; i++) {
            sql = sql.append( "?");
            if(i!= col.length-1){
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

                for (int i = 0; i < input.length ; i++) {
                    st.setString(i+1,input[i].replaceAll("\"",""));
                }

                st.execute();
            }
            catch (JsonProcessingException | SQLException e) {
                e.printStackTrace();
                System.out.println("Exception "+ e.getMessage());
            }
        }
    }
}
