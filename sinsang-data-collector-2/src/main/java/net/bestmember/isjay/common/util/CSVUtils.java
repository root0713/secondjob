package net.bestmember.isjay.common.util;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVUtils {
	public static void csvWriter(List<HashMap<String, Object>> listOfMap, Writer writer) throws IOException {
	    CsvSchema schema = null;
	    CsvSchema.Builder schemaBuilder = CsvSchema.builder();
	    if (listOfMap != null && !listOfMap.isEmpty()) {
	        for (String col : listOfMap.get(0).keySet()) {
	            schemaBuilder.addColumn(col);
	        }
	        schema = schemaBuilder.build().withLineSeparator(System.lineSeparator()).withHeader();
	    }
	    CsvMapper mapper = new CsvMapper();
	    mapper.writer(schema).writeValues(writer).writeAll(listOfMap);
	    writer.flush();
	}
}
