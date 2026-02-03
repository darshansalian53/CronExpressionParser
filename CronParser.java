import java.util.*;

public class CronParser {
    
    public static void main(String[] args) {
        if(args.length!=1) {
        return; 
        }      
        String cronString=args[0];
        String[] parts=cronString.split("\\s+", 6);
            if(parts.length<6) {
                throw new IllegalArgumentException("Invalid cron format. Should contain 5(time) fields, 1 command");
            }   
        String minuteField=parts[0];
        String hourField =parts[1];
        String dayOfMonthField=parts[2];
        String monthField= parts[3];
        String dayOfWeekField=parts[4];
        String command=parts[5];
            
        System.out.println(printOutput("minute",fieldCheck(minuteField,0,59)));
        System.out.println(printOutput("hour",fieldCheck(hourField,0,23)));
        System.out.println(printOutput("day of month",fieldCheck(dayOfMonthField,1,31)));
        System.out.println(printOutput("month",fieldCheck(monthField,1,12)));
        System.out.println(printOutput("day of week",fieldCheck(dayOfWeekField,1,7)));
        System.out.println(printOutput("command",List.of(command)));
    }
    private static List<Integer> fieldCheck(String field, int min, int max) {
        List<Integer> res = new ArrayList<>();
        
        // if field has * then every tim of that field will schedule
        if(field.equals("*")) {
            for(int i= min; i<=max;i++) {
                res.add(i);
            }
            return res;
        } 
        // if the field has */15 then for every (eg:min)time with 15 intervals of time should be scheduled
        if(field.startsWith("*/")) {
            int jump= Integer.parseInt(field.substring(2));
            for(int i=min; i<=max; i+=jump) {
                res.add(i);
            }
            return res;
        }  
        // values separted with commas, only those values will be used in the field
        if(field.contains(",")) {
            String[] parts = field.split(",");
            for(String part : parts) {
                res.addAll(fieldCheck(part,min,max));
            }
            // if there are any duplicates remove by adding to tree set, it will remove duplicates 
            // and sort automatically
            res =new ArrayList<>(new TreeSet<>(res));
            return res;
        }
        // if field contains - then time between those range will be scheduled
        if(field.contains("-")) {
            String[] range =field.split("-");
            int start=Integer.parseInt(range[0]);
            int end=Integer.parseInt(range[1]);
 
            if(start<min || end>max || start>end) {
                throw new IllegalArgumentException("Invalid range in field:"+field);
        }
        for(int i=start; i<=end;i++) {
                res.add(i);
            }
            return res;
        }
        // if the field contains single value
        int value=Integer.parseInt(field);
        if (value<min || value>max) {
            throw new IllegalArgumentException("Value out of range in field:"+field);
            }
        res.add(value);
        return res;
        }
    
    private static String printOutput(String fieldName,List<?> values) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("%-14s", fieldName));
        
        for(Object value : values) {
            sb.append(value).append(" ");
        }
        
        if(sb.length()>0) {
            sb.setLength(sb.length()-1);
        }
        
        return sb.toString();
    }
}
