package service.finder;

import java.util.List;

public interface FinderService<T> {

    void findAndDisplay(List<T> list);
}
