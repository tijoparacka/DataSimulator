package com.tijo.streaming.util;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimJavaCompiler
{
  public static void compile(String srcPath) throws URISyntaxException
  {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
    StandardJavaFileManager fm = compiler.getStandardFileManager(diagnostics, null, null);

    ArrayList<String> optionList = new ArrayList<String>();
    optionList.addAll(Arrays.asList("-classpath",System.getProperty("java.class.path")));
    ArrayList<File> files = new ArrayList<File>();
    files.add(new File(srcPath));
    JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, optionList, null, fm.getJavaFileObjectsFromFiles(files));
    task.call();
  }
}
