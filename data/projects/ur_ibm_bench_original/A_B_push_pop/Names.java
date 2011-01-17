/*************************************************************/
/* (C) IBM Corporation (2007), ALL RIGHTS RESERVED 	         */
/*                                                           */
/*	Benny Godlin	30/1/2007	Class created                */
/*************************************************************/
// Note: ConTest only version - not part of CMGTT

public class Names {
    public static String delim;
    public static String ext;

    public static String baseDir;

    public static void setBaseDir(String _baseDir) {
        delim = System.getProperty("file.separator");
        baseDir = _baseDir + delim;
        ext = ".jso";
    }

    public static String initProbFile() {
        return baseDir + "ProbTable_init" + ext;
    }

    public static String seriesDir(int seriesIndex) {
        return baseDir + "series"+ seriesIndex;
    }

    public static String probFile(int seriesIndex) {
        return seriesDir(seriesIndex) + delim + "ProbTable_s"+ seriesIndex + ext;
    }

    public static String runPathFile(int seriesIndex, int pathIndex) {
        return seriesDir(seriesIndex) + delim + "RunPath_s"+ seriesIndex +"_r"+ pathIndex + ext;
    }

    public static String runResultsFile(int seriesIndex) {
        return seriesDir(seriesIndex) + delim + "RunResults_s"+ seriesIndex + ext;
    }

    public static String threadName(Object codeObj, int seriesIndex, int runIndex) {
        String className = codeObj.getClass().getName();
        return "testedThread_"+ className +"_s"+ seriesIndex +"_r"+ runIndex;
    }

    public static String threadName(Object codeObj, int num, int seriesIndex, int runIndex) {
        String className = codeObj.getClass().getName();
        return "testedThread_"+ className +"_"+ num +"_s"+ seriesIndex +"_r"+ runIndex;
    }

    // static name - without the seriesIndex and runIndex
    public static String threadName(Object codeObj) {
        String className = codeObj.getClass().getName();
        return "testedThread_"+ className;
    }

    // static name - without the seriesIndex and runIndex
    public static String threadName(Object codeObj, int num) {
        String className = codeObj.getClass().getName();
        return "testedThread_"+ className +"_"+ num;
    }
}
