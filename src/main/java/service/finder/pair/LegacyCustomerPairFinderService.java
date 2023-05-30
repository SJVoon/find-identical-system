package service.finder.pair;

import model.LegacyCustomer;
import util.Pair;
import service.finder.FinderService;

import java.util.*;

public class LegacyCustomerPairFinderService implements FinderService<LegacyCustomer> {

    @Override
    public void findAndDisplay(List<LegacyCustomer> list) {
        if(list.isEmpty())
            throw new IllegalStateException("No Legacy Customer List To Compare");

        Map<String, List<LegacyCustomer>> propertiesMap = processIntoCompositePropertiesKeyMap(list);

        Set<Pair<LegacyCustomer>> pairedSet = processPairedLegacyCustomer(propertiesMap);

        if(Objects.nonNull(pairedSet) && !pairedSet.isEmpty())
            pairedSet.forEach(pair->
                    System.out.println("A new pair of customer found, ID for Customers that are matched : "
                            + pair.getFirst().getId() + " and " + pair.getSecond().getId()));
    }

    private Set<Pair<LegacyCustomer>> processPairedLegacyCustomer(Map<String, List<LegacyCustomer>> propertiesMap) {
        if(Objects.isNull(propertiesMap) || propertiesMap.isEmpty()) return null;
        Set<Pair<LegacyCustomer>> pairedSet = new HashSet<>();
        propertiesMap.forEach((key, valueList)->{
            if(valueList.size()>=2){
                for(int i = 0; i < valueList.size()-1; i++){
                    for(int j = (i+1); j < valueList.size(); j++){
                        pairedSet.add(new Pair<>(valueList.get(i),valueList.get(j)));
                    }
                }
            }
        });
        return pairedSet;
    }

    private Map<String, List<LegacyCustomer>> processIntoCompositePropertiesKeyMap(List<LegacyCustomer> list) {
        if(Objects.isNull(list) || list.isEmpty()) return null;
        Map<String, List<LegacyCustomer>> propertiesMap = new HashMap<>();
        list.forEach(legacyCustomer -> {
            List<String> keyList = generateKeyList(legacyCustomer.getName(),legacyCustomer.getIdTypeIdNumber(),legacyCustomer.getDob());
            if(Objects.nonNull(keyList) && !keyList.isEmpty())
                keyList.forEach(key -> propertiesMap.computeIfAbsent(key, k -> new ArrayList<>()).add(legacyCustomer));
        });
        return propertiesMap;
    }

    private List<String> generateKeyList(String... props){
        if(Objects.isNull(props) || props.length==0) return null;
        List<String> keyList = new ArrayList<>();
        for(int i = 0; i < props.length-1; i++) {
            for (int j = (i + 1); j < props.length; j++) {
                keyList.add(props[i] + "_" + props[j]);
            }
        }
        return keyList;
    }



}
