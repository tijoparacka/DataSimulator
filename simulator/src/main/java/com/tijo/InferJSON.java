package com.tijo;

import com.tijo.config.ConfigUtil;
import com.tijo.streaming.impl.domain.Event;
import com.tijo.streaming.util.JsonToPojo;
import com.tijo.streaming.util.SimJavaCompiler;
import org.apache.commons.io.FileUtils;
import org.apache.kafka.common.protocol.types.Field;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InferJSON
{
  public static void main(String[] args) throws Exception
  {
    if (args != null && args.length != 2) {
      System.out.println(" Please provide a valid config file .");
      System.exit(0);
    }
    String baseDir = args[0];
    ConfigUtil conf = new ConfigUtil(args[1]);
    String packageName = conf.getConfig("sim.generic.infer.packageName");
    String jsonPath = baseDir+File.separator+conf.getConfig("sim.generic.infer.jsonPath");
    String genCode = conf.getConfig("sim.generic.infer.generateCode");
    String pojoOutput = baseDir+File.separator+conf.getConfig("sim.generic.infer.pojoOutputDir");

    System.out.println("Base dir"+ baseDir);
    System.out.println("Location of the sample json file:"+jsonPath);
    System.out.println("Whether to generate the code:"+genCode);

    if(pojoOutput == null)
      pojoOutput = "pojoOutput";

    // if the file is java , keep the file in pojoOutput dir and set the flag genCode to false
    // this will just manipulate the java file for this simulator and create the class file.
    if(!jsonPath.endsWith(".json") &&  !jsonPath.endsWith(".java") ){
      throw  new Exception("sim.generic.infer.jsonPath should ends with .json or .java");
    }
    File jsonFile = new File(jsonPath);
    String className = jsonFile.getName().replace(".json","");
    File pojoOutputDir = new File(pojoOutput);
    pojoOutputDir.mkdirs();

   // File outputPojoDirectory=new File(pojoOutputDir.getAbsolutePath() + File.separator + jsonFile.getName());
    String javaFilePath = pojoOutputDir+File.separator+packageName.replace(".",File.separator)+File.separator+jsonFile.getName().replace(".json",".java");
    boolean gen = true;
    try {
      gen = Boolean.parseBoolean(genCode);
    }catch (Exception e){
      gen = true;
    }
    if( gen ) {
      JsonToPojo.convertJSON2POJO(jsonFile.toURI().toURL(), pojoOutputDir, packageName,
                                  jsonFile.getName().substring(0, jsonFile.getName().indexOf("."))
      );
    }
    injectEventCode( javaFilePath);

    SimJavaCompiler.compile(javaFilePath);

   // File classFolder = new File (pojoOutputDir+File.separator+packageName.substring(0,packageName.indexOf(".")));
    FileUtils.copyDirectory(pojoOutputDir,new File(baseDir+"/lib/"));
  }

  private static void injectEventCode(String javaFilePath) throws IOException
  {
    File javaFile = new File(javaFilePath);
    StringBuilder sb = new StringBuilder();
    List<String> contents = FileUtils.readLines(javaFile, "UTF-8");

    // Iterate the result to print each line of the file.
    for (String line : contents) {
      if (line.trim().startsWith("package")) {
        sb.append(line).append("\n");
        sb.append("import com.tijo.streaming.impl.domain.generic.GenericEvent;").append("\n");
        continue;
      }
      if (line.trim().startsWith("public")){
        Pattern pattern = Pattern.compile(".*public\\sclass\\s(.*)\\s\\{");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
          System.out.println("Found matching for the class definition");
          String className =matcher.group(1) ;
          sb.append("public class ").append(className).append(" extends GenericEvent {").append("\n");
          continue;
        }
      }
      sb.append(line).append("\n") ;
    }
    FileUtils.write(javaFile,sb.toString(),"UTF-8",false);

  }
}
