package com.paazl.cases.test4;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final String FS = File.separator;
    private static final String location = System.getProperty("user.dir") +
                    String.join(FS, FS  + "src", "main", "resources", "com", "paazl", "cases", "test4" + FS);
    private static final File csvFile = new File(location + "testdata.csv");
    private static final File newCsvFile = new File(location + "testdata.new.csv");
    private static final CsvMapper mapper = new CsvMapper();
    private static CsvSchema schema;

	/*
	 * Read and parse "testdata.csv" (located in
	 * src/main/resources/com/paazl/cases/test4) into a list of POJOs. Increase
	 * each POJO's "number" field by 1, and move the "date" field one day ahead.
	 * Write the results to a file named "testdata.new.csv".
	 */
	public static void main(String[] args) {
		// TODO Implement this
		// TODO Write unit tests
        initCsvMapper();
        initCsvSchema(true);
        List<TestData> list = readFile(TestData.class);
        list.forEach(TestData::increment);
        initCsvSchema(false);
        writeFile(list);
	}

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder({ "name", "city", "country", "number", "date" })
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TestData{
        private String name;
        private String city;
        private String country;
        private Long number;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate date;

        public void increment(){
            number++;
            date = date.plusDays(1);
        }
    }

    private static void initCsvMapper(){
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private static void initCsvSchema(boolean isSerializationMode){
        if(isSerializationMode){
            schema = mapper
                    .schemaFor(TestData.class)
                    .withColumnSeparator(';')
                    .withSkipFirstDataRow(true);
        }else{
            schema = mapper
                    .schemaFor(TestData.class)
                    .withColumnSeparator(';')
                    .withoutQuoteChar()
                    .withHeader();
        }
    }

    private static <T> List<T> readFile(Class<T> pojoType){
        try{
            MappingIterator<T> mappingIterator = mapper
                    .readerWithTypedSchemaFor(pojoType)
                    .with(schema)
                    .readValues(csvFile);
            return mappingIterator.readAll();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private static <T> void writeFile(List<T> container){
        try{
            ObjectWriter writer = mapper
                    .writerWithTypedSchemaFor(container.get(0).getClass())
                    .with(schema);
            writer.writeValues(newCsvFile).writeAll(container);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}