package com.klef.jfsd.sdp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CsvUtilityForMentor {
    public static String TYPE = "text/csv";
      static String[] HEADERs = {"Id","First Name","Last Name","Gender","Date of birth","Qualification",
    			  "Department","Designation", "Email","Password","Contact" };

		
      public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
          return false;
        }
        return true;
      }
      public static List<Mentor> csvToMentorList(InputStream is) {
          try (BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
              CSVParser csvParser = new CSVParser(bReader,
                  CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Mentor> mentorlist = new ArrayList<Mentor>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
              Mentor mentor = new Mentor();
              mentor.setId(Integer.parseInt(csvRecord.get("Id")));
              mentor.setFirstname(csvRecord.get("First Name"));
              mentor.setLastname(csvRecord.get("Last Name"));
              mentor.setGender(csvRecord.get("Gender"));
              mentor.setDateofBirth(csvRecord.get("Date of birth"));
              mentor.setQualification(csvRecord.get("Qualification"));
              mentor.setDepartment(csvRecord.get("Department"));
              mentor.setDesignation(csvRecord.get("Designation"));
              mentor.setExperience(Float.parseFloat(csvRecord.get("Experience")));
              mentor.setEmail(csvRecord.get("Email"));
              mentor.setPassword(csvRecord.get("Date of birth"));
              mentor.setContact(csvRecord.get("Contact"));
 
              
             mentorlist.add(mentor);
            }
            return mentorlist;
          } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
          }
        }
      
        }