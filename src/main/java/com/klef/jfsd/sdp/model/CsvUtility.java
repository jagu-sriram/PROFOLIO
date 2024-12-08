//package com.klef.jfsd.sdp.model;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.springframework.web.multipart.MultipartFile;
//
//public class CsvUtility {
//    public static String TYPE = "text/csv";
//      static String[] HEADERs = {"First Name","Last Name","Gender","Date of birth","Age","Department", "Email","Password","Contact" };
//
//		/*
//		 * static String[] HEADERs2 =
//		 * {"First Name","Last Name","Gender","Date of birth","Qualification",
//		 * "Department","Designation", "Email","Password","Contact" };
//		 */
//      public static boolean hasCsvFormat(MultipartFile file) {
//        if (!TYPE.equals(file.getContentType())) {
//          return false;
//        }
//        return true;
//      }
//      public static List<Student> csvToStuList(InputStream is) {
//            try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                CSVParser csvParser = new CSVParser(bReader,
//                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//              List<Student> stuList = new ArrayList<Student>();
//              Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//              for (CSVRecord csvRecord : csvRecords) {
//                Student stu = new Student();
//                
//                stu.setFirstname(csvRecord.get("First Name"));
//                stu.setLastname(csvRecord.get("Last Name"));
//                stu.setGender(csvRecord.get("Gender"));
//                stu.setDateofBirth(csvRecord.get("Date of birth"));
//                stu.setAge(Float.parseFloat(csvRecord.get("Age")));
//                stu.setDepartment(csvRecord.get("Department"));
//                stu.setEmail(csvRecord.get("Email"));
//                stu.setPassword(csvRecord.get("Date of birth"));
//                stu.setContact(csvRecord.get("Contact"));
//   
//                
//               stuList.add(stu);
//              }
//              return stuList;
//            } catch (IOException e) {
//              throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
//            }
//          }
//      
//        }