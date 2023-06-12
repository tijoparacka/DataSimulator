package com.tijo.streaming.impl.collectors;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.AbstractEventCollector;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.impl.domain.generic.DBTableConfig;
import com.tijo.streaming.impl.messages.DumpStats;
import org.apache.commons.collections4.list.TreeList;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseLookupEventCollector extends AbstractEventCollector {

  private String username;
  private String password;
  private DBTableConfig[] tables;
  private String driver;
  private  int maxLoggingRows;
  private long startTime =System.currentTimeMillis();
  private Connection con;
  private PreparedStatement[] statements ;
  private HashMap<String, TreeList<String>> lookups  = new HashMap<String,TreeList<String>>();

  public DatabaseLookupEventCollector() {
    super();
    try {
      ConfigUtil config = ConfigUtil.getInstance();
      String jdbcurl = config.getConfig("sim.database.connectionUrl");
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
      //Statement[] selectStatement = new Statement[tables.length];
      for (DBTableConfig table : tables) {

        TreeList lookup = lookups.get(table.getTableName());
        // if there is multiple tables with the same table name create lookup only for one
        if (null == lookup) {
          lookup = new TreeList<String>();
          lookups.put(table.getTableName(), lookup);
        } else {
          continue;
        }
        // read the existing data from file
        Statement selectStatement = con.createStatement();

        //first col is assumed to be key col
       String selectKey = "select *  from " + table.getTableName();
        ResultSet rs =selectStatement.executeQuery(selectKey);
        // Load data to lookup
        while (rs.next()) {
          if (!lookup.contains(rs.getString(1)))
            lookup.add(rs.getString(1));
        }
      }
      for (int i = 0; i < tables.length; i++) {
        //create insert statement
        String sql = createInsertSQL(tables[i]);
        statements[i] =con.prepareStatement(sql) ;
      }
    } catch (Throwable e) {
      e.printStackTrace();
      logger.info(e.getMessage(), e);
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

  public DatabaseLookupEventCollector(int maxEvents) {
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
          //get the lookup for this table
          TreeList lookup = lookups.get(tables[k].getTableName());
          //check if the generated data with key  input[1] exists in the lookup
          int[] indexes = tables[k].getIndexes();
          if (!lookup.contains(input[indexes[0]-1])) {
            for (int i = 0; i < indexes.length; i++) {
              statements[k].setString(i + 1, input[indexes[i] - 1].replaceAll("\"", ""));
            }
            statements[k].executeUpdate();
            lookup.add(input[indexes[0]-1]);
          }
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
