package com.tijo.streaming.util;


import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToPojo
{
     /**  
      * @param args  
      */  
     public static void main(String[] args) {  
          String packageName="com.tijo.jsonpojo";
          File inputJson= new File("//Users/tijothomas/run/data-simulator/output/CGX0TTUC3T");
          File outputPojoDirectory=new File("." + File.separator + "convertedPojo");
          if(!outputPojoDirectory.exists())
            outputPojoDirectory.mkdirs();
          try {  
               new JsonToPojo().convertJSON2POJO(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
          } catch (IOException e) {
               // TODO Auto-generated catch block  
               System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
               e.printStackTrace();  
          }  
     }  
     public static void convertJSON2POJO(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
          JCodeModel codeModel = new JCodeModel();
          URL source = inputJson;  
          GenerationConfig config = new DefaultGenerationConfig() {  
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
            public SourceType getSourceType(){
      return SourceType.JSON;
    }  
          };  
  //        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
  //        mapper.generate(codeModel,className,packageName,source);
            SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
            mapper.generate(codeModel,className,packageName,source);

       codeModel.build(outputPojoDirectory);
     }  
}