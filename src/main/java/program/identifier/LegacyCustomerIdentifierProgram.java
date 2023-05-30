package program.identifier;

import model.LegacyCustomer;
import program.Program;
import service.finder.pair.LegacyCustomerPairFinderService;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class LegacyCustomerIdentifierProgram implements Program {

    /**
     *
     * @see #runProgram(String)
     * This method expects 'legacyCustomer.csv' is already uploaded to root path.
     */
    @Override
    public void runProgram() {
        runProgram("legacyCustomer.csv");
    }

    /**
     *
     * @param filename
     * The core logic is to read through the legacy customer data records in the file of provided filename
     * and print out any pair of records that has 2 or more same properties found in the pair
     */
    @Override
    public void runProgram(String filename){
        List<LegacyCustomer> list = null;

        try {
            list = readAndReturnList(filename);
        } catch(Exception e){
            System.out.println("error reading the file");
            e.printStackTrace();
        }

        if(Objects.nonNull(list) && !list.isEmpty()){
            try{
                new LegacyCustomerPairFinderService().findAndDisplay(list);
            } catch (Exception e){
                System.out.println("error processing output");
                e.printStackTrace();
            }
        }
    }

    private List<LegacyCustomer> readAndReturnList(String filename) throws IOException {
        return new ArrayList<>(Files.readAllLines(new File(filename).toPath(), StandardCharsets.UTF_8)
                .stream().filter(Objects::nonNull)
                .map(line -> line.split(",")).filter(arr -> arr.length == 5)
                .map(arr -> new LegacyCustomer(arr[0], arr[1], arr[2], arr[3], arr[4]))
                .collect(Collectors.toList()));
    }

}
