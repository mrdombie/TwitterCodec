package cli.helper;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class for Apache CLI library
 */

public class CommandLineHelper {

  private Class<?> targetClass = null;
  private Options cmdLineOptions = null;
  private Set<String> requiredOptions = new HashSet<String>();
  private HashMap<String, Class<?>> typeMap = new HashMap<String, Class<?>>();
  private HashMap<String, Object> parsedArgs = new HashMap<String, Object>();
  private CommandLine parsedCmdLine = null;

  public CommandLineHelper(Class<?> thisClass) throws Exception {
    targetClass = thisClass;
    cmdLineOptions = buildOptions();
  }

  private Options buildOptions() throws Exception{
    Options opts = new Options();
    CLI copts = null;
    for(Annotation annotation : targetClass.getAnnotations()) {
      if(!(annotation instanceof CLI)) continue;
      copts = (CLI) annotation;
      break;
    }
    if(copts != null) {
      for(Option copt : copts.value()) {
        if(copt.longOpt().equals(""))
          opts.addOption(copt.opt(), copt.hasArg(), copt.description());
        else
          opts.addOption(copt.opt(), copt.longOpt(), copt.hasArg(), copt.description());
        if(copt.required()) {
          requiredOptions.add(copt.opt());
        }
        typeMap.put(copt.opt(), copt.type());
        try {
          parsedArgs.put(copt.opt(),
              parseObjectFromString(copt.defaultValueStr(), copt.type()));
        } catch (Exception e) {
          // This should never happen unless someone misused the API
          // typically happens when type is specified but not defaultValueStr 
          throw new Exception("Bad usage of CLI API: defaultValueStr for option '" +
              copt.opt() + "' does not match the type specified");
        }
      }
    }
    return opts;
  }

  public void displayHelp() {
    new HelpFormatter().printHelp(targetClass.getSimpleName(), cmdLineOptions);
    System.out.flush();
  }

  public CommandLine parse(String[] args) throws Exception {
    if(args.length == 0 && requiredOptions.size() != 0) {
      displayHelp();
      throw new Exception("Missing command line arguments");
    }
    return (parsedCmdLine =
        checkSanity(new PosixParser().parse(cmdLineOptions, args)));
  }

  public CommandLine getParsedCmdLine() {
    return parsedCmdLine;
  }


  private CommandLine checkSanity(CommandLine cmd) throws Exception {
    // Check for a help flag.
    if (cmd.hasOption("help")) {
      displayHelp();
      System.exit(-1);
    }

    // check for required option
    for(String option : requiredOptions) {
      if(!cmd.hasOption(option))
        throw new Exception("Required command line argument missing: " + option);
    }

    // iterate through all options with args and check for types
    // TODO: We're currently parsing only typed objects Fix this
    for(String option : typeMap.keySet()) {
      if(!cmdLineOptions.getOption(option).hasArg()) continue;
      Class<?> expectedType = typeMap.get(option);
      if(cmd.hasOption(option)) {
        try {
          Object argument =
              parseObjectFromString(cmd.getOptionValue(option), expectedType);
          // failure to parse will cause exception to be thrown
          // if no exceptions then we've type-checked!
          parsedArgs.put(option, argument);
        } catch (Exception e) {
          throw new Exception("Argument type violation for option '" +
              option + "'\nExpected type: " + expectedType.getSimpleName());
        }
      }
    }
    return cmd;
  }

  public String [] getUnparsedArgs() {
    if(parsedCmdLine == null) return null;
    return parsedCmdLine.getArgs();
  }

  public Object getOptionValue(String option) {
    if(!parsedArgs.containsKey(option)) return null;
    return parsedArgs.get(option);
  }

  private static <T> T parseObjectFromString(String s, Class<T> clazz) throws Exception {
    return clazz.getConstructor(new Class[] {String.class }).newInstance(s);
  }

}